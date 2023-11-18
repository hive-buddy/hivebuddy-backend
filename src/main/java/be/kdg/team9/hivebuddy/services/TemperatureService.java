package be.kdg.team9.hivebuddy.services;

import be.kdg.team9.hivebuddy.repository.TemperatureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TemperatureService {
    private final TemperatureRepository temperatureRepository;
    @Autowired
    public TemperatureService(TemperatureRepository temperatureRepository) {
        this.temperatureRepository = temperatureRepository;
    }
    public List<Double> getNumbers() {
        return temperatureRepository.readTemperature();
    }
}
