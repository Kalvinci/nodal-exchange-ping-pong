package com.nodelexchange.pingpong.service_1.task;

import com.nodelexchange.pingpong.service_1.service.PingPongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class StartupTask {

    private final PingPongService pingPongService;

    @Autowired
    public StartupTask(PingPongService pingPongService) {
        this.pingPongService = pingPongService;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void onApplicationReady() {
        pingPongService.serve("ping");
    }
}

