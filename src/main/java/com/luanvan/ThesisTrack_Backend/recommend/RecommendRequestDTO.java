package com.luanvan.ThesisTrack_Backend.recommend;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecommendRequestDTO {
    private Integer recommendId;
    private Integer teacherId;
    private Boolean status;
}
