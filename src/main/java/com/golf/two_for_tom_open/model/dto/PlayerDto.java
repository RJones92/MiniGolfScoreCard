package com.golf.two_for_tom_open.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Builder
@Getter
@Setter
@Component
public class PlayerDto {

    private String firstName;
    private String lastName;
}
