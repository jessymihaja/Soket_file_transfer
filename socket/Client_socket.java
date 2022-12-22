import java.io.*;
import java.awt.*;
import java.net.*;
public class Client_socket {
    //envoie des donnees

    static int port= 5792;
    static String localhost = "localhost";

    public static boolean send( String filepath,String filename ) {

        try {
            System.out.print("envoi des donnees...\n");
            Socket soket = new Socket(localhost, port);


            FileInputStream fis = new FileInputStream(filepath);
            BufferedInputStream in = new BufferedInputStream(fis);
            BufferedOutputStream out = new BufferedOutputStream( soket.getOutputStream() );
            filename=filename+"}";
            byte[] name=filename.getBytes();
            for(int i=0;i<name.length;i++)
                 out.write(name[i]);

            int i;
            while ((i = in.read()) != -1) {
                out.write(i);

            }


            out.flush();
            out.close();
            in.close();
            soket.close();

            return true;
        }
        catch( Exception e ) {


            System.out.print("erreur " + e + "\n");

            return false;
        }
    }
}