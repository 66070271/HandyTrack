package net.handytrack.type.product;

public class FreezeType implements TypeC {
    private static final double rate = 2.5 ;
    private double weight;
    private double extra_cost = 0;
    private double temperature;

//    Constructor
    public FreezeType(double weight){
        this(weight, 10);
    }

    public FreezeType(double weight, double temperature) {
        this.weight = weight;
        this.temperature = temperature;
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

    public double get_temperature() {
        return temperature;
    }

    public void set_temperature(double temperature) {
        this.temperature = temperature;
    }
    public void addExtra_cost(double extra_cost) {
        this.extra_cost += extra_cost;
    }
}

