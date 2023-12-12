package Utils;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;

import IJM.IJProcess;
import javafx.util.Pair;

/**
 * This class is used for serializing and deserializing config settings.
 */
public class ConfigScribe {
    /** The name of the config file that will be human-readable and have options that can be set within the application. */
    public static final String h_config_name = "flour-scan.config";
    /** The name of the config file that doesn't have to be human-readable and has options to help with application functions. */
    public static final String c_config_name = ".config";
    /** Constructs the class */
    public ConfigScribe() {}

    public Result<String> write_config(ConfigStoreH store_h, ConfigStoreC store_c) {
        String jar_location;
        try {
            // figure out path to write file to
            jar_location = new File(IJProcess.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getParentFile().toString();
            File config_h_filepath = new File(jar_location + File.separator + h_config_name);
            File config_c_filepath = new File(jar_location + File.separator + c_config_name);
            // make sure file exists
            if (!config_h_filepath.exists()) { config_h_filepath.createNewFile(); }
            if (!config_c_filepath.exists()) { config_c_filepath.createNewFile(); }
            // get all the lines from the config file
            List<String> config_h_lines = Files.readAllLines(config_h_filepath.toPath());
            List<String> config_c_lines = Files.readAllLines(config_c_filepath.toPath());
            // get list of fields to use for looking stuff up in match map
            Field[] fields_h = ConfigStoreH.class.getFields();
            Field[] fields_c = ConfigStoreC.class.getFields();
            // find the lines at which things are written in existing config, if found at all
            HashMap<String, Integer> match_map_h = match_config_lines(config_h_lines, fields_h);
            HashMap<String, Integer> match_map_c = match_config_lines(config_c_lines, fields_c);
            // update lines in config file with values from parameters
            for (int i = 0; i < fields_h.length; i++) {
                // get the formatting figured out beforehand since it will be the same
                String f_line = fields_h[i].getName() + " = " + fields_h[i].get(store_h);
                // check whether or not current field is already recorded in file
                if (match_map_h.containsKey(fields_h[i].getName())) {
                    // rewrite the line at index in match_map[fields[i].getName()]
                    int index = match_map_h.get(fields_h[i].getName());
                    config_h_lines.set(index, f_line);
                }//end if we can rewrite the corresponding line
                else {
                    // add a new line for fields[i]
                    config_h_lines.add(f_line);
                }//end else we'll have to add a new line for this field
            }//end looping over fields, matching and writing
            for (int i = 0; i < fields_c.length; i++) {
                // get the formatting figured out beforehand since it will be the same
                String f_line = fields_c[i].getName() + " = " + fields_c[i].get(store_c);
                // check whether or not current field is already recorded in file
                if (match_map_c.containsKey(fields_c[i].getName())) {
                    // rewrite the line at index in match_map[fields[i].getName()]
                    int index = match_map_c.get(fields_c[i].getName());
                    config_c_lines.set(index, f_line);
                }//end if we can rewrite the corresponding line
                else {
                    // add a new line for fields[i]
                    config_c_lines.add(f_line);
                }//end else we'll have to add a new line for this field
            }//end looping over fields, matching and writing
            // clear files of text
            new FileWriter(config_h_filepath, false).close();
            new FileWriter(config_c_filepath, false).close();
            new PrintWriter(config_c_filepath).close();
            // write changes to files
            Files.write(config_h_filepath.toPath(), config_h_lines);
            Files.write(config_c_filepath.toPath(), config_c_lines);
        } catch (Exception e) { e.printStackTrace(); return new Result<String>(e);}

        return new Result<String>("No Exceptions Encountered.");
    }//end write_config(store_h, store_c)

    public Result<Pair<ConfigStoreH,ConfigStoreC>> read_config() {
        String jar_location;
        try {
            // figure out path to read file from
            jar_location = new File(IJProcess.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getParentFile().toString();
            File config_h_filepath = new File(jar_location + File.separator + h_config_name);
            File config_c_filepath = new File(jar_location + File.separator + c_config_name);
            // make sure file exists
            if (!config_h_filepath.exists()) { config_h_filepath.createNewFile(); }
            if (!config_c_filepath.exists()) { config_c_filepath.createNewFile(); }
            // get all the lines from the config file
            List<String> config_h_lines = Files.readAllLines(config_h_filepath.toPath());
            List<String> config_c_lines = Files.readAllLines(config_c_filepath.toPath());
            // get list of fields to use for looking stuff up in match map
            Field[] fields_h = ConfigStoreH.class.getFields();
            Field[] fields_c = ConfigStoreC.class.getFields();
            // find the lines at which things are written in existing config, if found at all
            HashMap<String, Integer> match_map_h = match_config_lines(config_h_lines, fields_h);
            HashMap<String, Integer> match_map_c = match_config_lines(config_c_lines, fields_c);
            // create store objects to read information into
            ConfigStoreH configStoreH = new ConfigStoreH();
            ConfigStoreC configStoreC = new ConfigStoreC();
            // update lines in config file with values from parameters
            for (int i = 0; i < fields_h.length; i++) {
                // check whether or not current field is recorded in file
                if (match_map_h.containsKey(fields_h[i].getName())) {
                    // read line at index in match_map[fields[i].getName()]
                    int index = match_map_h.get(fields_h[i].getName());
                    String this_line = config_h_lines.get(index); // name = value
                    String[] split_line = this_line.split(" = ");
                    if (split_line.length == 2) {
                        // try and parse depending on type of field
                        if (fields_h[i].getType() == int.class) {
                            int val = Integer.parseInt(split_line[1]);
                            fields_h[i].setInt(configStoreH, val);
                        }//end if it's an integer
                        if (fields_h[i].getType() == double.class) {
                            double val = Double.parseDouble(split_line[1]);
                            fields_h[i].setDouble(configStoreH, val);
                        }//end if it's a double
                    }//end if split line has expected length
                    else {
                        // TODO: handle some sort of exceptional case
                    }//end else we need to try another method of parsing
                }//end if we found a line for this value
            }//end looping over fields, matching and writing
            for (int i = 0; i < fields_c.length; i++) {
                // check whether or not current field is recorded in file
                if (match_map_h.containsKey(fields_c[i].getName())) {
                    // read line at index in match_map[fields[i].getName()]
                    int index = match_map_c.get(fields_c[i].getName());
                    String this_line = config_c_lines.get(index); // name = value
                    String[] split_line = this_line.split(" = ");
                    if (split_line.length == 2) {
                        // we will just assume that the field is a string
                        fields_c[i].set(configStoreC, split_line[1]);
                    }//end if split line has expected length
                    else {
                        // TODO: handle some sort of exceptional case
                    }//end else we need to try another method of parsing
                }//end if we found a line for this value
            }//end looping over fields, matching and writing
            // return pair wrapped in result
            return new Result<>(new Pair<>(configStoreH, configStoreC));
        } catch (Exception e) { e.printStackTrace(); return new Result<Pair<ConfigStoreH,ConfigStoreC>>(e);}
    }//end read_config()

    /**
     * This helper method finds the index in the provided lines at which each property of ConfigStore starts a line.
     * @param lines The lines of text from a config file
     * @return Returns a hashmap, with keys being strings denoting the names of properties of ConfigStore, and values being the index they're found at
     */
    protected HashMap<String, Integer> match_config_lines(List<String> lines, Field[] fields) {
        HashMap<String, Integer> match_map = new HashMap<>();

        // get list of the names of properties of ConfigStore
        for (int i = 0; i < fields.length; i++) {
            String this_field_name = fields[i].getName();
            // loop through lines to see if one of them starts with this_field_name
            for (int j = 0; j < lines.size(); j++) {
                String this_line = lines.get(j);
                String this_trimmed_line = this_line.substring(0, Math.min(this_line.length(), this_field_name.length()));
                if (this_trimmed_line.equalsIgnoreCase(this_field_name)) {match_map.put(this_field_name, j); break;}
            }//end looping over each line
        }//end looping over each field

        return match_map;
    }//end match_config_lines
}//end class Config
