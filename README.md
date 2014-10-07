Algorithms
==========

Algorithms that I have implemented during the session of Algorithms Design and Analisys from Coursera. Also there are other stuff implemented just for fun. The code is covered by tests, some written in Spock and some still in JUnit, although I'm rewriting them.


Contents
---------

*Sorting
	*Quicksort - With some pivot strategies (first element, medium, medium of three).
	*Mergesort
*Heap array based. And HeapSort implemented for free.
*LinkedList single forward
*BinarySearchTree
*LucasSeries (like a fibonacci, implemented with memoization, runs really fast).
*Min Cut of a graph
*Connected Components (with Dijkstra and Kosaraju implementations)


All algorithms are implemented using Groovy, which I'm mostly learning and enjoying. 

Hope you also!

How to use:
-----------

To use it, clone it and:

1. Import to eclipse
	* $ gradle eclipse

	And then go to eclipse and import it.

2. Build it:
	* $ gradle build
	
That will build a jar, running all the tests. 
A few tests are ignored since they take a long time to run (minutes/hours), since they load large graphs and run some algorithms on it.
You can just remove the @Ignore annotation from Spock.

I advise you to increase your JVM configuration if you wanna run those tests, by:
In your gradle file under gradle/bin, change this line to:
DEFAULT_JVM_OPTS="-Xmx1024m -Xss100m"
 
Credit
------

All sources were implemented by Guilherme Pires. More info about me: http://ie.linkedin.com/in/guilhermelagepires/ or https://github.com/guilhermesmi

Thanks!
