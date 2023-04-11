package ru.tinkoff.edu.java.scrapper.services;


import org.springframework.stereotype.Service;
import ru.tinkoff.edu.java.scrapper.DTO.AddLinkRequest;
import ru.tinkoff.edu.java.scrapper.DTO.RemoveLinkRequest;
import ru.tinkoff.edu.java.scrapper.Model.Link;

import java.util.List;

@Service
public class LinkService {

    long nextId = 0;
    private final ChatService chatService;

    public LinkService(ChatService chatService) {
        this.chatService = chatService;
    }

    public Link addLink(Long chatId, AddLinkRequest request) {
        Link link = new Link(nextId, request.url());
        nextId++;
        chatService.addLink(chatId, link);
        return link;
    }

    public List<Link> getLinks(Long id) {
        return chatService.getLinks(id);
    }
    public Link deleteLink(Long id, RemoveLinkRequest request) {
        return chatService.deleteLink(id, request.link());
    }
}
