package study.datastructure;

public class HashMap<K, V> {

    private static final float LOAD_FACTOR = 0.75F;
    private Node<K, V>[] table;
    private int size = 0;

    public HashMap() {
        this.table = new Node[16];
    }

    public HashMap(int initialCapacity) {
        this.table = new Node[initialCapacity];
    }

    public void put(K key, V value) {
        if (size == table.length * LOAD_FACTOR) {
            resize();
        }

        int index = getIndex(key, table);
        Node<K, V> current = table[index];
        while (current != null) {
            if (current.getKey().equals(key)) {
                current.setValue(value);
                return;
            }
            current = current.getNext();
        }
        Node<K, V> node = new Node<>(key, value);
        node.setNext(table[index]);
        table[index] = node;

        size++;
    }

    public V get(K key) {
        int index = getIndex(key, table);
        Node<K, V> current = table[index];
        while (current != null &&
                !current.getKey().equals(key)) {
            current = current.getNext();
        }
        return current == null ? null : current.getValue();
    }

    public V remove(K key) {
        int index = getIndex(key, table);
        Node<K, V> current = table[index];
        Node<K, V> before = null;
        while (current != null
                && !current.getKey().equals(key)) {
            before = current;
            current = current.getNext();
        }
        if (current == null || !current.getKey().equals(key)) {
            return null;
        }
        if (before != null) {
            before.setNext(current.getNext());
        } else {
            table[index] = current.getNext();
        }
        size--;
        return current.getValue();
    }

    public boolean containsKey(K key) {
        int index = getIndex(key, table);
        Node<K, V> current = table[index];
        while (current != null) {
            if (current.getKey().equals(key)) {
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

    public Integer size() {
        return size;
    }

    public void resize() {
        Node<K, V>[] oldTable = table;
        Node<K, V>[] newTable = new Node[oldTable.length * 2];
        for (Node<K, V> node : oldTable) {
            Node<K, V> current = node;
            while (current != null) {
                int index = getIndex(current.getKey(), newTable);
                Node<K, V> nextNode = current.getNext();
                current.setNext(newTable[index]);
                newTable[index] = current;
                current = nextNode;
            }
        }
        table = newTable;
    }

    private int getIndex(K key, Node<K, V>[] table) {
        return Math.abs(key.hashCode()) % table.length;
    }

    private static class Node<K, V> {
        private final K key;
        private V value;
        private Node<K, V> next;

        public Node(K key, V value) {
            if (key == null) {
                throw new IllegalArgumentException("Key must not be null");
            }

            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public Node<K, V> getNext() {
            return next;
        }

        public void setNext(Node<K, V> next) {
            this.next = next;
        }

        public void setValue(V value) {
            this.value = value;
        }
    }

    public static void main(String[] args) {
    }
}

