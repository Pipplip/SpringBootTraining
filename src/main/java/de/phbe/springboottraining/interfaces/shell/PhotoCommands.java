package de.phbe.springboottraining.interfaces.shell;

import de.phbe.springboottraining.core.photo.PhotoService;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import javax.imageio.ImageIO;
import java.io.ByteArrayInputStream;
import java.io.IOException;

@ShellComponent
public class PhotoCommands {
    private final PhotoService photoService;

    public PhotoCommands(PhotoService photoService) {
        this.photoService = photoService;
    }

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

}
