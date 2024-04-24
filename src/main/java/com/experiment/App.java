package com.experiment;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

import db.DBController;

public class App {
    public static void main(String[] args) {
        try {
            startServer();
        } catch (Exception e) {
            System.out.println("Error");
        }
    }

    private static void startServer() throws Exception {
        DBController db = new DBController();

        // Create a new HttpServer listening on port 8000
        HttpServer server = HttpServer.create(new InetSocketAddress("0.0.0.0", 8000), 0);

        // Create a context for "/login" path
        server.createContext("/login", new LoginHandler(db));
        
        // Start the server
        server.start();
        
        System.out.println("Server started on port 8000");
    }

    static class LoginHandler implements HttpHandler {
        DBController db;

        LoginHandler(DBController db){
            this.db = db;
        }

        @Override
        public void handle(HttpExchange exchange) throws IOException {
            // Define response message
            String response = "Hello, World!";

            this.db.login();
            
            // Set response headers
            exchange.sendResponseHeaders(200, response.getBytes().length);
            
            // Get output stream and write response
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }
}