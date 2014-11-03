
package dropboxserver;

import java.io.*;
import java.net.*;
import java.util.StringTokenizer;

public class SignIn implements Runnable{
    
    private final Socket s;
    Thread t;
    
    public SignIn(Socket s){
        this.s=s;
        t=new Thread(this);
        t.start();
    }
    
    @Override
    public void run(){
        
        try{
            
            synchronized(this){
            	
                BufferedWriter bw=new BufferedWriter(
                		new OutputStreamWriter(s.getOutputStream()));
                
                BufferedReader br=new BufferedReader(
                new InputStreamReader(s.getInputStream()));
                
                String inputLine;
        	
            	while((inputLine=br.readLine())!=null){
            		break;
            	}
                
            	
                StringTokenizer st=new StringTokenizer(inputLine);
                String userName=null, password=null;
                int count=0;
                
                while(st.hasMoreTokens()){
                    if(count==0) userName=st.nextToken();
                    else if(count==1) password=st.nextToken();
                    else if(count==2) break;
                    count++;
                }
                
                File file=new File("G:\\"+userName);
                if(file.exists()){
                	
                    FileReader fr=new FileReader("G:\\"+userName+"\\data.txt");
                    br=new BufferedReader(fr);
                    
                    String savedPassword=br.readLine();
                    fr.close();
                    String trimmedPassword= password.trim();
                    String trimmedSavedPassword=savedPassword.trim();
 
                    
                    if(trimmedPassword.equals(trimmedSavedPassword)){
                        bw.write("Successfully Signed in.");
                        bw.newLine();
                        bw.flush();
                    }
                    
                    else{
                        bw.write("Wrong Username! Try Again.!");
                        bw.newLine();
                        bw.flush();
                    }
                }
                
                else{
                    bw.write("Wrong Username! Try Again.");
                    bw.newLine();
                    bw.flush();
                }
                
                bw.close(); br.close();
            }
            
        } catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
    
}
