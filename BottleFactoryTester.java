//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Tester class for BottleQueues
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
 * This utility class implements tester methods to check the correctness of the implementation of
 * classes defined in p08 Bottle Factory program.
 *
 */
public class BottleFactoryTester {

  /**
   * Ensures the correctness of the constructor and methods defined in the Bottle class
   * 
   * @return true if the tester verifies a correct functionality and false if at least one bug is
   *         detected
   */
  public static boolean bottleTester() {
    Bottle.resetBottleCounter();

    // test equals method
    Bottle bottle1 = new Bottle("Blue");
    Bottle bottle3 = bottle1;
    if (!bottle1.equals(bottle3)) {
      System.out.println("Error in equals method");
      return false;
    }

    // Test toString method

    String expectedOutput = "SN1Blue:Empty:Open";
    if (!bottle1.toString().equals(expectedOutput)) {
      System.out.print("ToString()1234 Fails");
      return false;
    }



    return true;
  }

  /**
   * Ensures the correctness of the constructor and methods defined in the LinkedBottleQueue class
   * 
   * @return true if the tester verifies a correct functionality and false if at least one bug is
   *         detected
   */
  public static boolean linkedBottleQueueTester() {
    try {
      Bottle.resetBottleCounter();

      // test constructor - verify fields and exception behavior (when capacity is invalid)
      try {
        LinkedBottleQueue test = new LinkedBottleQueue(-1);
        return false;
      } catch (IllegalArgumentException e) {

      }

      // Test scenario for a invalid capacity


      try {
        LinkedBottleQueue test2 = new LinkedBottleQueue(0);
        return false;
      } catch (IllegalArgumentException e) {
        // Do nothing

      }
      {
        LinkedBottleQueue queue = new LinkedBottleQueue(5);



      }


      {
        LinkedBottleQueue queue = new LinkedBottleQueue(2);

        // Enqueue two bottles
        Bottle bottle1 = new Bottle("Blue");
        Bottle bottle2 = new Bottle("Red");
        queue.enqueue(bottle1);
        queue.enqueue(bottle2);

        // Try to enqueue a third bottle, should throw IllegalStateException
        try {
          Bottle bottle3 = new Bottle("Green");
          queue.enqueue(bottle3);
          return false;
        } catch (IllegalStateException e) {
          // Test passed
        }
      }
      {
        // Test scenario for tryign to add a null bottle to the queue
        LinkedBottleQueue queue = new LinkedBottleQueue(3);

        try {
          queue.enqueue(null);
          return false;
        } catch (NullPointerException e) {
          // Exception was thrown as expected
        }
      }

      // Test the copy method
      {
        LinkedBottleQueue originalQueue = new LinkedBottleQueue(5);
        originalQueue.enqueue(new Bottle("Green"));
        originalQueue.enqueue(new Bottle("Yellow"));
        originalQueue.enqueue(new Bottle("Orange"));

        // Make a copy of the original queue
        LinkedBottleQueue copyQueue = (LinkedBottleQueue) originalQueue.copy();

        // Checks to make sure the size of the copy is the same

        if (originalQueue.size() != copyQueue.size()) {
          return false;
        } else {
          Iterator<Bottle> originalIterator = originalQueue.iterator();
          Iterator<Bottle> copyIterator = copyQueue.iterator();

          // Iterates through the copy queue checking each bottle to make sure they are the same

          while (originalIterator.hasNext() && copyIterator.hasNext()) {
            Bottle originalBottle = originalIterator.next();
            Bottle copyBottle = copyIterator.next();

            if (!originalBottle.equals(copyBottle)) {
              return false;
            }
          }
        }
      }

      {
        LinkedBottleQueue queue = new LinkedBottleQueue(4);
        Bottle.resetBottleCounter();


        // Test methods on an empty queue
        if (!queue.isEmpty()) {
          return false;
        }
        if (queue.isFull()) {
          return false;
        }
        if (queue.size() != 0) {
          return false;
        }
        try {
          queue.peek();
          return false;
        } catch (NoSuchElementException e) {
          // Do nothing
        }
        String expected = "";
        String actual = queue.toString();
        if (!expected.equals(actual)) {
          return false;
        }
        try {
          queue.dequeue();
          return false;
        } catch (NoSuchElementException e) {
          // Do nothing
        }


        // test scenarios for all methods on a partially filled queue
        Bottle b1 = new Bottle("Blue");
        queue.enqueue(b1);
        if (!queue.peek().getSerialNumber().equals(b1.getSerialNumber())) {
          return false;
        }
        if (queue.size() != 1) {
          return false;
        }
        if (queue.isFull()) {
          return false;
        }


        Bottle b2 = new Bottle("Yellow");
        try {
          queue.enqueue(b2);
        } catch (IllegalStateException e) {
          return false;
        }
        if (queue.size() != 2) {
          return false;
        }
        if (queue.isFull()) {
          return false;
        }
        if (!queue.peek().getSerialNumber().equals(b1.getSerialNumber())) {
          return false;
        }

        Bottle b3 = new Bottle("Green");
        try {
          queue.enqueue(b3);
        } catch (IllegalStateException e) {
          return false;
        }
        if (queue.size() != 3) {
          return false;
        }

        try {
          queue.dequeue();
        } catch (NoSuchElementException e) {
          return false;
        }
        if (queue.size() != 2) {
          return false;
        }
        if (!queue.peek().getSerialNumber().equals(b2.getSerialNumber())) {
          return false;
        }

        Bottle b4 = new Bottle("Tan");
        Bottle b5 = new Bottle("Purple");
        try {
          queue.enqueue(b4);
          queue.enqueue(b5);
        } catch (IllegalStateException e) {
          return false;
        }
        Bottle b6 = new Bottle("Purple");
        try {
          queue.enqueue(b6);
          return false;
        } catch (IllegalStateException e) {
          // DO nothing
        }
        if (!queue.peek().getSerialNumber().equals(b2.getSerialNumber())) {
          return false;
        }


        if (queue.size() != 4) {
          return false;
        }

        if (!queue.isFull()) {
          return false;
        }
        try {
          queue.dequeue();
        } catch (NoSuchElementException e) {
          return false;
        }
        if (!queue.peek().getSerialNumber().equals(b3.getSerialNumber())) {
          return false;
        }
        if (queue.size() != 3) {
          return false;
        }
        try {
          queue.dequeue();
          queue.dequeue();
        } catch (NoSuchElementException e) {
          return false;
        }
        if (!queue.peek().getSerialNumber().equals(b5.getSerialNumber())) {
          return false;
        }
        if (queue.size() != 1) {
          return false;
        }
        try {
          queue.dequeue();
        } catch (NoSuchElementException e) {
          return false;
        }
        if (queue.size() != 0) {
          return false;
        }
        if (!queue.isEmpty()) {
          return false;
        }

      }


      // test toString method
      {
        LinkedBottleQueue queue = new LinkedBottleQueue(5);
        Bottle b1 = new Bottle("blue");
        Bottle b2 = new Bottle("green");
        Bottle b3 = new Bottle("red");
        queue.enqueue(b1);
        queue.enqueue(b2);
        queue.enqueue(b3);
        String actual = queue.toString();
        String expected = b1.toString() + "\n" + b2.toString() + "\n" + b3.toString();
        if (!actual.equals(expected)) {
          return false;
        }
      }


      // Test iterator
      {
        LinkedBottleQueue queue = new LinkedBottleQueue(5);
        Bottle b1 = new Bottle("blue");
        Bottle b2 = new Bottle("green");
        Bottle b3 = new Bottle("red");
        queue.enqueue(b1);
        queue.enqueue(b2);
        queue.enqueue(b3);

        Iterator<Bottle> iter = queue.iterator();

        if (!iter.hasNext()) {
          return false;
        }
        if (!iter.next().equals(b1)) {
          return false;
        }

        if (!iter.hasNext()) {
          return false;
        }
        if (!iter.next().equals(b2)) {
          return false;
        }
        if (!iter.hasNext()) {
          return false;
        }
        if (!iter.next().equals(b3)) {
          return false;
        }

        if (iter.hasNext()) {
          return false;
        }


      }


    } catch (Exception e) {
      return false;
    }
    return true;
  }


