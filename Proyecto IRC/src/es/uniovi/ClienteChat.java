package es.uniovi;

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
	/*
	 * Lanzamos algunos hilos como est�ticos para poder acceder
	 * a ellos desde los dem�s.
	 */
	static SalidaRed netOut = new SalidaRed();
	static EntradaRed netIn = new EntradaRed();
	static Network net = new Network();
	
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
		
		nick = args[0];
		System.out.println("Bienvenido/a "+ nick);
		
		// Lanzamos los hilos
		HiloEntrada in = new HiloEntrada();
		HiloSalida out = new HiloSalida();
	}

}
