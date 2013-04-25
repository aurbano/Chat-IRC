package es.uniovi;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.HashMap;

/**
 * Contiene toda la informaci�n de un comando IRC
 */

public class Comando {
	String[] params;
	
	/**
	 * Constructor para la clase Comando
	 * toma el texto introducido por el usuario como par�metro
	 * @param Comando introducido por el usuario
	 */
	public Comando(String text){
		// Genera el string de parametros:
		if (text.startsWith("/")){
			this.params = text.split(" ");
		}else{
			// /MSG requiere algunas cosas extra
			this.params = new String[]{"/MSG", text, ClienteChat.sala};
		}
	}
	
	/**
	 * Devuelve el comando como String en el formato adecuado
	 * @return Comando en string
	 * @throws UnsupportedEncodingException 
	 */
	public byte[] get() throws UnsupportedEncodingException{
		HashMap<String,Short> tabla = new HashMap<String,Short>();
		tabla.put("/MSG", (short)1);
		tabla.put("/JOIN", (short)2);
		tabla.put("/LEAVE", (short)3);
		tabla.put("/NICK", (short)4);
		tabla.put("/QUIT", (short)5);
		tabla.put("/LIST", (short)10);
		tabla.put("/WHO", (short)11);
		
		// La "carga" para calcular el tama�o
		String content = params[1];
		if(params[0]=="/MSG") content += params[2];
		
		Short size = new Short((short)(content.getBytes("UTF-8").length + params.length*2));
		
		ByteBuffer command = ByteBuffer.allocate(4+size);
	
		command.putShort(tabla.get(params[0]));
		command.putShort(size);
		if(size>0){
			command.putShort((short)(params.length-1));
			if(params[0]=="/MSG"){
				command.putShort((short)params[2].getBytes("UTF-8").length);
				command.put(params[2].getBytes("UTF-8"));
				command.putShort((short)params[1].getBytes("UTF-8").length);
				command.put(params[1].getBytes("UTF-8"));
			}else{
				command.putShort((short)params[1].getBytes("UTF-8").length);
				command.put(params[1].getBytes("UTF-8"));
			}
		}
		return command.array();		
	}
	
	 public static String asHex(byte buf[])
     {
             StringBuffer strbuf = new StringBuffer(buf.length * 2);

             for(int i=0; i< buf.length; i++)
             {
                     if(((int) buf[i] & 0xff) < 0x10)
                             strbuf.append("0");
                     strbuf.append(Long.toString((int) buf[i] & 0xff, 16));
             }
             return strbuf.toString();
     }
}
