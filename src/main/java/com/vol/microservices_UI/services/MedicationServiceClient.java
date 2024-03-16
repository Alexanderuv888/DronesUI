package com.vol.microservices_UI.services;

import com.vol.microservices_UI.model.DroneDTO;
import com.vol.microservices_UI.model.MedicationDTO;
import lombok.AllArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class MedicationServiceClient {
    private final RestTemplate restTemplate;

    public List<MedicationDTO> getAll() {
        String url = "http://localhost:8080/medication/get-all";
        ResponseEntity<List<MedicationDTO>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                }
        );
        return response.getBody();
    }

    public void loadMedications(DroneDTO drone, Set<MedicationDTO> medications) {

    }

}
