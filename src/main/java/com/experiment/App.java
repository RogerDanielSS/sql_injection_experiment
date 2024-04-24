package com.experiment;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.Map;

import db.DBController;
import utils.HttpUtils;

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

        /**
         * Method that deals with login requests
         * gets request params, calls database login method and send it return as response 
         * 
         * @param exchange request data
         * 
         * @return void
         */
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            
            Map<String, String> queryParams = HttpUtils.parseQueryParams(exchange.getRequestURI().getQuery());
            
            // Define response message
            String response = this.db.login(queryParams.get("username"), queryParams.get("password"));
            
            // Set response headers
            exchange.sendResponseHeaders(200, response.getBytes().length);
            
            // Get output stream and write response
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }
}