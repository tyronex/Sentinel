/*
 * frxs Inc.  湖南兴盛优选电子商务有限公司.
 * Copyright (c) 2017-2019. All Rights Reserved.
 */
package com.tyrone.sentinel.example.flow;

import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;

import java.util.ArrayList;
import java.util.List;

/**
 * QPS限流 匀速排队
 *
 * @author huang tao
 * @version : FlowQpsPace.java,v 0.1 2020年04月11日 17:12
 */
public class FlowQpsPace {
    public static void main(String[] args) {
        //
        final String resource = "test_flow_qps_pace";
        //
        List<FlowRule> rules = new ArrayList<>();
        //
        FlowRule rule = new FlowRule();
        //行为--匀速排队
        rule.setControlBehavior(RuleConstant.CONTROL_BEHAVIOR_RATE_LIMITER);
        //最长排队时间
//        rule.setMaxQueueingTimeMs(5 * 1000);

        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        rule.setCount(1);
        rule.setResource(resource);
        rule.setLimitApp("default");

        rules.add(rule);
        //
        FlowRuleManager.loadRules(rules);
        //
        for (int i = 0; i < 1000; i++) {
            try {
//                        Thread.sleep((long) (Math.random() * 10 * 5000));
                SphU.entry(resource);
                System.out.println("通过");
            } catch (BlockException e) {
                System.err.println("限流");
            }
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    try {
////                        Thread.sleep((long) (Math.random() * 10 * 5000));
//                        SphU.entry(resource);
//                        System.out.println("通过");
//                    } catch (BlockException e) {
//                        System.err.println("限流");
//                    } /*catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }*/
//                }
//            }).start();


        }

    }
}
