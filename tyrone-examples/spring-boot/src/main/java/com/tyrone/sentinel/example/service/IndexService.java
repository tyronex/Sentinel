/*
 * frxs Inc.  湖南兴盛优选电子商务有限公司.
 * Copyright (c) 2017-2019. All Rights Reserved.
 */
package com.tyrone.sentinel.example.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.stereotype.Service;

/**
 * @author huang tao
 * @version : IndexService.java,v 0.1 2020年04月13日 17:09
 */
@Service
public class IndexService {

    @SentinelResource(value = "service", blockHandler = "indexBlockHandler", fallback = "indexFallback")
    public String index(String name) {
        int a = 10 / 0;
        return "SERVICE RETURN :" + name;
    }

    public String indexBlockHandler(String name, BlockException e) {
        e.printStackTrace();
        return "BLOCK HANDLER RETURN:" + name;
    }

    public String indexFallback(String name) {
        return "FALLBACK RETURN:" + name;
    }

}
