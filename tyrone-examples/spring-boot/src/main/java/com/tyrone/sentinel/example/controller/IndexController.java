/*
 * frxs Inc.  湖南兴盛优选电子商务有限公司.
 * Copyright (c) 2017-2019. All Rights Reserved.
 */
package com.tyrone.sentinel.example.controller;

import com.alibaba.csp.sentinel.SphO;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.tyrone.sentinel.example.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author huang tao
 * @version : IndexController.java,v 0.1 2020年04月13日 16:06
 */
@RestController
public class IndexController {

    @Autowired
    private IndexService indexService;

    @GetMapping("/index/{name}")
    public String index(@PathVariable String name) {

        String resource = "index";

        List<FlowRule> rules = new ArrayList<>();

        FlowRule rule = new FlowRule();
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        rule.setCount(2);
        rule.setResource(resource);
        rule.setLimitApp("default");
        rules.add(rule);

        FlowRuleManager.loadRules(rules);


        if (SphO.entry(resource)) {
            try {

                //
            } catch (Exception e) {
                return "flow qps: " + e;
            } finally {
                SphO.exit();
            }
            return "INDEX: " + name;
        } else {
            return "flow qps: " + name;
        }

    }

    //    @GetMapping("/index2/{name}")
//    public String index2(@PathVariable String name) {
//        String resource = "name";
//        if (SphO.entry(resource)) {
//            try {
//
//                //
//            } catch (Exception e) {
//                return "flow qps: " + e;
//            } finally {
//                SphO.exit();
//            }
//            return "INDEX: " + name;
//        } else {
//            return "flow qps: " + name;
//        }
//    }
    @GetMapping("/index3/{name}")
    public String index3(@PathVariable String name) {
        return indexService.index(name);
    }
}
