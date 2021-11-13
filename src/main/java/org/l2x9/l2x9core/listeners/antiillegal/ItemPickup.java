package org.l2x9.l2x9core.listeners.antiillegal;

import org.bukkit.block.ShulkerBox;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BlockStateMeta;
import org.l2x9.l2x9core.Main;
import org.l2x9.l2x9core.util.Utils;

@SuppressWarnings("deprecation")
public class ItemPickup implements Listener {
    Main plugin;

    public ItemPickup(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    @AntiIllegal(EventName = "PlayerPickupItemEvent")
    public void onPickup(PlayerPickupItemEvent event) {
        try {
            if (plugin.getConfig().getBoolean("Antiillegal.ItemPickup-Enabled")) {
                ItemStack item = event.getItem().getItemStack();
                if (plugin.getItemUtils().isEnchantedBlock(item) || plugin.getItemUtils().hasIllegalNBT(item) || plugin.getItemUtils().hasIllegalEnchants(item) || plugin.getItemUtils().isIllegal(item)) {
                    event.setCancelled(true);
                    event.getItem().remove();
                }
            }
        } catch (Error | Exception throwable) {

        }
    }
}
