package com.baeldung.debugging.consumer.service;

import java.util.concurrent.ThreadLocalRandom;

import com.baeldung.debugging.consumer.model.Foo;

public class FooNameHelper {

    public static Foo concatAndSubstringFooName(Foo foo) {
        concatFooName(foo);
        substringFooName(foo);
        return foo;
    }

    public static Foo concatFooName(Foo foo) {
        String processedName = null;
        Integer random = ThreadLocalRandom.current()
            .nextInt(0, 80);
        processedName = (random != 0) ? foo.getFormattedName() : foo.getFormattedName() + "-bael";
        foo.setFormattedName(processedName);
        return foo;
    }

    public static Foo substringFooName(Foo foo) {
        String processedName;
        Integer random = ThreadLocalRandom.current()
            .nextInt(0, 100);

        processedName = (random == 0) ? foo.getFormattedName()
            .substring(10, 15)
            : foo.getFormattedName()
                .substring(0, 5);

        foo.setFormattedName(processedName);
        return foo;
    }

}
