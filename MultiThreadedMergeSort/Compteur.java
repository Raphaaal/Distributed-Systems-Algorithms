import java.util.Random;
import java.util.List;
import java.util.ArrayList;

public class Compteur extends Thread {

	int nbThreads;
	List<Thread> l = new ArrayList<>();

	public Compteur(int nbThreads) {
		this.nbThreads = nbThreads;	
	}
	
	public void createThreads() {
		Random r = new Random();
		for (int i = 0; i < nbThreads; ++i) {
			Thread t = new Thread() {
				public void run() {
					for(int k = 1; k <= 10; ++k) {
						System.out.println(this.getName() + " compte : " + k);
						try {
							sleep(r.nextInt(5000));
						} catch (InterruptedException e) {
						};	
					}
					System.out.println(this.getName() + " a fini de compter jusque 10.");			
				}
			};
			t.setName("Thread " + i);
			l.add(t);
			t.start();
		}	
	}
	
	public static void main(String[] args) throws InterruptedException {
		Compteur c = new Compteur(Integer.parseInt(args[0]));
		c.createThreads();
		for (Thread t : c.l)
			t.join();
		System.out.println("--- FIN ---");
	}

}
