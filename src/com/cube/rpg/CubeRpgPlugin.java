package com.cube.rpg;

import com.cube.rpg.simplemon.command.SimpleMonsterCommand;
import com.cube.rpg.simplemon.monster.SimpleMonsterDataManager;
import com.cube.rpg.simplemon.monster.event.MonsterGUIListener;
import com.ndy.module.PluginModuleManager;
import com.ndy.module.impl.IModuleInitializer;
import com.ndy.module.type.ModuleLoadResult;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class CubeRpgPlugin extends JavaPlugin implements IModuleInitializer{

    public static CubeRpgPlugin instance;

    @Override
    public void onEnable() {
        instance = this;

        PluginModuleManager.getManager().registerModule(this, this);
    }

    @Override
    public ModuleLoadResult initialize() {
        Bukkit.getPluginManager().registerEvents(new MonsterGUIListener(), this);
        getCommand("spm").setExecutor(new SimpleMonsterCommand());

        SimpleMonsterDataManager.getManager().loadMonsters();
        SimpleMonsterDataManager.getManager().loadSpawners();

        return ModuleLoadResult.Success;
    }

    @Override
    public void dispose() { }
}
