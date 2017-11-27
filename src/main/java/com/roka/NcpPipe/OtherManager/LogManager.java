package com.roka.NcpPipe.OtherManager;


import org.slf4j.LoggerFactory;

import java.util.function.Function;

public class LogManager {

    public Function<Object, Object> resultLog() {
        return (result)-> {
            LoggerFactory.getLogger(this.getClass()).info("result: "+result.toString());
            return result;
        };
    }

    public Function<Object, Object> log(String log) {
        return (result)-> {
            LoggerFactory.getLogger(this.getClass()).info(log);
            return result;
        };
    }
}
