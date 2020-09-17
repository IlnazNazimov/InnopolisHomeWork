package ru.innopolis.homework4;

import java.util.*;

public class MyHashMap<K, V> implements Map<K, V> {
    private int countBucket = 16;
    private Node<K, V>[] nodes = new Node[countBucket];
    private int countFullBucked = 0;  // количество занятых бакетов
    private final double PROCENT_FULL = 0.75; // коэфициент заполнения мапы
    private int size = 0;

    public static class Node<K, V> implements Entry<K, V> {
        private Node<K, V> next;
        private final K key;
        private V value;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public K getKey() {
            return this.key;
        }

        @Override
        public V getValue() {
            return this.value;
        }

        @Override
        public V setValue(V value) {
            return this.value = value;
        }
    }

    /**
     * Добавляет пару ключ-значение в HashMap или заментяет, если такой ключ уже есть
     *
     * @param key   ключ
     * @param value значение
     * @return Значение заменяемого элемента или null, если такого ключа нет
     */
    public V put(final K key, final V value) {
        final int bucket = getBucked(key);
        Node<K, V> node = nodes[bucket];

        if (node == null) {
            nodes[bucket] = new Node(key, value);
            countFullBucked++;
        } else {
            while (true) {
                if (Objects.equals(node.key, key)) {
                    V oldValue = node.value;
                    node.value = value;
                    return oldValue;
                }
                if (node.next == null) {
                    break;
                }
                node = node.next;
            }
            node.next = new Node(key, value);
        }
        size++;
        if (countFullBucked >= countBucket * PROCENT_FULL) {
            resize();
        }
        return null;
    }

    /**
     * Получает значение по ключу
     *
     * @param key ключ
     * @return значение
     */
    public V get(final Object key) {
        final int bucket = getBucked(key);
        Node<K, V> node = nodes[bucket];

        while (node != null) {
            if (Objects.equals(node.key, key)) {
                return node.value;
            }
            node = node.next;
        }

        return null;
    }

    private int getBucked(final Object key) {
        final int hash = Objects.hashCode(key);
        return Math.abs(hash % countBucket);
    }

    /**
     * Удаляет элемент
     *
     * @param key ключ удалемого элемента
     * @return значение удаляемого элемента
     */
    public V remove(final Object key) {
        final int bucket = getBucked(key);
        Node<K, V> node = nodes[bucket];

        try {
            if (Objects.equals(node.key, key)) {
                if (node.next == null) {
                    nodes[bucket] = null;
                    countFullBucked--;
                } else {
                    nodes[bucket] = node.next;
                }
                size--;
                return node.value;
            } else {
                while (node.next != null) {
                    if (Objects.equals(node.next.key, key)) {
                        Node<K, V> oldNode = node.next;
                        node.next = node.next.next;
                        size--;
                        return oldNode.value;
                    }
                    node = node.next;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Добавляет все элементы из Map в HashMap
     *
     * @param map Map из которой берем все элементы
     */
    @Override
    public void putAll(Map<? extends K, ? extends V> map) {
        for (Entry<? extends K, ? extends V> entry : map.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    /**
     * Удаляет все элементы
     */
    @Override
    public void clear() {
        size = 0;
        for (int i = 0; i < countBucket; i++) {
            nodes[i] = null;
        }
    }

    /**
     * Достает все ключи
     *
     * @return Set из ключей
     */
    @Override
    public Set<K> keySet() {
        Set<K> keySet = new HashSet<>();
        Node<K, V> node;
        for (int i = 0; i < countBucket; i++) {
            node = nodes[i];
            while (node != null) {
                keySet.add(node.key);
                node = node.next;
            }
        }
        return keySet;
    }

    /**
     * Достает все значения
     *
     * @return Колллекция значений
     */
    @Override
    public Collection<V> values() {
        List<V> listValues = new ArrayList<>();
        Node<K, V> node;
        for (int i = 0; i < countBucket; ++i) {
            node = nodes[i];
            while (node != null) {
                listValues.add(node.value);
                node = node.next;
            }
        }
        return listValues;
    }

    /**
     * Берет все Node из HashMap
     *
     * @return возвращает Set из всех Node
     */
    @Override
    public Set<Entry<K, V>> entrySet() {
        Set<Entry<K, V>> entrySet = new HashSet<>();
        Node<K, V> node;

        for (int i = 0; i < countBucket; i++) {
            node = nodes[i];
            while (node != null) {
                entrySet.add(node);
                node = node.next;
            }
        }
        return entrySet;
    }

    /**
     * Проверяет наличие ключа в HashMap
     *
     * @param key ключ
     * @return имеется ли ключ в HashMap(true/false)
     */
    public boolean containsKey(final Object key) {
        return get(key) != null;
    }

    /**
     * Проверяет наличие значение в HashMap
     *
     * @param value значение
     * @return имеется ли значение в HashMap(true/false)
     */
    @Override
    public boolean containsValue(Object value) {
        Node<K, V> node;
        for (int i = 0; i < countBucket; i++) {
            node = nodes[i];
            while (node != null) {
                if (Objects.equals(node.value, value)) {
                    return true;
                }
                node = node.next;
            }
        }
        return false;
    }

    /**
     * Количество элементов в HashMap
     *
     * @return количество элементов
     */
    public int size() {
        return size;
    }

    /**
     * Проверяет пустой HashMap или нет
     *
     * @return true если пустой, иначе false
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void resize() {
        countBucket *= 2;
        countFullBucked = 0;
        Node<K, V>[] newNodes = new Node[countBucket];
        Node<K, V>[] oldNodes = nodes;
        nodes = newNodes;
        Node<K, V> node;

        for (Node<K, V> node_i : oldNodes) {
            if (node_i != null) {
                node = node_i;
                while (true) {
                    this.put(node.key, node.value);
                    if (node.next == null) {
                        break;
                    }
                    node = node.next;
                }
            }
        }
    }
}
