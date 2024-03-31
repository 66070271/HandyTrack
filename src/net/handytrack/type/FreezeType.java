package net.handytrack.type;

public class FreezeType implements Type{
    private static final double rate = 2.5 ;
    private double weight;
    public FreezeType(double weight){
        this.weight = weight;
    }
    @Override
    public double calculate() {
        return (weight*rate) + Type.BaseCost;
    }
}

