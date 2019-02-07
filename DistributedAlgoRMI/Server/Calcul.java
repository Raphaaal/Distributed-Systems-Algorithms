import java.rmi.*;    
import java.rmi.server.*; 
   
public class Calcul extends java.rmi.server.UnicastRemoteObject implements CalculInterface {

	public int somme (int a, int b) throws java.rmi.RemoteException {
		return a+b;
	}

	public Calcul() throws RemoteException{}
}    
