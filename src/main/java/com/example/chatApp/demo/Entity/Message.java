package com.example.chatApp.demo.Entity;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@Entity
public class Message{


    public Message() {
    }

    public enum MessageType{
        USER_jOIN, USER_LEAVVE, USER_CHAT, USER_RENAME
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "content",columnDefinition = "varchar(1000)")
    private String content;

    @Column(name = "created_at")
    private Long createdAt;

    @Column(name = "modified_at")
    private Long modifiedAt;

    @Column(name = "message_type")
    @Enumerated(EnumType.STRING)
    private MessageType messageType;

    @Column(name = "sender")
    @ManyToOne(fetch = FetchType.EAGER)
    private User sender;

    @JoinColumn(name = "chat_room")
    @ManyToOne(fetch = FetchType.LAZY)
    private ChatRoom chatRoom;

}
