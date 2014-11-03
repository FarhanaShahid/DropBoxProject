package dropboxserver;

import java.net.*;
import java.util.StringTokenizer;
import java.io.*;

public class FileExistCheck implements Runnable{
	
	Socket socket;
	Thread t;
	
	public FileExistCheck(Socket s){
		socket=s;
		t=new Thread(this);
		t.start();
	}
	
	public void run(){
		try{
			
			//System.out.println("Entered FileExistCheck");
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            BufferedReader br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
            
            String input=null, msg=null;
            while((input=br.readLine())!=null){
            	break;
            }
            
            StringTokenizer st=new StringTokenizer(input);
            String fileName=null, type=null; int count=0;
            while(st.hasMoreTokens()){
            	if(count==0) fileName=st.nextToken();
            	else if(count==1) type= st.nextToken();
            	else if(count==2) break;
            	count++;
            }
            
            File file=new File(fileName);
            //System.out.println(fileName+" "+type);
            if(file.exists()) msg="yes";
            else{
            	
            	msg="no";
            	if(type.equals("file")){
            		file.createNewFile();
            	}
            	
            	else if(type.equals("dir")){
            		file.mkdir();
            	}
            }
            
            while(true){
            	bw.write(msg,0,msg.length());
            	bw.newLine();
            	bw.flush();
            	break;
            }
           
            
		}catch(IOException e){
            System.out.println(e.getMessage());
            System.exit(1);
        }
	}
}
