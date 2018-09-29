# toyrpc

#### 这是一个为了学习netty而建的一个rpcdemo项目,加入了zk,注册发现机制服务端注册发现,在zk创建临时节点,消费端连接上zk后,从节点拿到服务端的数据,然后连接上服务端,并将channel放入容器中,当调用时生成动态代理,从容器中拿到channel,去服务端进行调用,详细说明https://www.panmingjie.cn/?p=151

