import java.rmi.*;  
  
public interface CalculInterface extends Remote {
	public int somme (int a, int b) throws java.rmi.RemoteException;
}
    
