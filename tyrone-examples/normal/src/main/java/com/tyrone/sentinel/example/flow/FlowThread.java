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
 * 线程数限流 直接拒绝（抛出异常）
 *
 * @author huang tao
 * @version : FlowThread.java,v 0.1 2020年04月11日 16:28
 */
public class FlowThread {

    public static void main(String[] args) {
        //
        final String resource = "test_flow_thread";
        //
        List<FlowRule> rules = new ArrayList<>();
        FlowRule rule = new FlowRule();
        rule.setResource(resource);
        rule.setGrade(RuleConstant.FLOW_GRADE_THREAD);
        rule.setCount(1);
        rule.setLimitApp("default");
        //
        rules.add(rule);
        FlowRuleManager.loadRules(rules);
        //
        for (int i = 0; i < 50; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        SphU.entry(resource);
                        System.out.println(Thread.currentThread().getName() + " --通过");
                    } catch (BlockException e) {
                        System.err.println(Thread.currentThread().getName() + " --限流");
                    } /*catch (InterruptedException e) {
                            e.printStackTrace();
                        }*/
                }
            });
            thread.start();
        }


    }
}
