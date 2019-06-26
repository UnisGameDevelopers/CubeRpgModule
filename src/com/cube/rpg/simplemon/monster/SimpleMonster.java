package com.cube.rpg.simplemon.monster;

import com.cube.rpg.impl.IYamlSaveableClass;
import com.cube.rpg.simplemon.monster.equip.SimpleMonsterEquip;
import com.cube.rpg.simplemon.monster.equip.SimpleMonsterItem;
import com.cube.rpg.simplemon.util.SimpleMonsterPacket;
import com.ndy.util.DataManager;
import net.minecraft.server.v1_5_R3.EntityLiving;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Monster;

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
    public String getName() { return name; }

    public void setArmourPercent(int armourPercent) { this.armourPercent = armourPercent; }
    public void setHealth(int health) { this.health = health; }
    public void setEntity(T entity) { this.entity = entity; }
    public void setName(String name) { this.name = name; }
    public SimpleMonsterEquip getEquip() { return equip; }
    public SimpleMonsterItem getDropItem() { return dropItem; }

    public void spawnEntity(Location location) {
        Entity entity = location.getWorld().spawnEntity(location, this.entity);
        LivingEntity livingEntity = (LivingEntity) entity;
        livingEntity.setCustomName(this.name);
        livingEntity.setCustomNameVisible(true);

        livingEntity.getEquipment().setArmorContents(this.equip.getArmourContents());
        livingEntity.getEquipment().setItemInHand(this.equip.getHand());

        livingEntity.getEquipment().setHelmetDropChance(0.0f);
        livingEntity.getEquipment().setChestplateDropChance(0.0f);
        livingEntity.getEquipment().setLeggingsDropChance(0.0f);
        livingEntity.getEquipment().setItemInHandDropChance(0.0f);
        livingEntity.getEquipment().setBootsDropChance(0.0f);

        if(!(entity instanceof Monster)) {
            entity.remove();

            SimpleMonsterPacket packet = new SimpleMonsterPacket(location, this.entity);
            packet.sendPacket();
        }
    }

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
