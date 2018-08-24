package com.cube.rpg.simplemon.monster.event;

import com.cube.rpg.pagegui.GUISession;
import com.cube.rpg.simplemon.monster.gui.SimpleMonsterDropSetting;
import com.cube.rpg.simplemon.monster.gui.SimpleMonsterEquipSetting;
import com.cube.rpg.simplemon.monster.gui.SimpleMonsterSettingMain;
import com.cube.rpg.simplemon.monster.gui.SimpleMonsterTypeSetting;
import com.cube.rpg.simplemon.util.SimpleMonsterBuilder;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.ItemStack;

public class MonsterGUIListener implements Listener {

    private static boolean isSetting = false;
    private static SimpleMonsterBuilder builder = null;
    private static int idx = 0;

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event){
        if(event.getInventory().getTitle().equals("몬스터 설정 메뉴")) {
            event.setCancelled(true);
            if(event.getCurrentItem() == null || event.getCurrentItem().getType() == Material.AIR) return;

            SimpleMonsterSettingMain gui = (SimpleMonsterSettingMain) GUISession.getSession((Player) event.getWhoClicked());
            switch (event.getRawSlot()) {
                case 10:
                    SimpleMonsterDropSetting dropSetting = new SimpleMonsterDropSetting(event.getWhoClicked().getName(),
                            "몬스터 드랍 세팅", 54, gui.getBuilder());
                    dropSetting.updateGUI();
                    dropSetting.open((Player) event.getWhoClicked());
                    break;
                case 14:
                    SimpleMonsterEquipSetting equipSetting = new SimpleMonsterEquipSetting(event.getWhoClicked().getName(),
                            "몬스터 방어구 세팅", 54, gui.getBuilder());
                    equipSetting.updateGUI();
                    equipSetting.open((Player) event.getWhoClicked());
                    break;
                case 12:
                    SimpleMonsterTypeSetting typeSetting = new SimpleMonsterTypeSetting(event.getWhoClicked().getName(),
                            "몬스터 타입 세팅", 54, gui.getBuilder());
                    typeSetting.updateGUI();
                    typeSetting.open((Player) event.getWhoClicked());
                    break;
                case 16:
                    gui.getBuilder().build(true);
                    event.getWhoClicked().closeInventory();
                    break;

            }
        }else if(event.getInventory().getTitle().equals("몬스터 드랍 세팅")) {
            event.setCancelled(true);
            if(event.getCurrentItem() == null || event.getCurrentItem().getType() == Material.AIR) return;

            SimpleMonsterDropSetting setting = (SimpleMonsterDropSetting) GUISession.getSession((Player) event.getWhoClicked());
            if(event.getRawSlot() == 45) {
                SimpleMonsterSettingMain settingMain = new SimpleMonsterSettingMain(event.getWhoClicked().getName(), "몬스터 설정 메뉴", 27, setting.getBuilder());
                settingMain.updateGUI();
                settingMain.open((Player) event.getWhoClicked());
                return;
            }
            if(event.getRawSlot() > 53) {
                setting.getBuilder().addDropItem(event.getCurrentItem(), false);
                setting.updateGUI();
                return;
            }
            int idx = event.getRawSlot() + (45 * (setting.getCurrentPage()-1));
            if(event.isShiftClick()) {
                setting.getBuilder().getDropItems().removeDropItem(idx);
                setting.updateGUI();
                return;
            }

            isSetting = true;
            MonsterGUIListener.idx = idx;
            builder = setting.getBuilder();
            event.getWhoClicked().closeInventory();
            ((Player) event.getWhoClicked()).sendMessage("§a설정할 확률을 입력해주세요.");
        }else if(event.getInventory().getTitle().equals("몬스터 방어구 세팅")) {
            event.setCancelled(true);
            if(event.getCurrentItem() == null || event.getCurrentItem().getType() == Material.AIR) return;

            SimpleMonsterEquipSetting setting = (SimpleMonsterEquipSetting) GUISession.getSession((Player) event.getWhoClicked());
            if(event.getRawSlot() == 45) {
                SimpleMonsterSettingMain settingMain = new SimpleMonsterSettingMain(event.getWhoClicked().getName(), "몬스터 설정 메뉴", 27, setting.getBuilder());
                settingMain.updateGUI();
                settingMain.open((Player) event.getWhoClicked());
                return;
            }
            if(event.getRawSlot() > 53) {
                if(setting.getSlot() != -1) {
                    setting.change(setting.getSlot(), event.getCurrentItem());
                    setting.setSlot(-1);
                    setting.updateGUI();
                    return;
                }
                return;
            }
            if(event.getRawSlot() == 10 || event.getRawSlot() == 12 || event.getRawSlot() == 14 || event.getRawSlot() == 16 ||
                    event.getRawSlot() == 40) {
                if(event.getClick().isShiftClick()) {
                    setting.change(event.getSlot(), new ItemStack(Material.AIR));
                    setting.updateGUI();
                    return;
                }

                setting.setSlot(event.getRawSlot());
                ((Player) event.getWhoClicked()).sendMessage("§a바꿀 아이템을 클릭하세요.");
            }
        }else if(event.getInventory().getTitle().equals("몬스터 타입 세팅")) {
            event.setCancelled(true);
            if(event.getCurrentItem() == null || event.getCurrentItem().getType() == Material.AIR) return;
            if(event.getRawSlot() > 53) return;

            SimpleMonsterTypeSetting typeSetting = (SimpleMonsterTypeSetting) GUISession.getSession((Player) event.getWhoClicked());
            if(event.getRawSlot() == 45) {
                SimpleMonsterSettingMain settingMain = new SimpleMonsterSettingMain(event.getWhoClicked().getName(), "몬스터 설정 메뉴", 27, typeSetting.getBuilder());
                settingMain.updateGUI();
                settingMain.open((Player) event.getWhoClicked());
                return;
            }

            EntityType entityType = typeSetting.getEntityType(event.getRawSlot());
            typeSetting.getBuilder().setEntityType(entityType);

            ((Player) event.getWhoClicked()).sendMessage("§a엔티티 타입을 " + entityType.getName() + " 으로 변경하였습니다.");
        }
    }

    @EventHandler
    public void onAsyncChatEvent(AsyncPlayerChatEvent event) {
        if(isSetting){
            event.setCancelled(true);
            isSetting = false;
            builder.getDropItems().getItem(idx).setChance(Integer.parseInt(event.getMessage()));
            SimpleMonsterDropSetting setting = new SimpleMonsterDropSetting(event.getPlayer().getName(), "몬스터 드랍 설정",
                    54, builder);
            setting.updateGUI();
            setting.open(event.getPlayer());

            idx = -1;
            MonsterGUIListener.builder = null;
        }
    }

}
