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
import javax.swing.JOptionPane;

public class LogInConnect{
    String user_name,pass_word;
	
	
	public LogInConnect(String name,String pw){
            user_name=name;
            pass_word=pw;
		
        try{
            @SuppressWarnings("resource")
            Socket socket=new Socket("localhost",2553);

            BufferedWriter out=new BufferedWriter(
                new OutputStreamWriter(socket.getOutputStream()));
            
            BufferedReader in=new BufferedReader(
                new InputStreamReader(socket.getInputStream()));

            while(true){
                out.write(user_name,0,user_name.length());
                out.write(" ");
                out.write(pass_word,0,pass_word.length());
                out.newLine();
                out.flush();
                break;
            }
            
            String msg;
            while((msg=in.readLine())!=null){

                JOptionPane.showMessageDialog(null,msg);
                break;
            }
           
            if(msg.equals("Successfully Signed in."))
                new UserAccountFrame(user_name).setVisible(true);

            in.close();

        } catch(UnknownHostException e){
            System.out.println(e.getMessage());
            System.exit(1);

        } catch(IOException e){
            //e.printStackTrace();
            System.out.println(e.getMessage());
            System.exit(1);

        }

		
    }

}
