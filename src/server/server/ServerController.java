package server.server;

import server.client.ClientController;
import server.service.FileWorker;

import java.util.ArrayList;
import java.util.List;

public class ServerController {
    private List<ClientController> clientControllers;
    private boolean work;
    private ServerView serverView;

    public ServerController(ServerView serverView) {
        this.serverView = serverView;
        clientControllers = new ArrayList<>();
        serverView.setServerController(this);
    }

    public void start() {
        if (this.work) {
            showOnWindow("Сервер уже был запущен");
            return;
        }

        this.work = true;
        showOnWindow("Сервер запущен!");
    }

    public void stop() {
        if (!this.work) {
            showOnWindow("Сервер уже был остановлен");
            return;
        }

        this.work = false;

        while (!clientControllers.isEmpty()) {
            disconnectUser(clientControllers.get(clientControllers.size() - 1));
        }

        showOnWindow("Сервер остановлен!");
    }

    public boolean connectUser(ClientController clientController) {
        if (!work) {
            return false;
        }

        clientControllers.add(clientController);
        showOnWindow(clientController.getName() + " вошёл в чат");

        return true;
    }

    private void showOnWindow(String text) {
        serverView.showMessage(text + "\n");
    }

    public void disconnectUser(ClientController clientController) {
        clientControllers.remove(clientController);

        if (clientController != null) {
            clientController.disconnectedFromServer();
            showOnWindow(clientController.getName() + " вышел из чата");
        }
    }

    /**
     * Добавляет сообщение на сервер
     *
     * @param text текст сообщения
     */
    public void message(String text) {
        if (!work) return;

        showOnWindow(text);
        answerAll(text);
        saveInHistory(text);
    }

    private void answerAll(String text) {
        clientControllers.forEach(c -> c.answerFromServer(text));
    }

    private void saveInHistory(String text) {
        FileWorker.saveInLog(text);
    }

    public String getHistory() {
        return FileWorker.readLog();
    }
}
