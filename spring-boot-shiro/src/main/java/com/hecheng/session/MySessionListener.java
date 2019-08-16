package com.hecheng.session;

import com.alibaba.fastjson.JSON;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;

public class MySessionListener implements SessionListener {
    @Override
    public void onStart(Session session) {
        System.out.println("Session start: " + JSON.toJSONString(session));
    }

    @Override
    public void onStop(Session session) {
        System.out.println("Session stop: " + JSON.toJSONString(session));
    }

    @Override
    public void onExpiration(Session session) {
        System.out.println("Session expire: " + JSON.toJSONString(session));
    }
}
