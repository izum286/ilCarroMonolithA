package com.telran.ilcarro.model.car;

import io.swagger.annotations.ApiModel;
import lombok.*;

import java.util.List;

/**
 * @author izum286
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ApiModel(value = "SearchResponse")
public class SearchResponse {
    private int currentPage;
    private int itemsOnPage;
    private long itemsTotal;
    List<FullCarDTOResponse> cars;
    CarStatDto statisticDtoForCurrentCar;
    String megaFilter;
}
