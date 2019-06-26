package com.cube.rpg.simplemon.util;

import net.minecraft.server.v1_5_R3.EntityLiving;
import net.minecraft.server.v1_5_R3.Packet;
import net.minecraft.server.v1_5_R3.Packet24MobSpawn;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_5_R3.entity.CraftLivingEntity;
import org.bukkit.craftbukkit.v1_5_R3.entity.CraftPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

public class SimpleMonsterPacket {

    private Location location;
    private EntityType type;

    public SimpleMonsterPacket(Location location, EntityType type) {
        this.location = location;
        this.type = type;
    }

    public void sendPacket() {
        Entity entity = location.getWorld().spawnEntity(this.location, EntityType.SPIDER);
        EntityLiving entityLiving = ((CraftLivingEntity) entity).getHandle();

        Packet24MobSpawn packet24MobSpawn = new Packet24MobSpawn(entityLiving);
        packet24MobSpawn.b = this.type.getTypeId();

        send(packet24MobSpawn);
    }

    public static void send(Packet packet) {
        for(Player player : Bukkit.getOnlinePlayers())
            ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
    }

}
