import java.rmi.*;  
  
public interface CompteurInterface extends Remote {
	public int nbOccurrences (String c, String mot) throws java.rmi.RemoteException;
}
    
