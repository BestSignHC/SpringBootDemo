package com.hecheng.config.algorithm;

import com.alibaba.fastjson.JSON;
import com.google.common.hash.Hashing;
import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;

import java.util.Collection;

@Slf4j
public class CustomTableAlgorithm implements PreciseShardingAlgorithm {
    private final String TABLE_PREFIX = "user_info_";

    @Override
    public String doSharding(Collection availableTargetNames, PreciseShardingValue shardingValue) {
        log.info("availableTargetNames: " + JSON.toJSONString(availableTargetNames));
        log.info("shardingValue: " + JSON.toJSONString(shardingValue));

        Long userId = Long.valueOf(shardingValue.getValue().toString());

        // guava jump consistent hash实现
        int tableIndex = Hashing.consistentHash(userId, 2);

        log.info("return: " + tableIndex);
        return TABLE_PREFIX + tableIndex + "";
    }
}
