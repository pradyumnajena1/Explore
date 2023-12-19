package epp.parallelcomputing;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SimpleWebServer {
    private static final int SERVERPORT =8080;
    private   ExecutorService executorService = Executors.newCachedThreadPool();
    public static void main(String[] args) throws IOException, InterruptedException {
        SimpleWebServer simpleWebServer = new SimpleWebServer();

        Thread c1 = new Thread(()->{


            for(;;) {
                OutputStreamWriter writer = null;
                LineNumberReader reader = null;
                try {
                    Socket socket = new Socket(InetAddress.getLocalHost(), SERVERPORT);
                    writer =
                            getWriter(socket);
                    reader = getReader(socket);
                    writer.write("hello "+System.nanoTime());
                    writer.write(System.lineSeparator());
                    writer.flush();

                    String response = reader.readLine();
                    System.out.println("received response "+ response);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }finally {
                    closeWriter(writer);
                    closeReader(reader);
                }

            }

        });
        c1.start();
        c1.join();
    }

    private static void closeReader(LineNumberReader reader) {
        try {
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void closeWriter(OutputStreamWriter writer) {
        try {
            writer.flush();
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static OutputStreamWriter getWriter(Socket socket) throws IOException {
        return new OutputStreamWriter(new BufferedOutputStream(socket.getOutputStream()));
    }

    public SimpleWebServer() throws IOException {
        Thread serverThread = new Thread(()-> {
            try {
                startServer();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        serverThread.start();
    }

    public    void startServer() throws IOException {
        ServerSocket serverSocket = new ServerSocket(SERVERPORT);
        System.out.println("server started listening on port "+ SERVERPORT);
        for(;;){
            Socket socket = serverSocket.accept();
            // basic
           // processRequest(socket);

            // first optimization
            Thread thread = new Thread(() -> processRequest(socket));
            thread.start();

            //second
            executorService.submit(()->processRequest(socket));


        }
    }

    private static void processRequest(Socket socket) {
        System.out.println("start");
        LineNumberReader lineNumberReader = null;
        OutputStreamWriter writer = null;
        try {
            Thread.sleep(1000);
              lineNumberReader =
                    getReader(socket);
              writer = getWriter(socket);

            String request = lineNumberReader.readLine();
            System.out.println("got request "+ request);
            String response  = request + " done";
            writer.write(response);
            writer.flush();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            closeWriter(writer);
            closeReader(lineNumberReader);
        }
        System.out.println("end");
    }

    private static LineNumberReader getReader(Socket socket) throws IOException {
        return new LineNumberReader(new InputStreamReader(new BufferedInputStream(socket.getInputStream())));
    }
}
