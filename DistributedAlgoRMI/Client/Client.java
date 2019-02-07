import java.rmi.*; 
   
public class Client {    
	public static void main (String[] args) {  

		try {    
			System.setSecurityManager(new RMISecurityManager());  

			// trouver une référence vers l’objet distant        
			CalculInterface calcul = (CalculInterface) Naming.lookup("rmi://192.168.154.152:2001/Calcul");
			// appel de méthode à distance        
			System.out.println("Résultat  1 + 2: " + calcul.somme(1,2));    

			// trouver une référence vers l’objet distant        
			CompteurInterface compteur = (CompteurInterface) Naming.lookup("rmi://192.168.154.152:2001/Compteur");
			// appel de méthode à distance        
			System.out.println("Occurrences 'e' dans 'Voyages': " + compteur.nbOccurrences("e","Voyages"));  
	 

		} catch (Exception e){    
			System.out.println ("Erreur client : " + e);
		}    
	}    
}    
