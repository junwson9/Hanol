package com.ssafy.hanol.global.sse.service;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.concurrent.ConcurrentHashMap;

@Component
public class SseEmitterManager {
    private final ConcurrentHashMap<Long, SseEmitter> concurrentHashMap = new ConcurrentHashMap<>();

    public void addSseEmitter(Long id, SseEmitter emitter) {
        concurrentHashMap.put(id, emitter);
    }

    public SseEmitter getSseEmitter(Long id) {
        return concurrentHashMap.get(id);
    }

    public void removeSseEmitter(Long id) {
        concurrentHashMap.remove(id);
    }

    public boolean isValidSseEmitter(Long id) {
        return concurrentHashMap.contains(id);
    }
}
