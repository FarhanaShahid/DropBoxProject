package dropboxserver;

import java.io.*;
import java.net.*;

public class SignUpUtil implements Runnable{
    Thread t;
    ServerSocket ss;

    public SignUpUtil(){
        t=new Thread(this);
        t.start();
    }

    @SuppressWarnings("unused")
	public void run(){
            try{
                ss=new ServerSocket(1601);
                while(true){
                    Socket socket=ss.accept();
                    System.out.println("Connected SignUp");

                    SignUp ob=new SignUp(socket);
                }

            } catch(IOException e){
                
                System.out.println(e.getMessage());
                System.exit(1);
            }
    }
}
