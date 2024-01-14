import java.io.*;
import java.net.*;

public class Cliente {
    private Socket socket = null;
    private ObjectOutputStream oos = null;
    private boolean estaConectado = false;

    private ObjectInputStream ois = null;

    public static void main(String[] args) {
        Cliente cliente = new Cliente();
        cliente.comunicar();
    }

    public Cliente() {
    }

    public void comunicar() {
        while (!estaConectado) {
            try {
                // CONECTA
                socket = new Socket("localhost", 4445);
                System.out.println("Conectado");
                estaConectado = true;

                // FLUJO DE SALIDA
                oos = new ObjectOutputStream(socket.getOutputStream());

                // CREAMOS OBJETO
                Persona persona = new Persona("Pepe", 25);
                System.out.println("Objeto a enviar: " + persona.getNombre() + " de " + persona.getEdad()
                        + " anyos. Viajado=" + persona.getViajado());

                /*
                 * LIMPIO EL BUFFER
                 * Cuando trabajas con ObjectOutputStream, los datos no se escriben
                 * inmediatamente en el flujo
                 * de salida cada vez que llamas a métodos como writeObject(). En su lugar, los
                 * datos se almacenan
                 * en un búfer interno para mejorar el rendimiento. La llamada a flush() fuerza
                 * la escritura de
                 * esos datos almacenados en el búfer al flujo subyacente.
                 */
                oos.flush();

                // ESCRIBO EL OBJETO EN EL FLUJO DE SALIDA
                oos.writeObject(persona);
                System.out.println("Enviado objeto persona");

                // FLUJO DE ENTRADA PARA OBJETOS PERSONA
                ois = new ObjectInputStream(socket.getInputStream());
                System.out.println("Recibido objeto");

                // ALMACENA OBJETO PERSONA
                Persona persona2 = (Persona) ois.readObject();
                System.out.println("Almacenado objeto persona2 de tipo Persona");
                System.out.println(persona2.getNombre() + " de " + persona2.getEdad()
                        + " anyos. Viajado=" + persona2.getViajado());

                // CIERRO STREAMS Y SOCKET
                oos.close();
                socket.close();

            } catch (SocketException se) {
                System.out.println("SocketException error en comunicar(): " + se);
            } catch (IOException e) {
                System.out.println("IOException error en comunicar():" + e);
            } catch (ClassNotFoundException cn) {
                System.out.println("ClassNotFoundException error en comunicar():" + cn);
            }
        }
    }
}