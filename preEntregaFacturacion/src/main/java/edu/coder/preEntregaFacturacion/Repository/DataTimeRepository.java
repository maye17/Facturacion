package edu.coder.preEntregaFacturacion.Repository;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class DataTimeRepository {

    private static final String URL = "http://worldclockapi.com/api/json/utc/now";

    private final RestTemplate restTemplate;

    public DataTimeRepository(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String fetchCurrentUtcDateTime() {
        return restTemplate.getForObject(URL, String.class);
    }
}
