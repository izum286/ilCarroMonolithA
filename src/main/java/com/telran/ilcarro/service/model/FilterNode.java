package com.telran.ilcarro.service.model;

import lombok.*;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
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
public class FilterNode {
    String type;
    String value;
    List<FilterNode> childs = Collections.synchronizedList(new LinkedList<>());

    public FilterNode(String exit) {
        this.type = exit;
    }
}
