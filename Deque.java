/**creat a generic data type Deque to implement elementary data structures using
 * arrays and linked lists
 * j.z. 10/26/15 */

import java.util.Iterator;  //Iterators allow the caller to remove elements from the underlying collection
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
  
   private Node<Item> first;               // beginning of queue
   private Node<Item> last;               // end of queue
   private int sizeN;                       // number of elements on queue
   
   //linked list class
   private static class Node<Item> {
       private Item item;
       private Node<Item> next;
       private Node<Item> prior;
   }
      

   public Deque() {                           // construct an empty deque
        first = null;
        last  = null;
        sizeN = 0;
   }
   
   public boolean isEmpty() {                // is the deque empty?
         return sizeN == 0;
   }
   
   public int size() {                       // return the number of items on the deque
         return sizeN; 
   }
   
   public void addFirst(Item item) {          // add the item to the front, push old front to its next 
       if (item == null) {
           throw new NullPointerException("none item to add");
       }
       
       sizeN++;
       
       if (isEmpty()) {
              first = new Node<Item>();
              first.item = item;
              first.prior = null;
              first.next = null;
              last  = first; 
       } else {
                  Node<Item> oldfirst = first;
                  first = new Node<Item>();
                  first.item  = item;
                  oldfirst.prior = first;
                  first.next = oldfirst;
                  first.prior = null;
  
       }     
   }
  
   public void addLast(Item item) {          // add the item to the end
        if (item == null) {
           throw new NullPointerException("none item to add");
       }
        
       sizeN++;
       
        if (isEmpty()) {
              last = new Node<Item>();
              last.item = item;
              last.prior = null;
              last.next = null;
              first  = last ;
       } else {
                  Node<Item> oldlast = last;
                  last = new Node<Item>();
                  last.item  = item;
                  oldlast.next = last;
                  last.prior = oldlast;
                  last.next  = null;
                  
       }
   }
   
   public Item removeFirst() {               // remove and return the item from the front front = front.next
        if (isEmpty()) {
           throw new NullPointerException("Queue underlow");
       }
        Item item = first.item;
        
        sizeN--;
        if (isEmpty()) { first = null; last = null; }
        else {
            first = first.next;
            first.prior = null;
        }
        return item;
   }
   
   public Item removeLast() {                 // remove and return the item from the end
     if (isEmpty()) {
           throw new NullPointerException("Queue underlow");
       }
        Item item = last.item;
        
        sizeN--;
        if (isEmpty()) {last = null; first = null;}
        else {
            last = last.prior;
            last.next = null;
        }
        return item;
   }
   
   
   public Iterator<Item> iterator() {        // return an iterator over items in order from front to end
        return new ListIterator<Item>(first);
   }

   private class ListIterator<Itemzj> implements Iterator<Item> { // creat an iterator
        private Node<Item> curr;
        public ListIterator(Node<Item> first) {
            curr = first;
        }
        public boolean hasNext() {
            return curr != null;
        }
        public void remove() {
            throw new UnsupportedOperationException();
        }
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = curr.item;
            curr = curr.next;
            return item;
        }
   }

   public static void main(String[] args) {  // unit testing
      
   }
}
