package org.l2x9.l2x9core.listeners.antiillegal;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.ShulkerBox;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BlockStateMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.l2x9.l2x9core.Main;
import org.l2x9.l2x9core.util.Utils;

import java.util.Map.Entry;

public class BlockPlace implements Listener {
	Main plugin;

	public BlockPlace(Main plugin) {
		this.plugin = plugin;
	}

	public static void removeColours(ItemStack item, ItemMeta meta) {
		String name = ChatColor.stripColor(meta.getDisplayName());
		meta.setDisplayName(ChatColor.stripColor(meta.getDisplayName()));
		if (name.toCharArray().length > 35) {
			String newName = name.substring(0, 35);
			meta.setDisplayName(newName);
		}
		item.setItemMeta(meta);
	}

	@EventHandler
	@AntiIllegal(EventName = "BlockPlaceEvent")
	public void onPlace(BlockPlaceEvent event) {
		try {
			if (plugin.getConfig().getBoolean("Antiillegal.Block-Place-Enabled")) {
				if (plugin.getItemUtils().isIllegal(event.getItemInHand())) {
					event.setCancelled(true);
					event.getPlayer().getInventory().getItemInMainHand().setType(Material.AIR);
				}
			}

		} catch (Error | Exception throwable) {

		}
	}
}
