package zookeeper;

import java.util.ResourceBundle;

public class ZookeeperConstant {

    public static final String IP;

    public static final String PORT;

    public static final String SLASH_SEPARATOR = "/";

    public static final String SEPARATOR = ":";

    static {
        ResourceBundle zk = ResourceBundle.getBundle("zk");
        IP = zk.getString("zk.ip");
        PORT = zk.getString("zk.port");
    }
}
