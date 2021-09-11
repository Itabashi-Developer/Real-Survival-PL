package com.itabashi.dl;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class QuestInventory {

	public void openInventory(Player player) {
		Inventory inventory = Bukkit.createInventory(null, 45, "§d§lクエストメニュー");
		ItemStack close = new ItemStack(Material.ARROW, 1);
		ItemMeta close_meta = close.getItemMeta();
		close_meta.setDisplayName("§c§l戻る");
		close_meta.setLore(Arrays.asList(new String[] {"§eクリックで戻ります"}));
		close.setItemMeta(close_meta);
		inventory.setItem(0, close);
		inventory.setItem(8, close);
		inventory.setItem(36, close);
		inventory.setItem(44, close);
		if (QuestHandler.OBJECT.values().isEmpty()) {
			ItemStack empty = new ItemStack(Material.END_CRYSTAL, 1);
			ItemMeta empty_meta = empty.getItemMeta();
			empty_meta.setDisplayName("§5§lまだ投稿がありません");
			empty.setItemMeta(empty_meta);
			inventory.setItem(10, empty);
		}
		else {
			int de = 10;
			for (QuestObject quest : QuestHandler.OBJECT.values()) {
				if (quest.open) {
					ItemStack quest1 = new ItemStack(Material.WOODEN_SWORD, 1);
					ItemMeta quest1_meta = quest1.getItemMeta();
					quest1_meta.setDisplayName("§a§l" + quest.postName);
					quest1_meta.setLore(Arrays.asList(new String[]{"§e" + quest.lore1, "§e" + quest.lore2, "§e" + quest.lore3, "§e" + quest.lore4, "§5ID: " + quest.lore5_id}));
					quest1.setItemMeta(quest1_meta);
					inventory.setItem(de, quest1);
					de ++;
				}
			}
		}
		if (inventory.getItem(10) == null) {
			ItemStack empty = new ItemStack(Material.END_CRYSTAL, 1);
			ItemMeta empty_meta = empty.getItemMeta();
			empty_meta.setDisplayName("§5§lまだ投稿がありません");
			empty.setItemMeta(empty_meta);
			inventory.setItem(10, empty);
		}
		ItemStack questpost = new ItemStack(Material.COMPASS, 1);
		ItemMeta questpost_meta = questpost.getItemMeta();
		questpost_meta.setDisplayName("§b§lクエスト投稿");
		questpost_meta.setLore(Arrays.asList(new String[] {"§eクリックでクエストを投稿します"}));
		questpost.setItemMeta(questpost_meta);
		inventory.setItem(43, questpost);
		player.openInventory(inventory);
	}

}
