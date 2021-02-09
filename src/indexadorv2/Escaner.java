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
import java.io.IOException;
import java.util.ArrayList;
import org.hibernate.Session;

/**
 *
 * @author Fran
 */
public class Escaner {

    File f;
    
    Escaner(String url){
        this.f = new File(url);
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
                System.out.println(f2.getAbsolutePath());
                //Si es un directorio le añadimos recursividad
                if (f2.isDirectory()) {
                    ls(f2);

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
                String artista = mp3Tag.getArtist();
                cancion.setId(new CancionesId(titulo, artista));
                cancion.setAlbum(mp3Tag.getAlbum());
                cancion.setAno(mp3Tag.getYear());
                cancion.setGenero(mp3Tag.getGenreDescription());
                cancion.setDuracion((short) mp3.getLengthInSeconds());
                cancion.setCaratula(mp3Tag.getAlbumImage());
                cancion.setRuta(f.getAbsolutePath());
                cancion.setNombreFichero(f.getName());

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
                
                }else{
                    System.out.println("Se ha detectado una canción repetida");
                    
                }
            } finally {
                HibernateUtil.closeSessionAndUnbindFromThread();
                
            }         
            HibernateUtil.closeSessionFactory();
            
    }

    /**
     * Indexa en la base de datos todos los archivos mp3 encontrados
     * @param f
     */
    public void indexador() {
        ArrayList listaCanciones = ls(this.f);
        for (int i = 0; i < listaCanciones.size(); i++) {
            File f2 = (File) listaCanciones.get(i);
            subeCancion(creaCancion(f2));
        }
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
                System.out.println(c.getId());
            } else {
                resultado = true;
            }

        return resultado;
    }
    
    
    public void creaLog(){
        
    }
}
