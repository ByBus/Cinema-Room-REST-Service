package cinema;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Bean
    public Hall getHall() {
        return new Hall(9, 9);
    }

    @Bean
    public TicketManager getTicketManager() {
        return new TicketManager();
    }
}
