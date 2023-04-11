package ru.tinkoff.edu.java.scrapper.services;


import org.springframework.stereotype.Service;
import org.springframework.util.ConcurrentReferenceHashMap;
import ru.tinkoff.edu.java.scrapper.Model.Link;
import ru.tinkoff.edu.java.scrapper.exceptions.ChatExistException;
import ru.tinkoff.edu.java.scrapper.exceptions.ChatNotFoundException;
import ru.tinkoff.edu.java.scrapper.exceptions.LinkExistException;
import ru.tinkoff.edu.java.scrapper.exceptions.LinkNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentMap;

@Service
public class ChatService {

    ConcurrentMap<Long, List<Link>> chats = new ConcurrentReferenceHashMap<>();

    public void addChat(Long id){
        if(chats.containsKey(id)){
            throw new ChatExistException("Error: chat already created");
        }
        chats.put(id, new ArrayList<>());
    }

    public void deleteChat(Long id){
        if(!chats.containsKey(id)){
            throw new ChatNotFoundException("Error: chat not found");
        }
        chats.remove(id);
    }

    public void addLink(Long id, Link link){
        if(!chats.containsKey(id)) throw new ChatNotFoundException("Error: chat not found");
        if(chats.get(id).contains(link)){
            throw new LinkExistException("Error: link already created");
        }
        chats.get(id).add(link);
    }

    public List<Link> getLinks(Long id){
        if(!chats.containsKey(id)) throw new ChatNotFoundException("Error: chat not found");
        return chats.get(id);
    }

    public Link deleteLink(Long id, String url){
        if(!chats.containsKey(id)) throw new ChatNotFoundException("Error: chat not found");
        for(Link links: chats.get(id)){
            if(links.getUrl().equals(url)){
                chats.get(id).remove(links);
                return links;
            }
        }
        throw new LinkNotFoundException("Error: link not found");
    }
}
