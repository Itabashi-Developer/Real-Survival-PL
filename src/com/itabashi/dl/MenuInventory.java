package com.itabashi.dl;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class MenuInventory {

	/*
	・家にテレポート機能
	・掲示板
	 */

	public void openInventory(Player player) {
		Inventory inventory = Bukkit.createInventory(null, 45, "§6§lメニュー");
		ItemStack close = new ItemStack(Material.BARRIER, 1);
		ItemMeta close_meta = close.getItemMeta();
		close_meta.setDisplayName("§c§l閉じる");
		close_meta.setLore(Arrays.asList(new String[] {"§eクリックで閉じます"}));
		close.setItemMeta(close_meta);
		inventory.setItem(0, close);
		inventory.setItem(8, close);
		inventory.setItem(36, close);
		inventory.setItem(44, close);
		ItemStack home = new ItemStack(Material.DARK_OAK_DOOR, 1);
		ItemMeta home_meta = home.getItemMeta();
		home_meta.setDisplayName("§a§lホーム機能");
		home_meta.setLore(Arrays.asList(new String[] {"§eクリックで家に戻ります"}));
		home.setItemMeta(home_meta);
		inventory.setItem(10, home);
		ItemStack quest = new ItemStack(Material.ACACIA_SIGN, 1 );
		ItemMeta quest_meta = quest.getItemMeta();
		quest_meta.setDisplayName("§d§lクエスト一覧");
		quest_meta.setLore(Arrays.asList(new String[] {"§eクリックで確認します"}));
		quest.setItemMeta(quest_meta);
		inventory.setItem(20, quest);
		ItemStack an = new ItemStack(Material.ANVIL, 1 );
		ItemMeta an_meta = an.getItemMeta();
		an_meta.setDisplayName("§c§lEveryone機能");
		an_meta.setLore(Arrays.asList(new String[] {"§eクリックで注目を集めます"}));
		an.setItemMeta(an_meta);
		inventory.setItem(12, an);
		//
		ItemStack warp = new ItemStack(Material.RESPAWN_ANCHOR, 1);
		ItemMeta warp_meta = warp.getItemMeta();
		warp_meta.setDisplayName("§a§lワープ機能");
		warp_meta.setLore(Arrays.asList(new String[] {"§eクリックで確認します"}));
		warp.setItemMeta(warp_meta);
		inventory.setItem(22, warp);
		player.openInventory(inventory);
	}

}
