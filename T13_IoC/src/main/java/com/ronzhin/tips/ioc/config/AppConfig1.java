package com.ronzhin.tips.ioc.config;

import com.ronzhin.tips.ioc.appcontainer.api.AppComponent;
import com.ronzhin.tips.ioc.appcontainer.api.AppComponentsContainerConfig;
import com.ronzhin.tips.ioc.services.EquationPreparer;
import com.ronzhin.tips.ioc.services.EquationPreparerImpl;
import com.ronzhin.tips.ioc.services.IOService;
import com.ronzhin.tips.ioc.services.IOServiceConsole;

@AppComponentsContainerConfig(order = 1)
public class AppConfig1 {

    @AppComponent(order = 0, name = "equationPreparer")
    public EquationPreparer equationPreparer() {
        return new EquationPreparerImpl();
    }

    @AppComponent(order = 0, name = "ioService")
    public IOService ioService() {
        return new IOServiceConsole(System.out, System.in);
    }

}