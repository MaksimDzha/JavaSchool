import javax.swing.*;
import java.awt.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChatUDP extends JFrame {
    private JTextArea txtArea;
    private JTextField txtMsg;
    private final String FRM_TITLE = "My chat";
    private final int FRM_LOC_X = 100;
    private final int FRM_LOC_Y = 100;
    private final int FRM_WIDTH = 400;
    private final int FRM_HEIGHT = 400;
    private final int PORT = 1234;
    private final String IP_BROADCAST = "localhost";

    private class theReceiver extends Thread {
        @Override
        public void start() {
            super.start();
            try{
                customize();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private void customize() throws Exception{
            DatagramSocket receiverSocked = new DatagramSocket(PORT);
            Pattern regex = Pattern.compile("[\u0020-\uFFFF]");
            while (true){
                byte[] receiveData = new byte[1024];
                DatagramPacket receivePacked = new DatagramPacket(receiveData, receiveData.length);
                receiverSocked.receive(receivePacked);
                InetAddress ipAdress = receivePacked.getAddress();
                int port = receivePacked.getPort();
                String sentence = new String(receivePacked.getData());
                Matcher matcher = regex.matcher(sentence);
                txtArea.append(ipAdress.toString() + ":" + port + ": ");
                while (matcher.find())
                    txtArea.append(sentence.substring(matcher.start(), matcher.end()));
                txtArea.append("\r\n");
                txtArea.setCaretPosition(txtArea.getText().length());
            }
        }
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

    private void frameDraw(JFrame frame) {
        txtArea = new JTextArea(FRM_HEIGHT / 19, 50);
        txtMsg = new JTextField();
        JScrollPane spArea = new JScrollPane(txtArea);
        spArea.setLocation(0, 0);
        txtArea.setLineWrap(true);
        txtArea.setEditable(false);

        JButton sendBtn = new JButton();
        sendBtn.setText("Send");
        sendBtn.setToolTipText("Broadcast a message");
        sendBtn.addActionListener(e -> {
            try{
                btnSendHandler();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setTitle(FRM_TITLE);
        frame.setLocation(FRM_LOC_X, FRM_LOC_Y);
        frame.setSize(FRM_WIDTH, FRM_HEIGHT);
        frame.setResizable(false);
        frame.getContentPane().add(BorderLayout.NORTH, txtArea);
        frame.getContentPane().add(BorderLayout.CENTER, txtMsg);
        frame.getContentPane().add(BorderLayout.EAST, sendBtn);
        frame.setVisible(true);

    }

    private void antiStatic() {
        frameDraw(new ChatUDP());
        new theReceiver().start();
        System.out.println("In antiStatic, after...");
    }

    public static void main(String[] args) {
        new ChatUDP().antiStatic();

    }
}
