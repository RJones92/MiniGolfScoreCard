package com.golf.two_for_tom_open.model.dto;

import lombok.*;
import org.springframework.stereotype.Component;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Component
public class PlayerDto {

    private String firstName;
    private String lastName;
}
