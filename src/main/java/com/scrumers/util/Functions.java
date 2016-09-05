package com.scrumers.util;

import java.lang.reflect.Field;
import java.util.List;

import com.scrumers.model.Entity;

public class Functions {

    public static boolean conteins(final Entity entity, final List<Entity> list) {
        if (entity != null && list != null) {
            for (Entity tmp : list) {
                if (tmp.getId().equals(entity.getId())) {
                    return true;
                }
            }
        }
        return false;
    }

    public static Object getProperty(final Object obj, final String name) {
        return getProperty(obj, obj.getClass(), name);
    }

    private static Object getProperty(final Object obj, final Class<?> clazz, final String name) {
        try {
            Field field = clazz.getDeclaredField(name);

            if (field == null) {
                Class<?> superClazz = clazz.getSuperclass();

                if (superClazz == null) {
                    return null;
                } else {
                    return getProperty(obj, superClazz, name);
                }
            }

            boolean flag = field.isAccessible();
            field.setAccessible(true);
            Object ret = field.get(obj);
            field.setAccessible(flag);

            return ret;
        } catch (NoSuchFieldException | IllegalAccessException e) {
            return null;
        }
    }

    public static String hello(final boolean f) {
        return "hello" + (f ? "!!!" : "!");
    }
}
