package hugu1026.com.github.phantasycompassnavi;

import hugu1026.com.github.phantasycompassnavi.command.SetDestinationCommand;
import hugu1026.com.github.phantasycompassnavi.listener.PlayerInteract;
import hugu1026.com.github.phantasycompassnavi.util.DestinationUtil;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class PhantasyCompassNavi extends JavaPlugin {

    @Override
    public void onEnable() {
        super.onEnable();
        this.registerEvents();
        DestinationUtil.createDestinationYml();
        getCommand("destination").setExecutor(new SetDestinationCommand(this));
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }

    public void registerEvents() {
        PluginManager pm = Bukkit.getPluginManager();

        pm.registerEvents(new PlayerInteract(), this);
    }
}
