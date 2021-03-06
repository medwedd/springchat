package ru.nikolaev.chat.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nikolaev.chat.dao.EventDao;
import ru.nikolaev.chat.entity.Event;
import ru.nikolaev.chat.entity.User;
import ru.nikolaev.chat.enums.EventType;

import java.util.List;

@Service
public class MessageService {
    @Autowired
    private EventDao eventDao;

    @Autowired
    private EventService eventService;

    public Event sendMessage(User owner, String message, String ip) {
        return eventService.sendEvent(owner, EventType.MESSAGE, message, ip);
    }

    public List<Event> getLastMessages(int count) {
        return eventDao.getLastEventsByType(EventType.MESSAGE, count);
    }
}
