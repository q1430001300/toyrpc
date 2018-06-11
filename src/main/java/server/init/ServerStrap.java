package server.init;

import base.IStudent;
import base.Student;
import base.StudentImpl;
import server.registery.ServerRegistry;

public class ServerStrap {

    public static void beginServer() {
        ServiceContext.add(IStudent.class, new StudentImpl());
        ServerRegistry serverRegistry = new ServerRegistry();
        serverRegistry.connectAndRegister();
    }
}
