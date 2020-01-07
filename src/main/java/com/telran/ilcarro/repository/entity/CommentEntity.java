package com.telran.ilcarro.repository.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDateTime;

/**
 * @author Konkin Anton
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CommentEntity {
    private String id;
    private String post;
    @JsonFormat(pattern = "dd/MM/yyyy hh:mm")
    private LocalDateTime postDateTime;
    private String ownerEmail;
    private String serialNumber;
    private String firstName;
    private String lastName;
    private String photo;
}
