import java.io.*;
import java.net.*;

public class Servidor {
    private ServerSocket ss = null;
    private Socket socket = null;
    private ObjectInputStream ois = null;

    private ObjectOutputStream oos = null;

    public static void main(String[] args){
        Servidor servidor = new Servidor();
        servidor.comunicar();
    }

    public void comunicar(){
        try {
            // ss EN ESPERA DE CONEXION ENTRANTE
            ss = new ServerSocket(4445);
            System.out.println("Servidor en espera de conexion entrante...");
            socket = ss.accept();
            System.out.println("Recibida conexion entrante");

            //FLUJO DE ENTRADA PARA OBJETOS PERSONA
            ois = new ObjectInputStream(socket.getInputStream());
            System.out.println("Recibido objeto");

            //ALMACENA OBJETO PERSONA
            Persona persona = (Persona) ois.readObject();
            System.out.println("Almacenado objeto persona de tipo Persona");

            //MODIFICA OBJETO PERSONA
            persona.setViajado(true);
            System.out.println(persona.getNombre() + " de " + persona.getEdad()
                        + " anyos. Viajado=" + persona.getViajado());

            //FLUJO DE SALIDA
            oos = new ObjectOutputStream(socket.getOutputStream());
            oos.flush();

            //ESCRIBO EL OBJETO MODIFICADO EN EL FLUJO DE SALIDA
            oos.writeObject(persona);
            System.out.println("Enviado objeto persona");

            //CIERRO STREAMS Y SOCKET
            oos.close();
            ois.close();
            socket.close();

        } catch (SocketException so){
            System.out.println("SocketException error en comunicar():"+so);
        } catch (IOException e){
            System.out.println("IOException error en comunicar():"+e);
        } catch (ClassNotFoundException cn){
            System.out.println("ClassNotFoundException error en comunicar():"+cn);
        }
    }
}
