import java.lang.*;
import java.io.*;
import java.net.*;

class Server {
//creation de la connexion et reception des donnees
    static String dossier = "C:/receivedData/";//receivedData";
    static int port= 5792;

    public static void main(String args[]) {

        System.out.println("Server en attente de Client...\n\n");


        while (true) {

            try {

                ServerSocket serversoc = new ServerSocket(port);
                Socket soket = serversoc.accept();
                BufferedInputStream in = new BufferedInputStream( soket.getInputStream() );
                char j=0;
                while((j =(char)in.read()) != '}') {
                  dossier=dossier+j;
                }

                FileOutputStream fos = new FileOutputStream(dossier);
                BufferedOutputStream out = new BufferedOutputStream(fos);



                int i;
                while ((i = in.read()) != -1) {
                    out.write(i);
                    //System.out.println(i);
                    System.out.println("reception des donnees...");
                }
                dossier = "C:/receivedData/";
                out.flush();
                in.close();
                out.close();
                soket.close();
                serversoc.close();
                System.out.println("Transfert accompli");
            }
            catch(Exception e) {

                System.out.print("Error! It didn't work! " + e + "\n");
                dossier = "C:/receivedData/";
            }


            try {
                Thread.sleep(1000);
            } catch (InterruptedException ie) {
                System.err.println("Interruption");
            }

        }
    }
}