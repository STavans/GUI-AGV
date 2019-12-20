package bluebotclient.backend;

import jssc.SerialPort;
import jssc.SerialPortException;

import java.util.Scanner;

public class BlueBotConnection {
    private SerialPort serialPort;
    private Scanner scanner;

    public BlueBotConnection(SerialPort serialPort) {
        this.serialPort = serialPort;
        scanner = new Scanner(System.in);


        try {
            if (!serialPort.isOpened()) {
                serialPort.openPort();
            }
            serialPort.setParams(115200,8,1,0);
        } catch (SerialPortException ex) {
            System.out.println(ex);
        }
    }

    public void writeString(String input) {
        try {
            serialPort.writeString(input);
        } catch (SerialPortException ex) {
            System.out.println(ex);
        }

    }
}
