package org.example.demos;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Client {
    public void start() throws IOException {
        try (Socket socket = new Socket("127.0.0.1", 1234)) {

            // Write a string into socket, and flush the buffer
            OutputStream outputStream = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(
                    new OutputStreamWriter(outputStream, StandardCharsets.UTF_8)
            );

            writer.println("Hello world!");
            writer.flush(); // flush the buffer

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
