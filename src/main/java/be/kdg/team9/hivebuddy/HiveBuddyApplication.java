package be.kdg.team9.hivebuddy;

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

        long timeStart = System.currentTimeMillis();
        float humidity = 0;
        float temperature = 0;
        var sp = SerialPort.getCommPort("/dev/cu.usbmodem112301");
        sp.setComPortParameters(9600, Byte.SIZE, SerialPort.ONE_STOP_BIT, SerialPort.NO_PARITY);
        sp.setComPortTimeouts(SerialPort.TIMEOUT_WRITE_BLOCKING, 0, 0);
        var hasOpened = sp.openPort();

        if (!hasOpened) {
            throw new IllegalStateException("Failed to open serial port");
        }
        Runtime.getRuntime().addShutdownHook(new Thread(sp::closePort));
        var timer = new Timer();
        var timedSchedule = new ArduinoDataReceiver();

        sp.addDataListener(timedSchedule);
        System.out.println("Listen: " + timedSchedule.getListeningEvents());
    }

}
