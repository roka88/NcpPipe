package com.roka.NcpPipe.OtherManager;


import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;

import java.util.function.Function;

public class BuildManager {

    public Function<Object, Object> gradleBuild(String buildPath) {
        return (result)-> {

            DefaultExecutor executor = new DefaultExecutor();
            String command = new StringBuilder("gradle build").append(" ").append("--project-dir").append(" ").append(buildPath).toString();
            Integer exitValue = 0;
            try {
                exitValue = executor.execute(CommandLine.parse(command));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            return exitValue.toString();
        };
    }
}
