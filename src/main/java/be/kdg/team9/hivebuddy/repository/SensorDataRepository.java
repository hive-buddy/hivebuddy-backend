package be.kdg.team9.hivebuddy.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import be.kdg.team9.hivebuddy.data.SensorData;

@Repository
public interface SensorDataRepository extends JpaRepository<SensorData,Long> {
    Optional<SensorData> findAllDataByHiveId(Long hive_id);
}
