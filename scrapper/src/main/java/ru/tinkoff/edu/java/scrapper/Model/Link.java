package ru.tinkoff.edu.java.scrapper.Model;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Link {
    private long id;
    private String url;

    public Link(long id, String url) {
        this.id = id;
        this.url = url;
    }
}
