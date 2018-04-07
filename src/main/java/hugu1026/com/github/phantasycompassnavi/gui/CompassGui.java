package hugu1026.com.github.phantasycompassnavi.gui;

import hugu1026.com.github.phantasycompassnavi.util.DestinationUtil;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashSet;
import java.util.LinkedHashSet;

public class CompassGui extends Gui {

    private LinkedHashSet<ItemStack> icons = new LinkedHashSet<>();

    public CompassGui(Player player) {
        HashSet<String> destinationList = DestinationUtil.getNameList();

        destinationList.forEach(name -> {
            if (player.hasPermission("destination.compass." + name)) {
                this.icons.add(DestinationUtil.getItemStack(name));
            }
        });

        super.createInventory(this, 9 * 6, "目的地を選択");
    }

    @Override
    public void openInventory(Player player) {
        this.icons.forEach(super::addInventory);

        super.openInventory(player);
    }
}
