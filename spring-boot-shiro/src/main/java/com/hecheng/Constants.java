package com.hecheng;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

public class Constants {

    // 用于存储用户登陆信息
    // key:user_name value:sessionIds
    public static Map<String, Queue<Serializable>> userSessionMap =
            new HashMap<>();
}
