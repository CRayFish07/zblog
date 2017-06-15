package zb.blog.service;

import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by zhmt on 2017/6/6.
 */
//@Component
//public class SessionMap {
//    public static class Session {
//        private String sid = UUID.randomUUID().toString();
//        private long ttl = 1000*60*15; // 15 minutes
//        private volatile long lastUpdate = System.currentTimeMillis();
//        public final ConcurrentHashMap<Object,Object> attrs = new ConcurrentHashMap<>();
//
//        public void setTtl(long ttl) {
//            this.ttl = ttl;
//        }
//
//        public String getSid() {
//            return sid;
//        }
//
//        public long getTtl() {
//            return ttl;
//        }
//
//        boolean isTimeout(long now) {
//            if(now>ttl+lastUpdate) {
//                return true;
//            }
//            return false;
//        }
//    }
//    private final ConcurrentHashMap<String,Session> map = new ConcurrentHashMap<>();
//    public Session get(String sid) {
//        cleanSession();
//
//        Session ret = map.get(sid);
//        if(ret==null)
//            return null;
//        long now = System.currentTimeMillis();
//        if(ret.isTimeout(now)) {
//            return null;
//        }
//        ret.lastUpdate = now;
//        return  ret;
//    }
//
//    public Session newSession() {
//        cleanSession();
//
//        Session ret = new Session();
//        map.put(ret.sid,ret);
//        return ret;
//    }
//
//    public Session newSession(long ttl) {
//        cleanSession();
//
//        Session ret = new Session();
//        ret.ttl = ttl;
//        map.put(ret.sid,ret);
//        return ret;
//    }
//
//    private AtomicLong lastClean = new AtomicLong(System.currentTimeMillis());
//    private void cleanSession() {
//        //30秒清除一次
//        long now = System.currentTimeMillis();
//        if(now-lastClean.get()<30*1000) {
//            return ;
//        }
//        lastClean.set(now);
//        List<String> toClean = new LinkedList<>();
//        map.forEachEntry(1,(Map.Entry<String,Session> e)->{
//            if(e.getValue().isTimeout(now)) {
//                toClean.add(e.getKey());
//            }
//        });
//
//        toClean.forEach((String key)->map.remove(key));
//    }
//}
