package ba.unsa.etf.nwt.item_service.Controller;

import java.io.IOException;
import java.util.List;

import ba.unsa.etf.nwt.item_service.DTO.ImageDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import ba.unsa.etf.nwt.item_service.Service.ImageService;

@RestController
@RequestMapping(value = "/api/image", produces = MediaType.APPLICATION_JSON_VALUE)
public class ImageController {

    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping
    public ResponseEntity<List<ImageDTO>> getAllPictures() {
        return ResponseEntity.ok(imageService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ImageDTO> getPicture(@PathVariable Integer id) throws IOException {
        return ResponseEntity.ok(imageService.get(id));
    }

    @PostMapping(value = "/upload", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<Integer> uploadPicture(@RequestPart("file") MultipartFile file) throws IOException {
        return new ResponseEntity<>(imageService.uploadImage(file), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePicture(@PathVariable final Integer id) {
        imageService.delete(id);
        return ResponseEntity.ok("Successfully deleted!");
    }

    @DeleteMapping
    public ResponseEntity<String> deleteAll() {
        imageService.deleteAll();
        return ResponseEntity.ok("Successfully deleted!");
    }
}