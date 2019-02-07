public class Operation extends Thread {
	
	private Compte compte;   

	public static Object monitor = new Object(); 

	public Operation(String nom, Compte compte){
		super(nom);
		this.compte=compte;    
	}
    
	public void run(){
		while(true){
			int i= (int) (Math.random() * 10);
			String nom=this.getName();
			System.out.print(nom); 
			// this.compte.operationNulle(i); 
			synchronized(monitor) {
				this.compte.ajouter(i);
				this.compte.retirer(i);
				int montant=this.compte.getSolde(); 
				if (montant !=0){
					System.out.println(nom +"solde =" +montant);
					System.exit(1);
				} 
			}
		}
	}

	public static void main(String[] args){
		Compte c = new Compte();
		// Pb : on effectue des opérations concurrents sur le même Compte... -> Besoin de synchronize
		for(int i=1; i<=2; i++){
			Operation op= new Operation("op"+i,c);
			op.start();
		}
	}
}
