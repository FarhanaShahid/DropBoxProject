package dropboxserver;

import java.net.*;
import java.io.*;

public class ReceiveFileUtil implements Runnable{
	
	Thread t;
	ServerSocket ss;
	
	public ReceiveFileUtil(){
		t=new Thread(this);
		t.start();
	}
	
	public void run(){
		try{
			ss=new ServerSocket(1490);
			while(true){
				
				Socket s=ss.accept();
				System.out.println("Connected Receive File");
				new ReceiveFile(s);
			}
			
		}catch(IOException e){
			
			System.out.println(e.getMessage());
            System.exit(1);
		}
	}

}
