# toyrpc

#### 这是一个为了学习netty而建的一个rpcdemo项目,加入了zk,注册发现机制服务端注册发现,在zk创建临时节点,消费端连接上zk后,从节点拿到服务端的数据,然后连接上服务端,并将channel放入容器中,当调用时生成动态代理,从容器中拿到channel,去服务端进行调用,详细说明http://www.panmingjie.cn/index.php/2018/06/12/%E4%B8%80%E4%B8%AA%E7%AE%80%E5%8D%95%E7%9A%84rpc%E6%A1%86%E6%9E%B6%E7%9A%84%E5%AE%9E%E7%8E%B0/
