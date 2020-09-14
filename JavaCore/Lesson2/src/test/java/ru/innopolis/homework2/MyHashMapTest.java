package ru.innopolis.homework2;

import static org.junit.jupiter.api.Assertions.*;

class MyHashMapTest {

    private MyHashMap inicialization() {
        final MyHashMap myHashMap = new MyHashMap();

        myHashMap.put("Кошка1", 1);
        myHashMap.put(null, 2);
        myHashMap.put("Кошка3", 3);
        return myHashMap;
    }

    @org.junit.jupiter.api.Test
    void put() {
        final MyHashMap myHashMap = inicialization();

        assertTrue(myHashMap.containsKey("Кошка1") && myHashMap.get("Кошка1") != null);
    }

    @org.junit.jupiter.api.Test
    void putReplacement() {
        final MyHashMap myHashMap = inicialization();

        myHashMap.put("Кошка3", "33");
        assertTrue(myHashMap.containsKey("Кошка3") && myHashMap.get("Кошка3").equals("33"));
    }

    @org.junit.jupiter.api.Test
    void putNull() {
        final MyHashMap myHashMap = inicialization();

        assertTrue(myHashMap.containsKey(null) && myHashMap.get(null).equals(2));
    }

    @org.junit.jupiter.api.Test
    void get() {
        final MyHashMap myHashMap = inicialization();

        assertEquals(myHashMap.get("Кошка3"), 3);
    }

    @org.junit.jupiter.api.Test
    void getNull() {
        final MyHashMap myHashMap = inicialization();

        assertEquals(myHashMap.get(null), 2);
    }

    @org.junit.jupiter.api.Test
    void removeReturnValue() {
        final MyHashMap myHashMap = inicialization();

        assertEquals(myHashMap.remove("Кошка3"), 3);
    }

    @org.junit.jupiter.api.Test
    void remove() {
        final MyHashMap myHashMap = inicialization();

        myHashMap.remove("Кошка3");
        assertFalse(myHashMap.containsKey("Кошка3"));
    }

    @org.junit.jupiter.api.Test
    void removeNull() {
        final MyHashMap myHashMap = inicialization();

        myHashMap.remove(null);
        assertFalse(myHashMap.containsKey(null));
    }

    @org.junit.jupiter.api.Test
    void removeNullReturnValue() {
        final MyHashMap myHashMap = inicialization();

        assertEquals(myHashMap.remove(null), 2);
    }

    @org.junit.jupiter.api.Test
    void containsKeyNullTrue() {
        final MyHashMap myHashMap = inicialization();

        assertTrue(myHashMap.containsKey(null));
    }

    @org.junit.jupiter.api.Test
    void containsKeyFalse() {
        final MyHashMap myHashMap = inicialization();

        assertFalse(myHashMap.containsKey("1"));
    }

    @org.junit.jupiter.api.Test
    void containsKeyTrue() {
        final MyHashMap myHashMap = inicialization();

        assertTrue(myHashMap.containsKey("Кошка3"));
    }

    @org.junit.jupiter.api.Test
    void size() {
        final MyHashMap myHashMap = inicialization();

        assertEquals(myHashMap.size(), 3);
    }
}