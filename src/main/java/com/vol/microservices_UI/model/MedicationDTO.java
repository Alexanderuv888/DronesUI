package com.vol.microservices_UI.model;

import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class MedicationDTO {
    private Long id;
//    private Long droneId;
    @Pattern(regexp = "[0-9a-zA-Z_-]+")
    private String name;// (allowed only letters, numbers, ‘-‘, ‘_’);
    private int weight;
    @Pattern(regexp = "[0-9A-Z_]+")
    private String code;// (allowed only upper case letters, underscore and numbers);
    private byte[] image;// (picture of the medication case).
}
