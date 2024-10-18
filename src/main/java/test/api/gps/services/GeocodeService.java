package test.api.gps.services;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class GeocodeService {
    private final RestTemplate restTemplate = new RestTemplate();

    public Map<String, Double> getCoordinates(String cep) {
        String url = "https://nominatim.openstreetmap.org/search?format=json&q=" + cep;

        Map<String, Object>[] response = restTemplate.getForObject(url, Map[].class);

        if (response != null && response.length > 0) {
            Map<String, Double> location = new HashMap<>();
            location.put("latitude", Double.parseDouble((String) response[0].get("lat")));
            location.put("longitude", Double.parseDouble((String) response[0].get("lon")));

            return location;
        } else {
            throw new RuntimeException("CEP não encontrado ou inválido.");
        }
    }
}
