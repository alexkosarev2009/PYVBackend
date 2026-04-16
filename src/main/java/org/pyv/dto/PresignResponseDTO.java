package org.pyv.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PresignResponseDTO {
    private String uploadUrl;
    private String key;
}
