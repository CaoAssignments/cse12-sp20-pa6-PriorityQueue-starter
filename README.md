# CSE 12 Spring 2020 PA6 - Priority Queue (100 Points)
**Due date: Monday, May 11 @ 11:59PM PDT**  
(Tuesday, May 12 @ 11:59pm PDT w/ slip day. If you submit your assignment late, the autograder will automatically use your slip day if you have any remaining.)

### Useful Resources:
Throughout this assignment, you may find the following resources helpful.  Refer to them BEFORE posting questions on Piazza.
- [Connecting to the lab machines remotely](https://docs.google.com/document/d/1AM888aGCagZRQYSY3wgtzboTnHHzLOEnuUgl85_oMJQ/edit)
- [running bash on Windows](https://docs.google.com/document/d/1SMnopsKw0lHWCxkQ0ETgkfLFdfonjGGh4CgfagnFcSU/edit)
- [Unix reference sheet](https://files.fosswire.com/2007/08/fwunixref.pdf)
- [JUnit testing tutorial](https://www.tutorialspoint.com/junit/junit_environment_setup.htm)

### Provided Files:
- MyMinHeap.java
- MyMinHeapTester.java

### Files to Submit:
- MyMinHeap.java
- MyPriorityQueue.java
- MyMinHeapTester.java
- MyPriorityQueueTester.java

### Goal:
In Programming Assignment 6, we will be implementing an array-based min-heap (`MyMinHeap`) and a min-heap-based priority queue (`MyPriorityQueue`).

**As you get started, please pay attention to the following:**
* Please read the **ENTIRE write-up** before getting started.
* For this homework and all homework in CSE 12, you may not change any public class/method or variable names given in the write-up or starter code.

### Logistics:
In EACH AND EVERY FILE that you turn in, we need the following in comments at the top of each file.  These are essential so that we can more easily process your submissions and ensure that you receive proper credit for your work. 
```
NAME: <your name>  
ID: <your student ID>  
EMAIL: <your email>  
```

## Getting Started
We strongly recommend that you work on the lab machines (for remote access--see instructions [here](https://docs.google.com/document/d/1AM888aGCagZRQYSY3wgtzboTnHHzLOEnuUgl85_oMJQ/edit?usp=sharing)), but if you choose to work on your own machine, make sure your code runs correctly on ieng6, as that is where we will be testing it. 

**What is the ieng6 server?** The ieng6 server is a network of computers at UCSD. When you "ssh" to the ieng6 server, it means you are connected to a remote computer system. Your cs12 account has all of the necessary programs and correct versions installed.   
**Do I have to use the ieng6 server?** No, you can also work on your personal computer locally.  

The reason we reccomend the ieng6 server is so that your results are consistent with what we will see when we run it. One thing you could do is write and test your code locally and then before you submit check to see if your code will run on the ieng6 server.  

If you are still unsure about the ieng6 server ask a tutor or TA and they can help! Please also refer to discussion slides and provided documentation. 


#### Getting the Starter Code
Get the starter code from GitHub [here](https://github.com/CaoAssignments/cse12-sp20-pa6-PriorityQueue-starter). You have two options: 
1.  Use the command line. In your terminal, run the following: `git clone https://github.com/CaoAssignments/cse12-sp20-pa6-PriorityQueue-starter.git`  
2.  Download the repo as a zipped folder.

![](https://i.imgur.com/SjXKMV7.png)

When you run `javac` and `java` make sure you are in the same directory where the files are located. This is called your working directory. For this assignment, you will need to utilize the .jar files in the `libs` directory in the starter code. This will be similar to how you compiled and executed in PA1, PA2, PA3, PA4, and PA5. 


## Part 1 - Understanding and Testing First
In this programming assignment, you will be implementing the `MyMinHeap` and `MyPriorityQueue` classes. Note that we are providing starter code for only `MyMinHeap` and not `MyPriorityQueue` so you will have to create the `MyPriorityQueue.java` file yourself. Although we have provided you with a few tests, you will need to create additional tests of your own to test your code thoroughly and ensure that your implementation of the classes functions as specified.

**First, Understand what MyMinHeap and MyPriorityQueue will do**

In order to write a good tester, you will need a deep understanding of how the classes and methods you are testing are supposed to work. So before you start writing your tester, you should read part 2 in order to understand what each class is supposed to do. You may refer to the documentation for [priority queue](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/PriorityQueue.html). However, please note that our implementation will differ from this one in that we are separating the heap and the priority queue and that we won't be following the same inheritance structure (the reasons are uninteresting and unimportant). 

**Test `MyMinHeap` with `MyMinHeapTester` and `MyPriorityQueue` with `MyPriorityQueueTester`**

* The repository contains `MyMinHeapTester.java`. This is a starter file that defines a small number of tests against `MyMinHeap` using the JUnit testing framework. Observe and check how each test is written and what it tests for.
* You are strongly encouraged to add more tests, as the provided starter test is not comprehensive, and your program will later be tested against our master test. You may refer to the Testing section in this write-up when you write your unit tests.
* You should make sure to also include tests to check that your method throws the correct exceptions when they are expected to throw them. There are more sophisticated ways to do this (feel free to investgate and use them), but the simple approach is to do the following:
```
@Test
public void testInsertNullPointerException(){
	try{
		testMinHeap.insert(null);
		fail("Expected a NullPointerException to be thrown");
	}catch(NullPointerException e){
		assertEquals(e.getClass().getName(), "java.lang.NullPointerException");
	}
}
```
* After final submission, we will be running more extensive tests on your code. The points awarded from our tests will make up your final grade.
* You must not change any method names/signatures provided in the starter code.
* You can test `MyPriorityQueue` in pretty much the same way that you tested `MyMinHeap`. One detail about the autograder is that we will be testing your `MyPriorityQueue` implementation using our solution of `MyMinHeap` as specified by the writeup. Thus, even if your `MyMinHeap` does not function properly, your `MyPriorityQueue` may still be considered perfect. Therefore, to test your `MyPriorityQueue` implementation, you may find it a good idea to keep a local `MyPriorityQueue` object as well as a `MyMinHeap` object and perform the same operations on each and assert the expected operations against each other.

## Part 2 - MyMinHeap.java
```java
public class MyMinHeap<E extends Comparable<E>>
```
Our `MyMinHeap` class is an implementation of a min-heap. A min-heap is simply a binary tree with two properties: 

1. The tree is complete. This means that every level of the tree is completely filled, except possibly the bottom-most level. In the bottom-most level, all nodes are as far to the left as possible. 
1. The key of a node is _no greater than any_ of the keys of its children. Equivalently, the key of a node is _less than or equal to all_ the keys of its children. 

As a result of the first property, we can easily and efficiently represent this heap using an array. We've already had quite a bit of practice using arrays and manually expanding them, so we'll take a little break and use an `ArrayList` this time to save you the trouble of worrying about capacity. We might speak about arrays in the context of this PA, but when you actually implement the min-heap, you will implement it using an `ArrayList` (the concepts are the same). (Thus, there is no concept of capacity for `MyMinHeap`.) As a consequence, we no longer have any need for a node class, so we'll directly store elements in the array(list). However, we will still be calling elements nodes since it makes more sense when talking about children and parent nodes. 

The order of the nodes in the array is in the order of a level-order traversal. This means that the order of our nodes is from top-most level to bottom-most level and within each level, our nodes are ordered from left to right. For example, suppose we have the following heap:


<p align="center">
  <img src="https://i.imgur.com/UQ8ybQH.png">
</p>


This should translate to the following array. The arrows point from a parent node to the children nodes. The numbers at the top represent the index of the array. 

<p align="center">
  <img src="https://i.imgur.com/jknqFBl.png">
</p>

Since each heap can only be represented by a unique array, the words "heap" and "array" may be used interchangeably in the context of this PA. The "element at the end of the heap" refers to the last element in our array(list) and the "root of the heap" refers to the element at index 0 in our array. 

For this assignment, any element that is not in the heap should also not be in the array. Furthermore, every element in the heap should be at the correct index in the array. Our autograder will not award points for failures to abide by these expectations. 

Remember that the size of the `ArrayList` denotes the number of elements being used whereas the capacity is just for the underlying array's size. Again, we won't worry about the capacity as the `ArrayList` will automatically resize for us, but the size of the underlying `ArrayList` will be equal to the size of (number of elements in) our `MyMinHeap`. 

In this PA, we are using `compareTo()` to determine  the order of the elements. You can refer to the [javadoc](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/lang/Comparable.html) for how to use `compareTo()`. Comparisons ("smaller than," "bigger than," "equal to," etc.) in this writeup are all in this `compareTo()` sense, since our elements are of the generic type and may have their own `compareTo()` methods implemented that do not use some natural order. 

In the following code snippet, we use the `Character` class. `compareTo()` for this class compares the objects based on their natural ordering: characters with lower ASCII values (for letters with the same case, earlier in the alphabet is equivalent to a lower ASCII value). 

```java
Character smallElement = Character.valueOf('c'); // Character.valueOf('c') is an equivalent (but more efficient) way of getting new Character('c')
Character bigElement = Character.valueOf('y'); 
System.out.println(smallElement.compareTo(bigElement)); // prints out a negative number because calling object (smallElement) is smaller than the argument (bigElement)
System.out.println(bigElement.compareTo(smallElement)); // prints out a positive number because calling object (bigElement) is bigger than the argument (smallElement)
System.out.println(smallElement.compareTo(smallElement)); // prints out 0 because calling object (smallElement) is equal to the argument (smallElement)
System.out.println(bigElement.compareTo(bigElement)); // prints out 0 because calling object (smallElement) is equal to the argument (smallElement)
```

Furthermore, note that we are **not** allowing explicit `null` elements in our min-heap. The methods that add elements to our heap will throw a `NullPointerException` if an attempt to add `null` occurs, as specified below.

---

### MyMinHeap Instance Variables
```Java
protected List<E> list; 
```
- We will be using a single instance variable for our `MyMinHeap`. This `List` will contain all information we need to keep track of our heap.
- **Do not change this instance variable and do not add any extra instance variables. You can still have `private static final` variables for your constants, but do not add any other static variables.**  

#### `protected List<E> list`

- This list is the underlying data structure of `MyMinHeap`. This instance variable should contain an `ArrayList` since we are implementing an array-based heap. Note that we are using a `List` reference for this variable, so we have access to all of the list ADT's operations, but our underlying data structure is the `ArrayList`, which will have fast random access and insertion at the end, which are actually the only two operations we need. 
- You can assume that `list` will never be `null` unless your own code sets it to be. You should also assume that `list` will always define a valid heap structure when a call to a non-helper method is made, but note that our operations generally break the heap structure and then fix it. When testing your code, it will be expected that we can populate `list` with a heap and that your methods will independently perform the specified operations on it. 

---

### MyMinHeap Constructors
```Java
public MyMinHeap(); 
public MyMinHeap(Collection<? extends E> collection); 
```

#### `public MyMinHeap()`

- This is a no-argument constructor that initializes your `list` variable to an empty `ArrayList`.

#### `public MyMinHeap(Collection<? extends E> collection)`

- Throw a `NullPointerException` if `collection` or any element in `collection` is `null`.
- Otherwise, initialize a min-heap using the elements in `collection`. Note that in general, the insertion order will change the heap's structure, so it is important that we follow these instructions precisely. We do this specifically for runtime efficiency. 
    - First, initialize the `list` instance variable by utilizing the [`ArrayList` constructor](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/ArrayList.html#%3Cinit%3E(java.util.Collection)) and directly passing in `collection`. Note that this constructor also handles, in the way we specified above, the case that `collection` is `null`. 
    - Next, iterate through our list _backward_, percolating each element _down_. We will soon write the [`percolateDown()`](#protected-void-percolatedownint-index) helper method, which we can directly use for this. 
    
---

### MyMinHeap Helper Methods
```java
protected void swap(int from, int to);
protected int getParentIdx(int index);
protected int getLeftChildIdx(int index);
protected int getRightChildIdx(int index);
protected int getMinChildIdx(int index);
protected void percolateUp(int index);
protected void percolateDown(int index);
protected E deleteIndex(int index);
```
Let's implement some helper methods. You should find these methods useful when implementing the actual functionalities of `MyMinHeap` and even when implementing the other helper methods. 

**Note:** These helper methods do not perform any boundary checks nor do they check for any invalid inputs. We expect all inputs to these methods to be valid since we would only call them from our own functions. In practice, these would be `private` methods, but for our assignment, they will be `protected` so that we can autograde your methods and provide feedback for them. To be clear, **these methods are required**.





#### `protected void swap(int from, int to)`
- Swap the locations of the elements at the `from` and `to` indices in `list`. 
- This method has undefined behavior if `from` or `to` are out of bounds, but as stated above, we assume that we will only call this method from our own methods and thus only make valid calls. There is no need to handle this case. 
- This method shouldn't make any other changes to `list`. 

#### `protected int getParentIdx(int index)`
- Calculate and return the parent index of the parameter `index`. 
- For example, 0 is the parent index of indices 1 and 2. See the diagram above the [instance variables section](#myminheap-instance-variables) to see some more examples. 
- This method has undefined behavior if `index<=0`, but as stated above, we assume that we will only call this method from our own methods and thus only make valid calls. There is no need to handle this case. 
- This method is irrelevant of what is currently in `list` and should not make any changes to `list`. 

#### `protected int getLeftChildIdx(int index)`
- Calculate and return the left child index of the parameter `index`.
- For example, 1 is the left child index of index 0. See the diagram above the [instance variables section](#myminheap-instance-variables) to see some more examples. 
- This method has undefined behavior if `index<0`, but as stated above, we assume that we will only call this method from our own methods and thus only make valid calls. There is no need to handle this case. 
- This method is irrelevant of what is currently in `list` and should not make any changes to `list`. 

#### `protected int getRightChildIdx(int index)`
- Calculate and return the right child index of the parameter `index`.
- For example, 2 is the right child index of index 0. See the diagram above the [instance variables section](#myminheap-instance-variables) to see some more examples. 
- This method has undefined behavior if `index<0`, but as stated above, we assume that we will only call this method from our own methods and thus only make valid calls. There is no need to handle this case. 
- This method is irrelevant of what is currently in `list` and should not make any changes to `list`. 

#### `protected int getMinChildIdx(int index)`
- Return the index of the smaller child of the element at `index`. **If the two children are equal, return the index of the left child**. If the node at the specified index is a leaf (has no children), return `-1`. 
- Note that it's also possible for a single node in our heap to have only one child. In this case, return the index of the left child (we know that this is a heap so all nodes are as far left as possible). For this method, it is safe to assume that the heap will have the completeness property whenever this is called. 
- This method has undefined behavior if `index` is out of bounds, but as stated above, we assume that we will only call this method from our own methods and thus only make valid calls. There is no need to handle this case. 
- This method depends on what is currently in `list` but should not make any changes to `list`. 

#### `protected void percolateUp(int index)`
- Percolate the element at the parameter `index` up until no heap properties are violated by this element (the heap properties will not be violated once this element's parent is no greater than it). Do this by swapping the element with its parent as needed. 
- Note the case where the element that you are percolating is equal to the parent. In this case, the heap property requiring that a node be no greater than its children is already satisfied, so you should **not** swap the element you are percolating with the parent. 
- This method has undefined behavior if `index` is out of bounds, but as stated above, we assume that we will only call this method from our own methods and thus only make valid calls. There is no need to handle this case. 

#### `protected void percolateDown(int index)`
- Percolate the element at the parameter `index` down until no heap properties are violated by this element (the heap properties will not be violated once this element is no greater than its children). If swapping is needed, then swapping with the bigger child would typically cause a violation of the key order property, so always swap places with the smaller child. If both children are equal and swapping is needed, **swap with the left child.** 
- Note the case where the element that you are percolating is equal to the smaller child. In this case, the heap property requiring that a node be no greater than its children is already satisfied, so you should **not** swap the element you are percolating with the child. 
- This method has undefined behavior if `index` is out of bounds, but as stated above, we assume that we will only call this method from our own methods and thus only make valid calls. There is no need to handle this case. 

#### `protected E deleteIndex(int index)`
- Remove the element at the specified index from `list` and return it. If we are removing the last element, then note that the heap properties are still maintained after the deletion. In other cases, we want to maintain the heap properties, so we will replace the deleted element with the last element in the heap (the right-most node in the bottom-most level of the heap) to fix the completeness property. Then, either percolate this element down or percolate this element up as necessary until no heap properties are violated by this element (only one of these actions will be necessary to maintain the heap property, all fixes to the key order property should be by percolating the replacement element). The following bulletpoints are an explanation for this. 
    - The "complete" property is never violated due to the way we chose the replacement element. 
    - The "key order" propery is possibly violated. Suppose `orig` is the original element at `index` and `replacement` is the element that was originally the last element of the heap but has now been used as the replacement. 
        - In the case where `replacement` is equal to `orig`: this property is already maintained. 
        - In the case where `replacement` is smaller than `orig`: every element in the subtree is already guaranteed, by the heap property, to be greater than or equal to `orig` and thus are greater than `replacement`. This means that percolating down will not make any swaps. However, the ancestor nodes are only guaranteed to be no greater than `orig`, but since `replacement` is also smaller than `orig`, the property is not necessarily maintained. Thus, we must percolate up to make sure that the property is maintained. 
        - In the case where `replacement` is greater than `orig`: the ancestor nodes are guaranteed to be no greater than `orig` by the heap property. Therefore, since `replacement` is greater than `orig`, the ancestor nodes are also no greater than `orig`, meaning that percolating up will not make any swaps. However, the nodes in the subtree are only guaranteed to be greater than or equal to `orig`. Since `replacement` is also greater than `orig`, there is no guarantee that `replacement` is no greater than its new children, thus the possible need to percolate down to make sure that the property is maintained. 
- This method depends on what is currently in `list`. The size of `list` should be one less than the size before this method was called. 
- Below is an example sequence of deletion on some pre-existing heap. Note that this is deleting at index 0. Here, we are using the natural ordering of the elements for comparing the elements (i.e., earlier letters in the alphabet are smaller than later letters). 

<p align="center">
  <img src="https://i.imgur.com/VbHDJv6.png">
  <br>
  <b>deleteIndex() Figure 0</b>: Begin deletion at index 0, which currently contains the element 'A'.
</p>

<br><br><br>

<p align="center">
  <img src="https://i.imgur.com/vUnqODS.png">
  <br>
  <b>deleteIndex() Figure 1</b>: 'A' is removed and set aside to be returned. The last element in the heap, 'G', is moved to the old position to replace it. We then begin the process of percolating 'G' down. There is a violation because the element 'G' is greater than the element 'D'. Since both children are equal, we swap with the left child.
</p>

<br><br><br>

<p align="center">
  <img src="https://i.imgur.com/Ouo9QTS.png">
  <br>
  <b>deleteIndex() Figure 2</b>: 'G' has now swapped with 'D', but there is still a violation because 'G' is greater than 'E', so we will swap those two.
</p>

<br><br><br>

<p align="center">
  <img src="https://i.imgur.com/bf9p76i.png">
  <br>
  <b>deleteIndex() Figure 3</b>: 'G' has now swapped with 'E' and there are no more heap violations, thus ending the percolation process.We have completed the deletion process and will return the original deleted element, 'A'.
</p>

<br><br><br>

---

### MyMinHeap Core Methods
```java 
public void insert(E element);
public E getMin();
public E remove();
public int size();
public void clear();
```
These are the functions that we want our min-heap to support. 

#### `public void insert(E element)`  
- Throw a `NullPointerException` and do not add to the heap if `element` is `null`. 
- Otherwise, add `element` to the end of the heap (so that it is the right-most element in the bottom-most level) and percolate only the inserted element up until no heap properties are violated (all fixes to the heap properties should be by this percolation). 
- Below is an example sequence of insertion. <br>

<p align="center">
  <img src="https://i.imgur.com/aTuA6aL.png">
  <br>
  <b>insert() Figure 0</b>: Begin insertion of element 'D'.
</p>

<br><br><br>

<p align="center">
  <img src="https://i.imgur.com/AJhlO4U.png">
  <br>
  <b>insert() Figure 1</b>: Add 'D' to the end of the min-heap.
</p>

<br><br><br>

<p align="center">
  <img src="https://i.imgur.com/llbklew.png">
  <br>
  <b>insert() Figure 2</b>: There is a violation since 'D' is less than its parent 'G', so we will swap those.
</p>

<br><br><br>

<p align="center">
  <img src="https://i.imgur.com/ofwrHek.png">
  <br>
  <b>insert() Figure 3</b>: 'D' has now swapped with 'G' and there are no more heap violations, thus ending the percolation process. We have completed the insertion process.
</p>

<br>

#### `public E getMin()` 
- Return the root (this will be the smallest) element of the heap. 
- If the heap is empty, return `null` instead. Note that here, and in all similar methods, we can safely return `null` because `null` is not a valid element for `MyMinHeap` and therefore, returning `null` can have the special meaning that the min-heap is empty. 

#### `public E remove()` 
- Remove and return the root (this will be the smallest) element in the heap. See the [`deleteIndex()` specifications](#protected-e-deleteindexint-index) for how to fix the heap after removal. 
- If the heap is empty, return `null` (and do no deletion) instead. 

#### `public int size()` 
- Return the number of elements in this min-heap.

#### `public void clear()`
- Clear out the entire heap (the heap should be empty after this call). 
- `list` should be an empty `ArrayList`, not `null`, after this function is completed. 





## Part3 - MyPriorityQueue.java
```java
public class MyPriorityQueue<E extends Comparable<E>>
```
A priority queue is a queue where elements are sorted by their priority. Popping/dequeuing from the priority queue should always yield an element with the highest priority. In other words, elements with higher priority will be closer to the front of the priority queue. 

Our `MyPriorityQueue` implementation will be using a `MyMinHeap` backend. Our underlying data structure is a min-heap, so smaller elements (in the `compareTo()` sense) have higher priorities. The root node of the min-heap is the one with the highest priority and is also the smallest element in the min-heap. 


### MyPriorityQueue Instance Variables
```java 
protected MyMinHeap<E> heap;
```
- We will be using a single instance variable for our `MyPriorityQueue`. This `MyMinHeap` will contain all information we need to keep track of our heap. You should only use the `MyMinHeap` "core" methods in your `MyPriorityQueue` implementation. Do not directly access `heap.list` in your implementation of `MyPriorityQueue` (treat it as if it were a private instance variable). 
- **Do not change this instance variable and do not add any extra instance variables. You can still have `private static final` variables for your constants, but do not add any other static variables.**  

#### `protected MyMinHeap<E> heap`
- This heap holds and sorts all elements for our priority queue. Since we are using our `MyMinHeap` to store our elements, `null` is not allowed in this priority queue. 

### MyPriorityQueue Constructors
```java 
public MyPriorityQueue();
public MyPriorityQueue(Collection<? extends E> collection);
```
#### `public MyPriorityQueue()`
- This no-argument constructor initializes `heap` to be an empty `MyMinHeap`. 

#### `public MyPriorityQueue(Collection<? extends E> collection)` 
- Throw a `NullPointerException` if `collection` or any element in `collection` is `null`. 
- Otherwise, this constructor initializes `heap` to contain the elements in `collection`. Remember that we have already written a handy-dandy constructor for `MyMinHeap`. 


### MyPriorityQueue Methods
```java 
public void push(E element);
public E peek();
public E pop();
public int getLength();
public void clear();
```
#### `public void push(E element)` 
- Throw a `NullPointerException` and do not add to the priority queue if `element` is `null`. 
- Otherwise, add `element` to this priority queue. `heap` should be fixed accordingly. 

#### `public E peek()` 
- Return the element with the highest priority. Remember that this should be whichever element our min-heap says is the smallest element. 
- If the priority queue is empty, return `null` instead.  

#### `public E pop()` 
- Remove and return the element with the highest priority. Remember that this should be whichever element our min-heap says is the smallest element. `heap` should be fixed accordingly. 
- If the priority queue is empty, return `null` (and do no removal) instead.  

#### `public int getLength()` 
- Return the number of elements in the priority queue.

#### `public void clear()` 
- Clear out the entire priority queue (the priority queue should be empty after this call). 
- `heap` should be an empty `MyMinHeap`, not `null`, after this function is completed. 

## Testing
<table>
    <tbody>
        <tr>
            <th> Tester Name </th>
            <th> Description </th>
            <th> Points </th>
        </tr>
        <tr>
            <td>  TestSanity  </td> 
            <td>
                <ul>
                <li>MyMinHeap no-arg constructor: initializes <code>list</code> to be empty. </li>
                <li>MyMinHeap second constructor: call with some <code>Collection</code>, initializes <code>list</code> appropriately. </li>
                    <br>
                    <b> For these helper methods (in the sanity test and their respective testers), <code>list</code> may not be initially valid and also may not be valid afterward. Remember that our helper methods do not want to check as these helper methods are used in the middle of other operations. Even though some of these <code>list</code> states will never be valid even in the middle of an operation of your implementation, they may be possible in someone else's implementation, so the helper methods should not perform any checks. </b>
                    <li>MyMinHeap swap: <code>list</code> is {3,1,2}, call with args (0,1), <code>list</code> becomes {1,3,2}. </li>
                    <li>MyMinHeap getParentIdx: call with args (1), returns 0. </li>
                    <li>MyMinHeap getLeftChildIdx: call with args (0), returns 1. </li>
                    <li>MyMinHeap getRightChildIdx: call with args (0), returns 2. </li>
                    <li>MyMinHeap getMinChildIdx: <code>list</code> is {5,3,4}, call with args (0), returns 1. For this method, it is safe to assume that the heap will have the completeness property. </li>
                    <li>MyMinHeap percolateUp: <code>list</code> is {1,4,4,2,2}, call with args (3), <code>list</code> becomes {1,2,4,4,2}. </li>
                    <li>MyMinHeap percolateDown: <code>list</code> is {8,4,3,5,2}, call with args (0), <code>list</code> becomes {3,4,8,5,2}. </li>
                    <br>
                    <b> For these core methods (in the sanity test and their respective testers), we will only be testing with valid initial states for <code>list</code>. </b>
                    <li>MyMinHeap insert: call to min-heap with some elements. Should insert the element appropriately and update <code>list</code> appropriately. </li>
                    <li>MyMinHeap getMin: call to min-heap with some elements. Should return the correct element appropriately and make no changes to <code>list</code>. </li>
                    <li>MyMinHeap remove: call to min-heap with some elements. Should return the correct element and update <code>list</code> appropriately. </li>
                    <li>MyMinHeap size: call to min-heap with some elements. Should return the correct size and make no changes to <code>list</code>. </li>
                    <li>MyMinHeap clear: call to min-heap with some elements. Should update <code>list</code> appropriately. </li>
                    <br>
                    <br>
                    <li>MyPriorityQueue no-arg constructor: intializes <code>heap</code> to be empty. </li>
                    <li>MyPriorityQueue second constructor: call with some <code>Collection</code>, initializes <code>heap</code> appropriately. </li>
                    <li>MyPriorityQueue push: call to priority queue with some elements. Should insert the element appropriately and update <code>heap</code> appropriately. </li>
                    <li>MyPriorityQueue peek: call to priority queue with some elements. Should return the correct element appropriately and make no changes to <code>heap</code>. </li>
                    <li>MyPriorityQueue pop: call to priority queue with some elements. Should return the correct element and update <code>heap</code> appropriately. </li>
                    <li>MyPriorityQueue getLength: call to priority queue with some elements. Should return the correct size and make no changes to <code>heap</code>. </li>
                    <li>MyPriorityQueue clear: call to priority queue with some elements. Should update <code>heap</code> appropriately. </li>
                </ul>
            </td>
            <td> 22 </td>
        </tr>
        <tr>
            <td>  TestMyMinHeapConstructors  </td>    
            <td>
                <ul>
                    <li>Constructor has specified behavior when inputting valid <code>Collection</code>. </li>
                    <li>Constructor has specified behavior with invalid inputs. </li>
                </ul>
            </td>
            <td> 4 </td>
        </tr>
        <tr>
            <td>  TestMyMinHeapSwap  </td>    
            <td>
                <ul>
                    <li>Swaps the elements at the argument indices. </li>
                </ul>
            </td>
            <td> 1 </td>
        </tr>
        <tr>
            <td>  TestMyMinHeapGetParentIdx  </td>    
            <td>
                <ul>
                    <li>Returns the correct parent index. </li>
                </ul>
            </td>
            <td> 2 </td>
        </tr>
        <tr>
            <td>  TestMyMinHeapGetLeftChildIdx  </td>    
            <td>
                <ul>
                    <li>Returns the correct index for the left child. </li>
                </ul>
            </td>
            <td> 2 </td>
        </tr>
        <tr>
            <td>  TestMyMinHeapGetRightChildIdx  </td>    
            <td>
                <ul>
                    <li>Returns the correct index for the right child. </li>
                </ul>
            </td>
            <td> 2 </td>
        </tr>
        <tr>
            <td>  TestMyMinHeapGetMinChildIdx  </td>    
            <td>
                <ul>
                    <li>Returns the correct index for the smaller child. </li>
                    <li>Returns the left child for equal children. </li>
                    <li>Returns the correct index when there are fewer than two children. </li>
                </ul>
            </td>
            <td> 5 </td>
        </tr>
        <tr>
            <td>  TestMyMinHeapPercolateUp  </td>    
            <td>
                <ul>
                <li>Percolates the element at <code>index</code> up until no heap properties are violated by that element. </li>
                </ul>
            </td>
            <td> 13 </td>
        </tr>
        <tr>
            <td>  TestMyMinHeapPercolateDown  </td>   
            <td>
                <ul>
                    <li>Percolates the element at <code>index</code> down until no heap properties are violated by that element. </li>
                </ul>
            </td>
            <td> 13 </td>
        </tr>
        <tr>
            <td>  TestMyMinHeapDeleteIndex  </td>    
            <td>
                <ul>
                    <li>Removes and returns the element at <code>index</code> as specified above. </li>
                </ul>
            </td>
            <td> 4 </td>
        </tr>
        <tr>
            <td>  TestMyMinHeapInsert  </td>    
            <td>
                <ul>
                    <li>Inserts the element as specified above. </li>
                    <li>Has the correct behavior when attempting to insert <code>null</code>. </li>
                </ul>
            </td>
            <td> 3 </td>
        </tr>
        <tr>
            <td>  TestMyMinHeapGetMin  </td>    
            <td>
                <ul>
                    <li>Returns the appropriate element as specified above. </li>
                    <li>Has the correct behavior for empty heaps. </li>
                </ul>
            </td>
            <td> 3 </td>
        </tr>
        <tr>
            <td>  TestMyMinHeapRemove  </td>    
            <td>
                <ul>
                    <li>Removes and returns the appropriate element as specified above. </li>
                    <li>Has the correct behavior for empty heaps. </li>
                </ul>
            </td>
            <td> 4 </td>
        </tr>
        <tr>
            <td>  TestMyMinHeapSize  </td>    
            <td>
                <ul>
                    <li>Returns the size of the heap. </li>
                </ul>
            </td>
            <td> 1 </td>
        </tr>
        <tr>
            <td>  TestMyMinHeapClear  </td>    
            <td>
                <ul>
                    <li>Clears the heap. </li>
                </ul>
            </td>
            <td> 1 </td>
        </tr>
        <tr>
            <td>  TestMyPriorityQueueConstructors  </td>    
            <td>
                <ul>
                    <li>Constructor has specified behavior when inputting valid <code>Collection</code>. </li>
                    <li>Constructor has specified behavior with invalid inputs. </li>
                </ul>
            </td>
            <td> 1 </td>
        </tr>
        <tr>
            <td>  TestMyPriorityQueuePush  </td>    
            <td>
                <ul>
                    <li>Pushes the element as specified above. </li>
                    <li>Has the correct behavior when attempting to push <code>null</code>. </li>
                </ul>
            </td>
            <td> 2 </td>
        </tr>
        <tr>
            <td>  TestMyPriorityQueuePeek  </td>    
            <td>
                <ul>
                    <li>Returns the appropriate element as specified above. </li>
                    <li>Has the correct behavior for empty priority queues. </li>
                </ul>
            </td>
            <td> 2 </td>
        </tr>
        <tr>
            <td>  TestMyPriorityQueuePop  </td>    
            <td>
                <ul>
                    <li>Pops and returns the appropriate element as specified above. </li>
                    <li>Has the correct behavior for empty priority queues. </li>
                </ul>
            </td>
            <td> 2 </td>
        </tr>
        <tr>
            <td>  TestMyPriorityQueueGetLength  </td>    
            <td>
                <ul>
                    <li>Returns the size of the priority queue. </li>
                </ul>
            </td>
            <td> 1 </td>
        </tr>
        <tr>
            <td>  TestMyPriorityQueueClear  </td>    
            <td>
                <ul>
                    <li>Clears the priority queue. </li>
                </ul> 
            </td>
            <td> 1 </td>
        </tr>
    </tbody>
</table>


## Surveys
**Weekly Reflection Survey**

You can find the weekly reflection survey [here](https://docs.google.com/forms/d/e/1FAIpQLScUtbIcwDNkUWW4J3qFrTSACku3tVlTTvEcyzZ-2a6wzBuXOQ/viewform).
Please fill out the survey, it will count towards **1 point** of your PA6 score. 



## Submission

**Turning in your code**

Look through your files and make sure you've included your name, ID, and cs12 account at the top of each  of them. Submit all of the following files to Gradescope by **Monday, May 11 @ 11:59PM PDT**  (Tuesday, May 12 @ 11:59pm PDT w/ slip day):
- MyMinHeap.java
- MyPriorityQueue.java
- MyMinHeapTester.java
- MyPriorityQueueTester.java

**Important:** Even if your code does not pass all the tests, you will still be able to submit your homework to receive partial credit for the tests that you passed. ⚠️ Make sure your code compiles in order to receive partial credit. ⚠️

**How your assignment will be evaluated**
* **Coding Style** (10 points)
We will grade your code style in `MyMinHeap.java` and `MyPriorityQueue.java` thoroughly (the tester files will not be graded for style). Namely, there are a few things you must have in each file / class / method:

1. File headers
2. Class headers
3. Method headers
4. Inline comments
5. Proper indentation (do not intermingle spaces and tabs for indentation)
6. Descriptive variable names
7. No magic numbers
8. Reasonably short methods (if you have implemented each method according to specification in this write-up, you’re fine). This is not enforced as strictly.
9. Lines shorter than 80 characters (note, tabs will be counted as 4 characters toward this limit. It is a good idea to set your tab width/size to be 4)
10. Javadoc conventions (@param, @return tags, /** comments */, etc.)

A full [style guide](https://sites.google.com/view/cse12spr20/style-guide) can be found here. If you need any clarifications, feel free to ask on Piazza.
- **Weekly Reflection Survey** (1 point)
- **Correctness** (89 points)
    - You will earn points based on the autograder tests that your code passes. If the autograder tests are not able to run (e.g., your code does not compile or it does not match the naming and visibility specifications in this writeup), you will not earn credit. 
    - Although we have provided you with a few test cases in `MyMinHeapTester.java` and in the [Testing section](#testing), it is your job to thoroughly test your code based on the specifications in this writeup. 