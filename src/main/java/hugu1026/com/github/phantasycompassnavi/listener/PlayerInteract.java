package hugu1026.com.github.phantasycompassnavi.listener;

import hugu1026.com.github.phantasycompassnavi.gui.DestinationGui;
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

        if (event.getHand() != EquipmentSlot.HAND) {
            return;
        }
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK
                || event.getAction() == Action.RIGHT_CLICK_AIR) {
            if (player.getInventory().getItemInMainHand() == null
                    || !player.getInventory().getItemInMainHand().hasItemMeta()
                    || !player.getInventory().getItemInMainHand().getItemMeta().hasDisplayName())
                return;

            if (player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().contains("ナビゲーター")) {
                DestinationGui gui = new DestinationGui(player);
                gui.openInventory(player);
            }
        }
    }
}