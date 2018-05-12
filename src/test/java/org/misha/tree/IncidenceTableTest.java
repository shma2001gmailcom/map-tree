package org.misha.tree;

import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.misha.tree.Node.*;

/**
 * author: misha
 * date: 5/9/18
 * time: 12:34 PM
 */
public class IncidenceTableTest {
    private final Node<String> node;
    private final Node<String> node0;
    private final Node<String> node1;
    private final Node<String> node2;
    private final Node<String> node00;
    private final Node<String> node01;
    private final Node<String> node10;
    private final Node<String> node11;

    public IncidenceTableTest() {
        final IncidenceTable<String> table = new IncidenceTable<>();
        node = newNode(table, "");
        node0 = newNode(table, "0");
        node1 = newNode(table, "1");
        node2 = newNode(table, "2");
        node00 = newNode(table, "00");
        node01 = newNode(table, "01");
        node10 = newNode(table, "10");
        node11 = newNode(table, "11");
        node.addChildren(node0, node1, node2);
        node0.addChildren(node00, node01);
        node1.addChildren(node10, node11);
    }

    @Test
    public void put() throws Exception {
        assertTrue(node.getData().equals("")
                           && node.getParent() == null
                           && node0.getParent() == node
                           && node1.getParent() == node
                           && node2.getParent() == node
                           && node00.getParent() == node0
                           && node01.getParent() == node0
                           && node10.getParent() == node1
                           && node11.children().isEmpty());
    }
}