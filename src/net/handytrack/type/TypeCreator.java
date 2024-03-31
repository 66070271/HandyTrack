package net.handytrack.type;

import net.handytrack.type.product.Type;

public abstract class TypeCreator {
    private static final double cost_fragile = 60;
    private static final double cost_bigSize = 35;
    protected abstract Type createType(double weight);
    public Type generateType(double weight) {
        Type type = createType(weight);
        return type;
    }
    public Type reset_extra_cost(Type type) {
        type.setExtra_cost(0);
        return type;
    }
    public Type add_fragile(Type type) {
        type.addExtra_cost(cost_fragile);
        return type;
    }
    public Type add_bigSize(Type type) {
        type.addExtra_cost(cost_bigSize);
        return type;
    }
}
