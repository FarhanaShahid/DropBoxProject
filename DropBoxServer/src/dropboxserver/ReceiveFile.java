package dropboxserver;

import java.net.*;
import java.io.*;
import java.nio.ByteBuffer;

public class ReceiveFile extends Thread{
	
	Socket socket;
	
	public static long bytesToLong(byte[] bytes) {
		ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
	    buffer.put(bytes);
	    buffer.flip();//need flip 
	    return buffer.getLong();
    }
	
	public ReceiveFile(Socket s){
		socket=s;
		start();
	}
	
	public void run(){
		
		try {
			
			synchronized(this){
				BufferedReader br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
				String fileName=null;
				
				while((fileName=br.readLine())!=null){
					break;
				}
				
				System.out.println(fileName);
				File receivedFile=new File(fileName);
				
				InputStream is=socket.getInputStream();
				FileOutputStream fos=new FileOutputStream(receivedFile,true);
				BufferedOutputStream bos=new BufferedOutputStream(fos);

				byte[] mybytearray=new byte[1000000];

				byte [] tempbuffer=new byte[Long.BYTES];
	            is.read(tempbuffer,0,tempbuffer.length);
	            long fileSize=bytesToLong(tempbuffer);
	            System.out.println(fileSize);
            
             //READ
                int bytesRead=0;
                int totalbytes=0;         
                while(totalbytes<fileSize)
                {
                  
                    bytesRead = is.read(mybytearray,0,mybytearray.length);
                    //System.out.println(receivedFile.getName()+" "+bytesRead);
                    totalbytes+=bytesRead;
                    //System.out.println("Total bytesRead "+totalbytes);
                    if(bytesRead>0)bos.write(mybytearray,0, bytesRead);                   
                    bos.flush();
                 
             
                }
                
                System.out.println(receivedFile.getName()+" Done!");
                
                is.close();
                fos.close();
                bos.close();
                socket.close();
			}
			
			
		} catch (IOException e) {
			
			System.out.println(e.getMessage());
			System.exit(1);
		}
	}

}
