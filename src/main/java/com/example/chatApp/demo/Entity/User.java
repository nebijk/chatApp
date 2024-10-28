package com.example.chatApp.demo.Entity;

import jakarta.persistence.*;

@Entity
@Table
public class User {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String username;
        private String oauthId;

        public User() {}


        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public String getUsername() {
                return username;
        }

        public void setUsername(String username) {
                this.username = username;
        }

        public String getOauthId() {
                return oauthId;
        }

        public void setOauthId(String oauthId) {
                this.oauthId = oauthId;
        }

        @Override
        public String toString() {
                return "User{" +
                        "id=" + id +
                        ", username='" + username + '\'' +
                        ", oauthId='" + oauthId + '\'' +
                        '}';
        }
}


