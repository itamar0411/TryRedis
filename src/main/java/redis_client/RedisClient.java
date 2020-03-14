package redis_client;

import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Set;

public class RedisClient {
    public static void main(String[] args) {

        Jedis jedis = new Jedis("localhost");
        System.out.println("Connection to server sucessfully");

        /////// Redis Java List Example //////////
        jedis.lpush("itamar", "meital");
        jedis.lpush("itamar", "uriah");
        jedis.lpush("itamar", "ofri");
        jedis.lpush("itamar", "test1");
        jedis.set("amitai", "test2");
        jedis.append("amitai", "test3");
        jedis.append("amitai", "test4");

        // Get the stored data and print it for key="itamar"
        List<String> results = jedis.lrange("itamar", 0 ,3);
        System.out.println("Results: " + results);

        //////// Redis Java Keys Example, print all keys /////////
        Set<String> keys = jedis.keys("*");
        System.out.println("Keys: " + keys);

        // Get appended value for key = "amitai"
        System.out.println("Appended value for amitai: " + jedis.get("amitai"));

        if(jedis.exists("amitai")) {
            System.out.println("amitai exists");
        }

        jedis.close();
    }
}
