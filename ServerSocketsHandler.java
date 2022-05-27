package Server;

import com.example.jigsaw_sockets.Client.ClientSocketsHandler;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServerSocketsHandler {
    public static ServerSocket serv;
    private static int port;

    public static void setPort(int prt){
        port = prt;
    }

    public static int getPort(){
        return port;
    }

    private static void buildSocket() throws IOException {
        serv = new ServerSocket(port);
    }



    // в этой функции будет несколько подфункций
    // 1. получаем запрос на вставку
    // 2. пробуем вставить
    // 3. отправляем ответ обратно через сокет
    static void getSocket() throws IOException {

        Socket s = serv.accept();
        InputStream input = s.getInputStream();
        OutputStream output = s.getOutputStream();
        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(output)), true);
        Scanner in = new Scanner(input);
        while(in.hasNextLine()) {
            System.out.println(in.hasNextLine());
            String line = in.nextLine();
            System.out.println(line);    // почему-то hasNextLine блокирует процесс
        }
        System.out.println("message recieved");
    }


    public static void main(String[]  args) throws IOException {
        setPort(5000);
        buildSocket();
        ClientSocketsHandler cli1 = new ClientSocketsHandler();
        ClientSocketsHandler cli2 = new ClientSocketsHandler();
        cli1.createSocket();
        getSocket();
        cli2.createSocket();
        //cli1.sendRequest();
        getSocket();
        cli1.recieveAnswer();
    }


}
