package edu.coder.preEntregaFacturacion.Service;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.coder.preEntregaFacturacion.Repository.DataTimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DataTimeService {

    @Autowired
    private DataTimeRepository dataTimeRepository;
    private final ObjectMapper objectMapper;

    public DataTimeService(DataTimeRepository dataTimeRepository, ObjectMapper objectMapper) {


        this.dataTimeRepository = dataTimeRepository;
        this.objectMapper = objectMapper;
    }

    public DataTimeService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public String getCurrentUtcDateTime() throws Exception {
        String response = dataTimeRepository.fetchCurrentUtcDateTime();
        JsonNode jsonNode = objectMapper.readTree(response);
        return jsonNode.get("currentDateTime").asText();
    }
}
