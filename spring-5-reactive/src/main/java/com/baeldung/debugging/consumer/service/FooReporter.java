package com.baeldung.debugging.consumer.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baeldung.debugging.consumer.model.Foo;

public class FooReporter {

    private static Logger logger = LoggerFactory.getLogger(FooReporter.class);

    public static Foo reportResult(Foo foo, String approach) {
        if (foo.getId() == null)
            throw new IllegalArgumentException("Null id is not valid!");
        logger.info("Reporting for approach {}: Foo with id '{}' name '{}' and quantity '{}'", approach, foo.getId(), foo.getFormattedName(), foo.getQuantity());
        return foo;
    }

    public static Foo reportResult(Foo input) {
        return reportResult(input, "default");
    }
}
