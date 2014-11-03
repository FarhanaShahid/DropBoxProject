/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dropbox1;

import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;
/**
 *
 * @author Farhana Shahid
 */
public class SendFile implements Runnable{
    
    Thread t;
    File sentFile;
    String userName=null;
    
    SendFile(File file,String user){
        sentFile=file;
        userName=user;
        System.out.println(sentFile.getName()+" entered sendFile");
        t=new Thread(this);
        t.start();
    }
    
    public static byte[] longToBytes(long x) {
        ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
        buffer.putLong(x);
        return buffer.array();
    }
    
    @Override
    public void run(){
        try{
            Socket socket=new Socket("localhost",1490);
            synchronized(this){
                
                BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                
                String filename=sentFile.toString();
                String temp=null;
                
                temp=filename.replaceFirst("C","G");
                temp=temp.replaceFirst("Dropbox", userName);
                
                while(true){
                    bw.write(temp,0,temp.length());
                    bw.newLine();
                    bw.flush();
                    break;
                }
                
                
                FileInputStream fis=new FileInputStream(sentFile);
                BufferedInputStream bis=new BufferedInputStream(fis);
                OutputStream os=socket.getOutputStream();

                
                long fileSize=sentFile.length();
                byte[] tempbuffer=longToBytes(fileSize);
                os.write(tempbuffer,0,tempbuffer.length);
                os.flush();

                byte[] mybytearray = new byte[(int)socket.getSendBufferSize()];
                int bytesRead=0;
                int totalbytes=0;
                while(totalbytes!=fileSize)
                {
                
                    bytesRead=bis.read(mybytearray, 0, mybytearray.length);
                    totalbytes+=bytesRead;
                    System.out.println(sentFile.getName()+" "+totalbytes);                     
                    os.write(mybytearray, 0, bytesRead);
                    os.flush();
                  
                }
                fis.close();
                bis.close();
                os.close();
                socket.close();
                
            }
        } catch(UnknownHostException e){
            System.out.println(e.getMessage());
            System.exit(1);
            
        }catch(IOException e){
            e.printStackTrace();
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }
}
