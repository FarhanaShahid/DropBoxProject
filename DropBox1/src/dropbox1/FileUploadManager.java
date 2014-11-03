/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dropbox1;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Farhana Shahid
 */

import java.io.*;

public class FileUploadManager implements Runnable{
    
    Thread t;
    String userName;
    
    FileUploadManager(){
        t=new Thread(this);
        t.start();
    }
    @Override
    public void run() {
        File dataFile=new File("C:\\Dropbox\\data.txt");
        try {
            FileReader fr=new FileReader(dataFile);
            char[] c=new char[50];
            fr.read(c);
            String temp=new String(c);
            userName=temp.trim();
            
            File mainFile=new File("C:\\Dropbox");
            String[] list=mainFile.list();
            
            for(String x:list){
                
                new FileUploadUtil(x,userName);
            }
            
            
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        } catch(IOException e){
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }
    
}
