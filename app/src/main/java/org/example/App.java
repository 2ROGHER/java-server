/*
 * This source file was generated by the Gradle 'init' task
 */
package org.example;

import org.example.demos.Server;

public class App {


    public static void main(String[] args)  {
        Server s = new Server();
        try {
            System.out.println("Server running and starting in port: [1234]");


        } catch (Exception e) {
            throw  new Error("Error connecting to server");
        }
    }
}
