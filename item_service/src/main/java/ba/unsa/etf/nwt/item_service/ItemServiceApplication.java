package ba.unsa.etf.nwt.item_service;

import ba.unsa.etf.nwt.item_service.Model.Item;
import ba.unsa.etf.nwt.item_service.Model.Specifications;
import ba.unsa.etf.nwt.item_service.Repository.ItemRepository;
import ba.unsa.etf.nwt.item_service.Repository.SpecificationsRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ItemServiceApplication {

    @Bean
    public CommandLineRunner demo(ItemRepository repository) {
        return (args) -> {
            repository.save(new Item("Stolica", "Radna stolica", 120.5, Item.StatusType.AVAILABLE, 15));
            repository.save(new Item("Sto", "Bijeli sto", 150.3, Item.StatusType.NOT_AVAILABLE, 20));
            repository.save(new Item("Kauc", "Udobni kauc",200.0, Item.StatusType.AVAILABLE, 10));
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(ItemServiceApplication.class, args);
    }

}
