/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dropbox1;

/**
 *
 * @author Farhana Shahid
 */
import java.io.*;

import javax.swing.JOptionPane;


public class MakeDirectory {
	
    public MakeDirectory(String name) throws IOException{
        File dir=new File("C:\\Dropbox");

        if(dir.exists())
            JOptionPane.showMessageDialog(null, "Dropbox already exists!");
        else{

            if(dir.mkdir()){

                File newfile=new File(dir+"\\data.txt");
                newfile.createNewFile();
                try (FileWriter fw = new FileWriter(newfile)) {
                    fw.write(name,0,name.length());
                }
                JOptionPane.showMessageDialog(null, "Visit your custom dropbox folder at "+dir);
            }

            else
                JOptionPane.showMessageDialog(null,"Failed to create custom dropbox folder!\nTry again.\n");
        }
    }
}

