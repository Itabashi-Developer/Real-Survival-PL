package com.itabashi.dl;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class QuestPostInventory {

	public void openInventory(Player player) {
		QuestObject quest = new QuestObject(player.getName(), player.getUniqueId());
		QuestHandler.OBJECT.put(quest.lore5_id, quest);
		openInventory(player, quest);
	}

	public void openRemove(Player player, QuestObject quest) {
		Inventory inventory = Bukkit.createInventory(null, 9, "§c§lクエスト削除画面");
		ItemStack quest1 = new ItemStack(Material.WOODEN_SWORD, 1);
		ItemMeta quest1_meta = quest1.getItemMeta();
		quest1_meta.setDisplayName("§a§l" + quest.postName);
		quest1_meta.setLore(Arrays.asList(new String[]{"§e" + quest.lore1, "§e" + quest.lore2, "§e" + quest.lore3, "§e" + quest.lore4, "§5ID: " + quest.lore5_id}));
		quest1.setItemMeta(quest1_meta);
		inventory.setItem(4, quest1);
		ItemStack accept = new ItemStack(Material.LIME_WOOL, 1);
		ItemMeta accept_meta = accept.getItemMeta();
		accept_meta.setDisplayName("§c§l削除");
		accept_meta.setLore(Arrays.asList(new String[] {"§eクリックで削除します", "§c§l§o§n注意: 一度消すと元に戻せません！"}));
		accept.setItemMeta(accept_meta);
		inventory.setItem(0, accept);
		ItemStack deny = new ItemStack(Material.RED_WOOL, 1);
		ItemMeta deny_meta = deny.getItemMeta();
		deny_meta.setDisplayName("§a§lキャンセル");
		deny_meta.setLore(Arrays.asList(new String[] {"§eクリックでキャンセルします"}));
		deny.setItemMeta(deny_meta);
		inventory.setItem(8, deny);
		player.openInventory(inventory);
	}

	public void openInventory(Player player, QuestObject quest) {
		Inventory inventory = Bukkit.createInventory(null, 27, "§b§lクエスト投稿画面");
		ItemStack close = new ItemStack(Material.ARROW, 1);
		ItemMeta close_meta = close.getItemMeta();
		close_meta.setDisplayName("§c§l戻る");
		close_meta.setLore(Arrays.asList(new String[] {"§eクリックで戻ります"}));
		close.setItemMeta(close_meta);
		inventory.setItem(0, close);
		inventory.setItem(8, close);
		inventory.setItem(18, close);
		inventory.setItem(26, close);
		//
		ItemStack yourPostName = new ItemStack(Material.NAME_TAG, 1);
		ItemMeta yourPostName_meta = yourPostName.getItemMeta();
		yourPostName_meta.setDisplayName("§a§lクエストの名前");
		yourPostName_meta.setLore(Arrays.asList(new String[] {"§eポスト名は、", "§e" + quest.postName, "§eです"}));
		yourPostName.setItemMeta(yourPostName_meta);
		inventory.setItem(10, yourPostName);
		//
		ItemStack postId = new ItemStack(Material.SOUL_TORCH);
		ItemMeta postId_meta = postId.getItemMeta();
		postId_meta.setDisplayName("§6§l割り振りID");
		postId_meta.setLore(Arrays.asList(new String[] {"§eID: " + quest.lore5_id}));
		postId.setItemMeta(postId_meta);
		inventory.setItem(11, postId);
		//1
		ItemStack lore1 = new ItemStack(Material.WRITABLE_BOOK, 1);
		ItemMeta lore1_meta = lore1.getItemMeta();
		lore1_meta.setDisplayName("§b§l説明文 1行目");
		lore1_meta.setLore(Arrays.asList(new String[] {"§e" + quest.lore1, "§eクリックで変更できます"}));
		lore1.setItemMeta(lore1_meta);
		inventory.setItem(12, lore1);
		//2
		ItemStack lore2 = new ItemStack(Material.WRITABLE_BOOK, 1);
		ItemMeta lore2_meta = lore2.getItemMeta();
		lore2_meta.setDisplayName("§b§l説明文 2行目");
		lore2_meta.setLore(Arrays.asList(new String[] {"§e" + quest.lore2, "§eクリックで変更できます"}));
		lore2.setItemMeta(lore2_meta);
		inventory.setItem(13, lore2);
		//3
		ItemStack lore3 = new ItemStack(Material.WRITABLE_BOOK, 1);
		ItemMeta lore3_meta = lore3.getItemMeta();
		lore3_meta.setDisplayName("§b§l説明文 3行目");
		lore3_meta.setLore(Arrays.asList(new String[] {"§e" + quest.lore3, "§eクリックで変更できます"}));
		lore3.setItemMeta(lore3_meta);
		inventory.setItem(14, lore3);
		//4
		ItemStack lore4 = new ItemStack(Material.WRITABLE_BOOK, 1);
		ItemMeta lore4_meta = lore4.getItemMeta();
		lore4_meta.setDisplayName("§b§l説明文 4行目");
		lore4_meta.setLore(Arrays.asList(new String[] {"§e" + quest.lore4, "§eクリックで変更できます"}));
		lore4.setItemMeta(lore4_meta);
		inventory.setItem(15, lore4);
		//22
		ItemStack confirm = new ItemStack(Material.LIME_WOOL, 1);
		ItemMeta confirm_meta = confirm.getItemMeta();
		confirm_meta.setDisplayName("§a§l投稿する");
		confirm_meta.setLore(Arrays.asList(new String[] {"§eクリックで投稿します", "§c§l§o§n注意: 投稿は多くの人に閲覧されます！"}));
		confirm.setItemMeta(confirm_meta);
		inventory.setItem(22, confirm);
		player.openInventory(inventory);
	}

}
