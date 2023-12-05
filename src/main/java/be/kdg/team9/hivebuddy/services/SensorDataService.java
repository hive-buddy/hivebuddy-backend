package be.kdg.team9.hivebuddy.services;

import be.kdg.team9.hivebuddy.data.SensorData;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface SensorDataService {
    public List<SensorData> getSensorDatas();
    public void addNewData(SensorData sensorData);
    public void showRealtime(List<SensorData> sensorDataList);
//    public List<Object[]> getDataFrom(String sensorID, Date startDate);
    public void createCSVForSensorData(String sensorID, LocalDateTime startDate);
}
