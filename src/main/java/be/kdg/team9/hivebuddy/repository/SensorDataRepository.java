package be.kdg.team9.hivebuddy.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import be.kdg.team9.hivebuddy.data.SensorData;

@Repository
public interface SensorDataRepository extends JpaRepository<SensorData,Long> {
    List<SensorData> findAllLatestByHiveId(Long hive_id);
}
