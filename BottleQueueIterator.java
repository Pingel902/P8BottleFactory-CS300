import java.util.Iterator;
//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Iterator for Bottle Queue
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
import java.util.NoSuchElementException;

/**
 * An iterator over a Bottle Queue.
 * 
 * @author blake
 *
 */

public class BottleQueueIterator implements Iterator<Bottle> {
  private QueueADT<Bottle> bottleQueue;

  /**
   * Constructs a BottleQueueIterator with the specified Bottle Queue.
   * 
   * @param bottleQueue the Bottle Queue to iterate over
   * @throws IllegalArgumentException if the Bottle Queue is null
   */
  public BottleQueueIterator(QueueADT<Bottle> bottleQueue) {
    if (bottleQueue == null) {
      throw new IllegalArgumentException("Null Queue");
    }
    this.bottleQueue = bottleQueue.copy();

  }

  /**
   * Returns true if the iterator has more elements.
   * 
   * @return true if the iterator has more elements
   */
  @Override
  public boolean hasNext() {
    return !bottleQueue.isEmpty();
  }

  /**
   * Returns the next element in the iteration.
   * 
   * @return the next element in the iteration
   * @throws NoSuchElementException if the iteration has no more elements
   */
  @Override
  public Bottle next() {
    if (!hasNext()) {
      throw new NoSuchElementException();
    }
    return bottleQueue.dequeue();
  }
}
