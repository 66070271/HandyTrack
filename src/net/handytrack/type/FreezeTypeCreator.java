package net.handytrack.type;

import net.handytrack.type.product.FreezeType;
import net.handytrack.type.product.Type;

public class FreezeTypeCreator extends TypeCreator {
    protected Type createType(double weight) {
        return new FreezeType(weight);
    }

    protected Type createType(double weight, double temperature) {
        return new FreezeType(weight, temperature);
    }
}
