package com.itabashi.dl;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

public class Main extends JavaPlugin {

	public static final String PREFIX = ChatColor.GOLD.toString() + "[" + ChatColor.GREEN.toString() + "Survival" + ChatColor.GOLD.toString() + "] " + ChatColor.AQUA.toString();
	public static JavaPlugin PLUGIN;

	@Override
	public void onEnable() {
		Bukkit.getPluginManager().registerEvents(new Listener(), this);
		PLUGIN = this;
		ConfigProcess.onEnable();
		for (Player player : Bukkit.getOnlinePlayers()) {
			player.sendMessage(Main.PREFIX + "システムのアップデートが完了されました！\n最新機能を /verify で確認しよう！");
		}
	}

	@Override
	public void onDisable() {
		ConfigProcess.onDisable();
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (command.getName().equalsIgnoreCase("verify")) {
			sender.sendMessage(PREFIX + "新機能情報\n・プロキシの最適化\n・/proxy コマンドの追加\n§cThis is a just legacy command, maybe you will be not able to perform this command in the future.");
		}
		else if (command.getName().equalsIgnoreCase("getstick")) {
			if (sender instanceof Player) {
				Player player = (Player) sender;
				player.sendMessage(PREFIX + "棒は自分で作成してください！");
			}
			else {
				sender.sendMessage(PREFIX + "プレイヤーで実行してください！");
			}
		}
		else if (command.getName().equalsIgnoreCase("ackb")) {
			if (sender.hasPermission("admin")) {
				if (args.length >= 1) {
					Player player = Bukkit.getPlayer(args[0]);
					if (!(player == null)) {
						Location location = player.getLocation();
						player.setVelocity(new Vector(0.4D, 0.0D, 0.0D));
						Bukkit.getScheduler().runTaskLater(this, new Runnable() {

							@Override
							public void run() {
								double knockBackDistance = Math.abs(location.getX() - player.getLocation().getX());
								sender.sendMessage(PREFIX + player.getName() + " のノックバックは" + ((knockBackDistance > 0.75D) ? "正常" : "異常") + "です。\nノックバック距離: " + knockBackDistance + "m");
							}

						}, 8L);
					}
					else {
						sender.sendMessage(PREFIX + "§cSyntax: /ackb <player>");
					}
				}
				else {
					sender.sendMessage(PREFIX + "§cSyntax: /ackb <player>");
				}
			}
			else {
				sender.sendMessage(PREFIX + "§cYou are not allowed to execute this command.");
			}
		}
		else if (command.getName().equalsIgnoreCase("house")) {
			if (sender instanceof Player) {
				Player player = (Player) sender;
				player.teleport(new Location(Bukkit.getWorld("world"), 41, 64, 152));
				player.sendMessage(PREFIX + "You have been teleported to house.");
			}
			else {
				sender.sendMessage(PREFIX + "§cPlease execute this command as a player.");
			}
		}
		else if (command.getName().equalsIgnoreCase("proxy")) {
			sender.sendMessage("§9The server is running in proxy [ch-ddos-11].");
		}
		return true;
	}

}