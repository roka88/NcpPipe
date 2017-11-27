package com.roka.NcpPipe.OtherManager;

import com.jcraft.jsch.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.function.Function;

public class UploadFileManager {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public Function<Object, Object> uploadFile(String userName, String host, Integer port, String password, String filePath, String uploadPath) {

        return (result)-> {

            try {
                JSch jsch = new JSch();
                Session session = jsch.getSession(userName, host, port);
                session.setConfig("StrictHostKeyChecking", "no");
                session.setPassword(password);
                session.connect();
                Channel channel = session.openChannel("sftp");
                channel.connect();
                ChannelSftp channelSftp = (ChannelSftp) channel;
                File file = new File(filePath);
                FileInputStream fis = new FileInputStream(file);
                logger.info("File uploading - " + file.getAbsolutePath());
                channelSftp.cd(uploadPath);
                channelSftp.put(fis, file.getName());
                fis.close();
                logger.info("File uploaded successfully - " + file.getAbsolutePath());

                channel.disconnect();
                session.disconnect();
            } catch (JSchException e) {
                throw new RuntimeException(e);
            } catch (SftpException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return "ok";
        };
    }
}
