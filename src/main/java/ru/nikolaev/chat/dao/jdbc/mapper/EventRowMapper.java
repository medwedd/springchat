package ru.nikolaev.chat.dao.jdbc.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.nikolaev.chat.entity.Event;
import ru.nikolaev.chat.entity.User;
import ru.nikolaev.chat.enums.EventType;
import ru.nikolaev.chat.enums.UserRole;

import java.sql.ResultSet;
import java.sql.SQLException;


public class EventRowMapper implements RowMapper<Event> {
    @Override
    public Event mapRow(ResultSet rs, int rowNum) throws SQLException {
        Event event = new Event();
        event.setId(rs.getLong("ID"));
        event.setTimestamp(rs.getTimestamp("TIME_STAMP"));
        long ownerId = rs.getLong("OWNER_ID");
        if (ownerId != 0) {
            User owner = new User();
            owner.setId(ownerId);
            owner.setName(rs.getString("OWNER_NAME"));
            owner.setUserRole(UserRole.getUserRoleById(rs.getInt("OWNER_ROLE")));
            event.setOwner(owner);
        }
        long assigneeId = rs.getLong("ASSIGNEE_ID");
        if (assigneeId != 0) {
            User assignee = new User();
            assignee.setId(assigneeId);
            assignee.setName(rs.getString("ASSIGNEE_NAME"));
            event.setAssignee(assignee);
        }
        event.setEventType(EventType.getEventTypeById(rs.getInt("EVENT_TYPE_ID")));
        event.setMessage(rs.getString("MESSAGE"));
        event.setIp(rs.getString("IP"));
        return event;
    }
}
