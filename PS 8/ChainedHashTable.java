/*
 * ChainedHashTable.java
 *
 * Computer Science 112, Boston University
 * 
 * Modifications and additions by:
 *     name: Hongyi Yu
 *     email: yuhy@bu.edu
 */

import java.util.*;     // to allow for the use of Arrays.toString() in testing

/*
 * A class that implements a hash table using separate chaining.
 */
public class ChainedHashTable implements HashTable {
    /* 
     * Private inner class for a node in a linked list
     * for a given position of the hash table
     */
    private class Node {
        private Object key;
        private LLQueue<Object> values;
        private Node next;
        
        private Node(Object key, Object value) {
            this.key = key;
            values = new LLQueue<Object>();
            values.insert(value);
            next = null;
        }
    }
    
    private Node[] table;      // the hash table itself
    private int numKeys;       // the total number of keys in the table
        
    /* hash function */
    public int h1(Object key) {
        int h1 = key.hashCode() % table.length;
        if (h1 < 0) {
            h1 += table.length;
        }
        return h1;
    }
    
    /*** Add your constructor here ***/
    public ChainedHashTable(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("size must be positive");
        }
        table = new Node[size];
        this.numKeys = 0;
    }
    
    /*
     * insert - insert the specified (key, value) pair in the hash table.
     * Returns true if the pair can be added and false if there is overflow.
     */
    public boolean insert(Object key, Object value) {
        /** Replace the following line with your implementation. **/
        if (key == null) {
            throw new IllegalArgumentException("key must be non-null");
        }
        int i = h1(key); 
        Node newNode = new Node(key, value);
        if (table[i] == null) {
            table[i] = newNode;
            numKeys++;
        } else {
            Node trav = table[i];
            while (trav != null && !trav.key.equals(key)) {
                trav = trav.next;
            }
            if (trav == null) {
                Node next = table[i];
                table[i] = newNode;
                table[i].next = next;
                numKeys++;
            } else {
                trav.values.insert(value);
            }
        }
        return true;
    }
    
    /*
     * search - search for the specified key and return the
     * associated collection of values, or null if the key 
     * is not in the table
     */
    public Queue<Object> search(Object key) {
        /** Replace the following line with your implementation. **/
        if (key == null) {
            throw new IllegalArgumentException("key must be non-null");
        }
        int i = h1(key); 
        Node trav = table[i];
        while (!trav.key.equals(key)) {
            trav = trav.next;
            if (trav == null) {
                return null;
            }
        }
        return trav.values;
    }
    
    /* 
     * remove - remove from the table the entry for the specified key
     * and return the associated collection of values, or null if the key 
     * is not in the table
     */
    public Queue<Object> remove(Object key) {
        /** Replace the following line with your implementation. **/
        if (key == null) {
            throw new IllegalArgumentException("key must be non-null");
        }
        int i = h1(key); 
        Node trav = table[i];
        Node prev = table[i];
        if (trav == null) {
            return null;
        }
        if (trav.key.equals(key)) {
            table[i] = trav.next;
        }
        else {
            trav = trav.next;
            if (trav == null) {
                return null;
            }
        }
        while (!trav.key.equals(key)) {
            prev = trav;
            trav = trav.next;
            if (trav == null) {
                return null;
            }
        }
        Queue<Object> value = trav.values;
        prev.next = trav.next;
        trav = null;
        numKeys--;
        return value;
    }
    
    
    /*** Add the other required methods here ***/
    public int getNumKeys() {
        return numKeys;
    }

    public double load() {
        double value = (double)numKeys/table.length;
        return value;
    }

    public Object[] getAllKeys() {
        LLQueue<Object> keys = new LLQueue<Object>();
        for (int i = 0; i < table.length; i++) {
            Node trav = table[i];
            while (trav != null) {
                keys.insert(trav.key);
                trav = trav.next;
            }
        }
        Object[] allKeys = new Object[numKeys];
        for (int i = 0; i < numKeys; i++) {
            allKeys[i] = keys.remove();
        }
        return allKeys;
    }  

    public void resize(int newsize) {
        if (newsize < table.length) {
            throw new IllegalArgumentException();
        } else if (newsize == table.length) {
            return;
        } else {
            Node[] newTable = new Node[newsize];
            Object[] allKeys = this.getAllKeys();
            for (int i = 0; i < allKeys.length; i++) {
                Object key = allKeys[i];
                int value = key.hashCode() % newsize;;
                Node newNode = new Node(key, value);
                if (newTable[value] == null) {
                    newTable[value] = newNode;
                } else {
                    Node trav = newTable[i];
                while (trav != null && !trav.key.equals(key)) {
                    trav = trav.next;
                }
                if (trav == null) {
                    Node next = newTable[value];
                    newTable[value] = newNode;
                    newTable[value].next = next;
                } else {
                    trav.values.insert(value);
                    }   
                }
            }
            table = newTable;
        }
    }
    
    
    /*
     * toString - returns a string representation of this ChainedHashTable
     * object. *** You should NOT change this method. ***
     */
    public String toString() {
        String s = "[";
        
        for (int i = 0; i < table.length; i++) {
            if (table[i] == null) {
                s += "null";
            } else {
                String keys = "{";
                Node trav = table[i];
                while (trav != null) {
                    keys += trav.key;
                    if (trav.next != null) {
                        keys += "; ";
                    }
                    trav = trav.next;
                }
                keys += "}";
                s += keys;
            }
        
            if (i < table.length - 1) {
                s += ", ";
            }
        }       
        
        s += "]";
        return s;
    }

    public static void main(String[] args) {
        /** Add your unit tests here **/
        System.out.println("--- Testing method insert ---");
        System.out.println();

        System.out.println("(0) Testing on \"howdy, goodbye, apple\"");
        try {
            ChainedHashTable table = new ChainedHashTable(5);
            table.insert("howdy", 15);
            table.insert("goodbye", 10);
            System.out.println(table.insert("apple", 5));
            System.out.println(table);
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
        System.out.println(); 

        System.out.println("(1) Testing on \"apple, ant, world\"");
        try {
            ChainedHashTable table = new ChainedHashTable(10);
            table.insert("ant", 15);
            table.insert("apple", 7);
            System.out.println(table.insert("world", 5));
            System.out.println(table);
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
        System.out.println(); 


        System.out.println("--- Testing method search ---");
        System.out.println();

        System.out.println("(0) Testing on \"howdy, goodbye, apple\"");
        try {
            ChainedHashTable table = new ChainedHashTable(5);
            table.insert("howdy", 15);
            table.insert("goodbye", 10);
            table.insert("apple", 5);
            System.out.println(table.search("apple"));
            System.out.println(table);
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
        System.out.println(); 

        System.out.println("(1) Testing on \"howdy, goodbye, apple\"");
        try {
            ChainedHashTable table = new ChainedHashTable(5);
            table.insert("howdy", 15);
            table.insert("goodbye", 10);
            table.insert("apple", 5);
            System.out.println(table.search("howdy"));
            System.out.println(table);
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
        System.out.println(); 


        System.out.println("--- Testing method remove ---");
        System.out.println();

        System.out.println("(0) Testing on \"howdy, goodbye, apple\"");
        try {
            ChainedHashTable table = new ChainedHashTable(5);
            table.insert("howdy", 15);
            table.insert("goodbye", 10);
            table.insert("apple", 5);
            System.out.println(table.remove("apple"));
            System.out.println(table);
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
        System.out.println(); 

        System.out.println("(1) Testing on \"howdy, goodbye, apple\"");
        try {
            ChainedHashTable table = new ChainedHashTable(5);
            table.insert("howdy", 15);
            table.insert("goodbye", 10);
            table.insert("apple", 5);
            System.out.println(table.remove("goodbye"));
            System.out.println(table);
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
        System.out.println(); 


        System.out.println("--- Testing method getNumKeys ---");
        System.out.println();

        System.out.println("(0) Testing on \"howdy, goodbye, apple\"");
        try {
            ChainedHashTable table = new ChainedHashTable(5);
            table.insert("howdy", 15);
            table.insert("goodbye", 10);
            table.insert("apple", 5);
            System.out.println(table.getNumKeys());
            table.insert("howdy", 25);     
            System.out.println(table.getNumKeys());
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
        System.out.println(); 

        System.out.println("(1) Testing on \"howdy, goodbye, apple\"");
        try {
            ChainedHashTable table = new ChainedHashTable(5);
            table.insert("goodbye", 10);
            table.insert("apple", 5);
            System.out.println(table.getNumKeys());
            table.insert("apple", 25); 
            table.insert("apple", 30); 
            table.insert("goodbye", 25); 
            System.out.println(table.getNumKeys());
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
        System.out.println(); 


        System.out.println("--- Testing method load ---");
        System.out.println();

        System.out.println("(0) Testing on \"howdy, goodbye, apple\"");
        try {
            ChainedHashTable table = new ChainedHashTable(5);
            table.insert("howdy", 15);
            table.insert("goodbye", 10);
            table.insert("apple", 5);
            System.out.println(table.load());
            table.insert("pear", 6);
            System.out.println(table.load());
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
        System.out.println(); 

        System.out.println("(1) Testing on \"howdy, goodbye, apple\"");
        try {
            ChainedHashTable table = new ChainedHashTable(5);
            table.insert("howdy", 15);
            table.insert("goodbye", 10);
            System.out.println(table.load());
            table.insert("apple", 5);
            table.insert("banana", 30);
            table.insert("pear", 6);
            System.out.println(table.load());
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
        System.out.println(); 


        System.out.println("--- Testing method getAllKeys ---");
        System.out.println();

        System.out.println("(0) Testing on \"howdy, goodbye, apple\"");
        try {
            ChainedHashTable table = new ChainedHashTable(5);
            table.insert("howdy", 15);
            table.insert("goodbye", 10);
            table.insert("apple", 5);
            table.insert("howdy", 25);    // insert a duplicate
            Object[] keys = table.getAllKeys();
            System.out.println(Arrays.toString(keys));
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
        System.out.println(); 

        System.out.println("(1) Testing on \"how are you today\"");
        try {
            ChainedHashTable table = new ChainedHashTable(5);
            table.insert("how", 12);
            table.insert("are", 10);
            table.insert("you", 25);
            table.insert("today", 15);
            Object[] keys = table.getAllKeys();
            System.out.println(Arrays.toString(keys));
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
        System.out.println(); 


        System.out.println("--- Testing method resize ---");
        System.out.println();

        System.out.println("(0) Testing on \"howdy, goodbye, apple\"");
        try {
            ChainedHashTable table = new ChainedHashTable(5);
            table.insert("howdy", 15);
            table.insert("goodbye", 10);
            table.insert("apple", 5);
            System.out.println(table);
            table.resize(7);
            System.out.println(table);
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
        System.out.println(); 

        System.out.println("(1) Testing on \"howdy, goodbye, apple\"");
        try {
            ChainedHashTable table = new ChainedHashTable(5);
            table.insert("howdy", 15);
            table.insert("goodbye", 10);
            table.insert("apple", 5);
            System.out.println(table);
            table.resize(10);
            System.out.println(table);
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
        System.out.println(); 




    }
}
