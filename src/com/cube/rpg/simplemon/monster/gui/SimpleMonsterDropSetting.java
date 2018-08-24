package com.cube.rpg.simplemon.monster.gui;

import com.cube.rpg.pagegui.PageGUI;
import com.cube.rpg.simplemon.monster.equip.SimpleMonsterItem;
import com.cube.rpg.simplemon.util.SimpleMonsterBuilder;
import com.cube.rpg.util.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class SimpleMonsterDropSetting extends PageGUI {

    private SimpleMonsterBuilder builder;
    private int idx = -1;

    public SimpleMonsterDropSetting(String owner, String title, int size, SimpleMonsterBuilder builder) {
        super(owner, title, size, Object.class);

        this.builder = builder;
        setCurrentPage(1);
    }

    public SimpleMonsterBuilder getBuilder() { return builder; }
    public int getIdx() { return idx; }
    public void setIdx(int idx) { this.idx = idx; }

    @Override
    public void updateGUI() {
        List<SimpleMonsterItem.MonsterDropItem> values = this.builder.getDropItems().getDropItems();
        List<ItemStack> items = new ArrayList<>();
        super.setPageAmount(values.size());
        for(int i = 45*(super.getCurrentPage()-1); i < 45*super.getCurrentPage(); i++) {
            if(i >= values.size()) {
                items.add(new ItemStack(Material.AIR));
                continue;
            }
            SimpleMonsterItem.MonsterDropItem value = values.get(i);
            ItemStack itemStack = value.getItemStack().clone();
            ItemMeta meta = itemStack.getItemMeta();
            List<String> lore = meta.hasLore() ? meta.getLore() : new ArrayList<>();
            lore.add("§6드랍확률: §f" + value.getChance());
            meta.setLore(lore);
            itemStack.setItemMeta(meta);

            items.add(itemStack);
        }

        super.inventory.setItem(45, new ItemBuilder(Material.FIRE).setDisplayName("§c뒤로가기"));
        for(int i = 0; i < super.inventory.getSize()-9; i++) super.inventory.setItem(i, items.get(i));
        super.initializePage();
    }
}
