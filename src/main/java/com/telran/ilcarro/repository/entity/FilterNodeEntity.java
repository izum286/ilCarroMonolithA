package com.telran.ilcarro.repository.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
/**
 * сам нод который содержит в себе всю нужную информацию
 * пример:
 *
 * type: manufacture
 * value: audi
 * childs: models
 * ||                     ||
 * \/                     \/
 * type: model           type: model
 * value: a3             value: a4
 * childs: wd's          childs: wd's
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Document(collection = "filter")
public class FilterNodeEntity {
    @Id
    String type;
    String value;
    List<FilterNodeEntity> childs = Collections.synchronizedList(new LinkedList<>());

    public FilterNodeEntity(String exit) {
        this.type = exit;
    }

    public FilterNodeEntity(String type, String value) {
        this.type = type;
        this.value = value;
    }
}
