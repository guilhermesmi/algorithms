algorithms
==========

Algorithms that I have implemented either for Courses or just for fun.

There are Data Structures such as LinkedList, Heap, Binary Search Tree and algorithms such as:
Sorting (QuickSort, MergeSort, Minimun Cut, Connected Components, Lucas Series and so.

All algorithms are implemented using Groovy, which I'm mostly learning and enjoying. 

Hope you also!

I'm rewriting some tests using Spock, so they are more legible.

To use it, clone it and:

1. Import to eclipse
	$ gradle eclipse
And then import it.

2. Build it:

	$ gradle build
	
That will build a jar, running all the tests. 
Some tests are ignored since they take a long time to run (minutes/hours).
You can just remove the @Ignore annotation from Spock.

I advise you to increase your JVM configuration if you wanna run those tests, by:

In your gradle file under gradle/bin, change to:

DEFAULT_JVM_OPTS="-Xmx1024m -Xss100m"
 
 