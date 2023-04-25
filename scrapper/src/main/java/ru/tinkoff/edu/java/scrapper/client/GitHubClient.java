package ru.tinkoff.edu.java.scrapper.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.WebClient;
import ru.tinkoff.edu.java.scrapper.dto.GitHubResponse;
import ru.tinkoff.edu.java.scrapper.exception.GitHubRequestException;

public class GitHubClient {


    @Value("${gh.baseurl}")
    private String gitHubBaseUrl;

    private final WebClient webClient;

    public GitHubClient() {
        this.webClient = WebClient.create(gitHubBaseUrl);
    }


    public GitHubClient(String baseUrl) {
        this.webClient = WebClient.create(baseUrl);
    }


    public GitHubResponse fetchRepo(String owner, String repo) {
        GitHubResponse response = webClient.get().uri("/repos/{owner}/{repo}", owner, repo).exchangeToMono(r->{
            if (!r.statusCode().equals(HttpStatus.OK)) throw new GitHubRequestException("Error with request to GH API");
            return r.bodyToMono(GitHubResponse.class);
                }).block();


        return response;

    }

    public void strFetchRepo(String owner, String repo){
        String strReponse = webClient.get().uri("/repos/{owner}/{repo}", owner, repo).exchangeToMono(r->{
            if (!r.statusCode().equals(HttpStatus.OK)) throw new GitHubRequestException("Error with request to GH API");
            return r.bodyToMono(String.class);
        }).block();

        System.out.println(strReponse);

    }
}
