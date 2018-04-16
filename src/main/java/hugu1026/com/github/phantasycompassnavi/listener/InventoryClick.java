package hugu1026.com.github.phantasycompassnavi.listener;

import hugu1026.com.github.phantasycompassnavi.PhantasyCompassNavi;
import hugu1026.com.github.phantasycompassnavi.gui.CompassGui;
import hugu1026.com.github.phantasycompassnavi.gui.Gui;
import hugu1026.com.github.phantasycompassnavi.gui.TeleportGui;
import hugu1026.com.github.phantasycompassnavi.util.DestinationUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class InventoryClick implements Listener {

    @EventHandler
    public void InventoryClick(InventoryClickEvent event) {
        Inventory inventory = event.getInventory();
        if (inventory == null) return;

        if (!(event.getWhoClicked() instanceof Player)) return;

        Player player = (Player) event.getWhoClicked();
        ItemStack clickedItem = event.getCurrentItem();

        if (!(inventory.getHolder() instanceof Gui)) return;

        if (!clickedItem.hasItemMeta()) return;

        if (!clickedItem.getItemMeta().hasDisplayName()) return;

        String name = clickedItem.getItemMeta().getDisplayName();
        name = ChatColor.stripColor(name);

        if (inventory.getHolder() instanceof CompassGui) {
            player.setCompassTarget(DestinationUtil.getLocation(name));
            player.sendMessage(ChatColor.RED + name + ChatColor.GOLD + "をナビゲーターの目標地点に設定しました");

            Location destinationLoc = DestinationUtil.getLocation(name);
            Location playerLoc = player.getLocation();

            player.sendMessage(ChatColor.GOLD + "目的地地点までおよそ:" + ChatColor.RED + (int) destinationLoc.distance(playerLoc) + ChatColor.GOLD + "ブロック");
            player.closeInventory();
            event.setCancelled(true);
        }

        else if (inventory.getHolder() instanceof TeleportGui) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 20 * 3, 100));
            player.sendMessage(ChatColor.GOLD + "3秒後にテレポートします...");
            String finalName = name;
            Bukkit.getScheduler().scheduleSyncDelayedTask(PhantasyCompassNavi.getPlugin(PhantasyCompassNavi.class), () -> {
                player.teleport(DestinationUtil.getLocation(finalName));
                player.sendMessage(ChatColor.RED + finalName + ChatColor.GOLD + "にテレポートしました");
            }, 60);
            player.closeInventory();
            event.setCancelled(true);
        }
    }
}
