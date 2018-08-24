package com.cube.rpg.simplemon.monster.equip;

import com.cube.rpg.impl.IYamlSaveableClass;
import com.cube.rpg.simplemon.monster.SimpleMonsterDataManager;
import com.cube.rpg.util.DataManager;
import com.cube.rpg.util.MonsterItemSerialize;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class SimpleMonsterItem implements IYamlSaveableClass {

    private List<MonsterDropItem> dropItems = new ArrayList<>();

    public SimpleMonsterItem() {}

    public SimpleMonsterItem(List<MonsterDropItem> itemStacks) {
        this.dropItems = itemStacks;
    }

    public SimpleMonsterItem(String name) {
        DataManager dataManager = SimpleMonsterDataManager.getManager().getDataManager();
        this.dropItems = MonsterItemSerialize.deserializes(dataManager.getStringList(name + ".dropItem"));
    }

    public void addDropItem(ItemStack dropItem, boolean copy) {
        if(copy) dropItem = dropItem.clone();
        MonsterDropItem item = new MonsterDropItem(dropItem, 0);
        dropItems.add(item);
    }

    @Override
    public void save(DataManager dataManager, Object... objects) {
        String name = (String) objects[0];
        dataManager.set(name + ".dropItem", MonsterItemSerialize.serializes(this.dropItems));
        DataManager.save(dataManager);
    }

    public MonsterDropItem getItem(int idx) { return this.dropItems.get(idx); }
    public void removeDropItem(int idx) { this.dropItems.remove(idx); }
    public List<MonsterDropItem> getDropItems() { return dropItems; }

    public static class MonsterDropItem {

        private ItemStack itemStack;
        private int chance;

        public MonsterDropItem(ItemStack itemStack, int chance) {
            this.itemStack = itemStack;
            this.chance = chance;
        }

        public ItemStack getItemStack() { return itemStack; }
        public int getChance() { return chance; }

        public void setChance(int chance) { this.chance = chance; }
    }
}
