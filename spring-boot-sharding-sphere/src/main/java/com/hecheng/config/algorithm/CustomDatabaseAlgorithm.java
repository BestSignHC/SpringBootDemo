package com.hecheng.config.algorithm;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;

import java.util.Collection;

@Slf4j
public class CustomDatabaseAlgorithm implements PreciseShardingAlgorithm {
    private final String DATABASE = "user";

    @Override
    public String doSharding(Collection availableTargetNames, PreciseShardingValue shardingValue) {
        log.info("availableTargetNames: " + JSON.toJSONString(availableTargetNames));
        log.info("shardingValue: " + JSON.toJSONString(shardingValue));

        Long userId = Long.valueOf(shardingValue.getValue().toString());

        String table = DATABASE + (userId % 10 < 5 ? 1 : 0);
        log.info("return: " + table);
        return table;
    }
}
