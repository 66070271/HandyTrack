package net.handytrack.type;

import net.handytrack.type.product.NormalType;
import net.handytrack.type.product.Type;

public class Example {
    public static void main(String[] args) {
        TypeCreator normalFactory = new NormalTypeCreator();
        TypeCreator freezeFactory = new FreezeTypeCreator();

        Type normal1 = normalFactory.generateType(100);
        System.out.println(normal1.calculate());
        normalFactory.add_bigSize(normal1);
        System.out.println(normal1.calculate());

        System.out.println("----------");

        Type freeze1 = freezeFactory.generateType(100);
        System.out.println(freeze1.calculate());
        freezeFactory.add_bigSize(freeze1);
        System.out.println(freeze1.calculate());

        System.out.println("----------");

        Type product1 = freezeFactory.generateType(200);
        System.out.println(product1.calculate());
        freezeFactory.add_fragile(product1);
        System.out.println(product1.calculate());

    }
}
