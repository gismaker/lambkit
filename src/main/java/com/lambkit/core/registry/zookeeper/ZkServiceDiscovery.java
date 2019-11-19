package com.lambkit.core.registry.zookeeper;

import com.lambkit.Lambkit;
import com.lambkit.common.util.ArrayUtils;
import com.lambkit.core.registry.ServiceDiscovery;
import com.lambkit.core.rpc.RpcConfig;

import org.I0Itec.zkclient.ZkClient;
 
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadLocalRandom;
 
public class ZkServiceDiscovery implements ServiceDiscovery {

	private String ZK_REGISTRY = "/lambkit";
    private final List<String> addressCache = new CopyOnWriteArrayList<>();
    private ZkClient zkClient;
 
    public void init() {
    	RpcConfig config = Lambkit.config(RpcConfig.class);
    	zkClient = new ZkClient(config.getRegistryAddress(), 1 * 60 * 1000, 1000);
        System.out.println(">>> connect to zookeeper");
    }
 
    @Override
    public String discover(String name) {
 
        try {
            String servicePath = ZK_REGISTRY + "/" + name;
 
            //获取服务节点
            if (!zkClient.exists(servicePath)) {
                throw new RuntimeException(String.format(">>>can't find any service node on path {}",servicePath));
            }
 
            //从本地缓存获取某个服务地址
            String address;
            int addressCacheSize = addressCache.size();
            if (addressCacheSize > 0) {
                if (addressCacheSize == 1) {
                    address = addressCache.get(0);
                } else {
                    address = addressCache.get(ThreadLocalRandom.current().nextInt(addressCacheSize));
                }
                System.out.println(">>>get only address node:" + address);
 
                //从zk服务注册中心获取某个服务地址
            } else {
                List<String> addressList = zkClient.getChildren(servicePath);
                addressCache.addAll(addressList);
 
                //监听servicePath下的子文件是否发生变化
                zkClient.subscribeChildChanges(servicePath,(parentPath,currentChilds)->{
                    System.out.println(">>>servicePath is changed:" + parentPath);
                    addressCache.clear();
                    addressCache.addAll(currentChilds);
 
                });
 
                if (ArrayUtils.isNullOrEmpty(addressList)) {
                    throw new RuntimeException(String.format(">>>can't find any address node on path {}", servicePath));
                }
 
                int nodeSize = addressList.size();
                if (nodeSize == 1) {
                    address = addressList.get(0);
                } else {
 
                    //如果多个，则随机取一个
                    address = addressList.get(ThreadLocalRandom.current().nextInt(nodeSize));
                }
                System.out.println(">>>get address node:" + address);
 
            }
 
            //获取IP和端口号
            String addressPath = servicePath + "/" + address;
            String hostAndPort = zkClient.readData(addressPath);
            System.out.println(">>>host And Port:" + hostAndPort);
            return hostAndPort;
 
        } catch (Exception e) {
            System.out.println(">>> service discovery exception: " + e.getMessage());
            zkClient.close();
        }
 
        return null;
 
    }

}
