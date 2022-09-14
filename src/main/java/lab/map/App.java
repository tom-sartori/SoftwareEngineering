package lab.map;

import java.util.HashMap;
import java.util.Map;

public class App {

    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("Fred", "Wilma");
        map.put("Barney", "Betty");
        map.put("Tristan", "Isolde");
        map.put("Roméo", "Juliette");

        System.out.println(map);


        Map<String, String> map1 = new HashMap<>();
        map.forEach((k, v) -> map1.put(v, k));
        System.out.println(map1);
    }
}
