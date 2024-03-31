package net.handytrack.type;

public class NormalType implements Type{
    private static final double rate = 1 ;
    private double weight;
    public NormalType(double weight){
        this.weight = weight;
    }
    @Override
    public double calculate() {
        return (weight*rate) + Type.BaseCost;
    }
}

