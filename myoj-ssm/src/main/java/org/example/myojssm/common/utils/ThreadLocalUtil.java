package org.example.myojssm.common.utils;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: liaoyueyue
 * Date: 2024-03-26
 * Time: 19:34
 */
public class ThreadLocalUtil {
    private static final ThreadLocal<Object> THREAD_LOCAL = new ThreadLocal<>();

    public static <T> T get() {
        return (T) THREAD_LOCAL.get();
    }

    public static void set(Object value) {
        THREAD_LOCAL.set(value);
    }

    public static void remove() {
        THREAD_LOCAL.remove();
    }
}
