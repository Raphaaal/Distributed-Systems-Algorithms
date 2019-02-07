/*
Le  serveur  doit  :
•Attendre  des  connexions
•Créer  un  nouveau  thread  pour  chaque  nouveau  client
•Créer  et  maintenir  une  liste  des  clients  connectés 
*/
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.*;
import java.net.*;
import java.io.*;

public class ServeurIRC{

	int port= 4500;
	ServerSocket se;
	Socket ssv;
	int cl = 0;
	Vector<ThreadClientIRC> V;
	
	public ServeurIRC(){
		try{
			V = new Vector<>();
			se = new ServerSocket(port);
			System.out.println("Serveur à l'écoute sur le port 4500");
			while(true){
				ssv = se.accept();
				cl++;
				ThreadClientIRC th = new ThreadClientIRC(ssv, this);
				ajouterClient(th);
				th.start();
			}
		}
		catch (IOException e){
			System.err.println("Erreur : " +e);
		}
		finally{
			try{
				se.close();
			}
			catch (IOException e){}
			}
		}
		
	public void ajouterClient(ThreadClientIRC c){
		V.add(c);
	}
	
	synchronized public void supprimerClient(ThreadClientIRC c){
		V.remove(c);
		cl--;
	}
	
	synchronized public void envoyerATous(String s){
		Timestamp time = new Timestamp(System.currentTimeMillis());
		for(ThreadClientIRC t : V) {
			t.envoyer(time + " - " + s);
		}
	}
	
	synchronized public void envoyerListeClients(PrintWriter out){
		for(ThreadClientIRC t : V) {
			out.println(" - " + t.getNom());
		}
	}
	
	public static void main(String[] args){
		new ServeurIRC();
	}
}
