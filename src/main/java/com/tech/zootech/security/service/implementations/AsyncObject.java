package com.tech.zootech.security.service.implementations;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.function.Consumer;
import java.util.function.Function;

@Slf4j
@Component
@RequiredArgsConstructor
public class AsyncObject {
    private final Executor executor;

    public <R, T> CompletableFuture<Void> asyncCommander(Function<T, R> function, Consumer<R> consumer, T value) {
        final R resolveValue = function.apply(value);
        return CompletableFuture.runAsync(() -> consumer.accept(resolveValue), executor);
    }
}
