package es.uniovi;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 *  Cliente de consola de chat, para el hito 1
 *  Lanza los hilos necesarios para el funcionamiento.
 */
public class ClienteChat {
	/**
	 * Variable para el nombre de usuario
	 */
	static String nick;
	/**
	 * Nombre de la sala actual
	 */
	static String sala = "pruebas";
	/**
	 * IP del servidor
	 */
	static String host = "localhost";
	static int port = 69;
	/*
	 * Lanzamos algunos hilos como est�ticos para poder acceder
	 * a ellos desde los dem�s.
	 */
	static SalidaRed netOut = new SalidaRed();
	static EntradaRed netIn = new EntradaRed();
	static Network net = new Network();
	static Socket s;
	
	/**
	 * M�todo principal del Cliente, muestra por pantalla algo de informaci�n
	 * y lanza los hilos de entrada por teclado y salida por pantalla.
	 * @param args Es necesario especificar el nick por parametro.
	 */
	public static void main(String[] args){
		System.out.println("ClienteChat v1.0");
		
		
		if(args.length<1){
			System.err.println("Error: No has especificado tu nombre de usuario, p�salo por par�metro.");
			System.exit(-1);
		}
		
		try{
			System.out.println("Conectando...");
			s = new Socket(host, port);
			System.out.println("Conectado");
			nick = args[0];
			System.out.println("Bienvenido/a "+ nick);
			
			// Lanzamos los hilos
			new HiloEntrada();
			new HiloSalida();
			
		}catch(IOException e){
			e.printStackTrace();
		}
	}

}
