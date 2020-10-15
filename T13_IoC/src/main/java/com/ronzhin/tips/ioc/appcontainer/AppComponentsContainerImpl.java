package com.ronzhin.tips.ioc.appcontainer;


import com.ronzhin.tips.ioc.appcontainer.api.AppComponent;
import com.ronzhin.tips.ioc.appcontainer.api.AppComponentsContainer;
import com.ronzhin.tips.ioc.appcontainer.api.AppComponentsContainerConfig;
import lombok.SneakyThrows;
import lombok.val;
import org.reflections.ReflectionUtils;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.reflections.util.ClasspathHelper;

import java.lang.annotation.Annotation;
import java.util.*;
import java.util.stream.Collectors;

public class AppComponentsContainerImpl implements AppComponentsContainer {

    private final Map<String, Object> appComponentsByName = new HashMap<>();
    private final Map<Class<?>, Object> appComponentsByClass = new HashMap<>();

    public AppComponentsContainerImpl(String packageName) {
        Reflections reflections = new Reflections(ClasspathHelper.forPackage(packageName), new SubTypesScanner(), new TypeAnnotationsScanner());
        Set<Class<?>> typesAnnotatedWith = reflections.getTypesAnnotatedWith(AppComponentsContainerConfig.class);
        processConfig(typesAnnotatedWith.toArray(Class[]::new));
    }

    public AppComponentsContainerImpl(Class<?>... initialConfigClasses) {
        processConfig(initialConfigClasses);
    }

    private void processConfig(Class<?>... configClasses) {
        checkConfigClass(configClasses);
        processConfigClass(configClasses);
    }

    private void processConfigClass(Class<?>[] configClasses) {
        Class<AppComponent> appComponentClass = AppComponent.class;

        Map<Integer, List<Component>> allComponentsByOrder = getComponentsByOrder(configClasses, appComponentClass);
        generateBeans(allComponentsByOrder);
    }

    Comparator<? super Class<?>> classComparator = (Class<?> o1, Class<?> o2) -> {
        var annotation1 = o1.getAnnotation(AppComponentsContainerConfig.class);
        var annotation2 = o2.getAnnotation(AppComponentsContainerConfig.class);
        return Integer.compare(annotation1.order(), annotation2.order());
    };

    @SneakyThrows
    private Map<Integer, List<Component>> getComponentsByOrder(Class<?>[] configClasses, Class<? extends Annotation> annotation) {
        val sortedConfigClasses = Arrays.stream(configClasses).sorted(classComparator).collect(Collectors.toList());
        List<Component> allComponents = new ArrayList<>();
        for (Class<?> configClass : sortedConfigClasses) {

            val classMethods = ReflectionUtils.getMethods(configClass, ReflectionUtils.withAnnotation(annotation));
            val instance = configClass.getConstructor().newInstance();
            val classComponents = classMethods.stream()
                    .map(Component::new)
                    .peek(component -> component.setInstance(instance))
                    .collect(Collectors.toList());
            allComponents.addAll(classComponents);
        }
        return allComponents.stream().collect(Collectors.groupingBy(Component::getOrder));
    }

    private void generateBeans(Map<Integer, List<Component>> allComponentsByOrder) {
        for (Integer order : allComponentsByOrder.keySet()) {
            final List<Component> components = allComponentsByOrder.get(order);
            for (Component component : components) {
                Object object = generateBean(component);
                appComponentsByClass.put(object.getClass(), object);
                for (var iface : object.getClass().getInterfaces()) {
                    appComponentsByClass.put(iface, object);
                }
                appComponentsByName.put(component.getName(), object);
            }
        }
    }

    @SneakyThrows
    private Object generateBean(Component component) {
        val method = component.getMethod();
        val instance = component.getInstance();
        val parameterTypes = component.getParameterTypes();
        List<Object> parameters = Arrays.stream(parameterTypes)
                .map(appComponentsByClass::get)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        if (parameters.size() != parameterTypes.length)
            throw new RuntimeException("Not Founded beans");
        if (parameters.size() == 0)
            return method.invoke(instance);
        else
            return method.invoke(instance, parameters.toArray());
    }


    private void checkConfigClass(Class<?>... configClasses) {
        for (Class<?> configClass : configClasses) {
            if (!configClass.isAnnotationPresent(AppComponentsContainerConfig.class)) {
                throw new IllegalArgumentException(String.format("Given class is not config %s", configClass.getName()));
            }
        }
    }

    @Override
    public <C> C getAppComponent(Class<C> componentClass) {
        return componentClass.cast(appComponentsByClass.get(componentClass));
    }

    @Override
    public <C> C getAppComponent(String componentName) {
        return (C) appComponentsByName.get(componentName);
    }

}