package com.hecheng.filter;

import com.hecheng.Constants;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.DefaultSessionKey;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.Queue;

@Component
public class KickOutFilter extends AccessControlFilter {
    // 被踢出后的重定向地址
    private String kickOutUrl;

    // 同一用户最大同时登陆数
    private int maxSession = 1;

    private SessionManager sessionManager;

    public void setKickOutUrl(String kickOutUrl) {
        this.kickOutUrl = kickOutUrl;
    }

    public void setMaxSession(int maxSession) {
        this.maxSession = maxSession;
    }

    public void setSessionManager(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        Subject subject = getSubject(request, response);

        if(!subject.isAuthenticated() && !subject.isRemembered()) {
            return true;    // 没有登陆
        }

        String userName = (String) subject.getPrincipal();
        Session session = subject.getSession();
        Serializable sessionId = session.getId();
        System.out.println("Try access userName: " + userName
                + "; sessionId: " + sessionId);

        Queue<Serializable> sessionIds = Constants.userSessionMap.get(userName);
        if (sessionIds == null) {
            sessionIds = new LinkedList<>();

            // 第一次登陆，直接放进去
            System.out.println("Start add first session: " + sessionId);
            sessionIds.add(sessionId);

            Constants.userSessionMap.put(userName, sessionIds);
            return true;
        }

        // 当前用户未登陆，且该用户未被踢出
        Object kickOut = session.getAttribute("kickOut");
        if (!sessionIds.contains(sessionId) && null == kickOut) {
            System.out.println("Start add session: " + sessionId);
            sessionIds.add(sessionId);
        }
        else if (null != kickOut) {
            System.out.println("Start logout: " + subject.getPrincipal());
            subject.logout();
            saveRequest(request);
            WebUtils.issueRedirect(request, response, kickOutUrl);
            return false;
        }

        //如果队列里的sessionId数超出最大会话数，开始踢人
        while (sessionIds.size() > maxSession) {
            Serializable removeSessionId = sessionIds.remove();

            System.out.println("Start Remove Session: " + removeSessionId);
            Session removeSession = sessionManager.getSession(new DefaultSessionKey(removeSessionId));
            removeSession.setAttribute("kickOut", true);
        }
        Constants.userSessionMap.put(userName, sessionIds);
        return true;
    }
}
