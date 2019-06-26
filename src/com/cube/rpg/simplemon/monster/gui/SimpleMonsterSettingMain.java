package com.cube.rpg.simplemon.monster.gui;

import com.cube.rpg.simplemon.util.SimpleMonsterBuilder;
import com.ndy.util.ItemBuilder;
import com.ndy.util.gui.PageGUI;
import org.bukkit.Material;

public class SimpleMonsterSettingMain extends PageGUI {

    private SimpleMonsterBuilder builder;

    public SimpleMonsterSettingMain(String owner, String title, int size, SimpleMonsterBuilder builder) {
        super(owner, title, size, Object.class);
        this.builder = builder;
    }

    public SimpleMonsterBuilder getBuilder() { return builder; }

    @Override
    public void updateGUI() {
        inventory.setItem(10, new ItemBuilder(Material.CHEST).setDisplayName("§a드랍 아이템").build());
        inventory.setItem(12, new ItemBuilder(Material.BOOK).setDisplayName("§c몬스터 타입 설정").build());
        inventory.setItem(14, new ItemBuilder(Material.DIAMOND_SWORD).setDisplayName("§e무기/방어구").build());
        inventory.setItem(16, new ItemBuilder(351).setData((short) 10).setDisplayName("§a저장"));
    }
}
