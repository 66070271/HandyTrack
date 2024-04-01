package net.handytrack.type.product;

public class NormalType implements TypeC {
    private static final double rate = 1 ;
    private final double weight;
    private double extra_cost = 0;
    public NormalType(double weight){
        this.weight = weight;
    }
    @Override
    public double calculate() {
        return (weight*rate) + TypeC.BaseCost + extra_cost;
    }

    public double getExtra_cost() {
        return extra_cost;
    }

    public void setExtra_cost(double extra_cost) {
        this.extra_cost = extra_cost;
    }

    public void addExtra_cost(double extra_cost) {
        this.extra_cost += extra_cost;
    }
}

