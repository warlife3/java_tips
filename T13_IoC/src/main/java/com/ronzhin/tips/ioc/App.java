package com.ronzhin.tips.ioc;

/**
 * Приложение представляет из себя тренажер таблицы умножения)
 */

import com.ronzhin.tips.ioc.appcontainer.AppComponentsContainerImpl;
import com.ronzhin.tips.ioc.appcontainer.api.AppComponentsContainer;
import com.ronzhin.tips.ioc.services.GameProcessor;

public class App {

    public static void main(String[] args) {
        AppComponentsContainer container = new AppComponentsContainerImpl("com.ronzhin.tips.springdi.config");
        GameProcessor gameProcessor = container.getAppComponent("gameProcessor");
        gameProcessor.startGame();
    }
}
