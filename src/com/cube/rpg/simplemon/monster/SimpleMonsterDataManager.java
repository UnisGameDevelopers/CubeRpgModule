package com.cube.rpg.simplemon.monster;

import com.cube.rpg.simplemon.monster.equip.SimpleMonsterEquip;
import com.cube.rpg.simplemon.monster.equip.SimpleMonsterItem;
import com.cube.rpg.util.DataManager;
import org.bukkit.entity.EntityType;

import java.util.HashSet;
import java.util.Set;

public class SimpleMonsterDataManager {

    private static SimpleMonsterDataManager instance;

    private DataManager monsterDataManager = new DataManager("monster.yml");
    private DataManager spawnerDataManager = new DataManager("spawners.yml");
    private Set<SimpleMonster<?>> monsters = new HashSet<>();
    private Set<SimpleSpawner> spawners = new HashSet<>();

    private SimpleMonsterDataManager() {}

    public static SimpleMonsterDataManager getManager() {
        if(instance == null) instance = new SimpleMonsterDataManager();
        return instance;
    }

    public void loadMonsters() {
        for(String key : monsterDataManager.getKeys(false)) {
            SimpleMonsterEquip equip = new SimpleMonsterEquip(key);
            SimpleMonsterItem dropItem = new SimpleMonsterItem(key);
            EntityType type = EntityType.valueOf(monsterDataManager.getString(key + ".type"));
            int armourPercent = monsterDataManager.getInt(key + ".armourPercent");
            int health = monsterDataManager.getInt(key + ".health");
            SimpleMonster<EntityType> monster = new SimpleMonster<>(type, key, equip, dropItem, armourPercent, health);
            monsters.add(monster);
        }
    }

    public void loadSpawners() {
        for(String key : spawnerDataManager.getKeys(false)) {

        }
    }

    public void addMonsterInstance(SimpleMonster monster) {
        this.monsters.add(monster);
    }

    public DataManager getMonsterDataManager() { return monsterDataManager; }
    public DataManager getSpawnerDataManager() { return spawnerDataManager; }
}
