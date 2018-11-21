package com.sewef.burnbabyburn;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import org.apache.logging.log4j.Logger;

@Mod(modid = MainMod.MODID, version = MainMod.VERSION, serverSideOnly = true, acceptableRemoteVersions = "*")
public class MainMod {
	public static final String MODID = "burnbabyburn";
	public static final String VERSION = "1.4";

        private static Logger logger;
        
        @EventHandler
        public void preInit(FMLPreInitializationEvent event)
        {
            logger = event.getModLog();
        }
        
	@EventHandler
	public void init(FMLInitializationEvent event) {
            logger.info("Disco Inferno!");
            MinecraftForge.EVENT_BUS.register(new BabyZombiesBurn());
	}
}