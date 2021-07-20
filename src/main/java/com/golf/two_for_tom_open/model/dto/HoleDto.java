package com.golf.two_for_tom_open.model.dto;

import lombok.*;
import org.springframework.stereotype.Component;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Component
public class HoleDto {

    private int id;
    private int par;
    private int holeNumber;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HoleDto)) return false;

        HoleDto holeDto = (HoleDto) o;

        if (this.id != holeDto.id) return false;
        if (this.par != holeDto.par) return false;
        return this.holeNumber == holeDto.holeNumber;
    }

    @Override
    public int hashCode() {
        int result = this.id;
        result = 31 * result + this.par;
        result = 31 * result + this.holeNumber;
        return result;
    }
}
