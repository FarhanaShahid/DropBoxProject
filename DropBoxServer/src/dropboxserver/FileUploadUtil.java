package dropboxserver;

import java.io.*;
import java.net.*;

public class FileUploadUtil implements Runnable{
	
	Thread t;
	ServerSocket ss;
	
	public FileUploadUtil(){
		t=new Thread(this);
		t.start();
	}
	
	public void run(){
		try{
            ss=new ServerSocket(1890); 
            while(true){
                Socket socket=ss.accept();
                System.out.println("Connected FileUploadUtil");

                @SuppressWarnings("unused")
				FileExistCheck ob=new FileExistCheck(socket);
            }

        } catch(IOException e){
            //e.printStackTrace();
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }
	

}