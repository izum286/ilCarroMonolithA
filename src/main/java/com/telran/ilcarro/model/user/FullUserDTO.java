package com.telran.ilcarro.model.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.telran.ilcarro.model.car.BookedCarDto;
import com.telran.ilcarro.model.car.BookedPeriodDto;
import com.telran.ilcarro.model.car.FullCarDTOResponse;
import com.telran.ilcarro.model.comment.FullCommentDTO;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * FullUserDTO
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
    private String first_name;
    private String second_name;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime registration_date;
    private List<FullCommentDTO> comments;
    private String photo;
    private List<FullCarDTOResponse> own_cars;
    private List<BookedCarDto> booked_car;
    private List<BookedCarDto> history;
}
