package com.skillbox.users.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;



@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewsDto implements Serializable {
    private static final long serialVersionUID = -6721354416216073443L;
    private UUID id;
    private UserDto userCreator;
    private String subject;
    private String text;
    private LocalDateTime createDate;
}
