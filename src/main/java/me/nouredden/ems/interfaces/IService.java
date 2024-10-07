package me.nouredden.ems.interfaces;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface IService<T,K>
{
    CompletableFuture<List<T>> getAll();
    CompletableFuture<T> getById(K key);
    CompletableFuture<T> insert(T object);
    CompletableFuture<T> update(T object);
    CompletableFuture<Void> delete(K key);
}
