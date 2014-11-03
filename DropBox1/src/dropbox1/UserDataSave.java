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
import java.net.*;

public class UserDataSave {
    String user_name,pass_word;
	
    public UserDataSave(String name,String pw){
        user_name=name;
        pass_word=pw;
		
        try{
            Socket socket=new Socket("localhost",1601);

            OutputStreamWriter out=new OutputStreamWriter(
                    socket.getOutputStream());

            @SuppressWarnings("unused")
            BufferedReader in=new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));


            out.write(user_name,0,user_name.length());
            out.write(" ");	
            out.write(pass_word,0,pass_word.length());
            out.flush();

            socket.close();

        } catch(UnknownHostException e){
            System.out.println("UnknownHostException");
            System.exit(1);

        } catch(IOException e){
            System.out.println("Client IO Error");
            System.exit(1);
        }
		
    }
}

