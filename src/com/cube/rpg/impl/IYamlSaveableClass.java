package com.cube.rpg.impl;

import com.cube.rpg.util.DataManager;

public interface IYamlSaveableClass {
    public void save(DataManager dataManager, Object... objects);
}
