package org.misha.tree;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * author: misha
 * date: 6/12/18
 * time: 9:21 AM
 */
public class ProbabilityTreeTest {
    private final ProbabilityNode node1;

    public ProbabilityTreeTest() {
        IncidenceTable<ProbabilityData> table = new IncidenceTable<>();
        ProbabilityNode node = ProbabilityNode.create(1, "root", table);
        ProbabilityNode node0 = ProbabilityNode.create(0.5, "0", table);
        node.addChildren(node0);
        node1 = ProbabilityNode.create(0.3, "1", table);
        node0.addChildren(node1);
    }

    @Test
    public void testTotalProbability() {
        assertTrue(node1.getTotalProbability() == 0.5 * 0.3);
    }
}
