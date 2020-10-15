package com.ronzhin.tips.ioc.appcontainer;

import com.ronzhin.tips.ioc.appcontainer.api.AppComponent;
import lombok.Data;

import java.lang.reflect.Method;

@Data
class Component {
    private final String name;
    private final Method method;
    private final AppComponent annotation;
    private final int order;
    private Object instance;
    private final Class<?>[] parameterTypes;


    public Component(Method method) {
        this.method = method;
        this.annotation = method.getAnnotation(AppComponent.class);
        this.order = annotation.order();
        this.name = annotation.name();
        this.parameterTypes = method.getParameterTypes();
    }

}