import java.io.*;
import java.net.*;

public class ServeurTCP {

	public static void main(String[] args){
		int port=4500;
		ServerSocket se;
		Socket ssv=null;
		PrintWriter out;
		BufferedReader in;
		try{
			se = new ServerSocket(port);
			ssv = se.accept();


			in = new BufferedReader( new InputStreamReader(ssv.getInputStream()));
			OutputStream outputstream = ssv.getOutputStream();
			out = new PrintWriter(outputstream, true); 

		while(true) {
			String str = in.readLine();
			if(!str.equals("")) {
				System.out.println("Reçu : " + str);
				out.println();
				out.println(str + " : Bien reçu côté serveur");
			}
			else 
				break;
		}

		} catch (IOException e){
			System.err.println("Erreur : " +e);
		}
		finally{
			try{
				ssv.close();
			}catch (IOException e){}
		}
	}
}
