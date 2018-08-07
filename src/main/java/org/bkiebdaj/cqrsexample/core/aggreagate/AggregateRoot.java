package org.bkiebdaj.cqrsexample.core.aggreagate;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.bkiebdaj.cqrsexample.core.common.AggregadeId;
import org.bkiebdaj.cqrsexample.core.event.Event;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static java.lang.String.format;

abstract public class AggregateRoot {

    private static final AggregateMethodReferenceCache eventHandlersCache = new AggregateMethodReferenceCache(ApplyEvent.class);
    private static final AggregateMethodReferenceCache commandHandlersCache = new AggregateMethodReferenceCache(HandleCommand.class);

    @Getter
    private final AggregadeId aggregadeId;

    public AggregateRoot(AggregadeId aggregadeId) {
        this.aggregadeId = aggregadeId;
    }

    public void replay(List<Event> events) {
        events.forEach(this::apply);
    }

    private void apply(Event event) {

        Method method = eventHandlersCache.getMethod(getClass(), event.getClass());
        try {
            method.invoke(this, event);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @RequiredArgsConstructor
    private static class AggregateMethodReferenceCache {
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
    }

    @Value
    private static class HashClass {
        Class<?> handlerClass;
        Class<?> handledClass;
    }
}
