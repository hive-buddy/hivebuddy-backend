package be.kdg.team9.hivebuddy.repository;

import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class TemperatureRepository {
    public List<Double> readTemperature() {
        return SensorsData.getNumbers();
    }
}
