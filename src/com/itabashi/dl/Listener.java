package com.itabashi.dl;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

public class Listener implements org.bukkit.event.Listener {

	@EventHandler
	public void onExplode(EntityExplodeEvent event) {
		if (event.getEntityType() == EntityType.CREEPER) {
			event.blockList().clear();
		}
	}

	@EventHandler
	public void onDamage(EntityDamageByEntityEvent event) {
		if ((event.getDamager().getType() == EntityType.PLAYER || event.getDamager().getType() == EntityType.ARROW) && event.getEntity().getType() == EntityType.PLAYER) {
			if (event.getDamager().getType() == EntityType.PLAYER) {
				event.setCancelled(true);
				if (((Player) event.getDamager()).getInventory().getItemInMainHand().getType() == Material.STICK && event.getDamager().hasPermission("admin")) {
					event.getDamager().sendMessage(Main.PREFIX + "§aYou hit a player to determine a illegal client.");
					((Player) event.getDamager()).performCommand("ackb " + event.getEntity().getName());
					return;
				}
				event.getDamager().sendMessage(Main.PREFIX + "Currently, you are no longer allowed to damage a player.");
			}
		}
	}

	@EventHandler
	public void pro(ProjectileHitEvent hit) {
		if (hit.getEntity().getShooter() instanceof Player && hit.getHitEntity() != null && hit.getHitEntity().getType() == EntityType.PLAYER) {
			hit.setCancelled(true);
			((Player) hit.getEntity().getShooter()).sendMessage(Main.PREFIX + "Currently, you are no longer allowed to hit a player.");
		}
	}

	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		player.sendMessage(Main.PREFIX + "ようこそ、 " + player.getName() + " さん！\n最新機能を /verify で確認しよう！");
	}

	@EventHandler
	public void onClick(PlayerInteractEvent event) {
		if (event.getPlayer().getInventory().getItemInMainHand().getType() == Material.STICK) {
			if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
				new MenuInventory().openInventory(event.getPlayer());
			}
		}
	}

	@EventHandler
	public void on(BlockBreakEvent event) {
		if (event.getBlock().getRelative(BlockFace.DOWN).getType() == Material.FARMLAND) {
			if (event.getPlayer().getInventory().contains(Material.WHEAT_SEEDS)) {
				ItemStack itemStack = event.getPlayer().getInventory().getItem(event.getPlayer().getInventory().first(Material.WHEAT_SEEDS));
				itemStack.setAmount(itemStack.getAmount() - 1);
				Bukkit.getScheduler().runTaskLater(Main.PLUGIN, new Runnable() {

					@Override
					public void run() {
						event.getBlock().setType(Material.WHEAT);
					}

				}, 5L);
			}
		}
	}

	@EventHandler
	public void onInventoryClick(InventoryClickEvent event) {
		if (event.getView().getTitle().equalsIgnoreCase("§6§lメニュー")) {
			event.setCancelled(true);
			if (!(event.getCurrentItem() == null)) {
				if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§c§l閉じる")) {
					event.getWhoClicked().closeInventory();
					event.getWhoClicked().sendMessage(Main.PREFIX + "メニューを閉じました！");
				}
				else if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§a§lホーム機能")) {
					event.getWhoClicked().closeInventory();
					event.getWhoClicked().sendMessage(Main.PREFIX + "家にテレポートしました！");
					event.getWhoClicked().teleport(new Location(Bukkit.getWorld("world"), 41, 64, 152));
				}
				else if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§d§lクエスト一覧")) {
					event.getWhoClicked().closeInventory();
					new QuestInventory().openInventory((Player) event.getWhoClicked());
				}
				else if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§c§lEveryone機能")) {
					event.getWhoClicked().closeInventory();
					event.getWhoClicked().sendMessage(Main.PREFIX + "注目を集めました");
					Bukkit.broadcastMessage("§6========================\n§c§lお知らせ: §b§lBy " + event.getWhoClicked().getName() + "\n§6========================");
					for (Player player : Bukkit.getOnlinePlayers()) {
						player.sendTitle("§c§lAttention!", "§b§l" + event.getWhoClicked().getName() + " が行いました", 0, 60, 0);
					}
				}
				else if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§a§lワープ機能")) {
					event.getWhoClicked().closeInventory();
					new WarpInventory().openInventory((Player) event.getWhoClicked());
				}
			}
		}
		else if (event.getView().getTitle().equalsIgnoreCase("§a§lワープメニュー")) {
			event.setCancelled(true);
			if (!(event.getCurrentItem() == null)) {
				if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§c§l戻る")) {
					event.getWhoClicked().closeInventory();
					new MenuInventory().openInventory((Player) event.getWhoClicked());
				}
				else if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§a§lホーム")) {
					event.getWhoClicked().closeInventory();
					event.getWhoClicked().sendMessage(Main.PREFIX + "家にテレポートしました！");
					event.getWhoClicked().teleport(new Location(Bukkit.getWorld("world"), 41, 64, 152));
				}
				else if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§b§lエンドポータル")) {
					event.getWhoClicked().closeInventory();
					event.getWhoClicked().sendMessage(Main.PREFIX + "エンドポータルにテレポートしました！");
					event.getWhoClicked().teleport(new Location(Bukkit.getWorld("world"), 1585.5, 33.5, 1705.5));
				}
				else if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6§lエンダーマントラップ")) {
					event.getWhoClicked().closeInventory();
					event.getWhoClicked().sendMessage(Main.PREFIX + "エンダーマントラップにテレポートしました！");
					event.getWhoClicked().teleport(new Location(Bukkit.getWorld("world_the_end"), 264.5, 54, -7.5));
				}
			}
		}
		else if (event.getView().getTitle().equalsIgnoreCase("§d§lクエストメニュー")) {
			event.setCancelled(true);
			if (!(event.getCurrentItem() == null)) {
				if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§c§l戻る")) {
					event.getWhoClicked().closeInventory();
					new MenuInventory().openInventory((Player) event.getWhoClicked());
				}
				else if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§b§lクエスト投稿")) {
					event.getWhoClicked().closeInventory();
					new QuestPostInventory().openInventory((Player) event.getWhoClicked());
				}
				else if (event.getCurrentItem().getType() == Material.WOODEN_SWORD) {
					Player player = (Player) event.getWhoClicked();
					String individual_id = event.getCurrentItem().getItemMeta().getLore().get(4).substring(6, 13);
					QuestObject quest = QuestHandler.OBJECT.get(individual_id);
					if (!(quest == null)) {
						if (player.getUniqueId().equals(quest.ownerUUID)) {
							player.closeInventory();
							new QuestPostInventory().openRemove(player, quest);
						}
					}
				}
			}
		}
		else if (event.getView().getTitle().equalsIgnoreCase("§c§lクエスト削除画面")) {
			event.setCancelled(true);
			if (!(event.getCurrentItem() == null)) {
				Player player = (Player) event.getWhoClicked();
				String individual_id = event.getClickedInventory().getItem(4).getItemMeta().getLore().get(4).substring(6, 13);
				if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§a§lキャンセル")) {
					player.closeInventory();
					player.sendMessage(Main.PREFIX + "削除をキャンセルしました");
				}
				else if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§c§l削除")) {
					player.closeInventory();
					player.sendMessage(Main.PREFIX + "削除しました！");
					QuestHandler.OBJECT.get(individual_id).open = false;
				}
			}
		}
		else if (event.getView().getTitle().equalsIgnoreCase("§b§lクエスト投稿画面")) {
			event.setCancelled(true);
			if (!(event.getCurrentItem() == null)) {
				Player player = (Player) event.getWhoClicked();
				String individual_id = event.getClickedInventory().getItem(11).getItemMeta().getLore().get(0).substring(6, 13);
				if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§c§l戻る")) {
					event.getWhoClicked().closeInventory();
					new QuestInventory().openInventory((Player) event.getWhoClicked());
				}
				else if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§b§l説明文 1行目")) {
					event.getWhoClicked().closeInventory();
					QuestHandler.P_OB.put(player.getUniqueId(), individual_id);
					QuestHandler.P_OB1.put(player.getUniqueId(), 1);
					player.sendMessage(Main.PREFIX + "14文字以内でチャットに入力してください！");
				}
				else if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§b§l説明文 2行目")) {
					event.getWhoClicked().closeInventory();
					QuestHandler.P_OB.put(player.getUniqueId(), individual_id);
					QuestHandler.P_OB1.put(player.getUniqueId(), 2);
					player.sendMessage(Main.PREFIX + "14文字以内でチャットに入力してください！");
				}
				else if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§b§l説明文 3行目")) {
					event.getWhoClicked().closeInventory();
					QuestHandler.P_OB.put(player.getUniqueId(), individual_id);
					QuestHandler.P_OB1.put(player.getUniqueId(), 3);
					player.sendMessage(Main.PREFIX + "14文字以内でチャットに入力してください！");
				}
				else if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§b§l説明文 4行目")) {
					event.getWhoClicked().closeInventory();
					QuestHandler.P_OB.put(player.getUniqueId(), individual_id);
					QuestHandler.P_OB1.put(player.getUniqueId(), 4);
					player.sendMessage(Main.PREFIX + "14文字以内でチャットに入力してください！");
				}
				else if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§a§l投稿する")) {
					event.getWhoClicked().closeInventory();
					QuestObject questObject = QuestHandler.OBJECT.get(individual_id);
					if (questObject.lore1.equalsIgnoreCase("[未設定]")) {
						questObject.lore1 = "";
					}
					if (questObject.lore2.equalsIgnoreCase("[未設定]")) {
						questObject.lore2 = "";
					}
					if (questObject.lore3.equalsIgnoreCase("[未設定]")) {
						questObject.lore3 = "";
					}
					if (questObject.lore4.equalsIgnoreCase("[未設定]")) {
						questObject.lore4 = "";
					}
					questObject.open = true;
					player.sendMessage(Main.PREFIX + "投稿を完了しました！");
					new QuestInventory().openInventory(player);
				}
			}
		}
	}

	@EventHandler
	public void onChat(AsyncPlayerChatEvent event) {
		String a = QuestHandler.P_OB.get(event.getPlayer().getUniqueId());
		if (!(a == null)) {
			event.setCancelled(true);
			if (event.getMessage().length() > 14) {
				event.getPlayer().sendMessage(Main.PREFIX + "14文字を超えるので説明の変更に失敗しました。");
			}
			else {
				int b = QuestHandler.P_OB1.get(event.getPlayer().getUniqueId());
				event.getPlayer().sendMessage(Main.PREFIX + b + "行目の説明文を\n" + event.getMessage() + "\nに変更しました！");
				if (b == 1) {
					QuestHandler.OBJECT.get(a).lore1 = event.getMessage();
				}
				else if (b == 2) {
					QuestHandler.OBJECT.get(a).lore2 = event.getMessage();
				}
				else if (b == 3) {
					QuestHandler.OBJECT.get(a).lore3 = event.getMessage();
				}
				else if (b == 4) {
					QuestHandler.OBJECT.get(a).lore4 = event.getMessage();
				}
				Bukkit.getScheduler().runTask(Main.PLUGIN, new Runnable() {

					@Override
					public void run() {
						new QuestPostInventory().openInventory(event.getPlayer(), QuestHandler.OBJECT.get(a));
					}
				});
			}
			QuestHandler.P_OB.remove(event.getPlayer().getUniqueId());
			QuestHandler.P_OB1.remove(event.getPlayer().getUniqueId());
		}
		else {
			event.setFormat(ChatColor.GREEN + "<" + event.getPlayer().getWorld().getName() + ">" + ChatColor.GRAY+ event.getPlayer().getName() + ChatColor.WHITE + ": " + ChatColor.WHITE + ChatColor.translateAlternateColorCodes('&', event.getMessage()));
		}
	}

}
