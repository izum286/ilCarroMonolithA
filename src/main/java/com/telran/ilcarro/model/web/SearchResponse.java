package com.telran.ilcarro.model.web;

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
    List<FullCarDTO> cars;
    CarStatDto statisticDtoForCurrentCar;
    String megaFilter;
}
