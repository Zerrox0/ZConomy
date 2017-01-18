package de.zeroxtv.zconomy.ZObjects;

import org.bukkit.Material;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by ZeroxTV
 */
public class ZItemLoader {
    public static Boolean configLoaded;
    public static File defaultConfigFile = new File("ZPlugins/ZConomy/Items/Items.yml");
    private static File dirFile = new File("ZPlugins/ZConomy/Items");

    public static ArrayList<ZItem> loadItem(File configFile) {
        loadConfigFile();
        try {
            YamlConfiguration config = new YamlConfiguration();
            config.load(configFile);

            for (String path : config.getKeys(false)) {
                new ZItem(Material.getMaterial(config.getString(path + ".material")),
                        Short.parseShort(config.getString(path + ".durability")),
                        config.getDouble(path + ".value"),
                        config.getDouble(path + ".offer.valueMin"),
                        config.getDouble(path + ".offer.valueMax"),
                        config.getDouble(path + ".request.valueMin"),
                        config.getDouble(path + ".request.valueMax"));
            }

        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
        return ZItem.getItems();
    }

    public static ArrayList<ZItem> createItem(File configFile, ItemStack itemStack, Double value,Double offerValueMin, Double offerValueMax, Double requestValueMin, Double requestValueMax) {
        loadConfigFile();
        try {
            YamlConfiguration config = new YamlConfiguration();
            config.load(configFile);
            config.options().copyDefaults(true);
            String material = itemStack.getType().name().toUpperCase();
            String path;
            for (int i = 0; i < 100; i++) {
                if (config.contains(material + "_" + i)) {
                    path = material + "_" + i;
                    config.addDefault(path + ".material", itemStack.getType().name());
                    config.addDefault(path + ".durability", itemStack.getDurability());
                    config.addDefault(path + ".value", value);
                    config.addDefault(path + ".offer.minValue", offerValueMin);
                    config.addDefault(path + ".offer.maxValue", offerValueMax);
                    config.addDefault(path + ".request.minValue", requestValueMin);
                    config.addDefault(path + ".request.maxValue", requestValueMax);
                    config.save(configFile);
                    new ZItem(itemStack.getType(), itemStack.getDurability(), value, offerValueMin, offerValueMax, requestValueMin, requestValueMax);
                }
            }
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
        return ZItem.getItems();
    }

    private static void loadConfigFile() {
        try {
            if (configLoaded) return;
            if (!dirFile.exists()) dirFile.mkdirs();
            if (!defaultConfigFile.exists()) defaultConfigFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
