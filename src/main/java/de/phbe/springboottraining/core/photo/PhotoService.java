package de.phbe.springboottraining.core.photo;

import de.phbe.springboottraining.core.FileSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.UncheckedIOException;
import java.util.Optional;
import java.util.UUID;

// Ein Service, der Fotos hoch- und runterladen sowie Metadaten von Fotos anzeigen kann
@Service
public class PhotoService {
    private final FileSystem fs;
    private final Thumbnail thumbnail;

    public PhotoService(FileSystem fs, @Qualifier("fastThumbnailRenderer") Thumbnail thumbnail) {
        this.fs = fs;
        this.thumbnail = thumbnail;
    }

    public Optional<byte[]> download(String name){
        try{
            return Optional.of(fs.load(name+".jpg"));
            // richtiger: org.springframework.core.io.Resource
        }catch(UncheckedIOException e){
            return Optional.empty();
        }
    }

    public String upload(byte[] imageBytes){
        String imageName = UUID.randomUUID().toString();
        // First: Speichere das originale Bild
        fs.store(imageName + ".jpg", imageBytes);
        // Second: Speichere das thumbnail
        byte[] thumbnailBytes = thumbnail.thumbnail(imageBytes);
        fs.store(imageName + "-thumb.jpg", thumbnailBytes);
        return imageName;
    }
}
