package hugu1026.com.github.phantasycompassnavi.listener;

import hugu1026.com.github.phantasycompassnavi.gui.CompassGui;
import hugu1026.com.github.phantasycompassnavi.util.DestinationUtil;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class InventoryClick implements Listener {

    @EventHandler
    public void InventoryClick(InventoryClickEvent event) {
        Inventory inventory = event.getInventory();
        if (inventory == null) return;

        if (!(inventory.getHolder() instanceof CompassGui)) return;

        if (!(event.getWhoClicked() instanceof Player)) return;

        Player player = (Player) event.getWhoClicked();
        ItemStack clickedItem = event.getCurrentItem();

        if (!(clickedItem.hasItemMeta() || clickedItem.getItemMeta().hasDisplayName())) return;

        String name = clickedItem.getItemMeta().getDisplayName();
        name = ChatColor.stripColor(name);

        player.setCompassTarget(DestinationUtil.getLocation(name));
        player.sendMessage(ChatColor.RED + name + ChatColor.GOLD + "をナビゲーターの目標地点に設定しました");

        Location destinationLoc = DestinationUtil.getLocation(name);
        Location playerLoc = player.getLocation();

        player.sendMessage(ChatColor.GOLD + "目的地地点までおよそ:" + ChatColor.RED + (int) destinationLoc.distance(playerLoc) + ChatColor.GOLD + "ブロック");

        player.closeInventory();
        event.setCancelled(true);
    }
}
