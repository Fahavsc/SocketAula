import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class Cliente {
	private int porta = 4000;
	private String servidor = "localhost";
	private String nome = "Cliente : ";
	
	
	public Cliente(){
		try {
			Socket srv = new Socket(servidor, porta);
			InputStream in = srv.getInputStream();
			InputStreamReader isr = new InputStreamReader(System.in);
			BufferedReader br = new BufferedReader(isr);
			boolean sair = true;
			while(sair) {
				if(br.ready()) {
					String linha = nome;
					linha = linha + br.readLine();
					srv.getOutputStream().write(linha.getBytes());
					srv.getOutputStream().write("\n".getBytes());
					srv.getOutputStream().flush();
				}
				
				if(in.available()>0) {
					int i = in.read();
					System.out.print((char)i);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[]args) {new Cliente();}
}
