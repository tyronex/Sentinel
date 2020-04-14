/*
 * frxs Inc.  湖南兴盛优选电子商务有限公司.
 * Copyright (c) 2017-2019. All Rights Reserved.
 */
package com.tyrone.sentinel.example.controller;

import com.tyrone.sentinel.example.service.FlowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author huang tao
 * @version : FlowController.java,v 0.1 2020年04月14日 15:25
 */
@RestController
public class FlowController {

    @Autowired
    private FlowService flowService;

    @GetMapping("/flow1")
    public String flow1() {
        return flowService.flow1();
    }

    @GetMapping("/flow2")
    public String flow2() {
        return flowService.flow2();
    }

    @GetMapping("/flow3")
    public String flow3() {
        return flowService.flow3();
    }

    @GetMapping("/flow4")
    public String flow4() {
        return flowService.flow4();
    }
}
