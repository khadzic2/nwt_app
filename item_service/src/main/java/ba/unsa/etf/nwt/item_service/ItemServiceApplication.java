package ba.unsa.etf.nwt.item_service;

import ba.unsa.etf.nwt.item_service.Model.Image;
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
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

@SpringBootApplication
public class ItemServiceApplication {
    @Autowired
    private ItemCategoryRepository categoryRepository;
    SpecificationsRepository specificationsRepository;

    @Bean
    public CommandLineRunner demo(ItemRepository repository, SpecificationsRepository specificationsRepository, ItemCategoryRepository categoryRepository) {
        return (args) -> {
            repository.save(new Item("Stolica", "Radna stolica", 120.5, 15, null, false, null, null));
            repository.save(new Item("Sto", "Bijeli sto", 123.5, 15, null, false, null, null));
            repository.save(new Item("Kauc", "Udobni kauc", 155.5, 12, null, false, null, null));



            //NEW ADD:


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


            Specifications specification = new Specifications();
            specification.setColors("white, black");
            specification.setHeight("1m");
            specification.setWidth("1m");
            specification.setDepth("1m");
            specification.setMaterial("wood");

            specificationsRepository.save(specification);


            Item item = new Item();
            item.setName("Chair");
            item.setDescription("Home furniture modern high back dark Velvet Fabric dinning chair with metal legs for dining room");
            item.setPrice(12.5);
            item.setManufacturingdays(2);
            item.setSpecifications(specification);
            item.setCompared(false);
            item.setImage(null);
            item.setItemCategory(childrenRoomCategory);

            repository.save(item);

            //item2:

            Specifications specification1 = new Specifications();
            specification1.setColors("white, pink, black");
            specification1.setHeight("100cm");
            specification1.setWidth("100cm");
            specification1.setDepth("100cm");
            specification1.setMaterial("wood and plastic");

            specificationsRepository.save(specification1);


            Item item1 = new Item();
            item1.setName("Table");
            item1.setDescription("Long Round Dining Table With Wooden Top Diameter 35cm Smart Wooden Dining Table In Brown Color For Working Cafe Home");
            item1.setPrice(120.5);
            item1.setManufacturingdays(2);
            item1.setSpecifications(specification1);
            item1.setCompared(false);
            item1.setImage(null);
            item1.setItemCategory(livingRoomCategory);

            repository.save(item1);

            //item3

            Specifications specification2 = new Specifications();
            specification2.setColors("white, black");
            specification2.setHeight("1m");
            specification2.setWidth("1m");
            specification2.setDepth("1m");
            specification2.setMaterial("wood");

            specificationsRepository.save(specification2);


            Item item2 = new Item();
            item2.setName("Bed");
            item2.setDescription("Modern Simple Style Classic Double Wood Bed Frame King Size Wooden Bed");
            item2.setPrice(155.5);
            item2.setManufacturingdays(5);
            item2.setSpecifications(specification2);
            item2.setCompared(false);
            item2.setImage(null);
            item2.setItemCategory(bedroomCategory);

            repository.save(item2);

        };
    }


    public static void main(String[] args) {
        SpringApplication.run(ItemServiceApplication.class, args);
    }


}

