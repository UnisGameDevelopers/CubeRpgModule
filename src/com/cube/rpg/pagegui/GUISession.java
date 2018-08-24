package com.cube.rpg.pagegui;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class GUISession {

    private static Map<Player, PageGUI> session = new HashMap<>();

    public static void addGUIOpeningPlayer(Player player, PageGUI gui) throws AlreadySessionException {
        if(session.containsKey(player)) throw new AlreadySessionException();
        session.put(player, gui);
    }

    public static void removeGUIOpeningPlayer(Player player) {
        if(session.containsKey(player)) session.remove(player);
    }

    public static PageGUI getSession(Player player) {
        if(isOpened(player)) return session.get(player);
        return null;
    }

    public static Map<Player, PageGUI> getSession() { return session; }

    public static boolean isOpened(Player player) { return session.containsKey(player); }

    public static class AlreadySessionException extends Exception {
        public AlreadySessionException() {
            super("이미 세션에 추가된 플레이어입니다.");
        }
    }

}
