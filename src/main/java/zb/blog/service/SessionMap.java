package zb.blog.service;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by zhmt on 2017/6/6.
 */
@Component
public class SessionMap {
    public static class Session {
        private String sid = UUID.randomUUID().toString();
        private long ttl = 1000*60*15; // 15 minutes
        private volatile long lastUpdate = System.currentTimeMillis();
        private HashMap<Object,Object> attrs;

        public void setTtl(long ttl) {
            this.ttl = ttl;
        }

        public String getSid() {
            return sid;
        }

        public long getTtl() {
            return ttl;
        }
    }
    private final ConcurrentHashMap<String,Session> map = new ConcurrentHashMap<>();
    public Session get(String sid) {
        Session ret = map.get(sid);
        if(ret==null)
            return null;
        long now = System.currentTimeMillis();
        if(now>ret.ttl+ret.lastUpdate) {
            return null;
        }
        ret.lastUpdate = now;
        return  ret;
    }

    public Session newSession() {
        Session ret = new Session();
        map.put(ret.sid,ret);
        return ret;
    }

    public Session newSession(long ttl) {
        Session ret = new Session();
        ret.ttl = ttl;
        map.put(ret.sid,ret);
        return ret;
    }
}
