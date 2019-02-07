import java.util.Random;

public class TriParallele2 extends Thread{

	private int[] t;
	int debut; 
	int fin;
	TriParallele2 pere;
	int notifyCount;

	private TriParallele(TriParallele2 t, int[] t, int debut, int fin){
			this.t=t;
			this.debut=debut;
			this.fin=fin;
			this.pere = t;
			this.notifyCount = 0;
	}

	private void permuter (int a, int b){
		int temp=t[a];
		t[a]=t[b];
		t[b]=temp;
	}

	public void run(){
		if ((fin-debut)<2){
			if (t[debut]>t[fin]){
				permuter(debut,fin);
			}
		}
		else{
			int milieu=debut + (fin-debut)/2;
			Thread t1 = new TriParallele (this, t, debut,milieu); 
			Thread t2 = new TriParallele (this, t, milieu+1,fin); 
			try{
				t1.start();
				t2.start();
				this.wait();
			} catch (InterruptedException e) {}                  
			trifusion(debut,fin);

		}
		if (this.pere != null) {
			notifyCount++;
			if(notifyCount >= 2)
				this.notify();	
		}
	}

	private void trifusion(int debut, int fin){
		int[] tfusion = new int[fin-debut+1];
		int milieu = (debut + fin)/2;
		int i1=debut;
		int i2=milieu+1;
		int ifusion=0;

		while (i1<=milieu && i2<=fin){
			if (t[i1]<t[i2]){
				tfusion[ifusion++]=t[i1++];         
			}
			else{
				tfusion[ifusion++]=t[i2++];
			}
		}
      
		if (i1>milieu){
			for (int i=i2; i<=fin; i++){
				tfusion[ifusion++]=t[i];
			}
		}
		else{
			for (int i=i1; i<=milieu; i++){
				tfusion[ifusion++]=t[i];
			}
		} 
              
		for (int i=0, j=debut; i<=fin-debut; i++, j++){
			t[j]= tfusion[i];
		}   
	}

	public static void main(String[] args) throws InterruptedException{
		int[] tableau = new int[10000];
		Random r = new Random();
		for (int i =0; i<10000; ++i) {
			int k = r.nextInt(10000);
			tableau[i] = k;
		}
		TriParallele t = new TriParallele(null, tableau, 0, tableau.length - 1);
		t.start();
		t.join(); // obligatoire
		for(int i=0; i<tableau.length; i++){
			System.out.println(tableau[i] + " ; ");     
		}
		System.out.println();
	}
}
