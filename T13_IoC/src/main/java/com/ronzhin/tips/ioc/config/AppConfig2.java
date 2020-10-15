package com.ronzhin.tips.ioc.config;

import com.ronzhin.tips.ioc.appcontainer.api.AppComponent;
import com.ronzhin.tips.ioc.appcontainer.api.AppComponentsContainerConfig;
import com.ronzhin.tips.ioc.services.*;

@AppComponentsContainerConfig(order = 2)
public class AppConfig2 {

    @AppComponent(order = 0, name = "playerService")
    public PlayerService playerService(IOService ioService) {
        return new PlayerServiceImpl(ioService);
    }

    @AppComponent(order = 1, name = "gameProcessor")
    public GameProcessor gameProcessor(IOService ioService,
                                       PlayerService playerService,
                                       EquationPreparer equationPreparer) {
        return new GameProcessorImpl(ioService, equationPreparer, playerService);
    }

}