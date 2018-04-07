package hugu1026.com.github.phantasycompassnavi.command;

import hugu1026.com.github.phantasycompassnavi.PhantasyCompassNavi;
import hugu1026.com.github.phantasycompassnavi.util.DestinationUtil;
import org.apache.commons.lang.StringUtils;
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
            if (sender instanceof Player) {
                Player player = (Player) sender;
                if (args.length == 3) {
                    if (args[0].equalsIgnoreCase("set")) {
                        if (args[1] != null || args[2] != null) {
                            if (StringUtils.isNumeric(args[2])) {
                                DestinationUtil.createSection(args[1]);
                                DestinationUtil.setLocation(args[1], player.getLocation());
                                DestinationUtil.setItem(args[1], Integer.parseInt(args[2]));
                                player.sendMessage(ChatColor.GOLD + args[1] + "を設定しました");
                            } else {
                                player.sendMessage(ChatColor.RED + "アイテムIDは自然数で指定してください");
                                return false;
                            }
                        }
                    }
                } else {
                    player.sendMessage(ChatColor.RED + "/destination set <目的地名> <アイテムID>");
                    return false;
                }
            }
        }
        return false;
    }
}
