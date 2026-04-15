package LLD.LRUCache;

import lombok.Data;

import java.util.*;


public class LRUCache {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int cap = 7;//sc.nextInt();
        LRUCacheImpl<Object> lru = new LRUCacheImpl(cap);
        int q = 6;// sc.nextInt();
        String[] s = new String[]{
                "SET key1 value1",
                "SET key2 value2",
                "GET key1",
                "DELETE key2",
                "get key2",
                "set key3 value3 2"
        };
        while (q-- > 0) {
            String[] params = s[s.length - 1 - q].split(" ");
            String key = params[1];
            switch (params[0].toUpperCase()) {
                case "GET":
                    Object val = lru.get(key);
                    System.out.println(val == null ? "null" : val);
                    break;
                case "SET":
                    String value = params[2];
                    int ttl = (int) 1e9;
                    if (3 < params.length) {
                        ttl = Integer.parseInt(params[3]);
                    }
                    Node<Object> node = new Node<>(key, value, ttl);
                    lru.set(key, node);
                    break;
                case "DELETE":
                    lru.delete(key);
                    break;
            }
        }
        lru.printStats();
        sc.close();
    }
}

class LRUCacheImpl<T> {
    private int noOfHits, noOfMisses, noOfOp, noOfEvictions, capacity, size;
    private DLL<T> dll;
    private Map<String, Node<T>> nodeRef;

    public LRUCacheImpl(int capacity) {
        this.capacity = capacity;
        dll = new DLL<>();
        nodeRef = new HashMap<>();
    }

    public boolean isExpired(Node<T> node) {
        return node.getExpiry() < noOfOp;
    }

    public T get(String key) {
        noOfOp++;
        Node<T> node = nodeRef.getOrDefault(key, null);
        if (node == null || isExpired(node)) {
            noOfMisses++;
            if (node != null) {
                noOfOp--;
                delete(key);
            }
            return null;
        }
        noOfHits++;
        dll.remove(node);
        dll.addAtHead(node);
        return node.getValue();
    }

    public void set(String key, Node<T> node) {
        noOfOp++;
        node.setExpiry(noOfOp + node.getTTL());
        Node<T> existedNode = nodeRef.getOrDefault(key, null);
        if (existedNode == null) {
            if (size == capacity) {
                size--;
                noOfEvictions++;
                dll.removeLast();
                nodeRef.remove(dll.removeLast());
            }
            dll.addAtHead(node);
            nodeRef.put(key, node);
        } else {
            existedNode.update(node);
            dll.remove(existedNode);
            dll.addAtHead(existedNode);
        }
        size++;
    }

    public void delete(String key) {
        noOfOp++;
        Node<T> node = nodeRef.getOrDefault(key, null);
        if (node != null) {
            nodeRef.remove(key);
            dll.remove(node);
            size--;
        }
    }

    public void printStats() {
        System.out.println("Caches Hits: " + noOfHits);
        System.out.println("Caches Misses: " + noOfMisses);
        System.out.println("Caches Evictions: " + noOfEvictions);
    }
}


class DLL<T> {
    public static final String HEAD_KEY = "HEAD_KEY", TAIL_KEY = "TAIL_KEY";
    Node<T> head, tail;

    public DLL() {
        this.head = new Node<>(HEAD_KEY, null, (int) 1e9);
        this.tail = new Node<>(TAIL_KEY, null, (int) 1e9);
        head.setNext(tail);
        tail.setPrev(head);
    }

    void addAtHead(Node<T> node) {
        Node<T> nxt = head.getNext();
        head.setNext(node);
        node.setPrev(head);
        nxt.setPrev(node);
        node.setNext(nxt);
    }

    void remove(Node<T> node) {
        Node<T> nxt = node.getNext(), prev = node.getPrev();
        prev.setNext(nxt);
        nxt.setPrev(prev);
    }

    Node<T> removeLast() {
        Node<T> node = tail.getPrev();
        remove(node);
        return node;
    }
}


@Data
class Node<T> {
    private T value;
    private final String key;
    private final int TTL;
    private int expiry;
    private Node<T> next, prev;

    public Node(String key, T value, int TTL) {
        this.value = value;
        this.key = key;
        this.TTL = TTL;
    }

    public void update(Node<T> updates) {
        this.value = updates.getValue();
//        List<Person> ls = new ArrayList<>(), ls2 = new ArrayList<>();
//        Calendar c = Calendar.getInstance();
//        c.add(Calendar.DATE, -1);
//        long yesterday = c.getTimeInMillis();
//        List<Person> list = ls.stream().filter(person -> person.age == 25 && person.lastLogin.getTime() >= yesterday
//        ).toList();

//        select * from person
//            where name in (select distinct name from person where lastlogin >= '')
//        and las;
//        group by name having max(lastlogin) >'1';
    }

//    class Person implements Comparable {
//        String name;
//        int age;
//        Date lastLogin;
//
//        @Override
//        public int compareTo(Object o) {
//            Person p1 = (Person) o;
//            if (p1.name.equals(this.name)) return 0;
//            return p1.name.compareTo(this.name);
//        }
//    }
//    Users with age 25, and logged in last 1 day.. sorted by loginTime
}



























