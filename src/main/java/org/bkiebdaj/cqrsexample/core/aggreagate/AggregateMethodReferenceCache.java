package org.bkiebdaj.cqrsexample.core.aggreagate;

import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static java.lang.String.format;

@RequiredArgsConstructor
class AggregateMethodReferenceCache {
    private final Map<HashClass, Method> cache = new ConcurrentHashMap<>();
    private final Class<? extends Annotation> annotationClass;

    Method getMethod(Class<?> handlerClass, Class<?> handledClass) {
        HashClass hashClass = new HashClass(handlerClass, handledClass);
        Method method = cache.get(hashClass);
        if (method == null) {
            method = findMethod(handlerClass, handledClass);
            cache.put(hashClass, method);
        }
        return method;
    }

    private Method findMethod(Class<?> handlerClass, Class<?> handledClass) {
        for (Method method : handlerClass.getMethods()) {
            if (method.isAnnotationPresent(annotationClass)) {
                if (method.getParameterCount() == 1) {
                    Class<?> parameterType = method.getParameterTypes()[0];
                    if (parameterType.equals(handledClass)) {
                        return method;
                    }
                }
            }
        }
        throw new IllegalStateException(format("Can't find handler method for: %s %s", handlerClass.getSimpleName(), handledClass.getSimpleName()));
    }

    @Value
    private static class HashClass {
        Class<?> handlerClass;
        Class<?> handledClass;
    }
}


