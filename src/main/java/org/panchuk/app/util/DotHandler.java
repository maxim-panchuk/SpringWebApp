package org.panchuk.app.util;

import org.panchuk.app.entity.Dot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class DotHandler {
    private final Dot dot;

    @Autowired
    public DotHandler(Dot dot) {
        this.dot = dot;
    }

    public void service () {
        dot.setResult(setResult());
        dot.setTime(setTime());
    }

    private boolean setResult () {
        double x = dot.getX();
        double y = dot.getY();
        double r = dot.getR();

        return x <= 0 && y >= 0 && Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2)) <= r / 2 ||
                x <= 0 && y <= 0 && x >= -r && y >= -r ||
                x >= 0 && y <= 0 && y >= (x - r);
    }

    private String setTime () {
        return new SimpleDateFormat("hh:mm:ss").format(new Date());
    }
}
