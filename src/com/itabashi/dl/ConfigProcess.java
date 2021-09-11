package com.itabashi.dl;

import org.bukkit.configuration.ConfigurationSection;

import java.util.UUID;

public class ConfigProcess {

	public static final CustomConfig SERVER_DATA = new CustomConfig();

	public static void onEnable() {
		SERVER_DATA.initializeInstance("ServerData.yml", "ServerData");
		SERVER_DATA.reloadConfiguration();
		SERVER_DATA.saveConfiguration();
		ConfigurationSection section = Resource.getConfigurationSection(".Quest", SERVER_DATA);
		if (section != null) {
			for (String id : section.getKeys(false)) {
				String name = (String) Resource.get("Quest." + id + ".name", SERVER_DATA);
				String lore1 = (String) Resource.get("Quest." + id + ".lore1", SERVER_DATA);
				String lore2 = (String) Resource.get("Quest." + id + ".lore2", SERVER_DATA);
				String lore3 = (String) Resource.get("Quest." + id + ".lore3", SERVER_DATA);
				String lore4 = (String) Resource.get("Quest." + id + ".lore4", SERVER_DATA);
				String lore5_id = id;
				UUID uuid = UUID.fromString((String) Resource.get("Quest." + id + ".uuid", SERVER_DATA));
				QuestHandler.OBJECT.put(id, new QuestObject(name, lore1, lore2, lore3, lore4, uuid, (boolean) Resource.get("Quest." + id + ".open", SERVER_DATA), id));
			}
		}
	}

	public static void onDisable() {
		if (!QuestHandler.OBJECT.values().isEmpty()) {
			for (QuestObject quest : QuestHandler.OBJECT.values()) {
				Resource.set("Quest." + quest.lore5_id + ".name", quest.postName, SERVER_DATA);
				Resource.set("Quest." + quest.lore5_id + ".lore1", quest.lore1, SERVER_DATA);
				Resource.set("Quest." + quest.lore5_id + ".lore2", quest.lore2, SERVER_DATA);
				Resource.set("Quest." + quest.lore5_id + ".lore3", quest.lore3, SERVER_DATA);
				Resource.set("Quest." + quest.lore5_id + ".lore4", quest.lore4, SERVER_DATA);
				Resource.set("Quest." + quest.lore5_id + ".open", quest.open, SERVER_DATA);
				Resource.set("Quest." + quest.lore5_id + ".uuid", quest.ownerUUID.toString(), SERVER_DATA);
			}
		}
		SERVER_DATA.saveConfiguration();
	}

}
