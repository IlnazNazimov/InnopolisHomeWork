package ru.innopolis.homework4;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class MyHashMapTest {

    private MyHashMap<String, Integer> inicialMyHashMap() {
        final MyHashMap<String, Integer> myHashMap = new MyHashMap<>();

        myHashMap.put("Кошка1", 1);
        myHashMap.put(null, 2);
        myHashMap.put("Кошка3", 3);
        return myHashMap;
    }

    private HashMap<String, Integer> inicialOriginHashMap() {
        final HashMap<String, Integer> origHashMap = new HashMap<>();

        origHashMap.put("Кошка1", 1);
        origHashMap.put(null, 2);
        origHashMap.put("Кошка3", 3);
        return origHashMap;
    }

    @Test
    void put() {
        final MyHashMap<String, Integer> myHashMap = inicialMyHashMap();
        final HashMap<String, Integer> origHashMap = inicialOriginHashMap();

        assertEquals(origHashMap, myHashMap);
    }

    @Test
    void putReplacement() {
        final MyHashMap<String, Integer> myHashMap = inicialMyHashMap();
        final HashMap<String, Integer> origHashMap = inicialOriginHashMap();

        myHashMap.put("Кошка3", 33);
        origHashMap.put("Кошка3", 33);

        assertEquals(origHashMap, myHashMap);
    }


    @Test
    void get() {
        final MyHashMap<String, Integer> myHashMap = inicialMyHashMap();
        final HashMap<String, Integer> origHashMap = inicialOriginHashMap();

        assertEquals(myHashMap.get("Кошка3"), origHashMap.get("Кошка3"));
    }

    @Test
    void getNull() {
        final MyHashMap<String, Integer> myHashMap = inicialMyHashMap();
        final HashMap<String, Integer> origHashMap = inicialOriginHashMap();

        assertEquals(myHashMap.get(null), origHashMap.get(null));
    }

    @Test
    void remove() {
        final MyHashMap<String, Integer> myHashMap = inicialMyHashMap();
        final HashMap<String, Integer> origHashMap = inicialOriginHashMap();

        assertEquals(myHashMap.remove("Кошка1"), origHashMap.remove("Кошка1"));
    }

    @Test
    void removeNull() {
        final MyHashMap<String, Integer> myHashMap = inicialMyHashMap();
        final HashMap<String, Integer> origHashMap = inicialOriginHashMap();

        assertEquals(myHashMap.remove(null), origHashMap.remove(null));
    }

    @Test
    void putAll() {
        final MyHashMap<String, Integer> myHashMap = inicialMyHashMap();
        final HashMap<String, Integer> origHashMap = inicialOriginHashMap();

        final Map<String, Integer> putHashMap = new HashMap<>();
        putHashMap.put("Кошка5", 5);
        putHashMap.put("Кошка4", 4);

        myHashMap.putAll(putHashMap);
        origHashMap.putAll(putHashMap);

        assertEquals(myHashMap.containsKey("Кошка5") && myHashMap.containsValue(5),
                origHashMap.containsKey("Кошка5") && origHashMap.containsValue(5));
    }

    @Test
    void clear() {
        final MyHashMap<String, Integer> myHashMap = inicialMyHashMap();
        final HashMap<String, Integer> origHashMap = inicialOriginHashMap();
        myHashMap.clear();
        origHashMap.clear();

        assertEquals(origHashMap, myHashMap);
    }

    @Test
    void containsKeyNullTrue() {
        final MyHashMap<String, Integer> myHashMap = inicialMyHashMap();
        final HashMap<String, Integer> origHashMap = inicialOriginHashMap();

        assertEquals(myHashMap.containsKey(null), origHashMap.containsKey(null));
    }

    @Test
    void containsKeyFalse() {
        final MyHashMap<String, Integer> myHashMap = inicialMyHashMap();
        final HashMap<String, Integer> origHashMap = inicialOriginHashMap();

        assertEquals(myHashMap.containsKey("1"), origHashMap.containsKey("1"));
    }

    @Test
    void containsKeyTrue() {
        final MyHashMap<String, Integer> myHashMap = inicialMyHashMap();
        final HashMap<String, Integer> origHashMap = inicialOriginHashMap();

        assertEquals(myHashMap.containsKey("Кошка3"), origHashMap.containsKey("Кошка3"));
    }

    @Test
    void containsValue() {
        final MyHashMap<String, Integer> myHashMap = inicialMyHashMap();
        final HashMap<String, Integer> origHashMap = inicialOriginHashMap();

        assertEquals(myHashMap.containsValue(3), origHashMap.containsValue(3));
    }

    @Test
    void size() {
        final MyHashMap<String, Integer> myHashMap = inicialMyHashMap();
        final HashMap<String, Integer> origHashMap = inicialOriginHashMap();

        assertEquals(myHashMap.size(), origHashMap.size());
    }

    @Test
    void isEmpty() {
        final MyHashMap<String, Integer> myHashMap = inicialMyHashMap();
        final HashMap<String, Integer> origHashMap = inicialOriginHashMap();

        assertEquals(myHashMap.isEmpty(), origHashMap.isEmpty());
    }
}