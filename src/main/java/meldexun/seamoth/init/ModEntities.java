package meldexun.seamoth.init;

import meldexun.seamoth.Seamoth;
import meldexun.seamoth.entity.EntitySeamoth;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;

@ObjectHolder(Seamoth.MOD_ID)
public class ModEntities {

	private ModEntities() {

	}

	@EventBusSubscriber(modid = Seamoth.MOD_ID)
	public static class EntityRegistrationHandler {

		private static int entityID = 0;

		private EntityRegistrationHandler() {

		}

		@SubscribeEvent
		public static void registerEntities(RegistryEvent.Register<EntityEntry> event) {
			final EntityEntry[] entityEntries = {
					EntityRegistrationHandler.createEntityEntry(EntitySeamoth.class, "seamoth", 64, 1, true).build() };

			event.getRegistry().registerAll(entityEntries);
		}

		private static EntityEntryBuilder<Entity> createEntityEntry(Class<? extends Entity> entityClass, String name, int trackerRange, int trackerUpdateFrequency, boolean sendVelocityUpdates) {
			return EntityEntryBuilder.create().entity(entityClass).id(new ResourceLocation(Seamoth.MOD_ID, name), EntityRegistrationHandler.entityID++).name(name).tracker(trackerRange, trackerUpdateFrequency, sendVelocityUpdates);
		}

	}

}
