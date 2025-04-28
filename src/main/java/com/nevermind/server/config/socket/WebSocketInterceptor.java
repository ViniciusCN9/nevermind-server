package com.nevermind.server.config.socket;

import com.nevermind.server.config.security.JwtService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class WebSocketInterceptor implements HandshakeInterceptor {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    @Override
    public boolean beforeHandshake(@NonNull ServerHttpRequest request, @NonNull ServerHttpResponse response, @NonNull WebSocketHandler wsHandler, @NonNull Map<String, Object> attributes) {
        String query = request.getURI().getQuery();
        if (query != null && query.startsWith("token=")) {
            String token = query.substring(6);

            try {
                String username = jwtService.extractUsername(token);
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
                if (username != null && jwtService.isTokenValid(token, userDetails)) {
                    attributes.put("username", username);
                    return true;
                }
            } catch (Exception ignore) { }
        }
        return false;
    }

    @Override
    public void afterHandshake(@NonNull ServerHttpRequest request, @NonNull ServerHttpResponse response, @NonNull WebSocketHandler wsHandler, @NonNull Exception exception) { }
}
