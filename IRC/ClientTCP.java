import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ClientTCP{

	public static void main (String[] args){
		int port = 4500;
		InetAddress hote =null;
		Socket sc=null;
		BufferedReader in;
		PrintWriter out;

		try{
			if (args.length>=2){
				hote= InetAddress.getByName(args[0]); 
				port= Integer.parseInt(args[1]); 
			} else{
				hote = InetAddress.getLocalHost();
			}
		} catch(UnknownHostException e){
			System.err.println("Machine inconnue :" +e);
		}

		try{
			sc = new Socket(hote, port);
			in = new BufferedReader(new InputStreamReader(sc.getInputStream()));
			out = new PrintWriter(sc.getOutputStream(), true);

			Scanner scan = new Scanner(System.in);
			String str = scan.nextLine();
			out.println(str);
			String rep = in.readLine();
			System.out.println(rep);
			
		} catch(IOException e){
			System.err.println("Impossible de creer la socket du client : " +e);
		}

		finally{
			try{
				sc.close();
			} catch (IOException e){}
		}
	}
}
