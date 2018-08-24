package com.cube.rpg.pagegui;

import com.cube.rpg.CubeRpgPlugin;
import com.cube.rpg.util.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public abstract class PageGUI {

    public static Listener listener;

    protected Inventory inventory;
    private List values;
    private int page, currentPage;
    private Class<?> classType;
    protected String owner;
    protected String title;

    public PageGUI(String owner, String title, int size, Class<?> classType) {
        if(listener == null) {
            listener = new PageClickListener();
            Bukkit.getPluginManager().registerEvents(listener, CubeRpgPlugin.instance);
        }
        this.classType = classType;
        this.inventory = Bukkit.createInventory(null, size, title);
        this.owner = owner;
        this.title = title;
    }

    public <T> void setValues(List<T> values) {
        this.values = values;
        this.setPageAmount(values.size());

    }

    protected void setPageAmount(int valueSize) {
        this.page = (valueSize/45) + 1;
    }

    protected void initializePage() {
        this.inventory.setItem(53, new ItemStack(Material.AIR));
        this.inventory.setItem(45, new ItemStack(Material.AIR));
        if(this.page == 1) return;
        else if(this.currentPage == 1 && this.page > 1) {
            this.inventory.setItem(53, new ItemBuilder(Material.WOOL).setData((short) 13).setDisplayName("§a다음페이지").build());
        }else if(this.currentPage == this.page) {
            this.inventory.setItem(53, new ItemStack(Material.AIR));
            this.inventory.setItem(45, new ItemBuilder(Material.WOOL).setData((short) 5).setDisplayName("§c이전페이지").build());
            return;
        }else if(this.currentPage > 1 && this.page > 1) {
            this.inventory.setItem(45, new ItemBuilder(Material.WOOL).setData((short) 5).setDisplayName("§c이전페이지").build());
            this.inventory.setItem(53, new ItemBuilder(Material.WOOL).setData((short) 13).setDisplayName("§a다음페이지").build());
        }
    }

    public void next() {
        if(this.currentPage != this.page) this.currentPage += 1;
        this.updateGUI();
        this.initializePage();
    }

    public void before() {
        if(this.currentPage != 1 && this.currentPage > 1) this.currentPage -= 1;
        this.initializePage();
        this.updateGUI();
    }

    public <T> List<T> getValues() { return this.values; }
    public int getPage() { return page; }
    public int getCurrentPage() { return currentPage; }
    public void setCurrentPage(int currentPage) { this.currentPage = currentPage; }

    public void open(Player player) {
        player.openInventory(this.inventory);
        try {
            GUISession.addGUIOpeningPlayer(player, this);
        }catch (GUISession.AlreadySessionException e) {
            e.printStackTrace();
        }
    }

    public abstract void updateGUI();

    public static class PageClickListener implements Listener{

        @EventHandler
        public void pageClickEvent(InventoryClickEvent event) {
            if(event.getCurrentItem() == null || event.getCurrentItem().getType() == Material.AIR) return;
            if(event.getRawSlot() >= 54) return;

            Player player = (Player) event.getWhoClicked();
            if(GUISession.isOpened(player)) {
                PageGUI gui = GUISession.getSession(player);
                switch (event.getRawSlot()) {
                    case 45:
                        gui.before();
                        break;
                    case 53:
                        gui.next();
                        break;
                }
            }
        }

        @EventHandler
        public void onInventoryClose(InventoryCloseEvent event) {
            if(GUISession.isOpened((Player) event.getPlayer())) {
                GUISession.removeGUIOpeningPlayer((Player) event.getPlayer());
            }
        }

    }

}
