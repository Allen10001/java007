package com.tv.demo001.guice.official.github.getingStarted;

import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.Provides;

/**
 * @author hubo88
 * @description
 * @date 2023/2/2 5:54 PM
 */
public class Greeter {



    private final String message;
    private final int count;

    // Greeter declares that it needs a string message and an integer
    // representing the number of time the message to be printed.
    // The @Inject annotation marks this constructor as eligible to be used by
    // Guice.
    @Inject
    Greeter(@Message String message, @Count int count) {
        this.message = message;
        this.count = count;
    }

    void sayHello() {
        for (int i=0; i < count; i++) {
            System.out.println(message);
        }
    }
}


@interface Message{

}

@interface Count {

}

/**
 * Guice module that provides bindings for message and count used in
 * {@link Greeter}.
 * DemoModule uses the @Provides methods to specify the dependencies.
 */
class DemoModule extends AbstractModule {

    @Provides
    @Count
    static Integer provideCount() {
        return 3;
    }

    @Provides
    @Message
    static String provideMessage() {
        return "hello world";
    }
}