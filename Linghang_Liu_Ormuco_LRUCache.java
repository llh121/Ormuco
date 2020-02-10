//Linghang Liu
import java.util.HashMap;
import static org.junit.Assert.*;

public class LRUCache {

    public static void main(String[] args) {
        //Test Cache: capacity = 2
        LRUCache myCache = new LRUCache(2);
        assertTrue(myCache.get(6) == -1);
        //node 6 becomes the head
        myCache.set(6,1);
        //value of node 6 changes
        myCache.set(6,2);
        assertTrue(myCache.get(6)== 2);
        //node 7 becomes the head
        myCache.set(7, 2);
        //value of node 7 changes
        myCache.set(7, 3);
        assertTrue(myCache.get(7) == 3);
        //After accessing node 6, node 6 becomes the head and node 7 becomes LRU one.
        assertTrue(myCache.get(6) == 2);
        //node 8 becomes the head and node 7 is deleted since Cache is full
        myCache.set(8, 1);
        //Thus we cannot find node 7 in the Cache
        assertTrue(myCache.get(7) == -1);
    }
    class Node {
        int key;
        int value;
        Node prev;
        Node next;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
    private final int capacity;
    private HashMap<Integer, Node> map = new HashMap<Integer, Node>();
    private Node head=null;
    private Node tail=null;

    public LRUCache(int capacity) {
        this.capacity = capacity;
    }

    //Access a piece of data in the Cache
    //If it exists, relocate it to the head of the linked list.
    //Else return -1 in this program or search it in the disk in the real situation.
    public int get(int key) {
        if(map.containsKey(key)){
            Node n = map.get(key);
            remove(n);
            setHead(n);
            return n.value;
        }

        return -1;
    }
    //Remove the LRU node in the linked list
    public void remove(Node n){

        if(n.prev!=null){
            n.prev.next = n.next;
        }else{
            head = n.next;
        }

        if(n.next!=null){
            n.next.prev = n.prev;
        }else{
            tail = n.prev;
        }
        //Garbage collection, clean up expired node (not referenced)
        System.gc();

    }
    //Set the input node to be the head of the linked list
    public void setHead(Node n){
        n.next = head;
        n.prev = null;
        //check if the head is null
        if(head!=null)
            head.prev = n;
        //set the node to head
        head = n;
        //check if the end is null
        if(tail ==null)
            tail = head;
    }
    //Add a piece of data to the Cache
    //First check if it is in the Cache.
    //If it is, change the value of it to be the desired value and set it to be the head.
    //If it is not, check the capacity of the Cache first.
    //If the Cache is not full, just add it to the map and set it to be the head.
    //If the Cache is full, then delete the LRU node in the map and linked list and do the previous step.
    public void set(int key, int value) {
        if(map.containsKey(key)){
            Node old = map.get(key);
            old.value = value;
            remove(old);
            setHead(old);
        }else{
            Node newNode = new Node(key, value);
            if(map.size()>=capacity){
                map.remove(tail.key);
                remove(tail);
                setHead(newNode);
            }else{
                setHead(newNode);
            }
            map.put(key, newNode);
        }
    }
}

