package com.telran.ilcarro.model.car;

import com.telran.ilcarro.model.car.CarStatDto;
import com.telran.ilcarro.model.car.FullCarDTOResponse;
import io.swagger.annotations.ApiModel;
import lombok.*;
import org.springframework.data.domain.Page;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
/**
 * @author izum286
 */
@ApiModel(value = "SearchResponse")
public class SearchResponse {
    private int currentPage;
    private int itemsOnPage;
    private long itemsTotal;
    List<FullCarDTOResponse> cars;
    CarStatDto statisticDtoForCurrentCar;
    String megaFilter;
}
