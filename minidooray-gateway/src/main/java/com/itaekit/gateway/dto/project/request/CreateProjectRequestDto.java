package com.itaekit.gateway.dto.project.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateProjectRequestDto {
    private String name;
    private long adminId;
}