  /**
   * Ensures the correctness of the constructor and methods defined in the CircularBottleQueue class
   * 
   * @return true if the tester verifies a correct functionality and false if at least one bug is
   *         detected
   */
  public static boolean circularBottleQueueTester() {
    try {
      Bottle.resetBottleCounter();



      // test invalid capacities
      try {
        CircularBottleQueue test = new CircularBottleQueue(-1);
        return false;
      } catch (IllegalArgumentException e) {

      }

      try {
        CircularBottleQueue test2 = new CircularBottleQueue(0);
        return false;
      } catch (IllegalArgumentException e) {
        // Do nothing
      }
      {
        LinkedBottleQueue queue = new LinkedBottleQueue(4);
        Bottle.resetBottleCounter();

        // 1) all methods on an empty queue
        if (!queue.isEmpty()) {
          return false;
        }
        if (queue.isFull()) {
          return false;
        }
        if (queue.size() != 0) {
          return false;
        }
        try {
          queue.peek();
          return false;
        } catch (NoSuchElementException e) {
          // Do nothing
        }
        String expected = "";
        String actual = queue.toString();
        if (!expected.equals(actual)) {
          return false;
        }
        try {
          queue.dequeue();
          return false;
        } catch (NoSuchElementException e) {
          // Do nothing
        }

        // 3) all methods on a partially filled queue
        Bottle b1 = new Bottle("Blue");
        queue.enqueue(b1);
        if (!queue.peek().getSerialNumber().equals(b1.getSerialNumber())) {
          return false;
        }
        if (queue.size() != 1) {
          return false;
        }
        if (queue.isFull()) {
          return false;
        }

        Bottle b2 = new Bottle("Yellow");
        try {
          queue.enqueue(b2);
        } catch (IllegalStateException e) {
          return false;
        }
        if (queue.size() != 2) {
          return false;
        }
        if (queue.isFull()) {
          return false;
        }
        if (!queue.peek().getSerialNumber().equals(b1.getSerialNumber())) {
          return false;
        }

        Bottle b3 = new Bottle("Green");
        try {
          queue.enqueue(b3);
        } catch (IllegalStateException e) {
          return false;
        }
        if (queue.size() != 3) {
          return false;
        }

        try {
          queue.dequeue();
        } catch (NoSuchElementException e) {
          return false;
        }
        if (queue.size() != 2) {
          return false;
        }
        if (!queue.peek().getSerialNumber().equals(b2.getSerialNumber())) {
          return false;
        }

        Bottle b4 = new Bottle("Tan");
        Bottle b5 = new Bottle("Purple");
        try {
          queue.enqueue(b4);
          queue.enqueue(b5);
        } catch (IllegalStateException e) {
          return false;
        }
        Bottle b6 = new Bottle("Purple");
        try {
          queue.enqueue(b6);
          return false;
        } catch (IllegalStateException e) {
          // Do nothing
        }
        if (!queue.peek().getSerialNumber().equals(b2.getSerialNumber())) {
          return false;
        }
        if (queue.size() != 4) {
          return false;
        }

        if (!queue.isFull()) {
          return false;
        }

        try {
          queue.dequeue();
        } catch (NoSuchElementException e) {
          return false;
        }
        if (!queue.peek().getSerialNumber().equals(b3.getSerialNumber())) {
          return false;
        }
        if (queue.size() != 3) {
          return false;
        }

        try {
          queue.dequeue();
          queue.dequeue();
        } catch (NoSuchElementException e) {
          return false;
        }
        if (!queue.peek().getSerialNumber().equals(b5.getSerialNumber())) {
          return false;
        }
        if (queue.size() != 1) {
          return false;
        }

        try {
          queue.dequeue();
        } catch (NoSuchElementException e) {
          return false;
        }
        if (queue.size() != 0) {
          return false;
        }
        if (!queue.isEmpty()) {
          return false;
        }

        // Testing CircularBottleQueue after a series of enqueue and dequeue methods on the queue
        CircularBottleQueue queue4 = new CircularBottleQueue(4);
        Bottle.resetBottleCounter();
        Bottle b7 = new Bottle("White");
        Bottle b8 = new Bottle("Purple");
        Bottle b9 = new Bottle("Green");
        Bottle b10 = new Bottle("Blue");
        queue4.enqueue(b7);
        queue4.enqueue(b8);
        queue4.dequeue();
        queue4.enqueue(b9);
        queue4.enqueue(b10);
        queue4.dequeue();
        queue4.dequeue();
        queue4.enqueue(b8);
        String expected4 = b10.toString() + "\n" + b8.toString();
        String actual4 = queue4.toString();
        if (!expected4.equals(actual4)) {
          return false;
        }

        {

          // 5) Enqueue until queue is full and dequeue until queue is empty
          CircularBottleQueue queue5 = new CircularBottleQueue(3);
          Bottle.resetBottleCounter();
          Bottle b11 = new Bottle("Yellow");
          Bottle b12 = new Bottle("Blue");
          Bottle b13 = new Bottle("Green");
          Bottle b14 = new Bottle("Red");
          queue5.enqueue(b11);
          queue5.enqueue(b12);
          queue5.enqueue(b13);
          try {
            queue5.enqueue(b14);
            return false;
          } catch (IllegalStateException e) {
            // do nothing
          }
          queue5.dequeue();
          queue5.dequeue();
          queue5.dequeue();
          try {
            queue5.dequeue();
            return false;
          } catch (NoSuchElementException e) {
            // do nothing
          }
          if (!queue5.isEmpty()) {
            return false;
          }
        }
        // Test to String

        CircularBottleQueue queue9 = new CircularBottleQueue(4);
        Bottle.resetBottleCounter();
        Bottle bottle1 = new Bottle("White");
        Bottle bottle2 = new Bottle("Purple");
        Bottle bottle3 = new Bottle("Green");
        Bottle bottle4 = new Bottle("Blue");
        queue9.enqueue(bottle1);
        queue9.enqueue(bottle2);
        queue9.enqueue(bottle3);
        queue9.enqueue(bottle4);
        String actual9 = queue9.toString();
        String expected9 = bottle1.toString() + "\n" + bottle2.toString() + "\n"
            + bottle3.toString() + "\n" + bottle4.toString();
        if (!actual9.equals(expected9)) {
          return false;
        }

      }
    } catch (Exception e) {
      return false;
    }
    return true;

  }



