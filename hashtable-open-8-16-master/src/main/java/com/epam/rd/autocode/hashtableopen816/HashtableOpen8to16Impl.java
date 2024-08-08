package com.epam.rd.autocode.hashtableopen816;

public class HashtableOpen8to16Impl implements HashtableOpen8to16 {

    private static final int INITIAL_CAPACITY = 8;
    private static final int MAX_CAPACITY = 16;
    private static final int MIN_CAPACITY = 2;
    private Entry[] table;
    private int size;
    private int capacity;

    public HashtableOpen8to16Impl() {
        this.capacity = INITIAL_CAPACITY;
        this.table = new Entry[capacity];
        this.size = 0;
    }

    private class Entry {
        int key;
        Object value;
        boolean isDeleted;

        Entry(int key, Object value) {
            this.key = key;
            this.value = value;
            this.isDeleted = false;
        }
    }

    @Override
    public void insert(int key, Object value) {
        if (size >= MAX_CAPACITY) {
            throw new IllegalStateException("Maximum capacity reached");
        }

        int index = findIndex(key, true);
        if (table[index] != null && table[index].key == key && !table[index].isDeleted) {
            table[index].value = value;
        } else {
            table[index] = new Entry(key, value);
            size++;
            if (size >= capacity / 2 && capacity < MAX_CAPACITY) {
                resize(Math.min(capacity * 2, MAX_CAPACITY));
            }
        }
    }

    @Override
    public Object search(int key) {
        int index = findIndex(key, false);
        if (table[index] != null && table[index].key == key && !table[index].isDeleted) {
            return table[index].value;
        }
        return null;
    }

    @Override
    public void remove(int key) {
        int index = findIndex(key, false);
        if (table[index] != null && table[index].key == key && !table[index].isDeleted) {
            table[index].isDeleted = true;
            size--;
            if (size > 0 && size <= capacity / 4 && capacity > MIN_CAPACITY) {
                resize(Math.max(capacity / 2, MIN_CAPACITY));
            }
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int[] keys() {
        int[] keys = new int[size];
        int idx = 0;
        for (Entry entry : table) {
            if (entry != null && !entry.isDeleted) {
                keys[idx++] = entry.key;
            }
        }
        return keys;
    }

    private int hash(int key) {
        return Integer.hashCode(key);
    }

    private int findIndex(int key, boolean forInsert) {
        int index = hash(key) % capacity;
        int startIndex = index;

        while (table[index] != null && (table[index].key != key || table[index].isDeleted)) {
            index = (index + 1) % capacity;
            if (index == startIndex) {
                throw new IllegalStateException("Hashtable full");
            }
        }

        return index;
    }

    private void resize(int newCapacity) {
        Entry[] oldTable = table;
        table = new Entry[newCapacity];
        capacity = newCapacity;
        size = 0;

        for (Entry entry : oldTable) {
            if (entry != null && !entry.isDeleted) {
                int index = findIndex(entry.key, true);
                table[index] = entry;
                size++;
            }
        }
    }

    public static HashtableOpen8to16 getInstance() {
        return new HashtableOpen8to16Impl();
    }
}
