package com.kyung.util;

public interface ObjectFactory<T> {
    T create(String csvStr);
}
