package hugu1026.com.github.phantasycompassnavi.util;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;

public class DestinationUtil {

    public static void createDestinationYml() {
        File fileData = new File(Bukkit.getServer().getPluginManager().getPlugin("PhantasyCompassNavi").getDataFolder(), File.separator + "destination");
        File file = new File(fileData, File.separator + "destination.yml");
        FileConfiguration destinationData = YamlConfiguration.loadConfiguration(file);

        if (!file.exists()) {
            try {
                destinationData.save(file);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static File getDestinationFile() {
        File fileData = new File(Bukkit.getServer().getPluginManager().getPlugin("PhantasyCompassNavi").getDataFolder(), File.separator + "destination");
        return new File(fileData, File.separator + "destination.yml");
    }

    public static FileConfiguration getDestinationData() {
        File file = getDestinationFile();
        return YamlConfiguration.loadConfiguration(file);
    }

    public static void saveDestinationData(File file, FileConfiguration data) {
        try {
            data.save(file);
        } catch (IOException ex) {
            Bukkit.getServer().getLogger().severe("データの保存に失敗しました");
            ex.printStackTrace();
        }
    }

    public static void createSection(String name) {
        File file = getDestinationFile();
        FileConfiguration destinationData = getDestinationData();

        destinationData.createSection(name);
        destinationData.createSection(name + ".location");
        destinationData.createSection(name + ".item");

        saveDestinationData(file, destinationData);
    }

    public static void setLocation(String name, Location location) {
        File destinationFile = getDestinationFile();
        FileConfiguration destinationData = getDestinationData();

        destinationData.set(name + ".location.x", location.getX());
        destinationData.set(name + ".location.y", location.getY());
        destinationData.set(name + ".location.z", location.getZ());
        saveDestinationData(destinationFile, destinationData);
    }

    public static void setItem(String name, int id) {
        File destinationFile = getDestinationFile();
        FileConfiguration destinationData = getDestinationData();

        destinationData.set(name + ".item", id);
        saveDestinationData(destinationFile, destinationData);
    }

    public static Location getLocation(String name) {
        FileConfiguration destinationData = getDestinationData();

        int x, y, z;
        x = destinationData.getInt(name + ".location.x");
        y = destinationData.getInt(name + ".location.y");
        z = destinationData.getInt(name + ".location.z");

        return new Location(Bukkit.getWorld("world"), x, y, z);
    }

    public static ItemStack getItemStack(String name) {
        FileConfiguration destinationData = getDestinationData();

        ItemStack itemStack = new ItemStack(Material.getMaterial((Integer) destinationData.get(name + ".item")), 1);
        ItemMeta meta = itemStack.getItemMeta();
        meta.setDisplayName(ChatColor.GOLD + name);
        itemStack.setItemMeta(meta);

        return itemStack;
    }

    public static HashSet<String> getNameList() {
        FileConfiguration destinationData = getDestinationData();

        return new HashSet<>(destinationData.getKeys(false));
    }
}
