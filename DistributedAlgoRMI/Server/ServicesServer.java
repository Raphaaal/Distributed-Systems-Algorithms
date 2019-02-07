import java.rmi.*;    
public class ServicesServer {    
	public static void main (String[] args) {    
		try {    
			//crée une instance de la classe Calcul et Compteur et les enregistre dans le serveur de noms    
			Naming.rebind("rmi://192.168.154.152:2001/Calcul", new Calcul ());  
			Naming.rebind("rmi://192.168.154.152:2001/Compteur", new Compteur ());    
	  
			System.out.println ("Serveur prêt !");    
		} catch (Exception e){    
			System.out.println ("Erreur serveur : " + e);
		}    
	}    
}    
