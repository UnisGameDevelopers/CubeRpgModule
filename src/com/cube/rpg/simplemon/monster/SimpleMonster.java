package com.cube.rpg.simplemon.monster;

import com.cube.rpg.impl.IYamlSaveableClass;
import com.cube.rpg.simplemon.monster.equip.SimpleMonsterEquip;
import com.cube.rpg.simplemon.monster.equip.SimpleMonsterItem;
import com.cube.rpg.util.DataManager;
import org.bukkit.entity.EntityType;

public class SimpleMonster<T extends EntityType> implements IYamlSaveableClass{

    private T entity;
    private String name;
    private SimpleMonsterEquip equip;
    private SimpleMonsterItem dropItem;
    private int armourPercent = 10, health = 100;

    public SimpleMonster(T entity, String name, SimpleMonsterEquip equip, SimpleMonsterItem dropItem,
                         int armourPercent, int health) {
        this.entity = entity;
        this.name = name;
        this.equip = equip;
        this.dropItem = dropItem;
        this.armourPercent = armourPercent;
        this.health = health;
    }

    public int getArmourPercent() { return armourPercent; }
    public int getHealth() { return health; }
    public T getEntity() { return entity; }

    public void setArmourPercent(int armourPercent) { this.armourPercent = armourPercent; }
    public void setHealth(int health) { this.health = health; }
    public void setEntity(T entity) { this.entity = entity; }
    public void setName(String name) { this.name = name; }
    public SimpleMonsterEquip getEquip() { return equip; }
    public SimpleMonsterItem getDropItem() { return dropItem; }

    @Override
    public void save(DataManager dataManager, Object... objects) {
        EntityType type = entity;
        dataManager.set(name + ".type", type.name());
        dataManager.set(name + ".armourPercent", armourPercent);
        dataManager.set(name + ".health", health);
        equip.save(dataManager, name);
        dropItem.save(dataManager, name);
    }

}
