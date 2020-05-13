package com.softdev.system.demo.config;

import redis.clients.jedis.Jedis;

import java.util.Map;
public class Redis {
    public Jedis jedis;
    public Redis(String password,String local){
        System.out.println(local);
        jedis=new Jedis(local);
        jedis.auth(password);
        System.out.println(jedis.ping());
    }
    public Map<String, String> get(int type){
        System.out.println(type);
      try {
          switch (type){
              case 1:
                  System.out.println(jedis.hgetAll("temp"));
                  return jedis.hgetAll("temp");
              case 2:
                  return jedis.hgetAll("humi");
              case 3:
                  return jedis.hgetAll("smoke");
          }
      }catch (Exception E){
          System.out.println("redis error");
    }
        return null;
    }
}
