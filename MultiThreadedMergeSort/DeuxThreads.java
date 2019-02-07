public class DeuxThreads extends Thread {
	public void run(){
		for (int i=0; i<10; i++){
			System.out.println("nouveau thread " + this.getName());
		}
	}

	// L'ordre d'affichage ne peut pas être prévu (pseudo-parallèle)
	public static void main (String[] args) throws InterruptedException{
		DeuxThreads th = new DeuxThreads();    
		th.setName("Fils");
		th.start();
		Thread.currentThread().setName("Pere");
		for (int i=0; i<10; i++){
			System.out.println("Main thread " + Thread.currentThread().getName());
			System.out.flush();
			// Pour laisser la main à un Thread enfant : Thread.currentThread().yield();
			sleep(1);
		}
	}
}
