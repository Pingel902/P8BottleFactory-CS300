//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: A Circular Array Implementation of a Bottle Queue
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
 * A circular array implementation of a queue data structure that stores Bottle objects. The class
 * implements the QueueADT interface to provide standard queue operations such as enqueue, dequeue,
 * and peek.
 */
public class CircularBottleQueue implements QueueADT<Bottle> {
  private final int capacity;
  private Bottle[] bottles;
  private int front;
  private int back;
  private int size;

  /**
   * Constructs a new CircularBottleQueue with the specified capacity.
   * 
   * @param capacity the maximum number of Bottle objects the queue can hold
   * @throws IllegalArgumentException if the capacity is less than or equal to zero
   */
  public CircularBottleQueue(int capacity) {
    if (capacity <= 0) {
      throw new IllegalArgumentException("Invalid capacity");
    }
    // Itialization of all the queue's variables
    this.capacity = capacity;
    bottles = new Bottle[capacity];
    front = -1;
    back = -1;
    size = 0;
  }

  /**
   * Checks if the queue is empty.
   * 
   * @return true if the queue is empty, false otherwise
   */
  @Override
  public boolean isEmpty() {

    return size == 0;
  }

  /**
   * Checks if the queue is full.
   * 
   * @return true if the queue is full, false otherwise
   */
  @Override
  public boolean isFull() {

    return size == bottles.length;
  }

  /**
   * Returns the number of elements in the queue.
   * 
   * @return the number of elements in the queue
   */
  @Override
  public int size() {

    return size;
  }

  /**
   * Inserts the specified Bottle object into the queue.
   * 
   * @param element the Bottle object to be inserted
   * @throws IllegalStateException if the queue is full
   * @throws NullPointerException  if the specified Bottle object is null
   */
  @Override
  public void enqueue(Bottle element) {

    if (isFull()) {
      throw new IllegalStateException("Queue is full");

    }
    if (element == null) {
      throw new NullPointerException("Bottle has no information");
    }
    // Get the index of the back of the queue and adds one and adds the bottle to that index
    back = (back + 1) % bottles.length;
    bottles[back] = element;
    size++;

    if (front == -1) {
      front = back;
    }
  }

  /**
   * Removes and returns the front element of the queue.
   * 
   * @return the front element of the queue
   * @throws NoSuchElementException if the queue is empty
   */
  @Override
  public Bottle dequeue() {
    if (isEmpty()) {
      throw new NoSuchElementException("Queue is empty");
    }
    // Gets the bottle at the front of the index and removes it and sets the new front
    Bottle bottle = bottles[front];
    bottles[front] = null;
    front = (front + 1) % bottles.length;
    size--;
    

    if (isEmpty()) {
      back = front;
    }

    return bottle;

  }

  /**
   * Returns the front element of the queue without removing it.
   * 
   * @return the front element of the queue
   * @throws NoSuchElementException if the queue is empty
   */
  @Override
  public Bottle peek() {
    if (isEmpty()) {
      throw new NoSuchElementException("Queue is empty");
    }
    // Gets the index of the front of the queue
    front = (front + 1) % bottles.length;
    return bottles[front];
  }

  /**
   * Returns a copy of the queue.
   * 
   * @return a copy of the queue
   */
  @Override
  public QueueADT<Bottle> copy() {
    // Iterates through the queue copying each bottle from the queue into the copy queue
    CircularBottleQueue copy = new CircularBottleQueue(bottles.length);
    for (int i = 0; i < bottles.length; i++) {
      if (bottles[i] != null) {
        copy.enqueue(bottles[i]);
      }
    }
    return copy;
  }

  /**
   * Returns an iterator over the elements in the queue.
   * 
   * @return an iterator over the elements in the queue
   */
  public Iterator<Bottle> iterator() {
    QueueADT<Bottle> copyQueue = this.copy();
    return (Iterator<Bottle>) new BottleQueueIterator(copyQueue);
  }

  /**
   * Returns a string representation of the queue.
   * 
   * @return a string representation of the queue
   */
  @Override
  public String toString() {
    String s = ""; // Local variable that holds the overall string representation of the queue as
                   // each bottle's representation is added
    // Iterates over the queue getting the string representation of each bottle and additing it to
    // the local variable s
    for (int i = 0; i < size; i++) {
      int index = (front + i) % bottles.length;
      s += bottles[index].toString();
      if (i < size - 1) {
        s += "\n";
      }
    }
    return s;
  }

}
