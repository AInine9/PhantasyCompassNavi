package hugu1026.com.github.phantasycompassnavi.listener;

import hugu1026.com.github.phantasycompassnavi.gui.CompassGui;
import hugu1026.com.github.phantasycompassnavi.gui.TeleportGui;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;

public class PlayerInteract implements Listener {

    @EventHandler
    public void PlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        if (event.getHand() != EquipmentSlot.HAND) return;


        if (player.getInventory().getItemInMainHand() == null
                || !player.getInventory().getItemInMainHand().hasItemMeta()
                || !player.getInventory().getItemInMainHand().getItemMeta().hasDisplayName())
            return;

        if (event.getAction() == Action.RIGHT_CLICK_BLOCK
                || event.getAction() == Action.RIGHT_CLICK_AIR) {
            if (player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().contains("ナビゲーター")) {
                CompassGui gui = new CompassGui(player);
                gui.openInventory(player);
            }
        }

        if (event.getAction() == Action.LEFT_CLICK_BLOCK
                || event.getAction() == Action.LEFT_CLICK_AIR) {
            if (player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().contains("ナビゲーター")) {
                TeleportGui gui = new TeleportGui(player);
                gui.openInventory(player);
            }
        }
    }
}
