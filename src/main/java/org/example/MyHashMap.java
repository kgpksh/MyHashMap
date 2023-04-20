package org.example;

public class MyHashMap<K, V> {
    private V[] data;
    private int mapSize = 16;
    private int size = 0;

    public MyHashMap() {
        data = (V[]) new Object[mapSize];
    }
    public MyHashMap(int mapSize) {
        this.mapSize = mapSize;
        data = (V[]) new Object[this.mapSize];
    }

    private int keyToIndex(K key) {
        return key.hashCode() % mapSize;
    }

    public V put(K key, V value) {
        if (mapSize == size) {
            mapSize *= 2;
            V[] tmp = (V[]) new Object[mapSize];
            for (int i = 0; i < size; i++) {
                tmp[i] = data[i];
            }

            data = tmp;
            tmp = null;
        }

        int index = keyToIndex(key);

        V result = data[index];

        data[index] = value;

        if (result == null && data[index] != null) {
            size++;
        }
        return result;
    }

    public int size() {
        return size;
    }

    public V get(K key) {
        int index = keyToIndex(key);

        return data[index];
    }

    public V remove(K key) {
        int index = keyToIndex(key);

        V result = data[index];
        data[index] = null;

        if (result != null && data[index] == null) {
            size--;
        }

        return result;
    }

    public boolean containsKey(K key) {
        int index = keyToIndex(key);

        if (data[index] == null) {
            return false;
        }

        return true;
    }

    public boolean containsValue(V value) {
        for (V v : data) {
            if (v!= null && v.equals(value)) {
                return true;
            }
        }

        return false;
    }

    public void clear() {
        for (int index = 0; index < mapSize; index++) {
            data[index] = null;
        }

        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}
