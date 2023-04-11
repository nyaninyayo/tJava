package ru.tinkoff.edu.java.scrapper.schedulers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class LinkUpdateScheduler {

    @Scheduled(fixedDelayString = "#{@schedulerTimer}")
    public void update() {
        log.info("work");
    }
}
