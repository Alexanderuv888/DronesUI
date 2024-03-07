package com.vol.microservices_UI.listeners;

import com.vol.microservices_UI.event.DroneRegisteredEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class registerDroneListeners {
    @EventListener
    public void onRegisterDrone(DroneRegisteredEvent event) {
//        event.getGrid().
        System.out.println("NEW DRONE REGISTERED!!!! YYYYAAAHOOOO!!!!! Drone Id " + event.getDroneId());
    }
}
