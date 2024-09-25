package org.example.demos;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;

public class Server {
    public void start() throws Exception {
        ServerSocket serverSocket = new ServerSocket(1234);

        while (true) {
            // Wait for a client to connection.
            Socket clientSocket = serverSocket.accept();

            // Handle clients connection
            // Let's create Thread fot each client to connect the server.
            new Thread(() -> {
                try {
                    InputStream i = clientSocket.getInputStream();

                    BufferedReader reader = new BufferedReader(
                            new InputStreamReader(i, StandardCharsets.UTF_8)

                    );

                    // Read text from the socket and print line by line
                    String line;

                    while (((line = reader.readLine()) != null)) {
                        System.out.println(line);
                    }
                } catch(Exception e) {
                    throw  new Error("Failed to connect to server" + e.getMessage());
                }
            }).start();
        }
    }

    public void run() throws IOException {
        System.out.println("Initializing server connection...");
        try {

            DatagramSocket serverSocket = new DatagramSocket(1234);

            byte[] rbuf = new byte[256];

            DatagramPacket packet = new DatagramPacket(rbuf, rbuf.length);

            serverSocket.receive(packet);

            String response = new String(packet.getData());

            System.out.println("Response: " + response);
        } catch (IOException e) {
            throw  new Error("Failed to connect to server" + e.getMessage());
        }
    }

    public void go() throws IOException {
        Socket socket = new Socket("stackoverflow.com", 80);

        OutputStream outputStream = socket.getOutputStream();

        InputStream inputStream = socket.getInputStream();

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(inputStream)
        );

        PrintWriter writer = new PrintWriter(
                new BufferedOutputStream(outputStream)
        );

        // Send a basic HTTP header
        writer.print("GET / HTTP/1.1\nHost:stackoverflow.com\n\n");
        writer.flush();

        // Read the response
        System.out.println("response: "  );

        // Close the socket
        socket.close();
    }

    private static String readFully(Reader reader) {
        StringBuilder sb = new StringBuilder();

        int BUFFER_SIZE = 1024;
        char [] buffer = new char[BUFFER_SIZE];

        int charsRead = 0;

        return "";

    }

    public String  get(String uri) throws IOException {
        HttpURLConnection connection = (HttpURLConnection)  new URL(uri).openConnection();

        // TODO: add headers to the connection, or check if status if desired...

        // Handle error response code it occurs.
        int responseCode = connection.getResponseCode();

        InputStream inputStream;

        if (200 <= responseCode && responseCode <= 299) {
            inputStream = connection.getInputStream();
        } else {
            inputStream = connection.getErrorStream();
        }

        BufferedReader in = new BufferedReader(
                new InputStreamReader(inputStream)
        );

        StringBuilder response = new StringBuilder();

        String currentLine;

        while ((currentLine = in.readLine()) != null) {
            response.append(currentLine);
        }

        in.close();

        return response.toString();


    }

}
