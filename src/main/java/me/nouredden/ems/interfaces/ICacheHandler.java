package me.nouredden.ems.interfaces;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public interface ICacheHandler<K,T>
{
    void insert(T object);
    T  get(K key);
    List<T> getAll();
    void delete(K key);
}
