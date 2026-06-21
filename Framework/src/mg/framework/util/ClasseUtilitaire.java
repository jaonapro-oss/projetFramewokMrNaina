package mg.framework.util;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import mg.framework.annotation.Controller;

public class ClasseUtilitaire {
    public ClasseUtilitaire(){} ;

    public List<String> findControllers(String packageName)
            throws Exception {

        List<String> controllers = new ArrayList<>();

        String path = packageName.replace('.', '/');

        ClassLoader classLoader =
                Thread.currentThread().getContextClassLoader();

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

            String className =
                    packageName + "."
                    + file.getName().replace(".class", "");

            Class<?> clazz =
                    Class.forName(className);

            if (clazz.isAnnotationPresent(
                    Controller.class)) {

                controllers.add(clazz.getName());
            }
        }

        return controllers;
    }
}