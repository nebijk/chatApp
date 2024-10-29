package com.example.chatApp.demo.Config;

import com.example.chatApp.demo.Entity.ChatRoom;
import com.example.chatApp.demo.Entity.User;
import com.example.chatApp.demo.service.ChatRoomService;
import com.example.chatApp.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuthChannelInterceptor implements ChannelInterceptor {


    @Autowired
    private UserService userService;

    @Autowired
    private ChatRoomService chatRoomService;

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
        StompCommand cmd = accessor.getCommand();
        if (cmd == StompCommand.SUBSCRIBE) {
            Long id = null;
            String desination = accessor.getDestination();
            if (desination.startsWith("/topic/groupchat/")) {
                id = Long.valueOf(desination.split("/")[3]);
            }

            if (id != null) {
                User user = userService.getUserFromWebSocket(accessor.getUser());
                Optional<ChatRoom> optional = chatRoomService.findById(id);
                if (optional.isEmpty() || !optional.get().getUsers().contains(user)) {
                    return null;
                }
            }

        }
        return message;
    }

    }


