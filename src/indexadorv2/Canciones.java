package indexadorv2;
// Generated 09-feb-2021 15:52:52 by Hibernate Tools 4.3.1



/**
 * Canciones generated by hbm2java
 */
public class Canciones  implements java.io.Serializable {


     private CancionesId id;
     private String album;
     private String ano;
     private String genero;
     private Short duracion;
     private byte[] caratula;
     private String ruta;
     private String nombreFichero;

    public Canciones() {
    }

	
    public Canciones(CancionesId id, String ruta, String nombreFichero) {
        this.id = id;
        this.ruta = ruta;
        this.nombreFichero = nombreFichero;
    }
    public Canciones(CancionesId id, String album, String ano, String genero, Short duracion, byte[] caratula, String ruta, String nombreFichero) {
       this.id = id;
       this.album = album;
       this.ano = ano;
       this.genero = genero;
       this.duracion = duracion;
       this.caratula = caratula;
       this.ruta = ruta;
       this.nombreFichero = nombreFichero;
    }
   
    public CancionesId getId() {
        return this.id;
    }
    
    public void setId(CancionesId id) {
        this.id = id;
    }
    public String getAlbum() {
        return this.album;
    }
    
    public void setAlbum(String album) {
        this.album = album;
    }
    public String getAno() {
        return this.ano;
    }
    
    public void setAno(String ano) {
        this.ano = ano;
    }
    public String getGenero() {
        return this.genero;
    }
    
    public void setGenero(String genero) {
        this.genero = genero;
    }
    public Short getDuracion() {
        return this.duracion;
    }
    
    public void setDuracion(Short duracion) {
        this.duracion = duracion;
    }
    public byte[] getCaratula() {
        return this.caratula;
    }
    
    public void setCaratula(byte[] caratula) {
        this.caratula = caratula;
    }
    public String getRuta() {
        return this.ruta;
    }
    
    public void setRuta(String ruta) {
        this.ruta = ruta;
    }
    public String getNombreFichero() {
        return this.nombreFichero;
    }
    
    public void setNombreFichero(String nombreFichero) {
        this.nombreFichero = nombreFichero;
    }




}


