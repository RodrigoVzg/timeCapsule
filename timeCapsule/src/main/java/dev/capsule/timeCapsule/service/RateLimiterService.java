package dev.capsule.timeCapsule.service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

@Component
public class RateLimiterService {
    private final Map<String, RequestInfo> requestsPerIp = new ConcurrentHashMap<>();
    private final int MAX_REQUESTS = 10;
    private final long INTERVAL_MS = 60_000; // 1 min

    public boolean isAllowed(String ip) {
        long now = System.currentTimeMillis();
        RequestInfo info = requestsPerIp.computeIfAbsent(ip, _ -> new RequestInfo(0, now));
        synchronized (info) {
            if (now - info.startTime > INTERVAL_MS) {
                info.count = 1;
                info.startTime = now;
                return true;
            } else if (info.count < MAX_REQUESTS) {
                info.count++;
                return true;
            } else {
                return false;
            }
        }
    }

    private static class RequestInfo {
        int count;
        long startTime;

        RequestInfo(int count, long startTime) {
            this.count = count;
            this.startTime = startTime;
        }
    }
}