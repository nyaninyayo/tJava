package ru.tinkoff.edu.java.scrapper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;
import ru.tinkoff.edu.java.scrapper.configuration.ApplicationConfig;
import ru.tinkoff.edu.java.scrapper.model.jpa.LinkEntity;
import ru.tinkoff.edu.java.scrapper.service.jpa.impl.JpaSubscriptionServiceImpl;
import ru.tinkoff.edu.java.scrapper.service.jpa.impl.JpaTgChatServiceImpl;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@SpringBootApplication
@EnableConfigurationProperties(ApplicationConfig.class)
@EnableScheduling
public class ScrapperApplication {
    public static void main(String[] args) throws URISyntaxException {
        var ctx = SpringApplication.run(ScrapperApplication.class, args);
    }
}