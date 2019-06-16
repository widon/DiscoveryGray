package com.nepxion.discovery.gray.service;




import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.nepxion.discovery.plugin.framework.adapter.PluginAdapter;

import io.grpc.stub.StreamObserver;
import net.devh.springboot.autoconfigure.grpc.server.GrpcService;

/**
 * @author Michael (yidongnan@gmail.com)
 * @since 2016/11/8
 */

@GrpcService
public class GrpcServerService extends SimpleGrpc.SimpleImplBase {
	
    @Autowired
    private PluginAdapter pluginAdapter;

    @Override
    public void sayHello(HelloRequest req, StreamObserver<HelloReply> responseObserver) {
    	
        String serviceId = pluginAdapter.getServiceId();
        String host = pluginAdapter.getHost();
        int port = pluginAdapter.getPort();
        String version = pluginAdapter.getVersion();
        String region = pluginAdapter.getRegion();

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(" -> " + serviceId);
        stringBuilder.append("[" + host + ":" + port + "]");
        if (StringUtils.isNotEmpty(version)) {
            stringBuilder.append("[V" + version + "]");
        }
        if (StringUtils.isNotEmpty(region)) {
            stringBuilder.append("[Region=" + region + "]");
        }

        String serviceInfo =  stringBuilder.toString();
    	
        HelloReply reply = HelloReply.newBuilder().setMessage(req.getName()+serviceInfo).build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }
}
