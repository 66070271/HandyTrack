package net.handytrack.type.product;

public interface TypeC {
    public static final double BaseCost = 30;
    public abstract double calculate();
    public abstract void setExtra_cost(double extra_cost);
    public abstract void addExtra_cost(double extra_cost);
    public abstract double getExtra_cost();

}
