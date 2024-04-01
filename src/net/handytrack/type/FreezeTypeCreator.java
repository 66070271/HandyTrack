package net.handytrack.type;

import net.handytrack.type.product.FreezeType;
import net.handytrack.type.product.TypeC;

public class FreezeTypeCreator extends TypeCreator {
    protected TypeC createType(double weight) {
        return new FreezeType(weight);
    }

    protected TypeC createType(double weight, double temperature) {
        return new FreezeType(weight, temperature);
    }
}
