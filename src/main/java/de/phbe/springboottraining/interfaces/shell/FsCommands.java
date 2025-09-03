package de.phbe.springboottraining.interfaces.shell;

import de.phbe.springboottraining.core.FileSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.util.unit.DataSize;

@ShellComponent
public class FsCommands {

    // Shell Component
    // in den application.properties muss die shell aktiviert werden
    // spring.shell.interactive.enabled=true
    // sonst funktioniert die Shell Anwendung nicht

    private final FileSystem fs;

    @Autowired
    public FsCommands(FileSystem fs) {
        this.fs = fs;
    }

    @ShellMethod( "Display free disk space" )
    public String freeDiskSpace() { // free-disk-space
        return DataSize.ofBytes( fs.getFreeDiskSpace() ).toGigabytes() + " GB";
    }
    // Aufruf: shell:>free-disk-space
}
