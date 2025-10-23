package com.guranya.iottesttask.network;

import com.google.protobuf.InvalidProtocolBufferException;
import com.guranya.iottesttask.IotTestTaskByGurevaAnna;
import com.guranya.iottesttask.proto.MessageOuterClass;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public record MessagePacket(String text, UUID senderUuid) implements CustomPacketPayload {
    public static final CustomPacketPayload.Type<MessagePacket> TYPE =
        new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(IotTestTaskByGurevaAnna.MOD_ID, "message_packet"));
    public static final StreamCodec<FriendlyByteBuf, MessagePacket> CODEC = StreamCodec.of(
        MessagePacket::write, MessagePacket::read
    );

    private static void write(FriendlyByteBuf buf, MessagePacket packet) {
        MessageOuterClass.Message proto = MessageOuterClass.Message.newBuilder()
            .setText(packet.text == null ? "" : packet.text)
            .build();
        buf.writeByteArray(proto.toByteArray());
        buf.writeUUID(packet.senderUuid);
    }

    private static MessagePacket read(FriendlyByteBuf buf) {
        byte[] bytes = buf.readByteArray();
        String text;
        try {
            text = MessageOuterClass.Message.parseFrom(bytes).getText();
        } catch (InvalidProtocolBufferException e) {
            text = "[parse error]";
        }
        UUID uuid = buf.readUUID();
        return new MessagePacket(text, uuid);
    }

    @Override
    public CustomPacketPayload.@NotNull Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}