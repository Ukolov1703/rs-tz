package org.rs.rstz.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.rs.rstz.entity.HistoryEntity;

import java.util.List;

@AllArgsConstructor
@Getter
public class HistoryDto {

    private final List<HistoryEntity> history;
}
