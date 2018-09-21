package ru.nikolaev.chat.dao.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.nikolaev.chat.dao.EventDao;
import ru.nikolaev.chat.entity.EventType;
import ru.nikolaev.chat.entity.User;

import java.sql.PreparedStatement;

@Repository
public class JdbcEventDao implements EventDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private Environment env;


    @Override
    public int sendEvent(User owner, EventType eventType, String message) {
        return sendEvent(owner, eventType, message, null);
    }

    @Override
    public int sendEvent(User owner, EventType eventType, String message, User assignee) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(env.getProperty("event.add"), new String[]{"id"});
            return ps;
        }, keyHolder);
        return keyHolder.getKey().intValue();
    }
}
