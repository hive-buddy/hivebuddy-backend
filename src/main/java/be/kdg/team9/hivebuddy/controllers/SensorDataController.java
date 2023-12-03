package be.kdg.team9.hivebuddy.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import be.kdg.team9.hivebuddy.data.SensorData;
import be.kdg.team9.hivebuddy.services.SensorDataServiceImpl;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(path = "api/v1/data")
public class SensorDataController {
    private final SensorDataServiceImpl sensorDataServiceImpl;

    @Autowired
    public SensorDataController(SensorDataServiceImpl sensorDataServiceImpl) {
        this.sensorDataServiceImpl = sensorDataServiceImpl;
    }

    @GetMapping
    public List<SensorData> getSensorData() {
        return sensorDataServiceImpl.getSensorDatas();
    }

    @GetMapping("/latest")
    public List<SensorData> getSensorDataLatestById(@RequestBody int id){
        return sensorDataServiceImpl.getSensorDatasLatestById(id);
    }

    @PostMapping
    public void registerNewSensorData(@RequestBody SensorData sensorData) {
        sensorDataServiceImpl.addNewData(sensorData);
    }
}
