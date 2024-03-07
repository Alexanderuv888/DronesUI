package com.vol.microservices_UI.services;

import com.vol.microservices_UI.event.DroneRegisteredEvent;
import com.vol.microservices_UI.model.DroneDTO;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Service
public class DroneServiceClient {

    private final RestTemplate restTemplate;
    private final ApplicationEventPublisher publisher;


    public void registerDrone() {

    }
    public List<DroneDTO> getAllDrones() {
        String url = "http://localhost:8080/drone/get-all";
        ResponseEntity<List<DroneDTO>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                }
        );
        List<DroneDTO> drones = response.getBody();
        return drones;
    }

    public List<DroneDTO> getOneDrone() {
        HttpHeaders headers = new HttpHeaders();
        String url = "http://localhost:8080/drone/get-one";
        DroneDTO response = restTemplate.getForObject(url, DroneDTO.class);
        return Collections.singletonList(response);
    }

    public void registerNewDrone(DroneDTO drone) {
//        drone = new DroneDTO(String.valueOf(RandomGenerator.getDefault().nextInt()), DroneModel.CRUISER_WEIGHT, 1 , 100, DroneState.IDLE);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Create HttpEntity with DroneDTO as the request body and headers
        HttpEntity<DroneDTO> requestEntity = new HttpEntity<>(drone, headers);

        // Make HTTP POST request to backend API to save the DroneDTO
        ResponseEntity<Void> responseEntity = restTemplate.exchange(
                "http://localhost:8080/drone/register",
                HttpMethod.PUT,
                requestEntity,
                Void.class
        );
        if (HttpStatus.OK.value() != responseEntity.getStatusCode().value()) {
            throw new RuntimeException("Drone is not registered. Response is " + responseEntity);
        }

        publisher.publishEvent(new DroneRegisteredEvent());
    }

    public void removeDrones(Set<DroneDTO> drones) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Create HttpEntity with DroneDTO as the request body and headers
        HttpEntity<Set<DroneDTO>> requestEntity = new HttpEntity<>(drones, headers);

        // Make HTTP POST request to backend API to save the DroneDTO
        ResponseEntity<Void> responseEntity = restTemplate.exchange(
                "http://localhost:8080/drone/remove",
                HttpMethod.POST,
                requestEntity,
                Void.class
        );
        if (HttpStatus.OK.value() != responseEntity.getStatusCode().value()) {
            throw new RuntimeException("Drone is not deleted. Response is " + responseEntity);
        }
    }

}
