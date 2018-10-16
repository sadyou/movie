package com.quibotic.training.movie.movie.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Builder
@ToString
@EqualsAndHashCode
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponseDto implements Serializable {

    private String token;
    private User user;
}
