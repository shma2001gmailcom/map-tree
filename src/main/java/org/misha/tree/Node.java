package org.misha.tree;

import java.util.List;

/**
 * author: misha
 * date: 5/9/18
 * time: 12:13 PM
 */
public class Node<T> {
    private final IncidenceTable<T> table;
    private T data;
    private final int id;

    Node(final IncidenceTable<T> table, final T data, final int id) {
        this.table = table;
        this.data = data;
        this.id = id;
    }

    public static <S> Node<S> newNode(final IncidenceTable<S> table, final S data) {
        final Node<S> node = new Node<>(table, data, table.nodesCount());
        table.addNode(node);
        return node;
    }

    public T getData() {
        return data;
    }

    @SafeVarargs
    public final void addChildren(final Node<T>... children) {
        for (final Node<T> child : children) {
            table.put(child, this);
        }
    }

    public Node<T> getParent() {
        return table.get(this);
    }

    public List<Node<T>> children() {
        return table.children(this);
    }

    int getId() {
        return id;
    }

    public void setData(T data) {
        this.data = data;
    }
}
