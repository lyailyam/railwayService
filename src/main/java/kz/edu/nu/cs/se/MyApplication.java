package kz.edu.nu.cs.se;

import kz.edu.nu.cs.se.api.Ticket;
import kz.edu.nu.cs.se.api.TicketAPI;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;



@ApplicationPath("/api")
public class MyApplication extends Application {
    private Set<Object> singletons = new HashSet<Object>();
    private Set<Class<?>> empty = new HashSet<Class<?>>();

    public MyApplication() {
        singletons.add(new TestService());
        singletons.add(new TestDB());
        singletons.add(new TicketAPI());
    }

    @Override
    public Set<Class<?>> getClasses() {
        return empty;
    }

    @Override
    public Set<Object> getSingletons() {
        return singletons;
    }
}

