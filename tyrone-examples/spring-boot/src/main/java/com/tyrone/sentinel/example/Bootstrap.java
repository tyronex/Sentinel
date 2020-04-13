/*
 * frxs Inc.  湖南兴盛优选电子商务有限公司.
 * Copyright (c) 2017-2019. All Rights Reserved.
 */
package com.tyrone.sentinel.example;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author huang tao
 * @version : Bootstrap.java,v 0.1 2020年04月11日 16:08
 */
@SpringBootApplication
public class Bootstrap implements ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(Bootstrap.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        //
//        System.out.println("发送nacos配置");
        final String remoteAddress = "114.55.34.166";
        final String groupId = "Sentinel:Demo";
        final String dataId = "com.alibaba.csp.sentinel.demo.flow.rule";
        final String rule = "[\n"
                + "  {\n"
                + "    \"resource\": \"name\",\n"
                + "    \"count\": 2.0,\n"
                + "    \"grade\": 1,\n"
                + "    \"limitApp\": \"default\"\n"
                + "  }\n"
                + "]";
//        ConfigService configService = NacosFactory.createConfigService(remoteAddress);
//        System.out.println(configService.publishConfig(dataId, groupId, rule));
//
//        System.out.println("加载nacos配置");
        //加载nacos配置
        // remoteAddress 代表 Nacos 服务端的地址
        // groupId 和 dataId 对应 Nacos 中相应配置
//        ReadableDataSource<String, List<FlowRule>> flowRuleDataSource = new NacosDataSource<>(remoteAddress, groupId, dataId,
//                source -> JSON.parseObject(source, new TypeReference<List<FlowRule>>() {
//                }));
//        FlowRuleManager.register2Property(flowRuleDataSource.getProperty());
        System.out.println("启动成功");
    }
}
