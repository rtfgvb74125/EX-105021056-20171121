import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Server extends Thread{
    private InetAddress ipAdress;
    private ServerSocket sSocket;
    private Socket socket;
    private PrintStream outStream;
    private BufferedReader inStream;
    private ServerFrame sf;
    public Server(ServerFrame SF){
        sf = SF;
        try{
            ipAdress = InetAddress.getLocalHost();
            sSocket = new ServerSocket(2222);
        }catch (UnknownHostException e){
            javax.swing.JOptionPane.showMessageDialog(null,"Error"+e.toString());
        }catch (IOException ioe){
            javax.swing.JOptionPane.showMessageDialog(null,"Error"+ioe.toString());
        }
    }
    public void run(){
        try{
            socket = sSocket.accept();
            outStream = new PrintStream(socket.getOutputStream());
            inStream = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            sendMsgclient("Welcome");
            String str = "";
            while (!(str = inStream.readLine()).equals("")){
                sf.addMessage(str);
            }
        }catch (Exception e){
            javax.swing.JOptionPane.showMessageDialog(null,"Error"+e.toString());
        }
    }
    public String getIP(){
        return ipAdress.getHostAddress();
    }
    public void sendMsgclient(String msg){
        try{
            if(outStream != null){
                outStream.println(msg);
            }else{
                javax.swing.JOptionPane.showMessageDialog(null,"Error:make connection with Client!");
            }
        }catch (Exception e){
            javax.swing.JOptionPane.showMessageDialog(null,"Error"+e.toString());
        }
    }
}
