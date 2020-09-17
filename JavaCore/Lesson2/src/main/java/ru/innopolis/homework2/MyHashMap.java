package ru.innopolis.homework2;

import java.util.Objects;

public class MyHashMap {
    private int size = 16;
    private Node[] nodes = new Node[size];
    private int countFullBucked = 0; // количество занятых бакетов
    private final double PROCENT_FULL = 0.75; // коэфициент заполнения мапы

    public static class Node {
        private Node next;
        private final Object key;
        private Object value;

        public Node(Object key, Object value) {
            this.key = key;
            this.value = value;
        }
    }

    /**
     * Добавляет пару ключ-значение в HashMap или заментяет, если такой ключ уже есть
     *
     * @param key   ключ
     * @param value значение
     */
    public void put(final Object key, final Object value) {
        final int bucket = getBucked(key);
        Node node = nodes[bucket];

        if (node == null) {
            nodes[bucket] = new Node(key, value);
            countFullBucked++;
        } else {
            while (true) {
                if (Objects.equals(node.key, key)) {
                    node.value = value;
                    break;
                }
                if (node.next == null) {
                    break;
                }
                node = node.next;
            }
            node.next = new Node(key, value);
        }
        if (countFullBucked >= size * PROCENT_FULL) {
            resize();
        }
    }

    /**
     * Получает значение по ключу
     *
     * @param key ключ
     * @return значение
     */
    public Object get(final Object key) {
        final int bucket = getBucked(key);
        Node node = nodes[bucket];

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
        return Math.abs(hash % size);
    }

    /**
     * Удаляет элемент
     *
     * @param key ключ удалемого элемента
     */
    public void remove(final Object key) {
        final int bucket = getBucked(key);
        Node node = nodes[bucket];

        try {
            if (Objects.equals(node.key, key)) {
                if (node.next == null) {
                    nodes[bucket] = null;
                    countFullBucked--;
                } else {
                    nodes[bucket] = node.next;
                }
            } else {
                while (node.next != null) {
                    if (Objects.equals(node.next.key, key)) {
                        node.next = node.next.next;
                        break;
                    }
                    node = node.next;
                }
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
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
     * Считает количество элементов в HashMap
     *
     * @return количество элементов
     */
    public int size() {
        int count = 0;
        Node node;
        for (Node node_i : nodes) {
            if (node_i != null) {
                node = node_i;
                count++;
                while (node.next != null) {
                    count++;
                    node = node.next;
                }
            }
        }
        return count;
    }

    private void resize() {
        size *= 2;
        countFullBucked = 0;
        Node[] newNodes = new Node[size];
        Node[] oldNodes = nodes;
        nodes = newNodes;
        Node node;

        for (Node node_i : oldNodes) {
            if (node_i != null) {
                node = node_i;
                while (node != null) {
                    this.put(node.key, node.value);
                    node = node.next;
                }
            }
        }
    }
}
