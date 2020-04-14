/*
 * frxs Inc.  湖南兴盛优选电子商务有限公司.
 * Copyright (c) 2017-2019. All Rights Reserved.
 */
package com.tyrone.sentinel.example.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.context.Context;
import com.alibaba.csp.sentinel.context.ContextUtil;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 限流
 *
 * @author huang tao
 * @version : FlowService.java,v 0.1 2020年04月14日 15:21
 */
@Service
public class FlowService {
    public static final String RESOURCE1 = "flow1";
    public static final String RESOURCE2 = "flow2";
    public static final String RESOURCE3 = "flow3";
    public static final String RESOURCE4 = "flow4";
    public static final String LIMIT_APP = "default";
    {
        List<FlowRule> rules = Lists.newArrayListWithExpectedSize(1);
        FlowRule rule1 = new FlowRule();
        rule1.setGrade(RuleConstant.FLOW_GRADE_QPS);
        rule1.setCount(2);
        rule1.setResource(RESOURCE1);
        rule1.setLimitApp(LIMIT_APP);
        rules.add(rule1);
        FlowRule rule2 = new FlowRule();
        rule2.setGrade(RuleConstant.FLOW_GRADE_QPS);
        rule2.setCount(2);
        rule2.setResource(RESOURCE2);
        rule2.setLimitApp(LIMIT_APP);
        rules.add(rule2);
        FlowRule rule3 = new FlowRule();
        rule3.setGrade(RuleConstant.FLOW_GRADE_QPS);
        rule3.setCount(2);
        rule3.setResource(RESOURCE3);
        rule3.setLimitApp(LIMIT_APP);
        rules.add(rule3);
        FlowRule rule4 = new FlowRule();
        rule4.setGrade(RuleConstant.FLOW_GRADE_QPS);
        rule4.setCount(2);
        rule4.setResource(RESOURCE4);
        rule4.setLimitApp(LIMIT_APP);
        rules.add(rule4);
        FlowRuleManager.loadRules(rules);
    }

    @SentinelResource(value = RESOURCE1)
    public String flow1() {

        Context context = ContextUtil.getContext();
        System.out.println("context = " + context);
        return "FLOW";
    }

    @SentinelResource(value = RESOURCE2)
    public String flow2() {
        return "FLOW";
    }

    @SentinelResource(value = RESOURCE3)
    public String flow3() {
        return "FLOW";
    }

    @SentinelResource(value = RESOURCE4)
    public String flow4() {
        return "FLOW";
    }
}
