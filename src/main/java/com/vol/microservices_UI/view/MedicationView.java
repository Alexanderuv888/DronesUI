package com.vol.microservices_UI.view;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vol.microservices_UI.model.MedicationDTO;
import com.vol.microservices_UI.services.MedicationServiceClient;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Route("/medications")
@RequiredArgsConstructor
public class MedicationView extends VerticalLayout {

    private final MedicationServiceClient medicationServiceClient;

    @PostConstruct
    private void initView() {
        Grid<MedicationDTO> grid = genearteGrid();
        add(grid);
    }

    private Grid<MedicationDTO> genearteGrid() {
        Grid<MedicationDTO> grid = new Grid<>(MedicationDTO.class, false);
        grid.addColumn(MedicationDTO::getName).setHeader("Name");
        grid.addColumn(MedicationDTO::getCode).setHeader("Code");
        grid.addColumn(MedicationDTO::getWeight).setHeader("Weight");
        grid.setItems(medicationServiceClient.getAll());
        grid.setSelectionMode(Grid.SelectionMode.MULTI);
        return grid;
    }
}
