package be.kdg.team9.hivebuddy;

//import be.kdg.team9.hivebuddy.data.ArduinoDataReceiver;
/*
TODO: remove 4 commented lines below
 */

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

import be.kdg.team9.hivebuddy.data.ArduinoDataReceiver;
import com.fazecast.jSerialComm.SerialPort;
import java.util.Timer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import be.kdg.team9.hivebuddy.data.SensorData;


@SpringBootApplication
public class HiveBuddyApplication {

    public static void main(String[] args) {
        SpringApplication.run(HiveBuddyApplication.class, args);
    }

}
