package ba.unsa.etf.nwt.item_service.Service;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import ba.unsa.etf.nwt.item_service.Model.Image;
import ba.unsa.etf.nwt.item_service.Repository.ImageRepository;
import ba.unsa.etf.nwt.item_service.Util.ImageUtil;

@Service
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;

    public Image uploadImage(MultipartFile file) throws IOException {
        Image pImage = new Image();
        pImage.setName(file.getOriginalFilename());
        pImage.setType(file.getContentType());
        pImage.setImageData(ImageUtil.compressImage(file.getBytes()));
        return imageRepository.save(pImage);
    }

    public byte[] downloadImage(String fileName){
        Optional<Image> imageData = imageRepository.findByName(fileName);
        return ImageUtil.decompressImage(imageData.get().getImageData());
    }
}