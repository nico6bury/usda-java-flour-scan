package Config;

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
}//end class ConfigConfig
