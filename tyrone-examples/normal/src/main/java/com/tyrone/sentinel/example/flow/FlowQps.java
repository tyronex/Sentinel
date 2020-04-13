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
 * QPS限流 直接拒绝（抛出异常）
 *
 * @author huang tao
 * @version : FlowQps.java,v 0.1 2020年04月11日 16:10
 */
public class FlowQps {

    public static void main(String[] args) {
        //定义资源
        String resource = "test";
        //配置限流规则
        List<FlowRule> rules = new ArrayList<>();
        FlowRule rule = new FlowRule();
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        rule.setCount(2);
        rule.setResource(resource);
        rule.setLimitApp("default");

        rules.add(rule);
        //加载限流规则
        FlowRuleManager.loadRules(rules);

        boolean flag = true;
        int count = 0;
        int access = 0;

        //应用
        while (flag) {
            try {
                Thread.sleep(100);
                access++;
                System.out.println("访问" + access + "次");
                SphU.entry(resource);
            } catch (BlockException e) {
                count++;
                System.err.println("限流" + count + "次，" + e.getMessage());
                if (count == 10) {
                    flag = false;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                //
            }
        }
    }
}
