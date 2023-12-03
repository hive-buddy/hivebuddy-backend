package be.kdg.team9.hivebuddy.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.kdg.team9.hivebuddy.data.SensorData;
import be.kdg.team9.hivebuddy.repository.SensorDataRepository;

@Service
public class SensorDataServiceImpl implements SensorDataService {
    private final SensorDataRepository sensorDataRepository;

    @Autowired
    public SensorDataServiceImpl(SensorDataRepository sensorDataRepository) {
        this.sensorDataRepository = sensorDataRepository;
    }

    public List<SensorData> getSensorDatas() {
        return sensorDataRepository.findAll();
    }
    public List<SensorData> getSensorDatasLatestById(Long id) {
        return sensorDataRepository.findAllLatestByHiveId(id);
    }

    public void addNewData(SensorData sensorData) {
        sensorData.setTimestamp(LocalDateTime.now());
        sensorDataRepository.save(sensorData);
        System.out.println(sensorData);
    }

    // public void addNewData(
    //         Long sensorTypeId,
    //         Long hiveId,
    //         int value) {
    //     SensorData sensorData;
    //     sensorData.setId(hiveId);
    //     // sensorDataRepository.save(sensorData);
    //     System.out.println(sensorData);
    // }
}