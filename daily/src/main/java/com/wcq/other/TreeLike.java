package com.wcq.other;

import java.util.function.BiConsumer;

public interface TreeLike<K, V> {
    // Inserts value into the tree at key, returning existing value
    // at key if it exists, or null otherwise.
    V insert(K key, V value);

    // Searches the tree for key, returning the value at key if it
    // exists, or null otherwise.
    V search(K key);

    // Traverses the tree in pre-order, and consumes each (key, value)
    // pair with consumer.
    void preOrderTraverse(BiConsumer<K, V> consumer);

    // Traverses the tree in post-order, and consumes each (key, value)
    // pair with consumer.
    void postOrderTraverse(BiConsumer<K, V> consumer);
}
