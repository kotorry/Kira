import redis.clients.jedis.Jedis;

public class Tool {
    private Jedis jedis;
    Tool(String password,String local){
        System.out.println(local);
        jedis=new Jedis(local);
        jedis.auth(password);
        System.out.println(jedis.ping());
    }
    public void set(String key,String value,int type){
        switch (type){
            case 1:
                jedis.hset("temp",key,value);
                break;
            case 2:
                jedis.hset("humi",key,value);
                break;
            case 3:
                jedis.hset("smoke",key,value);
        }
    }

}
