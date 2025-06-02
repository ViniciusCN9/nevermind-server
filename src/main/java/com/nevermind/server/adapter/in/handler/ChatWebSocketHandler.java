package com.nevermind.server.adapter.in.handler;

import lombok.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class ChatWebSocketHandler extends TextWebSocketHandler {

    private final Map<String, WebSocketSession> activeUsers = new ConcurrentHashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        String username = (String) session.getAttributes().get("username");
        activeUsers.put(username, session);
        System.out.println("Connected user: " + username);

        String message = String.format("user|%s|connected", username);
        sendToActiveUsers(session, message);
        sendCurrentUsers(session, username);
    }

    @Override
    protected void handleTextMessage(@NonNull WebSocketSession session, TextMessage textMessage) throws Exception {
        System.out.println("Message received: " + textMessage.getPayload());
        String messagePayload = textMessage.getPayload();
        validateMessageSchema(messagePayload);

        String[] parameters = messagePayload.split("\\|");

        if (Objects.equals(parameters[0], "chat")) {
            String from = (String) session.getAttributes().get("username");
            String message = String.format("%s|%s|%s", parameters[0], from, parameters[2]);
            sendToUser(parameters[1], message);
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, @NonNull CloseStatus status) throws Exception {
        String username = (String) session.getAttributes().get("username");
        activeUsers.remove(username);
        System.out.println("Disconnected user: " + session.getId());

        String message = String.format("user|%s|disconnected", username);
        sendToActiveUsers(session, message);
    }

    public void sendToUser(String username, String message) throws Exception {
        WebSocketSession session = activeUsers.get(username);
        if (session != null && session.isOpen()) {
            session.sendMessage(new TextMessage(message));
        }
    }

    public void sendCurrentUsers(WebSocketSession session, String username) throws Exception {
        StringBuilder message = new StringBuilder("users|");
        for (String user : activeUsers.keySet()) {
            if (!Objects.equals(user, username)) {
                message.append(user).append(",");
            }
        }
        int lastIndex = message.length() - 1;
        if (message.charAt(lastIndex) == ',') {
            message.deleteCharAt(lastIndex);
        }
        session.sendMessage(new TextMessage(message.toString()));
    }

    public void sendToActiveUsers(WebSocketSession session, String message) throws Exception {
        for (WebSocketSession userSession : activeUsers.values()) {
            if (userSession != null && userSession.isOpen() && !userSession.getId().equals(session.getId())) {
                userSession.sendMessage(new TextMessage(message));
            }
        }
    }

    private void validateMessageSchema(String message) {
        if (message.split("\\|").length != 3) {
            throw new IllegalArgumentException("Invalid message");
        }
    }
}
