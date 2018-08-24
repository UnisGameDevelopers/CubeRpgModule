package com.cube.rpg.simplemon.monster.equip;

import com.cube.rpg.impl.IYamlSaveableClass;
import com.cube.rpg.simplemon.monster.SimpleMonsterDataManager;
import com.cube.rpg.util.DataManager;
import com.cube.rpg.util.ItemSerialize;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class SimpleMonsterEquip implements IYamlSaveableClass {

    private ItemStack head, chestplate, leggings, boots, hand;

    public SimpleMonsterEquip() {

    }
    
    public SimpleMonsterEquip(String name) {
        DataManager dataManager = SimpleMonsterDataManager.getManager().getDataManager();
        this.head = new ItemSerialize(dataManager.getString(name + ".equipment.head")).deserialize();
        this.chestplate = new ItemSerialize(dataManager.getString(name + ".equipment.chestplate")).deserialize();
        this.leggings = new ItemSerialize(dataManager.getString(name + ".equipment.leggings")).deserialize();
        this.boots = new ItemSerialize(dataManager.getString(name + ".equipment.boots")).deserialize();
        this.hand = new ItemSerialize(dataManager.getString(name + ".equipment.hand")).deserialize();
    }

    public SimpleMonsterEquip(ItemStack head, ItemStack chestplate, ItemStack leggings, ItemStack boots, ItemStack hand) {
        this.head = head;
        this.chestplate = chestplate;
        this.leggings = leggings;
        this.boots = boots;
        this.head = head;
    }

    public ItemStack getHead() { return head == null ? air() : head; }
    public ItemStack getBoots() { return boots == null ? air() : boots; }
    public ItemStack getChestplate() { return chestplate == null ? air() : chestplate; }
    public ItemStack getLeggings() { return leggings == null ? air() : leggings; }
    public ItemStack getHand() { return hand == null ? air() : hand; }
    public ItemStack air() { return new ItemStack(Material.AIR); }

    public void setHead(ItemStack head) { this.head = head; }
    public void setBoots(ItemStack boots) { this.boots = boots; }
    public void setChestplate(ItemStack chestplate) { this.chestplate = chestplate; }
    public void setHand(ItemStack hand) { this.hand = hand; }
    public void setLeggings(ItemStack leggings) { this.leggings = leggings; }

    @Override
    public void save(DataManager dataManager, Object... objects) { 
        String name = (String) objects[0];
        dataManager.set(name + ".equipment.head", new ItemSerialize(getHead()).serialize());
        dataManager.set(name + ".equipment.chestplate", new ItemSerialize(getChestplate()).serialize());
        dataManager.set(name + ".equipment.leggings", new ItemSerialize(getLeggings()).serialize());
        dataManager.set(name + ".equipment.boots", new ItemSerialize(getBoots()).serialize());
        dataManager.set(name + ".equipment.hand", new ItemSerialize(getHand()).serialize());
        DataManager.save(dataManager);
    }
}

