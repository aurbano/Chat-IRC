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
	static String nick = "Anonimo";
	/**
	 * Nombre de la sala actual
	 */
	static String sala = "";
	/**
	 * IP del servidor
	 */
	static String host;
	static int port;
	/**
	 * Finaliza la sesion
	 */
	static boolean quit = false;
	/*
	 * Lanzamos algunos hilos como est�ticos para poder acceder
	 * a ellos desde los dem�s.
	 */
	static SalidaRed netOut;
	static EntradaRed netIn;
	//static Network net = new Network();
	static Socket s;
	
	/**
	 * M�todo principal del Cliente, muestra por pantalla algo de informaci�n
	 * y lanza los hilos de entrada por teclado y salida por pantalla.
	 * @param args Es necesario especificar IP y puerto del servidor.
	 */
	public static void main(String[] args){
		System.out.println("ClienteChat v2.0");
		
		try{
			host = args[0];
			port = new Integer(args[1]);
		}catch(Exception e){
			System.err.println("Error: Debes especificar IP del servidor y puerto");
			System.exit(-1);
		}
		
		try{
			s = new Socket(host, port);
			
			// Lanzamos los hilos
			netOut = new SalidaRed();
			netIn = new EntradaRed();
			new HiloEntrada();
			new HiloSalida();
			
		}catch(IOException e){
			System.err.println("No se puede conectar con el servidor");
		}
	}

}
