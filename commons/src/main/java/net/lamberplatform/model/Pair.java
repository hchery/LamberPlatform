package net.lamberplatform.model;

/**
 * DATE: 2025/1/19
 * AUTHOR: hchery
 * URL: https://github.com/hchery
 * EMAIL: h.chery@qq.com
 */
public record Pair<T, V>(T v1, V v2) {

    public static <T, V> Pair<T, V> of(T v1, V v2) {
        return new Pair<>(v1, v2);
    }
}
