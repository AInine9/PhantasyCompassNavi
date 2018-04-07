package hugu1026.com.github.phantasycompassnavi.command;

import hugu1026.com.github.phantasycompassnavi.PhantasyCompassNavi;
import hugu1026.com.github.phantasycompassnavi.util.DestinationUtil;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetDestinationCommand implements CommandExecutor {
    private final PhantasyCompassNavi plg;

    public SetDestinationCommand(PhantasyCompassNavi plg) {
        this.plg = plg;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("destination")) {
            if (args[0].equalsIgnoreCase("set")) {
                if (sender instanceof Player) {
                    Player player = (Player) sender;
                    if (args[1] != null || args[2] != null) {
                        DestinationUtil.createSection(args[1]);
                        DestinationUtil.setLocation(args[1], player.getLocation());
                        DestinationUtil.setItem(args[1], Integer.parseInt(args[2]));
                        player.sendMessage(ChatColor.GOLD + args[1] + "を設定しました");
                    }
                }
            }
        }
        return false;
    }
}
