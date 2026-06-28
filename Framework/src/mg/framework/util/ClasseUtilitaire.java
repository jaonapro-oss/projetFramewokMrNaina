package mg.framework.util;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import mg.framework.annotation.Controller;
import mg.framework.annotation.UrlMapping;
import mg.framework.mapping.Mapping;

public class ClasseUtilitaire {
    public ClasseUtilitaire() {
    };

    public static List<String> findControllers(String packageName)
            throws Exception {

        List<String> controllers = new ArrayList<>();

        String path = packageName.replace('.', '/');

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

        URL resource = classLoader.getResource(path);

        if (resource == null) {
            return controllers;
        }

        File directory = new File(resource.getFile());

        File[] files = directory.listFiles();

        if (files == null) {
            return controllers;
        }

        for (File file : files) {

            if (!file.getName().endsWith(".class")) {
                continue;
            }

            String className = packageName + "."
                    + file.getName().replace(".class", "");

            Class<?> clazz = Class.forName(className);

            if (clazz.isAnnotationPresent(
                    Controller.class)) {

                controllers.add(clazz.getName());
            }
        }

        return controllers;
    }

    public static HashMap<String, Mapping> findRoutes(List<String> controllers)
            throws Exception {

        HashMap<String, Mapping> routes = new HashMap<>();

        for (String controller : controllers) {

            Class<?> clazz = Class.forName(controller);

            Method[] methods = clazz.getDeclaredMethods();

            for (Method method : methods) {

                if (method.isAnnotationPresent(UrlMapping.class)) {

                    UrlMapping annotation = method.getAnnotation(UrlMapping.class);

                    String route = annotation.route();

                    if (routes.containsKey(route)) {

                        throw new Exception("La route '" + route + "' est déjà utilisée.");

                    }

                    routes.put(
                            route,
                            new Mapping(
                                    clazz.getSimpleName(),
                                    method.getName()));

                }

            }

        }

        return routes;

    }
}