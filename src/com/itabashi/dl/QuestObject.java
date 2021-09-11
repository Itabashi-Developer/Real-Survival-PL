package com.itabashi.dl;

import org.apache.commons.lang.RandomStringUtils;

import java.util.UUID;

public class QuestObject {

	public String postName;
	public String lore1;
	public String lore2;
	public String lore3;
	public String lore4;
	public String lore5_id;
	public UUID ownerUUID;
	public boolean open;

	public QuestObject(String postName, String lore1, String lore2, String lore3, String lore4, UUID uuid, boolean open) {
		this.postName = postName;
		this.lore1 = lore1;
		this.lore2 = lore2;
		this.lore3 = lore3;
		this.lore4 = lore4;
		this.ownerUUID = uuid;
		this.open = open;
		boolean b = true;
		String a = null;
		while (b) {
			a = RandomStringUtils.randomAlphanumeric(7).toLowerCase();
			if (!QuestHandler.OBJECT.contains(a)) {
				this.lore5_id = a;
				b = false;
			}
		}
	}

	public QuestObject(String postName, String lore1, String lore2, String lore3, String lore4, UUID uuid, boolean open, String id) {
		this.postName = postName;
		this.lore1 = lore1;
		this.lore2 = lore2;
		this.lore3 = lore3;
		this.lore4 = lore4;
		this.ownerUUID = uuid;
		this.open = open;
		this.lore5_id = id;
	}

	public QuestObject(String postName, UUID uuid) {
		this(postName, "[未設定]", "[未設定]", "[未設定]", "[未設定]", uuid, false);
	}

}
