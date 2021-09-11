package com.itabashi.dl;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class WarpInventory {

	public void openInventory(Player player) {
		Inventory inventory = Bukkit.createInventory(null, 45, "§a§lワープメニュー");
		ItemStack close = new ItemStack(Material.ARROW, 1);
		ItemMeta close_meta = close.getItemMeta();
		close_meta.setDisplayName("§c§l戻る");
		close_meta.setLore(Arrays.asList(new String[] {"§eクリックで戻ります"}));
		close.setItemMeta(close_meta);
		inventory.setItem(0, close);
		inventory.setItem(8, close);
		inventory.setItem(36, close);
		inventory.setItem(44, close);
		//
		ItemStack home = new ItemStack(Material.DARK_OAK_DOOR, 1);
		ItemMeta home_meta = home.getItemMeta();
		home_meta.setDisplayName("§a§lホーム");
		home_meta.setLore(Arrays.asList(new String[] {"§eクリックで家に戻ります"}));
		home.setItemMeta(home_meta);
		inventory.setItem(10, home);
		//
		ItemStack end = new ItemStack(Material.END_PORTAL_FRAME, 1);
		ItemMeta end_meta = end.getItemMeta();
		end_meta.setDisplayName("§b§lエンドポータル");
		end_meta.setLore(Arrays.asList(new String[] {"§eクリックでエンドポータルに移動します"}));
		end.setItemMeta(end_meta);
		inventory.setItem(20, end);
		//
		ItemStack et = new ItemStack(Material.ENDER_PEARL, 1);
		ItemMeta et_meta = et.getItemMeta();
		et_meta.setDisplayName("§6§lエンダーマントラップ");
		et_meta.setLore(Arrays.asList(new String[] {"§eクリックでエンダーマントラップに移動します"}));
		et.setItemMeta(et_meta);
		inventory.setItem(12, et);
		player.openInventory(inventory);
	}

}
