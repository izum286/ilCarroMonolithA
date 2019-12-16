package com.telran.ilcarro.service.converters;

import com.telran.ilcarro.model.web.FeedbackDTO;
import com.telran.ilcarro.repository.entity.FeedbackEntity;

public class FeedbackDtoEntityConverter {

    public static FeedbackDTO map(FeedbackEntity entity) {
        return FeedbackDTO.builder()
                .id(entity.getId())
                .message(entity.getMessage())
                .owner(entity.getOwner())
                .date(entity.getDate())
                .build();
    }

    public static FeedbackEntity map(FeedbackDTO dto) {
        return FeedbackEntity.builder()
                .id(dto.getId())
                .message(dto.getMessage())
                .owner(dto.getMessage())
                .date(dto.getDate())
                .build();
    }
}
