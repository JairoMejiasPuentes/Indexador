/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package indexadorv2;

/**
 *
 * @author Fran
 */
import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

/**
 *
 * @author Fran
 */
public class Escaner {

    private File f;
    private File archivoLog;
    
        
    Escaner(String url){
        this.f = new File(url);
        creaLog();
    }
    
    /**
     * Busca todos los archivos mp3 contenido en un directorio y subdirectorios
     *
     * @param f Directorio en el que buscar
     * @return ArrayList que contiene todos los archivos de tipo mp3
     */
    private ArrayList ls(File f) {
        ArrayList listaMp3 = new ArrayList();
        if (f.exists()) {
            //Almacenamos en un array todos los elementos que contiene el archivo f
            String[] lista = f.list();

            for (int i = 0; i < lista.length; i++) {
                //Creamos un nuevo archivo
                File f2 = new File(f.getAbsolutePath() + "\\" + lista[i]);
                //Si es un directorio le añadimos recursividad
                if (f2.isDirectory()) {
                    ArrayList listaRecursiva = ls(f2);
                    //Si lo que nos devuelve no está vacio, lo incluye a nuestra lista actual de canciones
                    if(listaRecursiva.size() != 0){
                        for (int j = 0; j < listaRecursiva.size(); j++) {
                            listaMp3.add(listaRecursiva.get(j));
                        }
                    }
                } else {
                    //Si no es un directorio, comprobaremos si su extensión es mp3
                    if (lista[i].endsWith(".mp3")) {
                        listaMp3.add(f2);

                    }
                }
            }
        } else {
            System.out.println("Error: No existe el fichero o directorio");

        } 
        return listaMp3;
    }

    /**
     * Crea un objeto de tipo Canciones, obteniendo todos los datos y metadatos
     * de un archivo
     *
     * @param f
     * @return objeto canciones creado
     */
    private Canciones creaCancion(File f) {
        Canciones cancion = new Canciones();

        try {
            Mp3File mp3 = new Mp3File(f);

            if (mp3.hasId3v2Tag()) {
                ID3v2 mp3Tag = mp3.getId3v2Tag();
                String titulo = mp3Tag.getTitle();
                if(titulo == null) titulo = "Titulo desconocido";
                String artista = mp3Tag.getArtist();
                if(artista == null) artista = "Artista desconocido"; 
                cancion.setId(new CancionesId(titulo, artista));
                cancion.setAlbum(mp3Tag.getAlbum());
                cancion.setAno(mp3Tag.getYear());
                cancion.setGenero(mp3Tag.getGenreDescription());
                cancion.setDuracion((short) mp3.getLengthInSeconds());
                cancion.setCaratula(mp3Tag.getAlbumImage());
                cancion.setRuta(f.getAbsolutePath());
                cancion.setNombreFichero(f.getName());

            }else{
                String mensaje = String.format("Ha surgido un problema al "
                        + "recuperar los datos de %s", f.getAbsolutePath());
                escribeLog(mensaje);
                cancion = null;
            }
        } catch (IOException | UnsupportedTagException | InvalidDataException ex) {
            System.out.println("Error al obtener el archivo mp3" + ex.toString());
        }
        return cancion;

    }

    /**
     * Sube a la base de datos una canción
     *
     * @param cancion archivo mp3 que se va a subir a la base de datos
     */
    private void subeCancion(Canciones cancion) {
            HibernateUtil.buildSessionFactory();
            
            try {
                HibernateUtil.openSessionAndBindToThread();
                Session session
                        = HibernateUtil.getSessionFactory().getCurrentSession();
                
                // Comprobamos que si la canción que se va a subir se encuentra en la base de datos
                if(compruebaCancion(cancion.getId(),session)){
                session.beginTransaction();
                session.save(cancion);
                session.getTransaction().commit();
                
                String mensaje = String.format("Se ha subido la canción %s - %s a la base de datos",
                        cancion.getId().getTitulo(), cancion.getId().getArtista());
                escribeLog(mensaje);
                
                }
            } finally {
                HibernateUtil.closeSessionAndUnbindFromThread();
                
            }         

            
    }

    /**
     * Indexa en la base de datos todos los archivos mp3 encontrados
     * @param f
     */
    public void indexador() {
        ArrayList listaCanciones = ls(this.f);
        for (int i = 0; i < listaCanciones.size(); i++) {
            File f2 = (File) listaCanciones.get(i);
            if(creaCancion(f2) == null){
                listaCanciones.remove(i);
                i--;
            }else{
                subeCancion(creaCancion(f2));
            }
        }
        HibernateUtil.closeSessionFactory();
    }

    /**
     * Comprueba si la canción que se desea subir a la base de datos está repetida
     * @param id
     * @return false -> Está repetida || true -> No está en la bd
     */
    private boolean compruebaCancion(CancionesId id, Session session) {
        boolean resultado = false;

            Canciones c = (Canciones) session.get(Canciones.class, id);
            if (c != null) {
                String mensaje = String.format("La canción %s - %s ya se encuentra en la base de datos",
                        c.getId().getTitulo(), c.getId().getArtista());
                escribeLog(mensaje);
            } else {
                resultado = true;
            }

        return resultado;
    } 
    
    /**
     * Recupera todas las canciones de la base de datos
     */
    public ArrayList recuperaCanciones(){
        ArrayList listaCanciones;
        
        HibernateUtil.buildSessionFactory();
            
            try {
                HibernateUtil.openSessionAndBindToThread();
                Session session
                        = HibernateUtil.getSessionFactory().getCurrentSession();
                
                //Creamos la sentencia para recuperar todo el contenido de la bd
                String sql = "SELECT * FROM canciones";
                SQLQuery query = session.createSQLQuery(sql);
                
                //Le indicamos que la clase a recuperar es Canciones
                query.addEntity(Canciones.class);
                listaCanciones =(ArrayList) query.list();
                
                System.out.println(listaCanciones);
                

            } finally {
                HibernateUtil.closeSessionAndUnbindFromThread();
                
            }         
            HibernateUtil.closeSessionFactory();
            return listaCanciones;
            
    }
    
    /**
     * Crea un archivo Log, donde se incluiran los movimientos del indexador
     */
    private void creaLog(){
            //Inicializamos el fichero log
            archivoLog = new File("log.txt");
            
            //Registramos el mensaje de inicio
            String mensaje = "Se ha creado un nuevo Escaner";
            escribeLog( mensaje);
         
    }
    
    /**
     * Escribe en el archivo Log.txt, la acción que ha realizado, la fecha y hora.
     * @param mensaje Acción que ha realizado nuestro Escaner
     */
    private void escribeLog(String mensaje){
            
            Date fecha = new Date();
            SimpleDateFormat formateaFecha = new SimpleDateFormat("hh: mm: ss a dd-MMM-yyyy"); 

            //Añadimos la fecha y un salto de línea al mensaje
            mensaje += String.format(" --> %s \n", formateaFecha.format(fecha));
            
            FileWriter fic = null;
           
        try {
            //Creamos flujo de salida
            fic = new FileWriter(archivoLog, true);
            fic.write(mensaje);
        } catch (IOException ex) {
            Logger.getLogger(Escaner.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fic.close();
            } catch (IOException ex) {
                Logger.getLogger(Escaner.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
