package com.vol.microservices_UI.event;

import com.vaadin.flow.component.grid.Grid;
import com.vol.microservices_UI.model.DroneDTO;
import lombok.Data;

@Data
public class DroneRegisteredEvent {
    Grid<DroneDTO> grid;
    Long droneId;
}
