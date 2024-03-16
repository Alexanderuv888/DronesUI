package com.vol.microservices_UI.dialogs;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;

public class MessageDialog extends Dialog {

    public MessageDialog(String message) {
        setHeaderTitle(message);
        Button okButton = new Button("OK");
        okButton.addClickListener(event -> close());
        add(okButton);
    }

}
