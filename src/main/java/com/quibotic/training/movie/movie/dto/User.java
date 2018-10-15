package com.quibotic.training.movie.movie.dto;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Email;

@Entity
@Builder
@ToString
@EqualsAndHashCode
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @Email
    private String username;
    private String firstName;
    private String lastName;
    private String password;
}
