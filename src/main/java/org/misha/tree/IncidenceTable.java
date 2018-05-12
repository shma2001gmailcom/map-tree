package org.misha.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * author: misha
 * date: 5/9/18
 * time: 12:12 PM
 */
class IncidenceTable<T> {
    private final Map<Integer, Integer> table;
    private final ReentrantReadWriteLock lock;
    private final Map<Integer, Node<T>> nodes;

    IncidenceTable() {
        table = new HashMap<>();
        lock = new ReentrantReadWriteLock();
        nodes = new HashMap<>();
    }

    //set up parent for node
    void put(final Node<T> child, final Node<T> parent) {
        final Lock writeLock = lock.writeLock();
        final int id = child.getId();
        try {
            writeLock.lock();
            checkArgument(!table.containsKey(id), "already exists");
            table.put(id, parent.getId());
        } finally {
            writeLock.unlock();
        }
    }

    Node<T> get(final Node<T> node) {
        final Lock readLock = lock.readLock();
        try {
            readLock.lock();
            return nodes.get(table.get(node.getId()));
        } finally {
            readLock.unlock();
        }
    }

    void addNode(final Node<T> node) {
        try {
            lock.writeLock().lock();
            nodes.put(node.getId(), node);
        } finally {
            lock.writeLock().unlock();
        }
    }

    int nodesCount() {
        return nodes.size();
    }

    List<Node<T>> children(final Node<T> node) {
        final ArrayList<Node<T>> result = new ArrayList<>();
        try {
            lock.readLock().lock();
            for (final Map.Entry<Integer, Integer> e : table.entrySet()) {
                if (e.getValue() == node.getId()) {
                    result.add(nodes.get(table.get(e.getKey())));
                }
            }
        } finally {
            lock.readLock().unlock();
        }
        return result;
    }
}
