/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dropbox1;

import java.io.*;
/**
 *
 * @author Farhana Shahid
 */
public class FolderHandle {
    
    File directory;
    String userName=null, parentDir=null;
    public FolderHandle(File folder,String user,String parent){
        directory=folder; userName=user;
        parentDir=parent;
        
        String[] list=directory.list();
        for(String x:list){
            String name=parentDir+"\\"+x;
            new FileUploadUtil(name,userName);
        }
    }
}
