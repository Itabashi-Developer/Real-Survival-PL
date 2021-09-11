package com.itabashi.dl;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class QuestHandler {

	public static final ConcurrentHashMap<String, QuestObject> OBJECT = new ConcurrentHashMap<>();

	public static final ConcurrentHashMap<UUID, String> P_OB = new ConcurrentHashMap<>();

	public static final ConcurrentHashMap<UUID, Integer> P_OB1 = new ConcurrentHashMap<>();

}
