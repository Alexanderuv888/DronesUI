package com.vol.microservices_UI.view;

import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vol.microservices_UI.dialogs.LoadMedicineDialog;
import com.vol.microservices_UI.dialogs.MessageDialog;
import com.vol.microservices_UI.dialogs.RegisterDroneDialog;
import com.vol.microservices_UI.grid.DroneGrid;
import com.vol.microservices_UI.model.DroneDTO;
import com.vol.microservices_UI.services.DroneServiceClient;
import com.vol.microservices_UI.services.MedicationServiceClient;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Route("") // map view to the root
@RequiredArgsConstructor
class MainView extends VerticalLayout {

    private final RestTemplate restTemplate;
    private final DroneServiceClient droneServiceClient;
    private final MedicationServiceClient medicationServiceClient;

    private void fetchData(Grid<DroneDTO> grid) {
        List<DroneDTO> drones = droneServiceClient.getAllDrones();
        grid.setItems(drones);
    }

    private void deleteData(Grid<DroneDTO> grid) {
        droneServiceClient.removeDrones(grid.getSelectedItems());
    }

    @PostConstruct
    public void initView() {

        Grid<DroneDTO> grid = genearteGrid();
        Button registerButton = getRegisterButton(grid);

        Button fetchButton = new Button("Fetch data");
        fetchButton.addClickListener(event -> fetchData(grid));

        Button deleteButton = new Button("Delete selected");
        deleteButton.addClickListener(event -> {
            deleteData(grid);
            fetchData(grid);
        });

        Button loadButton = createLoadButton(grid);


        add(new H1("Drones"));
        HorizontalLayout hl = new HorizontalLayout(fetchButton, registerButton, deleteButton, loadButton);
        hl.setWidthFull();
        hl.setAlignItems(Alignment.START);
        add(hl);
        add(grid);
    }

    private Button getRegisterButton(Grid<DroneDTO> grid) {
        Button registerButton = new Button("Register new drone");

        registerButton.addClickListener(event -> {
            Dialog dlg = new RegisterDroneDialog(droneServiceClient);
            dlg.addOpenedChangeListener(
                    (ComponentEventListener<Dialog.OpenedChangeEvent>) openedChangeEvent -> {
                        if (!openedChangeEvent.isOpened()) {
                            fetchData(grid);
                        }
                    });
            dlg.open();
        });
        return registerButton;
    }

    private Button createLoadButton(Grid<DroneDTO> grid) {
        Button loadButton = new Button("Load medicine to drone");
        loadButton.addClickListener(event -> {
            if (grid.getSelectedItems().size() == 1) {
                Dialog dlg = new LoadMedicineDialog(droneServiceClient,
                        medicationServiceClient,
                        grid.getSelectedItems().iterator().next());
                dlg.open();
            } else {
                new MessageDialog("Chose one drone for loading medicine.").open();
            }
        });
        return loadButton;
    }

    private Grid<DroneDTO> genearteGrid() {
        Grid<DroneDTO> grid = new DroneGrid();
        grid.setItems(droneServiceClient.getAllDrones());
        return grid;
    }


}
