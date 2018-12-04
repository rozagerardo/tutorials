package com.baeldung.debugging.consumer.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.baeldung.debugging.consumer.model.Foo;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

@Component
public class FooService {

    private static Logger logger = LoggerFactory.getLogger(FooService.class);

    public void processFoo(Flux<Foo> flux) {
        flux.map(FooNameHelper::concatFooName)
            .map(FooNameHelper::substringFooName)
            .log()
            .map(FooReporter::reportResult)
            .doOnError(error -> {
                logger.error("The following error happened on processFoo method!", error);
            })
            .subscribe();
    }

    public void processFooInAnotherScenario(Flux<Foo> flux) {
        flux.map(FooNameHelper::substringFooName)
            .map(FooQuantityHelper::divideFooQuantity)
            .subscribe();
    }

    public void processUsingApproachOneWithErrorHandling(Flux<Foo> flux) {
        logger.info("starting approach one w error handling!");
        flux.map(FooNameHelper::concatAndSubstringFooName)
            .map(FooNameHelper::concatAndSubstringFooName)
            .map(FooNameHelper::substringFooName)
            .map(FooQuantityHelper::processFooReducingQuantity)
            .map(FooQuantityHelper::processFooReducingQuantity)
            .map(FooQuantityHelper::processFooReducingQuantity)
            .map(foo -> FooReporter.reportResult(foo, "ONE w/ EH"))
            .doOnError(error -> {
                logger.error("Approach 1 with Error Handling failed!", error);
            })
            .subscribe();
    }

    public void processUsingApproachThree(Flux<Foo> flux) {
        logger.info("starting approach three!");
        flux.map(FooNameHelper::concatAndSubstringFooName)
            .map(foo -> FooReporter.reportResult(foo, "THREE"))
            .doOnError(error -> {
                logger.error("Approach 3 failed!", error);
            })
            .subscribe();
    }

    public void processUsingApproachFourWithCheckpoint(Flux<Foo> flux) {
        logger.info("starting approach four!");
        flux.map(FooNameHelper::concatAndSubstringFooName)
            .checkpoint("CHECKPOINT 1")
            .map(FooNameHelper::concatAndSubstringFooName)
            .map(FooQuantityHelper::divideFooQuantity)
            .checkpoint("CHECKPOINT 2", true)
            .map(foo -> FooReporter.reportResult(foo, "FOUR"))
            .map(FooNameHelper::concatAndSubstringFooName)
            .doOnError(error -> {
                logger.error("Approach 4 failed!", error);
            })
            .subscribe();
    }

    public void processUsingApproachFourWithInitialCheckpoint(Flux<Foo> flux) {
        logger.info("starting approach four!");
        flux.map(FooNameHelper::concatAndSubstringFooName)
            .checkpoint("CHECKPOINT 1", true)
            .map(FooNameHelper::concatAndSubstringFooName)
            .map(FooQuantityHelper::divideFooQuantity)
            .map(foo -> FooReporter.reportResult(foo, "FOUR"))
            .doOnError(error -> {
                logger.error("Approach 4-2 failed!", error);
            })
            .subscribe();
    }

    public void processUsingApproachFivePublishingToDifferentParallelThreads(Flux<Foo> flux) {
        logger.info("starting approach five-parallel!");
        flux.map(FooNameHelper::concatAndSubstringFooName)
            .publishOn(Schedulers.newParallel("five-parallel-foo"))
            .log()
            .map(FooNameHelper::concatAndSubstringFooName)
            .map(FooQuantityHelper::divideFooQuantity)
            .map(foo -> FooReporter.reportResult(foo, "FIVE_PARALLEL"))
            .publishOn(Schedulers.newSingle("five-parallel-bar"))
            .map(FooNameHelper::concatAndSubstringFooName)
            .doOnError(error -> {
                logger.error("Approach 5-parallel failed!", error);
            })
            .subscribeOn(Schedulers.newParallel("five-parallel-starter"))
            .subscribe();
    }

    public void processUsingApproachFivePublishingToDifferentSingleThreads(Flux<Foo> flux) {
        logger.info("starting approach five-single!");
        flux.log()
            .subscribeOn(Schedulers.newSingle("five-single-starter"))
            .map(FooNameHelper::concatAndSubstringFooName)
            .publishOn(Schedulers.newSingle("five-single-foo"))
            .map(FooNameHelper::concatAndSubstringFooName)
            .map(FooQuantityHelper::divideFooQuantity)
            .map(foo -> FooReporter.reportResult(foo, "FIVE-SINGLE"))
            .publishOn(Schedulers.newSingle("five-single-bar"))
            .map(FooNameHelper::concatAndSubstringFooName)
            .doOnError(error -> {
                logger.error("Approach 5-single failed!", error);
            })
            .subscribe();
    }

}
