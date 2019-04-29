import javax.swing.*;
import java.net.*;
import java.util.regex.*;

class Receiver implements Runnable {
    private JTextArea txtArea;
    private int port;

    Receiver(JTextArea txtArea, int port){
        this.txtArea = txtArea;
        this.port = port;
    }

    @Override
    public void run() {
        try {
            customize();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void customize() throws Exception {
        DatagramSocket receiverSocked = new DatagramSocket(port);
        Pattern regex = Pattern.compile("[\u0020-\uFFFF]");

        while (true) {
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
