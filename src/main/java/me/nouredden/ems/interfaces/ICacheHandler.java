package me.nouredden.ems.interfaces;

import java.util.List;
import java.util.Map;

public interface ICacheHandler<K,T>
{
    void insert(T object);
    T get(K key);
    List<T> getAll();
    void delete(K key);
    void save();
    Map<K,T> getManager();
}
