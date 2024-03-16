package com.vol.microservices_UI.grid;

import com.vaadin.flow.component.grid.Grid;
import com.vol.microservices_UI.model.MedicationDTO;


public class MedicationGrid extends Grid<MedicationDTO> {

    public MedicationGrid() {
        addColumn(MedicationDTO::getName).setHeader("Name");
        addColumn(MedicationDTO::getCode).setHeader("Code");
        addColumn(MedicationDTO::getWeight).setHeader("Weight");
        setSelectionMode(Grid.SelectionMode.MULTI);
    }
}
