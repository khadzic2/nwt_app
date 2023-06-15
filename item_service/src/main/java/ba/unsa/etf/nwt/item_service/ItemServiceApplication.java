package ba.unsa.etf.nwt.item_service;

import ba.unsa.etf.nwt.item_service.Model.Image;
import ba.unsa.etf.nwt.item_service.Model.Item;
import ba.unsa.etf.nwt.item_service.Model.ItemCategory;
import ba.unsa.etf.nwt.item_service.Model.Specifications;
import ba.unsa.etf.nwt.item_service.Repository.ItemCategoryRepository;
import ba.unsa.etf.nwt.item_service.Repository.ItemRepository;
import ba.unsa.etf.nwt.item_service.Repository.SpecificationsRepository;
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

//    @Bean
//    public CommandLineRunner demo(ItemRepository repository, ItemCategoryRepository itemCategoryRepository, SpecificationsRepository specificationsRepository, ImageService imageService) {
//        return (args) -> {
//            ItemCategory itemCategory1 = new ItemCategory("Dnevna soba","sto");
//            ItemCategory itemCategory2 = new ItemCategory("Dnevna soba","stolica");
//            ItemCategory itemCategory3 = new ItemCategory("Dnevna soba","kauč");
//            ItemCategory itemCategory4 = new ItemCategory("Spavaca soba","ormar");
//            ItemCategory itemCategory5 = new ItemCategory("Spavaca soba","krevet");
//
//            itemCategoryRepository.save(itemCategory1);
//            itemCategoryRepository.save(itemCategory2);
//            itemCategoryRepository.save(itemCategory3);
//            itemCategoryRepository.save(itemCategory4);
//            itemCategoryRepository.save(itemCategory5);
//
//            Specifications specifications1 = new Specifications("Bijela,Crna","45cm","60cm","101cm","materijal1,materijal2");
//            Specifications specifications2 = new Specifications("Zuta,Smedja,Crvena","45cm","45cm","45cm","materijal3,materijal4");
//            Specifications specifications3 = new Specifications("Bijeli,Crni,Zeleni","45cm","60cm","101cm","materijal1,materijal2");
//            Specifications specifications4 = new Specifications("Bijeli,Crni","45cm","60cm","101cm","materijal5,materijal6");
//            Specifications specifications5 = new Specifications("Sivi,Crni","45cm","60cm","101cm","materijal1,materijal2");
//
//            specificationsRepository.save(specifications1);
//            specificationsRepository.save(specifications2);
//            specificationsRepository.save(specifications3);
//            specificationsRepository.save(specifications4);
//            specificationsRepository.save(specifications5);
//
//
//            File file1 = new File("item_service/src/main/java/ba/unsa/etf/nwt/item_service/images/sto-bert-bijeli.jpg");
//            FileInputStream fis1 = new FileInputStream(file1);
//            MockMultipartFile sto = new MockMultipartFile("file", file1.getName(), "image/jpeg", fis1);
//
//            imageService.uploadImage(sto);
//
//            File file2 = new File("item_service/src/main/java/ba/unsa/etf/nwt/item_service/images/stolica.jpg");
//            FileInputStream fis2 = new FileInputStream(file2);
//            MockMultipartFile stolica = new MockMultipartFile("file", file2.getName(), "image/jpeg", fis2);
//
//            imageService.uploadImage(stolica);
//
//            File file3 = new File("item_service/src/main/java/ba/unsa/etf/nwt/item_service/images/kauc.jpg");
//            FileInputStream fis3 = new FileInputStream(file3);
//            MockMultipartFile kauc = new MockMultipartFile("file", file3.getName(), "image/jpeg", fis3);
//
//            imageService.uploadImage(kauc);
//
//            File file4 = new File("item_service/src/main/java/ba/unsa/etf/nwt/item_service/images/ormar.jpg");
//            FileInputStream fis4 = new FileInputStream(file4);
//            MockMultipartFile ormar = new MockMultipartFile("file", file4.getName(), "image/jpeg", fis4);
//
//            imageService.uploadImage(ormar);
//
//            File file5 = new File("item_service/src/main/java/ba/unsa/etf/nwt/item_service/images/krevet.jpg");
//            FileInputStream fis5 = new FileInputStream(file5);
//            MockMultipartFile krevet = new MockMultipartFile("file", file5.getName(), "image/jpeg", fis5);
//
//            imageService.uploadImage(krevet);
//
//            Image image1 = new Image();
//            imageService.mapToEntity(sto,image1);
//            image1.setId(1);
//            Image image2 = new Image();
//            imageService.mapToEntity(sto,image2);
//            image2.setId(2);
//            Image image3 = new Image();
//            imageService.mapToEntity(sto,image3);
//            image3.setId(3);
//            Image image4 = new Image();
//            imageService.mapToEntity(sto,image4);
//            image4.setId(4);
//            Image image5 = new Image();
//            imageService.mapToEntity(sto,image5);
//            image5.setId(5);
//
//            repository.save(new Item("Sto", "Opis o stolu", 120.5, 15, specifications1,image1, itemCategory1));
//            repository.save(new Item("Stolica", "Opis o stolici", 123.5, 15, specifications2, image2, itemCategory2));
//            repository.save(new Item("Kauc", "Opis o kaucu", 155.5, 12, specifications3, image3, itemCategory3));
//            repository.save(new Item("Ormar", "Opis o ormaru", 120.5, 15, specifications4, image4, itemCategory4));
//            repository.save(new Item("Krevet", "Opis o krevetu", 123.5, 15, specifications5, image5, itemCategory5));
//        };
//    }

    public static void main(String[] args) {
        SpringApplication.run(ItemServiceApplication.class, args);
    }

}
