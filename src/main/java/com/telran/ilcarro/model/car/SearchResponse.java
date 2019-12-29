package com.telran.ilcarro.model.car;

import com.telran.ilcarro.model.car.CarStatDto;
import com.telran.ilcarro.model.car.FullCarDTOResponse;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
/**
 * @author izum286
 */
public class SearchResponse {
    private int currentPage;
    private int itemsOnPage;
    private int itemsTotal;
    List<FullCarDTOResponse> cars;
    CarStatDto statisticDtoForCurrentCar;
    String megaFilter;
}
