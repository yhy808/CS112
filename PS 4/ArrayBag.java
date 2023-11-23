/* 
 * ArrayBag.java
 *
 * A blueprint class for objects that represent a bag of other objects --
 * i.e., a collection of items in which the items do not have a position.
 * This implementation uses an array to store to objects in the bag.
 *
 * Computer Science 112
 *
 * modified by: <Hongyi Yu>, <yuhy@bu.edu>
 */

import java.util.*;

import org.omg.CORBA.ObjectHolder;

public class ArrayBag {
    /** 
     * The array used to store the items in the bag.
     */
    private Object[] items;
    
    /** 
     * The number of items in the bag.
     */
    private int numItems;
    
    public static final int DEFAULT_MAX_SIZE = 50;
    
    /**
     * Constructor with no parameters - creates a new, empty ArrayBag with 
     * the default maximum size.
     */
    public ArrayBag() {
        this.items = new Object[DEFAULT_MAX_SIZE];
        this.numItems = 0;
    }
    
    /** 
     * A constructor that creates a new, empty ArrayBag with the specified
     * maximum size.
     */
    public ArrayBag(int maxSize) {
        if (maxSize <= 0) {
            throw new IllegalArgumentException("maxSize must be > 0");
        }
        this.items = new Object[maxSize];
        this.numItems = 0;
    }
    
    /**
     * numItems - accessor method that returns the number of items 
     * in this ArrayBag.
     */
    public int numItems() {
        return this.numItems;
    }
    
    /** 
     * add - adds the specified item to this ArrayBag. Returns true if there 
     * is room to add it, and false otherwise.
     * Throws an IllegalArgumentException if the item is null.
     */
    public boolean add(Object item) {
        if (item == null) {
            throw new IllegalArgumentException("item must be non-null");
        } else if (this.numItems == this.items.length) {
            return false;    // no more room!
        } else {
            this.items[this.numItems] = item;
            this.numItems++;
            return true;
        }
    }
    
    /** 
     * remove - removes one occurrence of the specified item (if any)
     * from this ArrayBag.  Returns true on success and false if the
     * specified item (i.e., an object equal to item) is not in this ArrayBag.
     */
    public boolean remove(Object item) {
        for (int i = 0; i < this.numItems; i++) {
            if (this.items[i].equals(item)) {
                // Shift the remaining items left by one.
                for (int j = i; j < this.numItems - 1; j++) {
                    this.items[j] = this.items[j + 1];
                }
                this.items[this.numItems - 1] = null;
                
                this.numItems--;
                return true;
            }
        }
        
        return false;  // item not found
    }
    
    /**
     * contains - returns true if the specified item is in the Bag, and
     * false otherwise.
     */
    public boolean contains(Object item) {
        for (int i = 0; i < this.numItems; i++) {
            if (this.items[i].equals(item)) {
                return true;
            }
        }
        
        return false;
    }
    
    /**
     * grab - returns a reference to a randomly chosen item in this ArrayBag.
     */
    public Object grab() {
        if (this.numItems == 0) {
            throw new IllegalStateException("the bag is empty");
        }
        
        int whichOne = (int)(Math.random() * this.numItems);
        return this.items[whichOne];
    }
    
    /**
     * toArray - return an array containing the current contents of the bag
     */
    public Object[] toArray() {
        Object[] copy = new Object[this.numItems];
        
        for (int i = 0; i < this.numItems; i++) {
            copy[i] = this.items[i];
        }
        
        return copy;
    }
    
    /**
     * toString - converts this ArrayBag into a string that can be printed.
     * Overrides the version of this method inherited from the Object class.
     */
    public String toString() {
        String str = "{";
        
        for (int i = 0; i < this.numItems; i++) {
            str = str + this.items[i];
            if (i != this.numItems - 1) {
                str += ", ";
            }
        }
        
        str = str + "}";
        return str;
    }

    /** 
     * roomLeft - return the number of additional items that the called ArrayBag has room to store. 
     */
    public int roomLeft() {
        return this.items.length - this.numItems;

    }

    /**
     * isFull - return true if the called ArrayBag is full, and false otherwise. 
     */
    public boolean isFull() {
        boolean full = true;
        for (int i = 0; i < this.items.length; i++) {
            if (this.items[i] == null) {
                full = false;
            }
        }
        return full;
    }

    /**
     * increase the maximum capacity of the called ArrayBag by the specified amount.
     */
    public void increaseCapacity(int increment) {
        Object[] b = new Object[this.items.length + increment];
        for (int i = 0; i < this.numItems; i++) {
            b[i] = this.items[i];
        }
        this.items = b;
    }

    /**
     * remove from the called ArrayBag all occurrences of the items found in the parameter other.
     */
    public boolean removeItems(ArrayBag other) {
        boolean remove = false;
        for (int i = 0; i < this.numItems; i++) {
            while (other.contains(this.items[i])) {
                remove = true;
                this.remove(this.items[i]);
            }
        }
        return remove;
    }

    /**
     * create and return an ArrayBag containing one occurrence of any item that is found in both the called object and the parameter other.
     */
    public ArrayBag intersectionWith(ArrayBag other){
        if (other == null) {
            throw new IllegalArgumentException();
        }
        int num;
        if (this.numItems == 0 || other.numItems == 0) {
            num = 1;
        } else if (other.numItems < this.numItems) {
            num = other.numItems;
        } else {
            num = this.numItems;
        }
        ArrayBag another = new ArrayBag(num);
        for (int i = 0; i < other.numItems; i++) {
            if (this.contains(other.items[i])) {
                if (!another.contains(other.items[i])) {
                    another.add(other.items[i]);
                }
            }
        }
        return another;
    }

    /* Test the ArrayBag implementation. */
    public static void main(String[] args) {
        ArrayBag b1 = new ArrayBag(10);
        String[] letters1 = {"a", "a", "b", "d", "f", "f", "f", "g"};
        for (String ltr: letters1) {
            b1.add(ltr);
        }
        System.out.println(b1);

        ArrayBag b2 = new ArrayBag(8);
        String[] letters2 = {"a", "b", "c", "d", "d", "e", "f"};
        for (String ltr: letters2) {
            b2.add(ltr);
        }
        System.out.println(b2);

        ArrayBag b3 = b1.intersectionWith(b2);
        System.out.println(b3);
        System.out.println(b3.numItems());
        System.out.println(b3.roomLeft());
        System.out.println(b1);   // make sure original bags are unchanged
        System.out.println(b2);
    }
}
