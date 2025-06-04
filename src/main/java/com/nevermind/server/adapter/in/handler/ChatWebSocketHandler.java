package com.nevermind.server.adapter.in.handler;

import lombok.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class ChatWebSocketHandler extends TextWebSocketHandler {

    private final Map<String, WebSocketSession> activeUsers = new ConcurrentHashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        String username = (String) session.getAttributes().get("username");
        String publicKey = (String) session.getAttributes().get("publicKey");
        String userInfo = String.format("%s:%s", username, publicKey);
        activeUsers.put(userInfo, session);
        System.out.println("Connected user: " + username);

        String message = String.format("user|%s|connected", userInfo);
        sendToActiveUsers(session, message);
        sendCurrentUsers(session, userInfo);
    }

    @Override
    protected void handleTextMessage(@NonNull WebSocketSession session, TextMessage textMessage) throws Exception {
        String messagePayload = textMessage.getPayload();
        validateMessageSchema(messagePayload);

        String[] parameters = messagePayload.split("\\|");

        if (Objects.equals(parameters[0], "chat")) {
            String from = (String) session.getAttributes().get("username");
            String message = String.format("%s|%s|%s", parameters[0], from, parameters[2]);
            sendToUser(parameters[1], message);
            System.out.println(String.format("Command: message from '%s' to '%s' content: %s", from, parameters[1], message));
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, @NonNull CloseStatus status) throws Exception {
        String username = (String) session.getAttributes().get("username");
        String publicKey = (String) session.getAttributes().get("publicKey");
        String userInfo = String.format("%s:%s", username, publicKey);
        activeUsers.remove(userInfo);
        System.out.println("Disconnected user: " + username);

        String message = String.format("user|%s|disconnected", userInfo);
        sendToActiveUsers(session, message);
    }

    public void sendToUser(String username, String message) throws Exception {
        WebSocketSession session = getSessionByUsername(username);
        if (session != null && session.isOpen()) {
            session.sendMessage(new TextMessage(message));
        }
    }

    public void sendCurrentUsers(WebSocketSession session, String currentUserInfo) throws Exception {
        StringBuilder message = new StringBuilder("users|");
        for (String userInfo : activeUsers.keySet()) {
            if (!Objects.equals(userInfo, currentUserInfo)) {
                message.append(userInfo).append(",");
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

    private WebSocketSession getSessionByUsername(String username) {
        Optional<String> optionalKey = activeUsers.keySet().stream()
                .filter(e -> Objects.equals(getUsernameFromUserInfo(e), username))
                .findFirst();
        return optionalKey.map(activeUsers::get).orElse(null);
    }

    private String getUsernameFromUserInfo(String userInfo) {
        String[] info = userInfo.split(":");
        return info[0];
    }
}
