//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: A Linked List Implementation of a Bottle Queue
// Course: CS 300 Spring 2023
//
// Author: Blake Pingel
// Email: bepingel@wisc.edu
// Lecturer: Mouna Kacem
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name: none
// Partner Email: none
// Partner Lecturer's Name: none
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
// __X_ Write-up states that pair programming is allowed for this assignment.
// __X_ We have both read and understand the course Pair Programming Policy.
// __X_ We have registered our team prior to the team registration deadline.
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons: none
// Online Sources: none
//
///////////////////////////////////////////////////////////////////////////////
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This class implements a queue data structure that stores Bottle objects using a linked list. It
 * implements the QueueADT interface and provides an Iterable interface to allow iteration over the
 * elements of the queue.
 */

public class LinkedBottleQueue implements QueueADT<Bottle>, Iterable<Bottle> {
  LinkedNode front; // Bottle at the front of the queue in format of LinkedNode
  LinkedNode back; // Bottle at the back of the queue in format of LinkedNode
  private int size; // Size of the queue
  private int capacity; // How many bottles can be in the queue


  /**
   * Constructs a new LinkedBottleQueue object with the specified capacity.
   * 
   * @param capacity how big the queue should be
   */
  public LinkedBottleQueue(int capacity) {
    if (capacity <= 0) {
      throw new IllegalArgumentException("Invalid capacity");
    }

    // Initialize the size, front, and back of the queue to null or zero.

    this.capacity = capacity;
    size = 0;
    front = null;
    back = null;

  }

  /**
   * Method checks if the queue is empty
   * 
   * @Return true if the queue is empty, false otherwise.
   */
  @Override
  public boolean isEmpty() {
    return size == 0;
  }

  /**
   * Checks if the queue is full
   * 
   * @Return true if the queue is full, false otherwise.
   */
  @Override
  public boolean isFull() {
    if (size == capacity) {
      return true;
    }

    return false;
  }

  /**
   * getter method for the size of the queue.
   * 
   * @return the current size of the queue.
   */

  @Override
  public int size() {
    return size;
  }

  /**
   * Adds a new Bottle object to the back of the queue. Throws an IllegalStateException if the queue
   * is full, or a NullPointerException if the element is null.
   * 
   * @param the bottle to be added to the queue
   */
  @Override
  public void enqueue(Bottle element) {
    if (isFull()) {
      throw new IllegalStateException("Queue is full");
    }
    if (element == null) {
      throw new NullPointerException("Bottle has no information");
    }

    LinkedNode<Bottle> node = new LinkedNode<>(element);
    if (isEmpty()) {
      front = node;
    } else {
      back.setNext(node);
    }
    back = node;
    size++;

  }

  /**
   * Removes and returns the front Bottle object in the queue.
   * 
   * @return the bottle that was removed from the queue
   */
  @Override
  public Bottle dequeue() {
    if (isEmpty() || front == null || back == null) {
      throw new NoSuchElementException("Queue is empty");
    }
    if (front.getData() == null) {
      throw new NullPointerException("No next element");
    }


    Bottle bottle = (Bottle) front.getData();
    if (size == 1) {
      front = null;
      back = null;
    } else {
      front = front.getNext();
    }
    if (front == null) {
      back = null;
    }
    size--;
    return bottle;
  }

  /**
   * Getter method for the front of the queue
   * 
   * @return the bottle at the front of the queue
   */
  @Override
  public Bottle peek() {
    if (front == null || size <= 0 || this == null) {
      throw new NoSuchElementException("Nothing in Queue");
    }
    return (Bottle) front.getData();


  }

  /**
   * Creates and returns a copy of the LinkedBottleQueue object.
   * 
   * @return copy of this queue
   */
  @Override
  public QueueADT<Bottle> copy() {
    LinkedBottleQueue copyQueue = new LinkedBottleQueue(this.capacity);

    // Create a new node to hold the data from the original queue
    LinkedNode<Bottle> currentNode = this.front;

    // Traverse the original queue and enqueue the data into the copy queue
    while (currentNode != null) {
      // Enqueue the current bottle object into the copy queue without creating a deep copy
      copyQueue.enqueue(currentNode.getData());

      // Move to the next node in the original queue
      currentNode = currentNode.getNext();
    }

    return copyQueue;
  }



  /**
   * Returns an iterator over the elements in the queue. This method creates a new
   * BottleQueueIterator object using the copy() method and returns it
   * 
   * @return iterator that goes over a copy of this queue
   */
  public Iterator<Bottle> iterator() {
    return new BottleQueueIterator(this);
  }

  /**
   * Gets a string representation of the queue
   * 
   * @return the string representation
   */
  @Override
  public String toString() {
    if (this.isEmpty()) {
      return "";
    }
    String s = ""; // local variable to hold the build up of the string
    Iterator<Bottle> iter = iterator();
    // Iterates through the queue getting the string representations and adding to the local
    // variable s
    while (iter.hasNext()) {
      Bottle bottle = iter.next();
      s += bottle.toString();
      if (iter.hasNext()) {
        s += "\n";
      }

    }
    return s;

  }

}
