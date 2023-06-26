package Config;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * This class keeps track of, reads from, and writes to various configuration files. It does not know anything about what is inside a particular configuration file, or even what type various lines should be parsed to.
 * @author Nicholas Sixbury
 */
public class ConfigConfig {
    /*
     * So, probably might as well have a structure like this:
     * One config class for handling where we're even storing files, keep things close to the jar (ConfigConfig), also handles reading and writing files.
     * One config class for each area we need to store settings for (like scan settings)
     * Each config class for storing actual settings should probably have either its own file or own folder, depending on if there's more than one file or not. Also, the individual config classes for various settings should just have properties, plus a method or two for regenerating appropriate config settings.
     * we'll use reflection plus a little string parsing in order to hold comments and settings in various files. 
    */ 

    /**
     * The name of the final that this class will look for in order to remember where various config files are already located.
     */
    public static final String FileRefName = "fileRefName.dontEditThis";

    /**
     * This is the current path to the directory containing the jar file.
     */
    public Path currentLocation;

    /**
     * Constructs the object. If you're having issues getting the object to construct, try specifying the directory where the jar is located.
     * @throws URISyntaxException This object needs to know the directory where the jar is located in order to function. If it's unable to perform the necessary file operations, a URISyntaxException might be thrown by underlying methods.
     */
    public ConfigConfig() throws URISyntaxException {
        // figure out where the program is running from
        currentLocation = Paths.get(getClass().getProtectionDomain().getCodeSource().getLocation().toURI().getPath());
    }//end no-arg constructor

    /**
     * This constructor allows the current jar path to be specified manually instead of automatically detecting it.
     * @param currentJarDirPath The path to the directory containing the jar file which is currently running the program.
     */
    public ConfigConfig(Path currentJarDirPath) {
        currentLocation = currentJarDirPath.toAbsolutePath();
    }//end 1-arg constructor

}//end class ConfigConfig
