package hugu1026.com.github.phantasycompassnavi.gui;

import hugu1026.com.github.phantasycompassnavi.util.DestinationUtil;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashSet;
import java.util.LinkedHashSet;

public class TeleportGui extends Gui {

    private LinkedHashSet<ItemStack> icons = new LinkedHashSet<>();

    public TeleportGui(Player player) {
        HashSet<String> destinationList = DestinationUtil.getNameList();

        destinationList.forEach(name -> {
            if (player.hasPermission("destination.teleport." + name)) {
                this.icons.add(DestinationUtil.getItemStack(name));
            }
        });

        super.createInventory(this, 9 * 6, "テレポート地点を選択");
    }

    @Override
    public void openInventory(Player player) {
        this.icons.forEach(super::addInventory);

        super.openInventory(player);
    }
}
