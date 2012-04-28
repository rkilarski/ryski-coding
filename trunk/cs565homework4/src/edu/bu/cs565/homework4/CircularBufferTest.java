package edu.bu.cs565.homework4;

// Fig 23.19: CircularBufferTest.java
// Producer and Consumer threads manipulating a circular buffer.
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import edu.bu.cs565.homework4.util.CircularBuffer;
import edu.bu.cs565.homework4.util.Consumer;
import edu.bu.cs565.homework4.util.Producer;

/**
 * Author: Ryszard Kilarski (Id: U81-39-8560) CS565 Homework #4.
 * 
 * Deitel "Java How To Program", page 1117: 26.11 This very brief problem statement points us to the
 * example of section 26.8, which is shown in Figures 26.18 and 26.19 which uses the technique of
 * "bounded buffers". Then, it asks us to re-implement that example using the
 * "Lock and Condition Interfaces" technique of section 26.9. This section already contains an
 * example which uses "Lock and Condition Interfaces", which is illustrated in Figures 26.20 and
 * 26.21. However, this illustration does not contain the "Consumer/Producer" example.
 * 
 * Our task is to substitute the "Lock and Condition Interfaces" technique into the program of
 * Figures 26.18 and 26.19 and to make the program execute properly.
 * 
 * Please study the sections 26.8 and 26.9. We have not covered these sections in class. However,
 * their primary purpose is to extend the concepts of the previous sections in Chapter 26 which use
 * the "Producer/Consumer" example to include two new Java resource-sharing techniques.
 * 
 * Also, please be aware that you do not have to typewrite the Java programs in sections 26.8 and
 * 26.9 into your computer. Those programs can be copied from the website
 * www.pearsonhighered.com/deitel/. When you get to the Deitel page, look for the illuminated
 * picture of the waterfall on the book cover in the upper left. Then, look at the menu on the right
 * of that book cover and click on "Code Examples". This will show you a selection of all of the
 * chapters in the book. Select Chapter 26, which is a zip file, then download it. The zip file can
 * be expanded into a folder which contains all of the examples of Chapter 26.
 * 
 * 
 * Notes: The Producer, Consumer, and Buffer classes were not modified in any way in this exercise.
 * Only CircularBuffer was modified. This class was also not modified, except for the insertion of
 * this comment.
 */

public class CircularBufferTest {
	public static void main(String[] args) {
		// create new thread pool with two threads
		ExecutorService application = Executors.newCachedThreadPool();

		// create CircularBuffer to store ints
		CircularBuffer sharedLocation = new CircularBuffer();

		// display the initial state of the CircularBuffer
		sharedLocation.displayState("Initial State");

		// execute the Producer and Consumer tasks
		application.execute(new Producer(sharedLocation));
		application.execute(new Consumer(sharedLocation));

		application.shutdown();
	} // end main
} // end class CircularBufferTest

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
