/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dropbox1;

import java.io.*;
import java.net.*;


/**
 *
 * @author Farhana Shahid
 */
public class FileUploadUtil implements Runnable{

    Thread t;
    String filename=null;
    String userName=null;
    
    FileUploadUtil(String name,String user){
        
        filename=name;
        userName=user;
        t=new Thread(this);
        t.start();
    }
    
    @Override
    public void run() {
        
        try{
            Socket socket=new Socket("localhost",1890);
            BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            BufferedReader br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
            
            String input="G:\\"+userName+"\\"+filename;
            String temp=null;
            File file=new File("C:\\Dropbox\\"+filename);
            if(file.isFile()) temp="file";
            else if(file.isDirectory()) temp="dir";
            
            while(true){
                bw.write(input,0,input.length());
                bw.write(" ");
                bw.write(temp,0,temp.length());
                bw.newLine();
                bw.flush();
                break;
            }
            
            
            String msg=null;
            while((msg=br.readLine())!=null){
                break;
            }
           
            if(msg.equals("no")){
                //System.out.println(input+" "+msg+" "+temp);
                if(temp.equals("file")){
                    new SendFile(file,userName);
                }
                else if(temp.equals("dir")){
                    new FolderHandle(file,userName,"");
                }
            }
            
        } catch(UnknownHostException e){
            System.out.println(e.getMessage());
            System.exit(1);
        }
        catch(IOException e){
            e.printStackTrace();
            System.out.println(e.getMessage());
            System.exit(1);
        }
        
    }
    
}
