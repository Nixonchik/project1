package com.korobkin.controller;


import com.korobkin.annotation.RequestMapper;
import com.korobkin.command.Command;
import org.apache.log4j.Logger;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by Java Developer on 04.12.2015.
 *
 * This class help us to get Commands from particular package.
 */
public class AnnotationCommands {
    private static final Logger logger = Logger.getLogger(AnnotationCommands.class);


    /**
     * This method through reflection search all Classes with annotation @RequestMapper
     * and add this instance of this class to Map
     *
     * @param pathPackage package where method search classes with annotation @RequestMapper
     * @param prefix prefix of request that concatenates to value of annotation
     * @return HashMap of all Commands where key is request Uri, value is instance of Command class
     */
    Map<String, Command> putCommandsToMap(String pathPackage, String prefix) {
        Map<String, Command> result = new HashMap<>();
        Reflections reflections = new Reflections(pathPackage, new SubTypesScanner(false));

        Set<Class<? extends Object>> allClasses =
                reflections.getSubTypesOf(Object.class);

        for(Class c: allClasses) {
            if (c.isAnnotationPresent(RequestMapper.class)) {
                RequestMapper annotation = (RequestMapper) c.getAnnotation(RequestMapper.class);
                try {
                    String s = prefix + annotation.value();
                    Constructor con = c.getConstructor();
                    Command command = (Command) con.newInstance();
                    result.put(s, command);
                } catch (NoSuchMethodException|InvocationTargetException
                        |InstantiationException|IllegalAccessException e) {
                    logger.error(e);
                }
            }
        }
        return result;
    }
}
