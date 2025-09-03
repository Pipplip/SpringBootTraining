package de.phbe.springboottraining.core.photo;

import de.phbe.springboottraining.core.FileSystem;
import org.springframework.stereotype.Service;

import java.io.UncheckedIOException;
import java.util.Optional;

// Ein Service, der Fotos hoch- und runterladen sowie Metadaten von Fotos anzeigen kann
@Service
public class PhotoService {
    private final FileSystem fs;

    public PhotoService(FileSystem fs) {
        this.fs = fs;
    }

    public Optional<byte[]> download(String name){
        try{
            return Optional.of(fs.load(name+".jpg"));
            // richtiger: org.springframework.core.io.Resource
        }catch(UncheckedIOException e){
            return Optional.empty();
        }
    }
}
