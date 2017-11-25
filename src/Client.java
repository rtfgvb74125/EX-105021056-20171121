import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class Client extends Thread{
    private Socket socket;
    private PrintStream outStream;
    private BufferedReader inStream;
    private ClientFrame cf;
    public Client(ClientFrame CF){
        this.setDaemon(true);
        cf = CF;
    }
    public void run(){
        try{
            socket = new Socket(cf.getIP(),2222);
            outStream = new PrintStream(socket.getOutputStream());
            inStream = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            send2server("Client is connected");
            String str = "";
            while (!(str = inStream.readLine()).equals("")){
                cf.addMsg(str);
            }
        }catch (Exception e){
            javax.swing.JOptionPane.showMessageDialog(null,"Error"+e.toString());
        }
    }
    public void send2server(String msg){
        try{
            if(outStream!=null){
                outStream.println(msg);
            }else {
                javax.swing.JOptionPane.showMessageDialog(null,"Please make connection with server");
            }
        }catch (Exception e){
            javax.swing.JOptionPane.showMessageDialog(null,"Error"+e.toString());
        }
    }
    public void closeSocket(){
        try{
            inStream.close();
        }catch (Exception e){
            javax.swing.JOptionPane.showMessageDialog(null,"Error"+e.toString());
        }
    }
}
