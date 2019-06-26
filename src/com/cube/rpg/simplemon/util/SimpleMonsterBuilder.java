package com.cube.rpg.simplemon.util;

import com.cube.rpg.simplemon.monster.SimpleMonster;
import com.cube.rpg.simplemon.monster.SimpleMonsterDataManager;
import com.cube.rpg.simplemon.monster.equip.SimpleMonsterEquip;
import com.cube.rpg.simplemon.monster.equip.SimpleMonsterItem;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;

public class SimpleMonsterBuilder {

    private static final int DEFAULT_ARMOUR = 10;
    private static final int DEFAULT_HEALTH = 100;

    private SimpleMonster<EntityType> monster;

    public SimpleMonsterBuilder(String name) {
        this.monster = new SimpleMonster<EntityType>(EntityType.PIG, name, new SimpleMonsterEquip(), new SimpleMonsterItem(),
                DEFAULT_ARMOUR, DEFAULT_HEALTH);
    }

    public SimpleMonsterBuilder(SimpleMonster<?> monster) {
        /* Monster Data Loaded */
    }

    public SimpleMonsterBuilder setEntityType(EntityType entityType) {
        monster.setEntity(entityType);
        return this;
    }

    public SimpleMonsterBuilder setName(String name) {
        monster.setName(name);
        return this;
    }

    public SimpleMonsterBuilder setHealth(int health) {
        monster.setHealth(health);
        return this;
    }

    public SimpleMonsterBuilder setArmourPercent(int armour) {
        monster.setArmourPercent(armour);
        return this;
    }

    public SimpleMonsterBuilder setHead(ItemStack head) {
        monster.getEquip().setHead(head);
        return this;
    }

    public SimpleMonsterBuilder setChestPlate(ItemStack chestPlate) {
        monster.getEquip().setChestplate(chestPlate);
        return this;
    }

    public SimpleMonsterBuilder setLeggings(ItemStack leggings) {
        monster.getEquip().setLeggings(leggings);
        return this;
    }

    public SimpleMonsterBuilder setBoots(ItemStack boots) {
        monster.getEquip().setBoots(boots);
        return this;
    }

    public SimpleMonsterBuilder setHand(ItemStack hand) {
        monster.getEquip().setHead(hand);
        return this;
    }

    public SimpleMonsterBuilder addDropItem(ItemStack itemStack, boolean copy) {
        monster.getDropItem().addDropItem(itemStack, copy);
        return this;
    }

    public SimpleMonsterBuilder removeDropItem(int idx) {
        monster.getDropItem().removeDropItem(idx);
        return this;
    }

    public SimpleMonsterEquip getEquipment() { return monster.getEquip(); }
    public SimpleMonsterItem getDropItems() { return monster.getDropItem(); }

    public SimpleMonster<EntityType> build(boolean isPermanent) {
        if(isPermanent) {
            SimpleMonsterDataManager.getManager().addMonsterInstance(this.monster);
            monster.save(SimpleMonsterDataManager.getManager().getMonsterDataManager());
        }
        return monster;
    }

}
