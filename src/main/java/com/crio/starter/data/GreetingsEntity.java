package com.crio.starter.data;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
@Document(collection = "memes")
public class GreetingsEntity {
    
    @Id
    private String id;
    private String name;
    private String url;
    private String caption;

    // Getters and Setters
}

// @Data
// @Document(collection = "greetings")
// @NoArgsConstructor
// public class GreetingsEntity {

//   private Long id;

//   private String extId;

//   private String message;

// }
