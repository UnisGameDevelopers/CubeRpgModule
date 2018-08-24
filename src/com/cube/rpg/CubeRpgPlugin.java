package com.cube.rpg;

import com.cube.rpg.simplemon.command.SimpleMonsterCommand;
import com.cube.rpg.simplemon.monster.SimpleMonsterDataManager;
import com.cube.rpg.simplemon.monster.event.MonsterGUIListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class CubeRpgPlugin extends JavaPlugin {

    public static CubeRpgPlugin instance;

    @Override
    public void onEnable() {
        instance = this;

        this.initialize();
    }

    private void initialize() {
        if(!getDataFolder().exists()) getDataFolder().mkdir();

        Bukkit.getPluginManager().registerEvents(new MonsterGUIListener(), this);
        getCommand("spm").setExecutor(new SimpleMonsterCommand());

        SimpleMonsterDataManager.getManager().loadMonsters();
        SimpleMonsterDataManager.getManager().loadSpawners();
    }

}
