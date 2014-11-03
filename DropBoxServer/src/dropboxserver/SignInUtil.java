package dropboxserver;

import java.io.*;
import java.net.*;


public class SignInUtil implements Runnable{
    Thread t;
    ServerSocket ss;

    public SignInUtil(){
        t=new Thread(this);
        t.start();
    }

    @SuppressWarnings("unused")
	public void run(){
        try{
            ss=new ServerSocket(2553); 
            while(true){
                Socket socket=ss.accept();
                System.out.println("Connected SignIn");

                SignIn ob=new SignIn(socket);
            }

        } catch(IOException e){
            //e.printStackTrace();
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }
}

