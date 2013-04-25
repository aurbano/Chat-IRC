package es.uniovi;

import java.io.*;
import java.nio.*;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * Clase de Entrada de red, recibe los mensajes y los va guardando
 * en un buffer de entrada.
 */
public class EntradaRed extends Thread {
	
	private ArrayBlockingQueue<Respuesta> inQueue;
	
	/**
	 * Constructor de la clase, inicializa el buffer y lanza el hilo.
	 */
	public EntradaRed(){
		inQueue = new ArrayBlockingQueue<Respuesta>(20);
		this.start();
	}
	
	public void run(){
		String message;
		int status, code;
		Respuesta res;
		try{
			//BufferedReader in = new BufferedReader(new InputStreamReader(ClienteChat.s.getInputStream()));
			//LineNumberReader in = new LineNumberReader( new InputStreamReader(ClienteChat.s.getInputStream()));
			//ObjectInputStream in = new ObjectInputStream(ClienteChat.s.getInputStream());
			DataInputStream in = new DataInputStream(ClienteChat.s.getInputStream());
			while(true){
				try{
					// Lee los 2 primeros bytes
					status = in.readByte();
					code = in.readByte();
				
					short length = in.readShort();
					byte[] content = new byte[length];
					// Lee la respuesta
					in.readFully(content);
					message = new String(content,"UTF-8");
					
					// Guardamos el mensaje recibido en la cola
					res = new Respuesta(code,status,message);
					add(res);
					/*
					System.out.println("Recibido:");
					System.out.println("	Status= "+status);
					System.out.println("	Code= "+code);
					System.out.println("	Tama�o="+length);
					System.out.println("	Mensaje: "+new String(content,"UTF-8"));
					System.out.println("-----------");*/
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * A�ade un elemento al buffer de entrada, puede lanzar una
	 * excepcion.
	 * @param ans Elemento Respuesta para a�adir
	 * @throws InterruptedException si el hilo es bloqueado mientras espera por un mensaje.
	 */
	public void add(Respuesta ans) throws InterruptedException{
		inQueue.put(ans);
	}
	
	/**
	 * Extrae el ultimo elemento del buffer, puede lanzar una excepcion
	 * @return Respuesta Elemento de tipo Respuesta
	 * @throws InterruptedException si el hilo es bloqueado mientras espera por un mensaje.
	 */
	public Respuesta remove() throws InterruptedException{
		return inQueue.take();
	}
}
