import javax.swing.*;
import java.awt.*;
import java.net.*;

public class ChatUDP extends JFrame {

    private final String FRM_TITLE = "Локальный чат";
    private final int FRM_LOC_X = 100;
    private final int FRM_LOC_Y = 100;
    private final int FRM_WIDTH = 600;
    private final int FRM_HEIGHT = 400;
    private final int PORT = 1234;
    private final String IP_BROADCAST = "localhost";
    private JTextField txtMsg = new JTextField();

    public static void main(String[] args) {
        new ChatUDP().frameStart();
    }

    private void frameStart() {
        JTextArea txtArea = new JTextArea(FRM_HEIGHT / 20, 40);
        JButton sendBtn = new JButton();
//        sendBtn.setSize(20,10);
        sendBtn.setPreferredSize(new Dimension(100,10));
        txtArea.setLineWrap(true);
        txtArea.setEditable(false);
        txtMsg.addActionListener(e -> sendBtn.doClick());
        JScrollPane spArea = new JScrollPane(txtArea);
        spArea.setLocation(0, 0);
        sendBtn.setText("Отправить");
        sendBtn.setToolTipText("Отправить сообщение");
        sendBtn.addActionListener(e -> {
            try{
                btnSendHandler();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setTitle(FRM_TITLE);
        this.setLocation(FRM_LOC_X, FRM_LOC_Y);
        this.setSize(FRM_WIDTH, FRM_HEIGHT);
        this.getContentPane().add(BorderLayout.NORTH, spArea);
        this.getContentPane().add(BorderLayout.CENTER, txtMsg);
        this.getContentPane().add(BorderLayout.EAST, sendBtn);
        this.setVisible(true);
        Thread receiver = new Thread(new Receiver(txtArea, PORT));
        receiver.start();
    }

    private void btnSendHandler() throws Exception{
        DatagramSocket sendSocket = new DatagramSocket();
        InetAddress ipAdress = InetAddress.getByName(IP_BROADCAST);
        byte[] sendData;
        String sentence = txtMsg.getText();
        txtMsg.setText("");
        sendData = sentence.getBytes("UTF-8");
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, ipAdress, PORT);
        sendSocket.send(sendPacket);
    }
}
