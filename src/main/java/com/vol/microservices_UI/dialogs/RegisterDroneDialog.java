package com.vol.microservices_UI.dialogs;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vol.microservices_UI.enums.DroneModel;
import com.vol.microservices_UI.model.DroneDTO;
import com.vol.microservices_UI.services.DroneServiceClient;

@Route("dialog-basic")
public class RegisterDroneDialog extends Dialog {
    public static final String REG_DRONE_DIALOG_TITLE = "Register new dron";

    private TextField serialNumberField;
    private ComboBox<DroneModel> modelField;

    private final DroneServiceClient droneServiceClient;

    public RegisterDroneDialog(DroneServiceClient droneServiceClient) {
        this.droneServiceClient = droneServiceClient;
        // tag::snippet[]

        setHeaderTitle(REG_DRONE_DIALOG_TITLE);

        VerticalLayout dialogLayout = createDialogLayout();
        add(dialogLayout);

        Button saveButton = createSaveButton();
        Button cancelButton = new Button("Cancel", e -> this.close());
        getFooter().add(cancelButton);
        getFooter().add(saveButton);
    }

    private VerticalLayout createDialogLayout() {

        serialNumberField = new TextField("Serial number");
        modelField = new ComboBox<>("Model");
        modelField.setItems(DroneModel.values());

        VerticalLayout dialogLayout = new VerticalLayout(serialNumberField,
                modelField);
        dialogLayout.setPadding(false);
        dialogLayout.setSpacing(false);
        dialogLayout.setAlignItems(FlexComponent.Alignment.STRETCH);
        dialogLayout.getStyle().set("width", "18rem").set("max-width", "100%");

        return dialogLayout;
    }

    private Button createSaveButton() {
        Button saveButton = new Button("Register", e -> save());
        saveButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        return saveButton;
    }

    private void save() {
        DroneDTO drone = new DroneDTO();
        drone.setSerialNumber(serialNumberField.getValue());
        drone.setModel(modelField.getValue());
        droneServiceClient.registerNewDrone(drone);
        close();
    }
}
