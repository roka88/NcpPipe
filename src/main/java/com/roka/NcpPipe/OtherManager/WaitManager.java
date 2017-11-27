package com.roka.NcpPipe.OtherManager;


import java.util.function.Function;

public class WaitManager {

    public Function<Object, Object> waitJob(long waitTimeMills) {
        return (result) -> {
            try {
                Thread.sleep(waitTimeMills);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return result;
        };
    }
}
