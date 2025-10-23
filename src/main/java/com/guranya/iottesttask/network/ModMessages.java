package com.guranya.iottesttask.network;

import com.guranya.iottesttask.IotTestTaskByGurevaAnna;
import com.guranya.iottesttask.entity.PlayerMessage;
import com.guranya.iottesttask.service.PlayerMessageService;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ModMessages {
    private static final Logger LOGGER = LoggerFactory.getLogger("ModMessages");
    @SuppressWarnings("resource")
    public static void registerC2SPackets() {
        PayloadTypeRegistry.playC2S().register(MessagePacket.TYPE, MessagePacket.CODEC);

        ServerPlayNetworking.registerGlobalReceiver(MessagePacket.TYPE,
            (payload, context) -> context.server().execute(() -> {
                PlayerMessageService service = IotTestTaskByGurevaAnna.SPRING_CONTEXT.getBean(PlayerMessageService.class);

                PlayerMessage message = new PlayerMessage(context.player().getUUID(), payload.text());

                service.save(message);

                LOGGER.info("Saving message: {}", context.player().getName().getString());
            }));
    }
}