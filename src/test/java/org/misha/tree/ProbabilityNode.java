package org.misha.tree;

/**
 * author: misha
 * date: 6/12/18
 * time: 9:00 AM
 */
public class ProbabilityNode extends Node<ProbabilityData> {

    private ProbabilityNode(IncidenceTable<ProbabilityData> table, ProbabilityData data, int id) {
        super(table, data, id);
    }

    double getTotalProbability() {
        ProbabilityNode parent = this;
        double result = this.getData().getProbability();
        while (true) {
            parent = parent.getParent();
            if (parent == null) {
                break;
            }
            result *= parent.getData().getProbability();
        }
        return result;
    }

    static ProbabilityNode create(double probability, String label, IncidenceTable<ProbabilityData> table) {
        final ProbabilityNode node = new  ProbabilityNode(table, new ProbabilityData(label, probability), table.nodesCount());
        table.addNode(node);
        return node;
    }

    @Override
    public ProbabilityNode getParent() {
        return (ProbabilityNode) super.getParent();
    }
}
