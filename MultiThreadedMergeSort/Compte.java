public class Compte {

	private Object monitor = new Object();

	private int solde=0;    

	public void ajouter(int somme){
		synchronized(monitor) {
			solde=solde+somme;
			System.out.println(" ajout de " +somme);
		}   
	} 

	public void retirer (int somme){
		synchronized(monitor) {
			solde=solde-somme;
			System.out.println(" retrait de " +somme);
		}
	}    

	public void operationNulle (int somme){
		synchronized(monitor) {
				solde=solde+somme;
				System.out.print(" ajout de " +somme +",");
				solde=solde-somme;
				System.out.print("et retrait de " +somme +".");
				System.out.println();
		}
	}

	public int getSolde(){
		synchronized(monitor) {
			return solde;
		}
	}
}
