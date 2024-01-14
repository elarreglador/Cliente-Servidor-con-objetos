import java.io.Serializable;

public class Persona implements Serializable{
    private String nombre;
    private int edad;
    private boolean viajado;

    // serialVersionUID evita problemas al serializar en diferentes equipos al definir una 
    // version especifica.
    private static final long serialVersionUID = 1L;

    public Persona(String nombre, int edad){
        super();
        this.nombre = nombre;
        this.edad = edad;
        this.viajado = false;
    }    

    public String getNombre(){
        return nombre;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    
    public int getEdad(){
        return edad;
    }

    public void setEdad(int edad){
        this.edad = edad;
    }

    public boolean getViajado(){
        return viajado;
    }
    
    public void setViajado(boolean viajado){
        this.viajado = viajado;
    }
}