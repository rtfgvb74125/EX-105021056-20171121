import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientFrame extends JFrame{
    private int ScreenW = Toolkit.getDefaultToolkit().getScreenSize().width;
    private int ScreenH = Toolkit.getDefaultToolkit().getScreenSize().height;
    private int frmW = 400,frmH = 500;
    private JTextArea jta = new JTextArea();
    private JTextArea jtx = new JTextArea();
    private JScrollPane jsca = new JScrollPane(jta);
    private JScrollPane jscx = new JScrollPane(jtx);
    private JLabel jlab = new JLabel("IP");
    private JTextField jtxIP = new JTextField("127.0.0.1");
    private JButton jbtnConnect = new JButton("Connect");
    private JButton jbtnClose = new JButton("Close");
    private JButton jbtnSend = new JButton("Send");
    private JPanel jpnSend = new JPanel(new BorderLayout(3,3));
    private JPanel jpnControl = new JPanel(new GridLayout(6,1,3,3));
    private  Container cp;
    private Client client;
    public ClientFrame(){
        init();
    }
    private void init(){
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setBounds(ScreenW/4-frmW/2,ScreenH/2-frmH/2,frmW,frmH);
        this.setTitle("Client");
        cp = this.getContentPane();
        cp.setLayout(new BorderLayout(3,3));
        cp.add(jpnSend,BorderLayout.SOUTH);
        cp.add(jsca,BorderLayout.CENTER);
        cp.add(jpnControl,BorderLayout.EAST);
        jpnSend.add(jscx,BorderLayout.CENTER);
        jpnSend.add(jbtnSend,BorderLayout.EAST);
        jbtnSend.setPreferredSize(new Dimension(100,50));
        jpnControl.add(jlab);
        jpnControl.add(jtxIP);
        jpnControl.add(jbtnConnect);
        jpnControl.add(jbtnClose);
        jta.setBackground(new Color(79, 255, 186));
        jlab.setHorizontalAlignment(SwingConstants.CENTER);
        jta.setLineWrap(true);
        jtx.setLineWrap(true);
        client = new Client(ClientFrame.this);
        jbtnClose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        jbtnConnect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(((JButton)e.getSource()).getText().equals("Connect")){
                     client.start();
                }
            }
        });
        jbtnSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                client.send2server(jtx.getText());
                jta.append("Client:"+jtx.getText()+"\n");
                jtx.setText("");
            }
        });
    }
    public void addMsg(String instr){
        jta.append("Server:"+instr+"\n");
    }
    public String getIP(){
        return jtxIP.getText();
    }

}
