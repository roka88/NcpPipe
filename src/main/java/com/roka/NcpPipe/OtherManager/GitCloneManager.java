package com.roka.NcpPipe.OtherManager;



import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.function.Function;

public class GitCloneManager {

    public Function<Object, Git> clone(String repositoryPath, String downloadDirPath, String userName, String password) {
        return (result)-> {


            File file = new File(downloadDirPath);
            Git git;
            try {
                git = Git.cloneRepository().setURI(repositoryPath).setDirectory(file).setCredentialsProvider(new UsernamePasswordCredentialsProvider(userName, password)).call();
            } catch (GitAPIException e) {
                throw new RuntimeException(e);
            }
            return git;
        };
    }

    public Function<Object, Object> removeDir(String removeDirPath) {
        return (result)-> {

            if (removeDirPath.equals("/") || removeDirPath.equals("./") || removeDirPath.equals("~/")) {
                LoggerFactory.getLogger(this.getClass()).info("Caution!! : removeDirPath / or ./ or ~/ is incorrect");
                throw new RuntimeException("You Must Not Root Directory Path");
            }
            DefaultExecutor executor = new DefaultExecutor();
            Integer exitValue = 0;
            try {
                exitValue = executor.execute(CommandLine.parse("rm -rf " + removeDirPath));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return exitValue.toString();
        };
    }

}
