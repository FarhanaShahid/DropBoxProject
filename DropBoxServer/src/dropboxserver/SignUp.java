package dropboxserver;


import java.io.*;
import java.net.*;
import java.util.StringTokenizer;

class SignUp implements Runnable{
    private final Socket socket;
    Thread t;

    public SignUp(Socket s){
        socket=s;
        t=new Thread(this);
        t.start();
    }

    @Override
    public void run(){

        try{
            PrintWriter out=new PrintWriter(
                socket.getOutputStream(),true);

            BufferedReader in=new BufferedReader(new
                InputStreamReader(socket.getInputStream()));

            synchronized(this){

                int count;
                FileWriter fw=new FileWriter("G:\\Dropbox\\temp.txt");

                String inputLine;
                while((inputLine=in.readLine())!=null){
                    fw.write(inputLine);
                    fw.flush();
                    break;
                }
                out.close();
                in.close();
                fw.close();

                FileReader fr=new FileReader("G:\\Dropbox\\temp.txt");
                char[] ch=new char[100];
                fr.read(ch);

                inputLine=new String(ch); count=0;
                String folder=null, password=null;
                StringTokenizer st=new StringTokenizer(inputLine);
                while(st.hasMoreTokens()){

                    if(count==0) folder=st.nextToken();
                    else if(count==1) password=st.nextToken();
                    else if(count==2) break;
                    count++;
                }
                
                fr.close();

                File file=new File("G:\\"+folder);
                System.out.println(file.mkdir());
                if(file.exists()){

                    File newFile=new File("G:\\"+folder+"\\data.txt");

                    if(!newFile.exists()){
                        if(newFile.createNewFile()){

                            fw=new FileWriter(newFile);
                            fw.write(password);
                            fw.flush();
                            fw.close();
                        }
                    }
                }
            } 
        }catch(IOException e){
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }
}