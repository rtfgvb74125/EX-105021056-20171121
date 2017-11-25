import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ServerFrame extends JFrame {
    private int ScreenW = Toolkit.getDefaultToolkit().getScreenSize().width;
    private int ScreenH = Toolkit.getDefaultToolkit().getScreenSize().height;
    private int frmW = 400, frmH = 500;
    private JLabel jlab = new JLabel();
    private JTextArea jta = new JTextArea();
    private JScrollPane jsca = new JScrollPane(jta);
    private JTextArea jtx = new JTextArea();
    private JScrollPane jscx = new JScrollPane(jtx);
    private JButton jbtnClose = new JButton("Close");
    private JButton jbtnStart = new JButton("Start");
    private JButton jbtnSend = new JButton("Send");
    private JPanel jpnSend = new JPanel(new BorderLayout(3, 3));
    private JPanel jpnControl = new JPanel(new GridLayout(7, 1, 3, 3));
    private Container cp;
    private Server srv;

    public ServerFrame() {
        init();
    }

    private void init() {
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setBounds(ScreenW / 2 - frmW / 2, ScreenH / 2 - frmH / 2, frmW, frmH);
        this.setTitle("Srever");
        jbtnSend.setSize(20, 20);
        cp = this.getContentPane();
        cp.setLayout(new BorderLayout(3, 3));
        cp.add(jpnSend, BorderLayout.SOUTH);
        cp.add(jpnControl, BorderLayout.EAST);
        cp.add(jsca, BorderLayout.CENTER);
        jpnControl.add(jbtnStart);
        jpnControl.add(jbtnClose);
        jpnSend.add(jscx, BorderLayout.CENTER);
        jpnSend.add(jbtnSend, BorderLayout.EAST);
        jbtnSend.setPreferredSize(new Dimension(100,50));
        jbtnClose.setPreferredSize(new Dimension(100,10));
        jbtnStart.setPreferredSize(new Dimension(100,10));
        jtx.setLineWrap(true);
        jta.setLineWrap(true);
        jta.setBackground(new Color(191, 87, 255));
        jta.setEditable(false);
        jbtnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                srv = new Server(ServerFrame.this);
                srv.start();
                jta.append("Waiting Connect in...\n");
                ((JButton) e.getSource()).setEnabled(false);
            }
        });
        jbtnClose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        jbtnSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                srv.sendMsgclient(jtx.getText());
                jta.append("Srever :" + jtx.getText() + "\n");
                jtx.setText("");
            }
        });
    }

    public void addMessage(String instr) {
        jta.append("Client :" + instr + "\n");
    }
}

