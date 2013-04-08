/**
 *  Cliente de consola de chat
 *
 */

package es.uniovi;

public class ClienteChat {
	static String nick;
	
	public static void main(String[] args){
		System.out.println("ClienteChat v1.0");
		
		
		if(args.length<1){
			System.err.println("Error: No has especificado tu nombre de usuario, p�salo por par�metro.");
			System.exit(-1);
		}
		
		nick = args[0];
		System.out.println("Bienvenido/a "+ nick);
				
	}

}
