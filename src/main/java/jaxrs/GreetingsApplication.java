package jaxrs;

import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

public class GreetingsApplication extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        final Set<Class<?>> classes = new HashSet<>();
        classes.add(Greetings.class);
        return classes;
    }
}
