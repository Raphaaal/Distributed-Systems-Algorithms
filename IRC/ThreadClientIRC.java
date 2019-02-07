import java.util.*;
import java.net.*;
import java.io.*;

public class ThreadClientIRC extends Thread{
	
	ServeurIRC serv;
	Socket ssv;
	String nom;
	BufferedReader in;
	PrintWriter out;
	
	public ThreadClientIRC(Socket ssv, ServeurIRC serv){
		this.ssv = ssv;
		this.serv = serv;
	}
	
	public void run(){
		try{
			in = new BufferedReader(new InputStreamReader(ssv.getInputStream()));
			out = new PrintWriter(ssv.getOutputStream(), true);
			String req = in.readLine();
			setNom(req);
			
			//envoi d'un premier message d'accueil
			out.println("##### BIENVENUE #####");
			out.println("Salam " + req + " !");
			//envoi la liste des clients connectes
			out.println("Voici les clients actuellement connectés : ");
			serv.envoyerListeClients(out);
			out.println("#####################");
			// Signal pour signifier au client qu'il peut parler
			out.println("A toi...");
			
			while(true){
				//attendre une phrase de reponse 
				req = in.readLine();
				
				if (req.equals("Bye")){ 
					//supprimer le client 
					serv.supprimerClient(this);
					break;
				}
				
				//repondre au client
				out.println("Message reçu, en transmission à tous les clients...");
				
				//envoyer le message à tous les clients
				serv.envoyerATous(this.getNom() + " : " + req);
			}
		
			System.out.println("Fin Communication");
		
		} catch (IOException e){
			System.err.println("Erreur : " +e);
		}
		
		finally{
			try{
				ssv.close(); 
				in.close(); 
				out.close();
			}
			catch (IOException e){}
		}
	}
	
	public void envoyer(String s){
		out.println(s);
		out.println("A toi...");
	}
	
	public void setNom(String s){
		this.nom = s;
	}
	
	public String getNom(){
		return nom;
	}
}
