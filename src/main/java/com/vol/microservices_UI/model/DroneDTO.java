package com.vol.microservices_UI.model;

import com.vol.microservices_UI.enums.DroneModel;
import com.vol.microservices_UI.enums.DroneState;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DroneDTO {
    Long id;
    @Size(max = 100)
    private String serialNumber; //(100 characters max);
    @NotNull
    private DroneModel model;// (Lightweight, Middleweight, Cruiserweight, Heavyweight);
    @Max(500)
    private int weight; //limit (500gr max);
    @Max(100)
    private int batteryCapacity;// (percentage);

    private DroneState state;// (IDLE, LOADING, LOADED, DELIVERING, DELIVERED, RETURNING).
}
