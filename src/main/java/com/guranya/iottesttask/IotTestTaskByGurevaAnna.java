package com.guranya.iottesttask;

import com.guranya.iottesttask.config.SpringDataConfig;
import com.guranya.iottesttask.network.ModMessages;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class IotTestTaskByGurevaAnna implements ModInitializer {
    public static final String MOD_ID = "iot-test-task-by-gureva-anna";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    public static ApplicationContext SPRING_CONTEXT;

    @Override
    public void onInitialize() {
        LOGGER.info("Инициализация мода {}", MOD_ID);
        SPRING_CONTEXT = new AnnotationConfigApplicationContext(SpringDataConfig.class);
        ModMessages.registerC2SPackets();
    }
}