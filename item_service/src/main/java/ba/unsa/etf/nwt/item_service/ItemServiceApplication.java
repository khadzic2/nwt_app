package ba.unsa.etf.nwt.item_service;

import ba.unsa.etf.nwt.item_service.Model.Item;
import ba.unsa.etf.nwt.item_service.Model.ItemCategory;
import ba.unsa.etf.nwt.item_service.Model.Specifications;
import ba.unsa.etf.nwt.item_service.Repository.ItemCategoryRepository;
import ba.unsa.etf.nwt.item_service.Repository.ItemRepository;
import ba.unsa.etf.nwt.item_service.Repository.SpecificationsRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ItemServiceApplication {
    @Autowired
    private ItemCategoryRepository categoryRepository;

    @Bean
    public CommandLineRunner demo(ItemRepository repository) {
        return (args) -> {
            repository.save(new Item("Stolica", "Radna stolica", 120.5, 15, null, false, null, null));
            repository.save(new Item("Sto", "Bijeli sto", 123.5, 15, null, false, null, null));
            repository.save(new Item("Kauc", "Udobni kauc", 155.5, 12, null, false, null, null));

            ItemCategory livingRoomCategory = new ItemCategory("Living Room");
            ItemCategory bedroomCategory = new ItemCategory("Bedroom");
            ItemCategory childrenRoomCategory = new ItemCategory("Children's Room");

            // Create the subcategories for the living room category
            ItemCategory tableCategory = new ItemCategory("Table");
            ItemCategory sofaCategory = new ItemCategory("Sofa");
            ItemCategory shelfCategory = new ItemCategory("Shelf");

            // Create the subcategories for the bedroom category
            ItemCategory bedCategory = new ItemCategory("Bed");
            ItemCategory wardrobeCategory = new ItemCategory("Wardrobe");

            // Create the subcategories for the children's room category
            ItemCategory chairCategory = new ItemCategory("Chair");

            // Establish the hierarchical structure by adding subcategories to their parent categories
            livingRoomCategory.addSubCategory(tableCategory);
            livingRoomCategory.addSubCategory(sofaCategory);
            livingRoomCategory.addSubCategory(shelfCategory);

            bedroomCategory.addSubCategory(bedCategory);
            bedroomCategory.addSubCategory(wardrobeCategory);

            childrenRoomCategory.addSubCategory(tableCategory);
            childrenRoomCategory.addSubCategory(chairCategory);
            childrenRoomCategory.addSubCategory(bedCategory);
            childrenRoomCategory.addSubCategory(wardrobeCategory);


            categoryRepository.save(livingRoomCategory);
            categoryRepository.save(bedroomCategory);
            categoryRepository.save(childrenRoomCategory);
        };
    }


    public static void main(String[] args) {
        SpringApplication.run(ItemServiceApplication.class, args);
    }


}

