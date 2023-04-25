package ru.tinkoff.edu.java.scrapper.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import ru.tinkoff.edu.java.scrapper.client.BotClient;
import ru.tinkoff.edu.java.scrapper.client.GitHubClient;
import ru.tinkoff.edu.java.scrapper.client.StackOverflowClient;

@Configuration
public class ClientConfiguration {

    @Value("${gh.baseurl}")
    private String gitHubBaseUrl;

    @Value("${so.baseurl}")
    private String stackOverflowBaseUrl;

    @Value("${bot.baseurl}")
    private String botBaseUrl;


    @Bean
    public GitHubClient gitHubClientService() {
        return new GitHubClient(gitHubBaseUrl);
    }

    @Bean
    public StackOverflowClient stackOverflowClientService() {
        return new StackOverflowClient(stackOverflowBaseUrl);
    }

    @Bean
    public BotClient botClient(){return new BotClient(botBaseUrl);}

    @Bean
    public WebClient botWebClient(){
        return WebClient.create(botBaseUrl);
    }

    @Bean
    public WebClient ghWebClient(){
        return WebClient.create(gitHubBaseUrl);
    }

    @Bean
    public WebClient soWebClient(){
        return WebClient.create(stackOverflowBaseUrl);
    }
}
