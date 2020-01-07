package com.telran.ilcarro.model.car;

import lombok.*;

/**
 * @author vitalii_adler
 * @author izum286
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class FeatureDto {
    //TODO - may be more logically to use ENUM?
    String feature;
}
