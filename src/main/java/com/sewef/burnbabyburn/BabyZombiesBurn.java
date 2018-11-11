package com.sewef.burnbabyburn;

import java.util.Random;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.common.MinecraftForge;

import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;

import net.minecraft.util.math.BlockPos;
import net.minecraft.item.ItemStack;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.item.EntityBoat;

public class BabyZombiesBurn {
    
	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
	}
     
     
	@SubscribeEvent
	public void entityUpdate(LivingEvent.LivingUpdateEvent event) {
		EntityZombie zombie;
		if (event.getEntity() instanceof EntityZombie
				&& (zombie = (EntityZombie) event.getEntity()).getEntityWorld().isDaytime() && !zombie.getEntityWorld().isRemote && zombie.isChild()) {
			BlockPos blockpos;
			float f = zombie.getBrightness(0);
			//float f = zombie.getBrightness();
			BlockPos blockPos = blockpos = zombie.getRidingEntity() instanceof EntityBoat ? new BlockPos(zombie.posX,
					(double) Math.round(zombie.posY), zombie.posZ).up() : new BlockPos(zombie.posX, (double) Math.round(zombie.posY), zombie.posZ);
			if (f > 0.5f && zombie.getEntityWorld().rand.nextFloat() * 30.0f < (f - 0.4f) * 2.0f && zombie.getEntityWorld().canSeeSky(blockpos)) {
				boolean flag = true;
				ItemStack itemstack = zombie.getItemStackFromSlot(EntityEquipmentSlot.HEAD);
				if (itemstack != null) {
					if (itemstack.isItemStackDamageable()) {
						itemstack.setItemDamage(itemstack.getItemDamage() + zombie.getEntityWorld().rand.nextInt(2));
						if (itemstack.getItemDamage() >= itemstack.getMaxDamage()) {
							zombie.renderBrokenItemStack(itemstack);
							zombie.setItemStackToSlot(EntityEquipmentSlot.HEAD, null);
						}
					}
					flag = false;
				}
				if (flag) {
					zombie.setFire(8);
				}
			}
		}
	}

	public static Object instance;

	public void load(FMLInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(this);
	}

}
