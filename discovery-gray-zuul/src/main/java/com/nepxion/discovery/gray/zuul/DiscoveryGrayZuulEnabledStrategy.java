package com.nepxion.discovery.gray.zuul;

/**
 * <p>Title: Nepxion Discovery</p>
 * <p>Description: Nepxion Discovery</p>
 * <p>Copyright: Copyright (c) 2017-2050</p>
 * <p>Company: Nepxion</p>
 * @author Haojun Ren
 * @version 1.0
 */

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.nepxion.discovery.common.constant.DiscoveryConstant;
import com.nepxion.discovery.plugin.framework.adapter.PluginAdapter;
import com.nepxion.discovery.plugin.strategy.adapter.DiscoveryEnabledStrategy;
import com.nepxion.discovery.plugin.strategy.zuul.context.ZuulStrategyContextHolder;
import com.netflix.loadbalancer.Server;

// 实现了组合策略，版本路由策略+区域路由策略+IP和端口路由策略+自定义策略
public class DiscoveryGrayZuulEnabledStrategy implements DiscoveryEnabledStrategy {
    private static final Logger LOG = LoggerFactory.getLogger(DiscoveryGrayZuulEnabledStrategy.class);

    @Autowired
    private ZuulStrategyContextHolder zuulStrategyContextHolder;

    @Autowired
    private PluginAdapter pluginAdapter;

    @Override
    public boolean apply(Server server, Map<String, String> metadata) {
        // 对Rest调用传来的Header参数（例如：mobile）做策略
        String mobile = zuulStrategyContextHolder.getHeader("mobile");
        String version = metadata.get(DiscoveryConstant.VERSION);
        String serviceId = pluginAdapter.getServerServiceId(server);

        LOG.info("Zuul端负载均衡用户定制触发：mobile={}, serviceId={}, metadata={}", mobile, serviceId, metadata);

        if (StringUtils.isNotEmpty(mobile)) {
            // 手机号以移动138开头，路由到1.0版本的服务上
            if (mobile.startsWith("138") && StringUtils.equals(version, "1.0")) {
                return true;
                // 手机号以联通133开头，路由到2.0版本的服务上
            } else if (mobile.startsWith("133") && StringUtils.equals(version, "1.1")) {
                return true;
            } else {
                // 其它情况，直接拒绝请求
                return false;
            }
        }

        return true;
    }
}