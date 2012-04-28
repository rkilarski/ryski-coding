package edu.bu.cs565.homework4.util;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Author: Ryszard Kilarski (Id: U81-39-8560) CS565 Homework #4.
 * 
 * This class was modified from its original form by implementing the Lock/Condition method of
 * synchronization. No other changes were made.
 * 
 */
// Fig. 26.18: CircularBuffer.java
// Synchronizing access to a shared three-element bounded buffer.
public class CircularBuffer implements Buffer {

	// Lock object for critical section.
	private final Lock accessLock = new ReentrantLock();

	// Notifier for read condition.
	private final Condition canRead = accessLock.newCondition();
	// Notifier for write condition.
	private final Condition canWrite = accessLock.newCondition();

	private final int[] buffer = { -1, -1, -1 }; // shared buffer

	private int occupiedCells = 0; // count number of buffers used
	private int writeIndex = 0; // index of next element to write to
	private int readIndex = 0; // index of next element to read

	// place value into buffer
	public void set(int value) throws InterruptedException {

		// Issue a lock.
		accessLock.lock();

		try {
			// wait until buffer has space available, then write value;
			// while no empty locations, place thread in waiting state
			while (occupiedCells == buffer.length) {
				System.out.printf("Buffer is full. Producer waits.\n");

				canWrite.await(); // Notify write condition to wait.

			} // end while

			buffer[writeIndex] = value; // set new buffer value

			// update circular write index
			writeIndex = (writeIndex + 1) % buffer.length;

			++occupiedCells; // one more buffer cell is full
			displayState("Producer writes " + value);

			// Notify the reader(s) to read.
			canRead.signalAll();

		} finally {
			// Always release the lock.
			accessLock.unlock();
		}
	} // end method set

	// return value from buffer
	public int get() throws InterruptedException {

		int readValue = 0;

		// Issue a lock.
		accessLock.lock();

		try {
			// wait until buffer has data, then read value;
			// while no data to read, place thread in waiting state
			while (occupiedCells == 0) {
				System.out.printf("Buffer is empty. Consumer waits.\n");

				canRead.await(); // Notify write condition to wait.
			} // end while

			readValue = buffer[readIndex]; // read value from buffer

			// update circular read index
			readIndex = (readIndex + 1) % buffer.length;

			--occupiedCells; // one fewer buffer cells are occupied
			displayState("Consumer reads " + readValue);

			// Notify the writer(s) to write.
			canWrite.signalAll();

		} finally {
			// Always release the lock.
			accessLock.unlock();
		}

		return readValue;
	} // end method get

	// display current operation and buffer state
	public void displayState(String operation) {
		// output operation and number of occupied buffer cells
		System.out.printf("%s%s%d)\n%s", operation, " (buffer cells occupied: ", occupiedCells, "buffer cells:  ");

		for (int value : buffer)
			System.out.printf(" %2d  ", value); // output values in buffer

		System.out.print("\n               ");

		for (int i = 0; i < buffer.length; i++)
			System.out.print("---- ");

		System.out.print("\n               ");

		for (int i = 0; i < buffer.length; i++) {
			if (i == writeIndex && i == readIndex)
				System.out.print(" WR"); // both write and read index
			else if (i == writeIndex)
				System.out.print(" W   "); // just write index
			else if (i == readIndex)
				System.out.print("  R  "); // just read index
			else
				System.out.print("     "); // neither index
		} // end for

		System.out.println("\n");
	} // end method displayState
} // end class CircularBuffer

/**************************************************************************
 * (C) Copyright 1992-2012 by Deitel & Associates, Inc. and Pearson Education, Inc. All Rights
 * Reserved. DISCLAIMER: The authors and publisher of this book have used their best efforts in
 * preparing the book. These efforts include the development, research, and testing of the theories
 * and programs to determine their effectiveness. The authors and publisher make no warranty of any
 * kind, expressed or implied, with regard to these programs or to the documentation contained in
 * these books. The authors and publisher shall not be liable in any event for incidental or
 * consequential damages in connection with, or arising out of, the furnishing, performance, or use
 * of these programs.
 *************************************************************************/
