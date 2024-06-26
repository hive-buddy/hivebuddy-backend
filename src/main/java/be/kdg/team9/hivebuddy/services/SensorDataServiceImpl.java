package be.kdg.team9.hivebuddy.services;

import be.kdg.team9.hivebuddy.controllers.WebSocketTextController;
import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.hibernate.query.spi.Limit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import be.kdg.team9.hivebuddy.data.SensorData;
import be.kdg.team9.hivebuddy.repository.SensorDataRepository;

@Service
public class SensorDataServiceImpl implements SensorDataService {
    private final SensorDataRepository sensorDataRepository;
    private final WebSocketTextController webSocketTextController;

    @Autowired
    public SensorDataServiceImpl(SensorDataRepository sensorDataRepository, WebSocketTextController webSocketTextController) {
        this.sensorDataRepository = sensorDataRepository;
        this.webSocketTextController = webSocketTextController;
    }

    public List<SensorData> getSensorDatas() {
        return sensorDataRepository.findAll();
    }

//    public List<SensorData> getSensorDatasLatestById(Long id) {
//        return sensorDataRepository.findAllLatestByHiveId(id, LocalDateTime.now());
//    }

    public List<SensorData> getOneSensorDataLatest(Long hiveId, Long sensorTypeId, Long rowsAmount) {
        if (rowsAmount < 11){
            return sensorDataRepository.getFirst10ByHiveIdAndSensorTypeIdOrderByTimestampDesc(hiveId, sensorTypeId);
        } else if (rowsAmount < 51){
            return sensorDataRepository.getFirst50ByHiveIdAndSensorTypeIdOrderByTimestampDesc(hiveId, sensorTypeId);
        } else {
            return sensorDataRepository.getFirst100ByHiveIdAndSensorTypeIdOrderByTimestampDesc(hiveId, sensorTypeId);
        }
    }

    public List<SensorData> getAllHiveIds() {
        return sensorDataRepository.findAllHiveIds();
    }

    public boolean checkHiveIdExists(Long id) {
        return !sensorDataRepository.isHiveIdExists(id).isEmpty();
    }


    public void addNewData(SensorData sensorData) {
        sensorDataRepository.save(sensorData);
//        webSocketTextController.sendMessage(String.valueOf(sensorData.getValue()).lines().toList());
        System.out.println(sensorData);
    }

    public void showRealtime(SensorData sensorData) {
//        sensorDataList.get()
        webSocketTextController.sendMessage(sensorData);
    }


    //TODO: remove this
    public void createCSVForSensorData(String sensorID, LocalDateTime startDate) {
        List<Object[]> rawData = sensorDataRepository.getDataFrom(sensorID, startDate);

        // Specify the CSV file path
        String csvFilePath = "root/data_processing/csv_files/sensor_data.csv";

        try (CSVWriter writer = new CSVWriter(new FileWriter(new File(csvFilePath)))) {

            // Writing CSV header
            writer.writeNext(new String[]{"Value", "Timestamp"});

            // Writing data rows
            for (Object[] row : rawData) {
                String value = String.valueOf(row[0]); // Assuming value is a String, check with Ross!!!
                String timestamp = String.valueOf(row[1]); // Assuming timestamp is a String, check with Ross!!!

                writer.writeNext(new String[]{value, timestamp});
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}