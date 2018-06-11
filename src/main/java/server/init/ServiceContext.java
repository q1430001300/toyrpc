package server.init;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: 潘明杰
 * @date: 2018/6/11 13:05
 * @description:添加时是在服务启动时,而获取是在服务启动后,不会有线程安全问题
 * 如果将来和spring整合,可以用springcontext来处理
 */
public class ServiceContext {
    private static Map<Class, Object> map = new HashMap<>();


    public static void add(Class tClass, Object t) {
        map.put(tClass, t);
    }

    public static Object getInvoker(Class tclass) {
        if (!tclass.isInterface()) {
            throw new IllegalArgumentException("the param is not a interface,pls check out!");
        }
        return map.get(tclass);
    }
}
