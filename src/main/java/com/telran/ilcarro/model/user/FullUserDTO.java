package com.telran.ilcarro.model.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.telran.ilcarro.model.car.BookedPeriodDto;
import com.telran.ilcarro.model.car.FullCarDTOResponse;
import com.telran.ilcarro.model.comment.FullCommentDTO;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * FullUserDTO
 * need to do:
 * Update fields
 * private String ownCars;
 * private String bookedCars;
 * private String history;
 * to correct objects
 *
 * @author Konkin Anton
 * 19.12.2019
 */

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FullUserDTO {
    private String firstName;
    private String lastName;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDateTime registrationDate;
    private List<FullCommentDTO> comments;
    private String photo;
    private List<FullCarDTOResponse> ownCars;
    private List<BookedPeriodDto> bookedCars;
    private List<BookedPeriodDto> history;
}
