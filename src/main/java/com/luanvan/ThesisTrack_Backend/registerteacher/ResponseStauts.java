package com.luanvan.ThesisTrack_Backend.registerteacher;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseStauts {
    private Integer status;
    private String note = new String();
}
