package edu.coder.preEntregaFacturacion.Controller;
import edu.coder.preEntregaFacturacion.Service.DataTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/datatime")
public class DataTimeController {


    @Autowired
    private  DataTimeService dataTimeService;

    public DataTimeController(DataTimeService dataTimeService) {
        this.dataTimeService = dataTimeService;
    }

    @GetMapping("/current")
    public ResponseEntity<String> getCurrentDateTime() {
        try {
            String dateTime = dataTimeService.getCurrentUtcDateTime();
            return ResponseEntity.ok(dateTime);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error fetching date time: " + e.getMessage());
        }
    }

}
