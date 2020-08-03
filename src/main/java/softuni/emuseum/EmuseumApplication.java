package softuni.emuseum;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling // без тази анотация scheduled методите неработят
public class EmuseumApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmuseumApplication.class, args);
    }

}