  /**
   * Ensures the correctness of the constructor and methods defined in the BottleQueueIterator class
   * 
   * @return true if the tester verifies a correct functionality and false if at least one bug is
   *         detected
   */
  public static boolean bottleQueueIteratorTester() {

    /*
     * test 01: Create a LinkedBottleQueue with at least n bottles and use the bottleQueueIterator
     * to traverse the queue. Verify if all the bottles are returned correctly
     */

    try {
      {
        Bottle.resetBottleCounter();
        LinkedBottleQueue queue = new LinkedBottleQueue(4);
        Bottle b1 = new Bottle("Green");
        Bottle b2 = new Bottle("Yellow");
        Bottle b3 = new Bottle("Orange");
        Bottle b4 = new Bottle("Tan");
        queue.enqueue(b1);
        queue.enqueue(b2);
        queue.enqueue(b3);
        queue.enqueue(b4);

        Iterator<Bottle> iter = queue.iterator();

        // dequeue a bottle from the original queue
        queue.dequeue();

        // iterate over the copy and check if the bottles have the same serialnumber
        int i = 1;
        while (iter.hasNext()) {
          Bottle b = iter.next();
          if (!b.getSerialNumber().equals(b1.getSerialNumber())
              && !b.getSerialNumber().equals(b2.getSerialNumber())
              && !b.getSerialNumber().equals(b3.getSerialNumber())
              && !b.getSerialNumber().equals(b4.getSerialNumber())) {
            return false;
          }
          i++;
        }

      }
      // Test the iterator on a linked bottle queue
      {
        Bottle.resetBottleCounter();
        LinkedBottleQueue queue = new LinkedBottleQueue(4);
        Bottle b1 = new Bottle("Green");
        Bottle b2 = new Bottle("Yellow");
        Bottle b3 = new Bottle("Orange");
        Bottle b4 = new Bottle("Tan");
        queue.enqueue(b1);
        queue.enqueue(b2);
        queue.enqueue(b3);
        queue.enqueue(b4);

        Iterator<Bottle> iter = queue.iterator();

        if (!iter.hasNext()) {

          return false;
        }

        if (!iter.next().equals(b1)) {

          return false;
        }
        if (!iter.hasNext()) {

          return false;
        }
        if (!iter.next().equals(b2)) {

          return false;
        }
        if (!iter.hasNext()) {

          return false;
        }
        if (!iter.next().equals(b3)) {
          return false;
        }
        if (!iter.hasNext()) {
          return false;
        }
        if (!iter.next().equals(b4)) {
          return false;
        }
        if (iter.hasNext()) {
          return false;
        }

      }



      /*
       * test 02: Create a CircularBottleQueue with at least n bottles and use the
       * bottleQueueIterator to traverse the queue. Verify if all the bottles are returned correctly
       */

      {
        // test senerio for CircularBottleQueue and itability to create a deep copy with the same
        // references to the bottles
        CircularBottleQueue queue = new CircularBottleQueue(5);
        Bottle.resetBottleCounter();

        // enqueue 5 bottles
        Bottle b1 = new Bottle("Blue");
        Bottle b2 = new Bottle("Yellow");
        Bottle b3 = new Bottle("Green");
        Bottle b4 = new Bottle("Tan");
        Bottle b5 = new Bottle("Purple");
        queue.enqueue(b1);
        queue.enqueue(b2);
        queue.enqueue(b3);
        queue.enqueue(b4);
        queue.enqueue(b5);

        // create iterator and traverse the queue
        Iterator<Bottle> iterator = queue.iterator();
        int i = 1;
        if (!iterator.next().equals(b1)) {
          return false;

        }
        if (!iterator.next().equals(b2)) {
          return false;

        }
        if (!iterator.next().equals(b3)) {
          return false;

        }
        if (!iterator.next().equals(b4)) {
          return false;

        }
        if (!iterator.next().equals(b5)) {
          return false;

        }
        try {
          Bottle fail = iterator.next();
        } catch (NoSuchElementException e) {
          // Do nothing
        }
        if (iterator.hasNext()) {
          return false;
        }
      }



      {

        boolean result = true;
        // Tester method for bottleQueueIterator not using a deep copy
        QueueADT<Bottle> originalQueue = new LinkedBottleQueue(5);
        Bottle b1 = new Bottle("Blue");
        Bottle b2 = new Bottle("Red");
        Bottle b3 = new Bottle("Green");
        originalQueue.enqueue(b1);
        originalQueue.enqueue(b2);
        originalQueue.enqueue(b3);

        // Create a BottleQueueIterator object with the original queue
        Iterator<Bottle> iterator = new BottleQueueIterator(originalQueue);

        // Check if the iterator uses a deep copy of the queue, but has the same references to the
        // Bottles
        try {
          // Modify the original queue
          originalQueue.dequeue();
          originalQueue.enqueue(new Bottle("Yellow"));

          // Check that the iterator returns the original elements
          result &= iterator.next().equals(b1);
          result &= iterator.next().equals(b2);
          result &= iterator.next().equals(b3);

          // Check that the iterator is at the end of the queue
          result &= !iterator.hasNext();

        } catch (NoSuchElementException e) {
          // Iterator does not use a deep copy
          result = false;
        }
        if (result == false) {
          return false;
        }
      }

    } catch (Exception e) {
      return false;
    }
    return true;

  }

  /**
   * Runs all the tester methods defined in this class.
   * 
   * @return true if no bugs are detected.
   */
  public static boolean runAllTests() {
    System.out.println("bottleTester: " + (bottleTester() ? "Pass" : "Failed!"));
    System.out
        .println("bottleQueueIterator: " + (bottleQueueIteratorTester() ? "Pass" : "Failed!"));
    System.out
        .println("linkedBottleQueueTester: " + (linkedBottleQueueTester() ? "Pass" : "Failed!"));
    System.out.println(
        "circularBottleQueueTester: " + (circularBottleQueueTester() ? "Pass" : "Failed!"));

    return bottleTester() && bottleQueueIteratorTester() && linkedBottleQueueTester()
        && circularBottleQueueTester();
  }

  /**
   * Main method to run this tester class.
   * 
   * @param args list of input arguments if any
   */
  public static void main(String[] args) {
    System.out.println("runAllTests: " + (runAllTests() ? "Pass" : "Failed!"));
  }

}
