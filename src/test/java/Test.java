import com.ncloud.api.autoscaling.model.AutoScalingGroup;
import com.ncloud.api.connection.NcloudApiRequest;
import com.ncloud.api.image.model.MemberServerImageList;
import com.roka.NcpPipe.NcpExtendManager.InstanceImgExtendManager;
import com.roka.NcpPipe.NcpManager.AutoScalingGroupManager;
import com.roka.NcpPipe.NcpManager.ServerImageManager;
import com.roka.NcpPipe.NcpManager.ServerManager;
import com.roka.NcpPipe.OtherManager.GitCloneManager;
import com.roka.NcpPipe.OtherManager.LogManager;

import java.util.Collections;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.function.Function;

public class Test {

    @org.junit.Test
    public void test() {
        NcloudApiRequest ncloudApiRequest = new NcloudApiRequest();
        ncloudApiRequest.setRequestURL("https://api.ncloud.com");
        ncloudApiRequest.setConsumerKey("pwGP4oPJFG5IWrWlkCdB");
        ncloudApiRequest.setConsumerSecret("EBlbbvWccxxlRC7nX1Zbc3n8UbggAeko7E1JIX13");
        InstanceImgExtendManager instanceImgExtendManager = new InstanceImgExtendManager(ncloudApiRequest);


//
//        CompletableFuture.supplyAsync(()->new MemberServerImageList()).thenApplyAsync(instanceImgExtendManager.createMemberServerImageForJob("testtesttest", "zz", "507693"))
//                .thenApplyAsync((result)->{
//                    System.out.println(result.toString());
//                    return result;
//                }).thenApplyAsync(instanceImgExtendManager.waitChangedServerImgStatusOfStaticImgNoForJob(ServerImageManager.HealthCheckType.INIT, ServerImageManager.HealthCheckType.CREAT, 30000)).join();

    }
}
