package de.phbe.springboottraining.interfaces.shell;

import de.phbe.springboottraining.core.photo.PhotoService;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import javax.imageio.ImageIO;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@ShellComponent
public class PhotoCommands {
    private final PhotoService photoService;

    public PhotoCommands(PhotoService photoService) {
        this.photoService = photoService;
    }

    // show-photo 1ab1e0b0-970b-46f0-8bd7-b4b586f01347
    @ShellMethod( "Show photo" )
    String showPhoto(String name) {
        return photoService.download(name).map(bytes -> {
            try {
                var image = ImageIO.read(new ByteArrayInputStream(bytes));
                return "Width: " +  image.getWidth() + ", Height: " + image.getHeight();
            }catch(IOException e) {
                return "Unable to read image dimensions";
            }

        }).orElse("Image not found");
    }

    // shell:>upload-photo 'C:/Users/User/Desktop/neues-bild.jpg'
    @ShellMethod( "Upload photo" ) // upload-photo
    String uploadPhoto( String filename ) throws IOException {
        byte[] bytes = Files.readAllBytes( Paths.get( filename ) );
        return "Uploaded " + photoService.upload( bytes );
    }

}
