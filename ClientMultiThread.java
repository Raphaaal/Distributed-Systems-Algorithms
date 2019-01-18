import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ClientMultiThread extends Thread{

	int port = 4500;
	InetAddress hote =null;
	Socket sc=null;
	BufferedReader in;
	PrintWriter out;
	int compteur=0;

	public ClientMultiThread(InetAddress hote, int port) {
		this.hote = hote;
		this.port = port;
	}

	
	
	public void run() {

		try{
			sc = new Socket(hote, port);
			in = new BufferedReader(new InputStreamReader(sc.getInputStream()));

 		   for (int i=0;i<10;++i){	
		        //System.out.println(in.readLine());
			compteur+=1;
			out = new PrintWriter(sc.getOutputStream(), true);
			out.println("Je suis le client " + InetAddress.getLocalHost() +  " j'ai fait " + compteur + " appel(s)" + " Thread : " + currentThread().getName()) ;
			currentThread().sleep(2000);
			String rep = in.readLine();
			System.out.println(rep);
		
		  }
			
			out.println("bye");
			
		} catch(IOException | InterruptedException e){
			System.err.println("Impossible de creer la socket du client : " +e);
		}

		finally{
			try{
				sc.close();
			} catch (IOException e){}
		}
	}

	public static void main (String[] args){
		ClientMultiThread th;

		
			try{
				if (args.length>=2){
					for(int i =0; i<5; i++){
						th = new ClientMultiThread(InetAddress.getByName(args[0]),Integer.parseInt(args[1])); 
						th.setName(""+ i);
						th.start();
					}
				}
				
			} catch(UnknownHostException e){
				System.err.println("Machine inconnue :" +e);
			}
		
	}
}
