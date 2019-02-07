    import java.io.*;
    import java.net.*;

    public class ServeurMultiThread extends Thread {

        private int port = 4500;
      
        PrintWriter out;
        BufferedReader in;
        Socket ssv;

        public ServeurMultiThread(Socket ssv){
            this.ssv = ssv;  
        }


        public void run(){
        
            try{
                    in = new BufferedReader( new InputStreamReader(ssv.getInputStream()));
                    OutputStream outputstream = ssv.getOutputStream();
                    out = new PrintWriter(outputstream, true);
                    String str = in.readLine();
                    System.out.println("Reçu : " + str);
                    out.println(str + " : Bien reçu côté serveur");
              
            } catch (IOException e){
                System.err.println("Erreur : " +e);
            }
            finally{
                try{
                    ssv.close();
                }catch (IOException e){}
            }
        }
      
        public static void main(String[] args) throws Exception{

            ServerSocket se = new ServerSocket(4500);
            Socket ssv=null;
          
            while(true) {
                    ssv = se.accept();
                    ServeurMultiThread th = new ServeurMultiThread(ssv);
                    th.start();
            }
        }

    }
