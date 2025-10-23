package com.guranya.iottesttask.gui;

import com.guranya.iottesttask.network.MessagePacket;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MessageScreen extends Screen {
    private static final Logger LOGGER = LoggerFactory.getLogger("MessageScreen");
    private EditBox messageField;

    public MessageScreen() {
        super(Component.literal("Отправка сообщения"));
    }

    @Override
    protected void init() {
        int fieldWidth = 200;
        int fieldHeight = 20;
        int fieldX = (this.width - fieldWidth) / 2;
        int fieldY = (this.height - fieldHeight) / 2;
        this.messageField = new EditBox(this.font, fieldX, fieldY, fieldWidth, fieldHeight, Component.literal("Введите текст..."));

        int buttonWidth = 100;
        int buttonHeight = 20;
        int buttonX = (this.width - buttonWidth) / 2;
        int buttonY = fieldY + fieldHeight + 5;

        Button sendButton = Button.builder(Component.literal("Отправить"), button -> {
            String messageText = this.messageField.getValue();
            var player = Minecraft.getInstance().player;
            if (player == null || messageText.isEmpty()) return;

            MessagePacket packet = new MessagePacket(messageText, player.getUUID());
            ClientPlayNetworking.send(packet);

            LOGGER.info("Sent message: {}", messageText);
            this.onClose();
        }).bounds(buttonX, buttonY, buttonWidth, buttonHeight).build();

        this.addRenderableWidget(messageField);
        this.addRenderableWidget(sendButton);
    }

    @Override
    public void renderBackground(@NotNull GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        super.renderBackground(guiGraphics, mouseX, mouseY, partialTick);
    }

    @Override
    public void render(@NotNull GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        super.render(guiGraphics, mouseX, mouseY, partialTick);
        guiGraphics.drawCenteredString(this.font, this.title, this.width / 2, 20, 0xFFFFFF);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}