package ba.unsa.etf.nwt.item_service;

import ba.unsa.etf.nwt.item_service.Model.Image;
import ba.unsa.etf.nwt.item_service.Model.Item;
import ba.unsa.etf.nwt.item_service.Model.ItemCategory;
import ba.unsa.etf.nwt.item_service.Repository.ImageRepository;
import ba.unsa.etf.nwt.item_service.Repository.ItemCategoryRepository;
import ba.unsa.etf.nwt.item_service.Repository.ItemRepository;
import ba.unsa.etf.nwt.item_service.Service.ImageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.mock.web.MockMultipartFile;

import java.io.File;
import java.io.FileInputStream;

@SpringBootApplication
public class ItemServiceApplication {

    @Bean
    public CommandLineRunner demo(ItemRepository repository, ItemCategoryRepository itemCategoryRepository, ImageService imageService) {
        return (args) -> {
            repository.save(new Item("Stolica", "Radna stolica", 120.5, 15, null, null, null));
            repository.save(new Item("Sto", "Bijeli sto", 123.5, 15, null, null, null));
            repository.save(new Item("Kauc", "Udobni kauc", 155.5, 12, null, null, null));

            ItemCategory itemCategory1 = new ItemCategory("Dnevna soba","sto");
            ItemCategory itemCategory2 = new ItemCategory("Dnevna soba","stolica");
            ItemCategory itemCategory3 = new ItemCategory("Dnevna soba","kauč");
            ItemCategory itemCategory4 = new ItemCategory("Spavaca soba","ormar");
            ItemCategory itemCategory5 = new ItemCategory("Spavaca soba","krevet");

            itemCategoryRepository.save(itemCategory1);
            itemCategoryRepository.save(itemCategory2);
            itemCategoryRepository.save(itemCategory3);
            itemCategoryRepository.save(itemCategory4);
            itemCategoryRepository.save(itemCategory5);

            File file1 = new File("item_service/src/main/java/ba/unsa/etf/nwt/item_service/images/sto-bert-bijeli.jpg");
            FileInputStream fis1 = new FileInputStream(file1);
            MockMultipartFile multipar1 = new MockMultipartFile("file", file1.getName(), "image/jpeg", fis1);

            imageService.uploadImage(multipar1);
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(ItemServiceApplication.class, args);
    }

}
