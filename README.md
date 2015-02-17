# DataStructures

<b>1) Code for the following</b>

 a.  swap
        receives two index positions as parameters, and swaps the nodes at
        these positions, provided both positions are within the current size

   b.  shift
        receives an integer (positive or negative) and shifts the list this
        many positions forward (if positive) or backward (if negative).  

   c.  erase 
        receives an index position and number of elements as parameters, and
        removes elements beginning at the index position for the number of 
        elements specified, provided the index position is within the size
        and together with the number of elements does not exceed the size

   d.  insertList
        receives another MyLinkedList and an index position as parameters, and 
        moves the list from the passed list into the list at the specified
        position, provided the index position does not exceed the size.
        This should be done in constant time and the passed list should
        become empty.
   e.  main
        add code to the main method to demonstrate each of your methods
        
        
 <b> 2) Stack demonstration of Push and Pop</b>
  
 <b> 3) Splay Tree with custom methods: </b>
    A Bottom-Up Splay tree with:
    a) add
       Adds a node to the tree containing the passed integer value.  
       Rotates the node to the top.
       
   b) find
        Returns true if the value passed is in the tree.
        Rotates the node to the top if found or the last node
        accessed if not found.

   c) leafCount
        Returns the count of all of the leaves in the tree.
        This should be performed as a recursive function that
        traverses the tree and counts the leaves.

   d) treeSum
        Returns the sum of all of the values in the tree.
        This should be performed as a recursive function that
        traverses the tree and sums the values.
   
   e) toString
        Returns a string of the values of an inorder traversal of the nodes.
   
   f) printLevels
        Prints the values level-by-level, root first, then its children,
        then their children, etc.

   g) Main
        Demonstrates all of the above methods.
