package bluebotclient.backend;

import jssc.SerialPort;

class Program {
    private BlueBotConnection connection;

    Program() {
        connection = new BlueBotConnection(new SerialPort("COM3"));
    }

    void run() {

    }
}
