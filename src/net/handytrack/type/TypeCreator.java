package net.handytrack.type;

import net.handytrack.type.product.TypeC;

public abstract class TypeCreator {
    private static final double cost_fragile = 60;
    private static final double cost_bigSize = 35;
    protected abstract TypeC createType(double weight);
    public TypeC generateType(double weight) {
        TypeC type = createType(weight);
        return type;
    }
    public TypeC reset_extra_cost(TypeC type) {
        type.setExtra_cost(0);
        return type;
    }
    public TypeC add_fragile(TypeC type) {
        type.addExtra_cost(cost_fragile);
        return type;
    }
    public TypeC add_bigSize(TypeC type) {
        type.addExtra_cost(cost_bigSize);
        return type;
    }
}
