package net.handytrack.type;

import net.handytrack.type.product.NormalType;
import net.handytrack.type.product.TypeC;

public class NormalTypeCreator extends TypeCreator {
    protected TypeC createType(double weight) {
        return new NormalType(weight);
    }
}
