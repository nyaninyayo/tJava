package ru.tinkoff.edu.java.bot.configuration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import ru.tinkoff.edu.java.scrapper.configuration.ScrapperApplication;

@SpringBootApplication
@EnableConfigurationProperties(ApplicationConfig.class)
public class BotApplication {
  public static void main(String[] args) {
      var ctx = SpringApplication.run(ScrapperApplication.class, args);
      ApplicationConfig config = ctx.getBean(ApplicationConfig.class);
      System.out.println(config);
  }
}