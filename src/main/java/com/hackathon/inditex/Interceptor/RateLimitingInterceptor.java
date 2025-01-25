package com.hackathon.inditex.Interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.time.Instant;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class RateLimitingInterceptor implements HandlerInterceptor {

    // Configuration: Max requests per time window
    private static final int MAX_REQUESTS_PER_MINUTE = 100;

    // Data structure to track requests per client
    private final Map<String, ClientRequestInfo> clients = new ConcurrentHashMap<>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String clientId = getClientIdentifier(request);
        long currentTimestamp = Instant.now().getEpochSecond();

        ClientRequestInfo clientInfo = clients.computeIfAbsent(clientId, k -> new ClientRequestInfo(currentTimestamp, new AtomicInteger(0)));

        synchronized (clientInfo) {
            if (currentTimestamp > clientInfo.windowStartTimestamp + 60) {
                // Reset count and window
                clientInfo.windowStartTimestamp = currentTimestamp;
                clientInfo.requestCount.set(0);
            }

            if (clientInfo.requestCount.incrementAndGet() > MAX_REQUESTS_PER_MINUTE) {
                // Exceeded the limit
                response.setStatus(429);
                response.getWriter().write("Too Many Requests. Please try again later.");
                return false;
            }
        }

        return true; // Proceed with the request
    }

    /**
     * Identifies the client based on IP address.
     * For more robust identification, consider using API keys or tokens.
     */
    private String getClientIdentifier(HttpServletRequest request) {
        String xfHeader = request.getHeader("X-Forwarded-For");
        if (xfHeader == null){
            return request.getRemoteAddr();
        }
        return xfHeader.split(",")[0];
    }

    /**
     * Inner class to hold client request information
     */
    private static class ClientRequestInfo {
        long windowStartTimestamp;
        AtomicInteger requestCount;

        ClientRequestInfo(long windowStartTimestamp, AtomicInteger requestCount) {
            this.windowStartTimestamp = windowStartTimestamp;
            this.requestCount = requestCount;
        }
    }
}
