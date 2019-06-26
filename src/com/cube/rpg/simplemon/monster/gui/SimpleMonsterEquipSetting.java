package com.cube.rpg.simplemon.monster.gui;

import com.cube.rpg.simplemon.util.SimpleMonsterBuilder;
import com.ndy.util.GuiManager;
import com.ndy.util.ItemBuilder;
import com.ndy.util.gui.PageGUI;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class SimpleMonsterEquipSetting extends PageGUI {

    private SimpleMonsterBuilder builder;
    private int slot = -1;

    public SimpleMonsterEquipSetting(String owner, String title, int size, SimpleMonsterBuilder builder) {
        super(owner, title, size, Object.class);

        this.builder = builder;
        setCurrentPage(1);
    }

    public SimpleMonsterBuilder getBuilder() { return builder; }

    public int getSlot() { return slot; }
    public void setSlot(int slot) { this.slot = slot; }

    public void change(int slot, ItemStack itemStack) {
        switch (slot) {
            case 10:
                getBuilder().getEquipment().setHead(itemStack);
                break;
            case 12:
                getBuilder().getEquipment().setChestplate(itemStack);
                break;
            case 14:
                getBuilder().getEquipment().setLeggings(itemStack);
                break;
            case 16:
                getBuilder().getEquipment().setBoots(itemStack);
                break;
            case 40:
                getBuilder().getEquipment().setHand(itemStack);
                break;
        }
    }

    @Override
    public void updateGUI() {
        GuiManager guiManager = new GuiManager(inventory);
        guiManager.setLoopItem(0, new ItemBuilder(34).setDisplayName(" ").build());
        //10 12 14 16 40

        guiManager.setItem(10, getBuilder().getEquipment().getHead())
                .setItem(12, getBuilder().getEquipment().getChestplate())
                .setItem(14, getBuilder().getEquipment().getLeggings())
                .setItem(16, getBuilder().getEquipment().getBoots())
                .setItem(40, getBuilder().getEquipment().getHead());

        super.inventory.setItem(45, new ItemBuilder(Material.FIRE).setDisplayName("§c뒤로가기"));

    }

}
