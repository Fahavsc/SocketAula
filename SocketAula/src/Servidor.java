import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
	public Servidor(){
		try {
			ServerSocket srv = new ServerSocket(4000);
			Socket cliente = srv.accept(); 
			System.out.println("Cliente conectado");
			OutputStream out = cliente.getOutputStream();
			out.write("Bem vindo viadão".getBytes());
			out.flush();
			boolean sair = true;
			InputStream in = cliente.getInputStream();
			InputStreamReader ir = new InputStreamReader(in);
			BufferedReader br = new BufferedReader(ir);
			
			InputStreamReader kbIr = new InputStreamReader(System.in);
			BufferedReader kbdBr = new BufferedReader(kbIr);
			
			String linha = "";
			String linhaTeclado = "";
			
			while(!"sair".equalsIgnoreCase(linha)) {
//				if(in.available()>0) {
//					int i = in.read();
//					System.out.println((char)i);
//				}
				if (kbdBr.ready()) {
					linhaTeclado = kbdBr.readLine();
					out.write(linhaTeclado.getBytes());
					out.write("\n\r".getBytes());
					out.flush();
				}
				if (br.ready()) {
					linha = br.readLine();
					System.out.println(linha);
				}
			}
			srv.close();
			cliente.close();
		} catch (IOException e) {
	
			e.printStackTrace();
		}
	}
	
	public static void main(String[]args) {
		
		new Servidor();
	}
}
