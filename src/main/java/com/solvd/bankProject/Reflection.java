package com.solvd.bankProject;

import com.solvd.bankProject.consoleScanner.CreationObjectsFromConsole;
import lombok.extern.log4j.Log4j2;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@Log4j2
public class Reflection {


    private static Class<?> aClass;

    private static Object object;

    private String className;
    private List<String> interfaces = new LinkedList<>();
    private List<String> constructors = new LinkedList<>();
    private List<String> fields = new LinkedList<>();
    private List<String> methods = new LinkedList<>();

    @Override
    public String toString() {
        return "Reflection{" +
                "className='" + className + '\'' +
                ", interfaces=" + interfaces +
                ", constructors=" + constructors +
                ", fields=" + fields +
                ", methods=" + methods +
                '}';
    }

    public void getAllInformationAboutTheClass() {
        log.info("Enter the full name of the class");
        String name = CreationObjectsFromConsole.scanner.next();
        try {
            aClass = Class.forName(name);
            className = aClass.getName();
            interfaces.add(Arrays.toString(aClass.getInterfaces()));
            constructors.add(Arrays.toString(aClass.getConstructors()));
            fields.add(Arrays.toString(aClass.getDeclaredFields()));
            methods.add(Arrays.toString(aClass.getDeclaredMethods()));
            log.info("The full name of the class with package is {}", className);
            log.info("The class implements the following interfaces {}", interfaces);
            log.info("The class has the following constructors {}", interfaces);
            log.info("The class has the  following fields {}", fields);
            log.info("The class has the following methods {}", methods);
        } catch (ClassNotFoundException e) {
            log.error(e.getMessage());
        }
    }

    public Object createAnObjectOfACertainClass() {
        log.info("Enter the full name of the class");
        String name = CreationObjectsFromConsole.scanner.next();
        try {
            aClass = Class.forName(name);
            object = aClass.newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        log.info("A new object was created with the help of reflection - {}", object);
        return object;
    }

    public void invokeCertainMethod() {
        if (object == null) {
            createAnObjectOfACertainClass();
        }
        try {
            log.info("Enter the name of the method you want to invoke");
            String methodName = CreationObjectsFromConsole.scanner.next();
            Method method = aClass.getDeclaredMethod(methodName);
            method.setAccessible(true);
            method.invoke(object);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}
