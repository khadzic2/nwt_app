package ba.unsa.etf.nwt.item_service.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import ba.unsa.etf.nwt.item_service.DTO.ImageDTO;
import ba.unsa.etf.nwt.item_service.Exceptions.NotFoundException;
import ba.unsa.etf.nwt.item_service.Util.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import ba.unsa.etf.nwt.item_service.Model.Image;
import ba.unsa.etf.nwt.item_service.Repository.ImageRepository;

@Service
public class ImageService {

    @Autowired
    private final ImageRepository imageRepository;

    public ImageService(final ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public List<ImageDTO> findAll() {
        return imageRepository.findAll()
                .stream()
                .map(image -> mapToDTO(image, new ImageDTO()))
                .collect(Collectors.toList());
    }

    public void delete(final Integer id) {
        imageRepository.deleteById(id);
    }
    public void deleteAll() {
        imageRepository.deleteAll();
    }

    public ImageDTO get(Integer id) throws IOException {
        return imageRepository.findById(id)
                .map(image -> mapToDTO(image, new ImageDTO()))
                .orElseThrow(() -> new NotFoundException(id,"image"));
    }

    public Integer uploadImage(MultipartFile file) throws IOException {
        Image pImage = new Image();
        mapToEntity(file,pImage);
        return imageRepository.save(pImage).getId();
    }

    private ImageDTO mapToDTO(final Image image, final ImageDTO imageDTO) {
        imageDTO.setId(image.getId());
        imageDTO.setName(image.getName());
        imageDTO.setType(image.getType());
        imageDTO.setImageData(ImageUtil.decompressImage(image.getImageData()));
        return imageDTO;
    }

    public void mapToEntity(final MultipartFile file, final Image image) throws IOException{
        image.setName(file.getOriginalFilename());
        image.setType(file.getContentType());
        image.setImageData(ImageUtil.compressImage(file.getBytes()));
    }
}