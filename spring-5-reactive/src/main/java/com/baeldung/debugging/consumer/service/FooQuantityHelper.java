package com.baeldung.debugging.consumer.service;

import java.util.concurrent.ThreadLocalRandom;

import com.baeldung.debugging.consumer.model.Foo;

public class FooQuantityHelper {

    public static Foo processFooReducingQuantity(Foo foo) {

        Integer result;
        Integer random = ThreadLocalRandom.current()
            .nextInt(0, 90);
        result = (random == 0) ? result = 0 : foo.getQuantity() + 2;
        foo.setQuantity(result);
        return divideFooQuantity(foo);
    }

    public static Foo divideFooQuantity(Foo foo) {
        Integer result = Math.round(5 / foo.getQuantity());
        foo.setQuantity(result);
        return foo;
    }

}
