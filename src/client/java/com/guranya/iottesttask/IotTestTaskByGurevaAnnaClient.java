package com.guranya.iottesttask;

import com.guranya.iottesttask.gui.MessageScreen;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.Minecraft;


public class IotTestTaskByGurevaAnnaClient implements ClientModInitializer {

	private static boolean needsToOpenScreen = false;

	@Override
	public void onInitializeClient() {
		ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> dispatcher.register(ClientCommandManager.literal("openmessagegui")
            .executes(context -> {
                needsToOpenScreen = true;
                return 1;
            })));

		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			if (needsToOpenScreen) {
				needsToOpenScreen = false;
				Minecraft.getInstance().setScreen(new MessageScreen());
			}
		});
	}
}