package server;

import server.client.ClientController;
import server.client.ClientGUI;
import server.server.ServerController;
import server.server.ServerGUI;

public class Main {
    public static void main(String[] args) {
        ServerController serverController = new ServerController(new ServerGUI());

        new ClientController(new ClientGUI(), serverController);
        new ClientController(new ClientGUI(), serverController);
    }
}
