package com.telran.ilcarro.service.converters;

import com.telran.ilcarro.model.web.feedback.AddFeedbackDto;
import com.telran.ilcarro.model.web.feedback.FeedbackDTO;
import com.telran.ilcarro.repository.entity.FeedbackEntity;

import java.time.LocalDateTime;
import java.util.UUID;

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
    public static FeedbackEntity map(AddFeedbackDto dto) {
        return FeedbackEntity.builder()
                .id(UUID.randomUUID().toString())
                .message(dto.getMessage())
                .owner(dto.getOwner())
                .date(LocalDateTime.now())
                .build();
    }
}
