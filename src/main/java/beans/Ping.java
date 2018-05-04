package beans;

import javax.inject.Named;

@Named
public class Ping {

    public String answer() {
        return "pong";
    }
}
