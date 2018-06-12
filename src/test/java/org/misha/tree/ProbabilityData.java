package org.misha.tree;

/**
 * author: misha
 * date: 6/12/18
 * time: 9:01 AM
 */
class ProbabilityData {
    private final String string;
    private final double probability;

    ProbabilityData(String string, double probability) {
        this.string = string;
        this.probability = probability;
    }

    public String getString() {
        return string;
    }

    public double getProbability() {
        return probability;
    }
}
