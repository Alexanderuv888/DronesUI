package com.vol.microservices_UI.grid;

import com.vaadin.flow.component.grid.Grid;
import com.vol.microservices_UI.model.DroneDTO;

public class DroneGrid extends Grid<DroneDTO> {

    public DroneGrid() {
        addColumn(DroneDTO::getSerialNumber).setHeader("Serial number");
        addColumn(DroneDTO::getModel).setHeader("Model");
        addColumn(DroneDTO::getState).setHeader("State");
        addColumn(DroneDTO::getBatteryCapacity).setHeader("Battery");
        addColumn(DroneDTO::getWeight).setHeader("Weight");
        setSelectionMode(SelectionMode.MULTI);
    }

}
