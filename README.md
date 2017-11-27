# NcpPipe

NcpPipe만 담겨있는 라이브러리로 Java 8의 Function으로 랩핑되어있음

### 변경점
<pre>
0.0.1 초기버전
</pre>

### 의존성
* Java 8
* ncloud-api-0.2.5.jar 가 가져야 할 의존성
<pre>
compile group: 'commons-codec', name: 'commons-codec', version: '1.4'
compile group: 'commons-lang', name: 'commons-lang', version: '2.3'
compile group: 'commons-logging', name: 'commons-logging', version: '1.1.1'
compile group: 'org.codehaus.jettison', name: 'jettison', version: '1.3.3'
compile group: 'com.jcraft', name: 'jsch', version: '0.1.44-1'
compile group: 'log4j', name: 'log4j', version: '1.2.14'
compile group: 'org.slf4j', name: 'slf4j-api', version: '1.6.0'
compile group: 'stax', name: 'stax-api', version: '1.0.1'
compile group: 'xmlpull', name: 'xmlpull', version: '1.1.3.1'
compile group: 'xpp3', name: 'xpp3_min', version: '1.1.4c'
compile group: 'com.thoughtworks.xstream', name: 'xstream', version: '1.4.2'
compile group: 'org.slf4j', name: 'slf4j-log4j12', version: '1.6.0'
</pre>
* NcpPipe가 가져야 할 의존성

<pre>
compile files('libs/ncloud-api-0.2.5.jar')
compile group: 'org.eclipse.jgit', name: 'org.eclipse.jgit', version: '4.9.0.201710071750-r'
compile group: 'org.apache.commons', name: 'commons-exec', version: '1.3'
</pre>

### Function 사용(example)
<pre>
CompletableFuture<Function<Object, Object>> completableFuture = CompletableFuture.supplyAsync(()->logManager.log("start"), executor)
.thenApplyAsync(instanceManage.startServerInstancesForJob(Collections.singletonList(config.getBaseInstanceNo())), executor)
.thenApplyAsync(instanceManage.waitChangedServerStatusForJob(Collections.singletonList(config.getBaseInstanceNo()), ServerManager.HealthCheckType.NSTOP, ServerManager.HealthCheckType.RUN, 10000), executor)
.thenApplyAsync(uploadFileManager.uploadFile(config.getSshUserName(), config.getSshHost(), config.getSshPort(), config.getSshPasswd(), config.getSshFilePath(), config.getSshUploadPath()), executor)
.thenApplyAsync(instanceManage.stopServerInstancesForJob(Collections.singletonList(config.getBaseInstanceNo())), executor)
.thenApplyAsync(instanceManage.waitChangedServerStatusForJob(Collections.singletonList(config.getBaseInstanceNo()), ServerManager.HealthCheckType.RUN, ServerManager.HealthCheckType.NSTOP, 10000), executor)
.thenApplyAsync(instanceImgManage.createMemberServerImageForJob(config.getBaseServerImgName(), null, config.getBaseInstanceNo()), executor)
.thenApplyAsync(instanceImgManage.waitChangedServerImgStatusOfStaticImgNoForJob(ServerImageManager.HealthCheckType.INIT, ServerImageManager.HealthCheckType.CREAT, 30000), executor)
.thenApplyAsync(launchConfigurationManager.createLaunchConfigurationStaticWithImgNoForJob(config.getBaseServerImgName(), null, config.getServerProductCode(), Collections.singletonList(config.getAcgNo()), config.getLoginKeyName(), null), executor)
.thenApplyAsync(autoScalingGroupExtendManager.createAutoScalingGroupForJob(config.getBaseServerImgName(), config.getBaseServerImgName(), config.getDesiredCapacityOfAutoScaling(),
                config.getMinSizeOfAutoScaling(), config.getMaxSizeOfAutoScaling(), config.getDefalutCoolDownOfAutoScaling(), AutoScalingGroupManager.AutoScalingHealthCheckType.LOADB, config.getHealthCheckGracePeriodOfAutoScaling(),
                Collections.singletonList(config.getRegionNo()), Collections.singletonList(config.getLoadBalancerName())), executor)
.thenApplyAsync(autoScalingGroupExtendManager.checkServerStatusOfAutoScalingInServiceForJob(config.getBaseServerImgName(), 10000, 10000), executor)
.thenApplyAsync(autoScalingGroupExtendManager.setDesiredCapacityForJob(config.getPreviousAutoScalingGroupName(), 0), executor)
.thenApplyAsync(autoScalingGroupExtendManager.checkServerStatusOfAutoScalingInTerminatedForJob(config.getPreviousAutoScalingGroupName(), 10000, 30000), executor).join();

</pre>



### NcpManager
1. 기본적으로 ncloud-api-0.2.5.jar에 의존성이 있음
2. ncloud-api-0.2.5.jar의 기본 Api들이 Job으로 감싸여있음
3. ncloud-api-0.2.5.jar 모든 Api들이 포함되어 있지는 않음. 필요시 직접 구현
<pre>
NcloudApiRequest ncloudApiRequest = new NcloudApiRequest();

.....
.....

AutoScalingGroupManager autoScalingGroupManager = new AutoScalingGroupManager(ncloudApiRequest);        
AutoScalingLogManager autoScalingLogManager = new AutoScalingLogManager(ncloudApiRequest);
AutoScalingPolicyManager autoScalingPolicyManager = new AutoScalingPolicyManager(ncloudApiRequest);
LaunchConfigurationManager launchConfigurationManager = new LaunchConfigurationManager(ncloudApiRequest);
ProductManager productManager = new ProductManager(ncloudApiRequest);
ScheduledActionManager scheduledActionManager = new ScheduledActionManager(ncloudApiRequest);
ServerImageManager serverImageManager = new ServerImageManager(ncloudApiRequest);
ServerManager serverManager = new ServerManager(ncloudApiRequest);
SuspendProcessManager suspendProcessManager = new SuspendProcessManager(ncloudApiRequest);

</pre>


### NcpExtendManager
1. 기본적으로 ncloud-api-0.2.5.jar에 의존성이 있음
2. ncloud-api-0.2.5.jar의 기본 Api들이 Blue/Grean Deploy에 이용할 몇 가지 Job으로 감싸여있음
3. ncloud-api-0.2.5.jar 모든 Api들이 포함되어 있지는 않음. 필요시 직접 구현

<pre>
NcloudApiRequest ncloudApiRequest = new NcloudApiRequest();
.....
.....
AutoScalingGroupExtendManager autoScalingGroupExtendManager = new AutoScalingGroupExtendManager(ncloudApiRequest);
InstanceImgExtendManager instanceImgExtendManager = new InstanceImgExtendManager(ncloudApiRequest);
LaunchConfigurationExtendManager launchConfigurationExtendManager = new LaunchConfigurationExtendManager(ncloudApiRequest);
ServerExtendManager serverExtendManager = new ServerExtendManager(ncloudApiRequest);
</pre>


### OtherManager
<pre>
BuildManager buildManager = new BuildManager();
GitCloneManager gitCloneManager = new GitCloneManager();
LogManager logManager = new LogManager();
StopJobManager stopJobManager = new StopJobManager();
UploadFileManager uploadFileManager = new UploadFileManager();
WaitManager waitManager = new WaitManager();
</pre>


