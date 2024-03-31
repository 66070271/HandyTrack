package net.handytrack.type;

import net.handytrack.type.product.NormalType;
import net.handytrack.type.product.Type;

public class NormalTypeCreator extends TypeCreator {
    protected Type createType(double weight) {
        return new NormalType(weight);
    }
}
