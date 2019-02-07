import java.rmi.*;    
import java.rmi.server.*; 
   
public class Compteur extends java.rmi.server.UnicastRemoteObject implements CompteurInterface {

	public int nbOccurrences (String c, String mot) throws java.rmi.RemoteException{
		int l=mot.length();
		int nb=0;
		for (int i=0;i<l;i++){
			if ((mot.substring(i,i+1)).equals(c)) 
				nb++;
		}
		return nb;
	}
	
	public Compteur() throws RemoteException{}
}    
