/*
 * Copyright 1999-2018 Alibaba Group Holding Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.alibaba.csp.sentinel.node.metric;

import com.alibaba.csp.sentinel.Constants;
import com.alibaba.csp.sentinel.config.SentinelConfig;
import com.alibaba.csp.sentinel.log.RecordLog;
import com.alibaba.csp.sentinel.node.ClusterNode;
import com.alibaba.csp.sentinel.slotchain.ResourceWrapper;
import com.alibaba.csp.sentinel.slots.clusterbuilder.ClusterBuilderSlot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

/**
 * @author jialiang.linjl
 */
public class MetricTimerListener implements Runnable {

    /**
     * 初始化MetricWriter 从SentinelConfig获取单个文件大小和总问价个数
     * 构造方法：创建文件夹，设置单个文件大小，总文件个数，设置时间
     */
    private static final MetricWriter metricWriter = new MetricWriter(SentinelConfig.singleMetricFileSize(),
            SentinelConfig.totalMetricFileCount());

    /**
     * 定时数据采集，写入log文件
     */
    @Override
    public void run() {
        Map<Long, List<MetricNode>> maps = new TreeMap<>();
        //遍历集群节点
        for (Entry<ResourceWrapper, ClusterNode> e : ClusterBuilderSlot.getClusterNodeMap().entrySet()) {
            ClusterNode node = e.getValue();
            Map<Long, MetricNode> metrics = node.metrics();
            aggregate(maps, metrics, node);
        }
        //ClusterNode实例:Constants.ENTRY_NODE
        //统计数据：Constants.ENTRY_NODE.metrics(),ClusterNode是继承StatisticNode，统计数据调用StatisticNode.metrics()
        aggregate(maps, Constants.ENTRY_NODE.metrics(), Constants.ENTRY_NODE);
        if (!maps.isEmpty()) {
            for (Entry<Long, List<MetricNode>> entry : maps.entrySet()) {
                try {
                    //写入日志文件
                    metricWriter.write(entry.getKey(), entry.getValue());
                    System.out.println("entry-key = " + entry.getKey());
                    System.out.println("entry-value = " + entry.getValue());
                } catch (Exception e) {
                    RecordLog.warn("[MetricTimerListener] Write metric error", e);
                }
            }
        }
    }

    /**
     * 汇总统计的数据
     *
     * @param maps
     * @param metrics
     * @param node
     */
    private void aggregate(Map<Long, List<MetricNode>> maps, Map<Long, MetricNode> metrics, ClusterNode node) {
        for (Entry<Long, MetricNode> entry : metrics.entrySet()) {
            long time = entry.getKey();
            MetricNode metricNode = entry.getValue();
            metricNode.setResource(node.getName());
            metricNode.setClassification(node.getResourceType());
            if (maps.get(time) == null) {
                maps.put(time, new ArrayList<MetricNode>());
            }
            List<MetricNode> nodes = maps.get(time);
            nodes.add(entry.getValue());
        }
    }

}
