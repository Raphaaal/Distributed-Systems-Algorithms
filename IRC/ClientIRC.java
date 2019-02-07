import java.util.Scanner;

import java.io.*;
import java.net.*;
public class ClientIRC extends Thread{
static int port=4501;
static InetAddress hote= null ;
Socket sc;
BufferedReader in; 
PrintWriter out;
String nom;
int compteur=0;
OutputStream outputstream = sc.getOutputStream();
String rep;

String req;
			//envoyer lepseudonymeau serveur
	public ClientIRC(String nom,InetAddress hote, int  port){
		this.nom=nom;
		this.hote=hote;
		this.port=port;
		out =new PrintWriter(outputstream, true);
		in =new BufferedReader( new InputStreamReader(sc.getInputStream()));
		rep=in.readLine();
	}

	public void run(){
		try{
			sc=new Socket(hote,port);
			
		
			out.println(nom);

			System.out.println(rep);

			while(true){
				//recevoir un message du serveur

				rep=in.readLine();
				System.out.println(rep);
				//if(!rep.equals("A toi...")){
					//	rep=in.readLine();
					//	System.out.println(rep);
				//}
				//incrementer le nb d'echanges
				compteur+=1;
				//repondre au serveur
				//if(

			
			}
			rep=in.readLine();
			System.out.println(rep);
			currentThread().sleep(2000);
			out.println("bye");
			

		}catch(IOException  | InterruptedException e){
			System.err.println("Impossible creesocket du client : " +e);
		}
		finally{
			try{
				sc.close();in.close();out.close();
			}catch (IOException e){}
		}
			while(rep.equals("A toi...")){

					Scanner sc =new Scanner(System.in);
					String msg =sc.nextLine();
					if(msg.equals("STOP"))
							break;
					req= msg;
					out.println(req);
					
					//faire une pause de 3sec
					currentThread().sleep(1000);
					
			}	
	}
	public static void main (String[] args){
	
	try{
	
		if (args.length>=3){
			ClientIRC client=new ClientIRC(args[0],InetAddress.getByName(args[1]),Integer.parseInt(args[2]));
			client.start();
		}
	
	}catch(Exception e){
		System.err.println("Machine inconnue :" +e);
	}
		
	}
}

