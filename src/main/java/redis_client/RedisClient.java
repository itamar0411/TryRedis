package redis_client;

import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class RedisClient {
    public static void main(String[] args) throws Exception {

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

        // Find the type of the value of a given key and print it
        System.out.println("Value type for key=itamar: " + jedis.type("itamar"));

        // Add an entry - "itamar2" and define when it expires, verify that it gets deleted
        jedis.set("itamar2", "value for itamar2");
        jedis.expire("itamar2", 2);
        System.out.println("Value for key=itamar2 before it expires: " + jedis.get("itamar2"));
        Thread.sleep(3000);
        System.out.println("Value for key=itamar2 after it expires: " + jedis.get("itamar2"));

        // Use a method that sets a new value to a key and returns the old value
        String oldValue = jedis.getSet("amitai", "test5");
        System.out.println("Old value for amitai was: " + oldValue);
        System.out.println("New value for amitai is: " + jedis.get("amitai"));

        // Get the string values for a list of keys
        jedis.set("itamar3", "test6");
        System.out.println("Value for all keys: " + jedis.mget("itamar", "itamar2", "amitai","itamar3"));

        // Delete 3 keys using the del function. you can also usethe unlink function
        // to delete the keys. Using unlink the actual memory reclaiming in a different thread,
        // so it is not blocking, while del is
        jedis.del("itamar", "amitai", "itamar2");
        keys = jedis.keys("*");
        System.out.println("Keys after deletion: " + keys);

        jedis.set("counter", "1");
        jedis.decr("counter");
        System.out.println("Counter after decr: " + jedis.get("counter"));
        jedis.incr("counter");
        System.out.println("Counter after incrBy 2: " + jedis.incrBy("counter", 2));

        //value is a map, hset returns the number of values in the map
        //use hgetAll to get the map by the key
        Map<String, String> map = new HashMap<>();
        map.put("id1", "itamar sasson");
        map.put("id2", "amitai sasson");
        map.put("id3", "david sasson");
        map.put("id4", "hamutal sasson");
        Long num = jedis.hset("familyId", map);
        System.out.print(jedis.hgetAll("familyId"));

        jedis.close();
    }
}
