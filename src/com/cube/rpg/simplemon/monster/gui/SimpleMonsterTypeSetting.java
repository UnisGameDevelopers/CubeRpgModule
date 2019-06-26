package com.cube.rpg.simplemon.monster.gui;

import com.cube.rpg.simplemon.util.SimpleMonsterBuilder;
import com.ndy.util.ItemBuilder;
import com.ndy.util.gui.PageGUI;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;

public class SimpleMonsterTypeSetting extends PageGUI {

    private SimpleMonsterBuilder builder;
    private EntityType[] types = new EntityType[] {
            EntityType.PIG, EntityType.ZOMBIE, EntityType.BLAZE, EntityType.COW, EntityType.CHICKEN,
            EntityType.CREEPER, EntityType.IRON_GOLEM, EntityType.SKELETON, EntityType.SPIDER,
            EntityType.SHEEP
    };

    public SimpleMonsterTypeSetting(String owner, String title, int size, SimpleMonsterBuilder builder) {
        super(owner, title, size, Object.class);

        this.builder = builder;
    }

    public EntityType getEntityType(int idx) { return types[idx]; }
    public SimpleMonsterBuilder getBuilder() { return builder; }

    @Override
    public void updateGUI() {
        for(int i = 0; i < types.length; i++) {
            super.inventory.setItem(i, new ItemBuilder(Material.BOOK).setDisplayName(types[i].getName()).build());
        }

        super.inventory.setItem(45, new ItemBuilder(Material.FIRE).setDisplayName("§c뒤로가기"));
    }
}
