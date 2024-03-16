package com.vol.microservices_UI.dialogs;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.router.Route;
import com.vol.microservices_UI.grid.MedicationGrid;
import com.vol.microservices_UI.model.DroneDTO;
import com.vol.microservices_UI.model.MedicationDTO;
import com.vol.microservices_UI.services.DroneServiceClient;
import com.vol.microservices_UI.services.MedicationServiceClient;

@Route("load-medicine-dialog")
public class LoadMedicineDialog extends Dialog {

    public LoadMedicineDialog(DroneServiceClient droneServiceClient, MedicationServiceClient medicationServiceClient, DroneDTO drone){
        Grid<MedicationDTO> grid = new MedicationGrid();
        grid.setItems(medicationServiceClient.getAll());

        Button okButton = new Button("Load selected");
        okButton.addClickListener(event -> {
            droneServiceClient.loadMedications(drone, grid.getSelectedItems());
            close();
        });
        Button rejectButton = new Button("Reject");
        rejectButton.addClickListener(event -> close());

        add(grid);
        getFooter().add(okButton);
        getFooter().add(rejectButton);
        setWidth("50%");
    }
}
