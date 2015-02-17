/*
 * LinkedList class implements a doubly-linked list.
 */

import java.util.Scanner;

public class MyLinkedList<AnyType> implements Iterable<AnyType>
{
    /**
     * Construct an empty LinkedList.
     */
    public MyLinkedList( )
    {
        doClear( );
    }
    
    private void clear( )
    {
        doClear( );
    }
    
    /**
     * Change the size of this collection to zero.
     */
    public void doClear( )
    {
        beginMarker = new Node<>( null, null, null );
        endMarker = new Node<>( null, beginMarker, null );
        beginMarker.next = endMarker;
        
        theSize = 0;
        modCount++;
    }
    
    /**
     * Returns the number of items in this collection.
     * @return the number of items in this collection.
     */
    public int size( )
    {
        return theSize;
    }
    
    public boolean isEmpty( )
    {
        return size( ) == 0;
    }
    
    /**
     * Adds an item to this collection, at the end.
     * @param x any object.
     * @return true.
     */
    public boolean add( AnyType x )
    {
        add( size( ), x );   
        return true;         
    }
    
    /**
     * Adds an item to this collection, at specified position.
     * Items at or after that position are slid one position higher.
     * @param x any object.
     * @param idx position to add at.
     * @throws IndexOutOfBoundsException if idx is not between 0 and size(), inclusive.
     */
    public void add( int idx, AnyType x )
    {
        addBefore( getNode( idx, 0, size( ) ), x );
    }
    
    /**
     * Adds an item to this collection, at specified position p.
     * Items at or after that position are slid one position higher.
     * @param p Node to add before.
     * @param x any object.
     * @throws IndexOutOfBoundsException if idx is not between 0 and size(), inclusive.
     */    
    private void addBefore( Node<AnyType> p, AnyType x )
    {
        Node<AnyType> newNode = new Node<>( x, p.prev, p );
        newNode.prev.next = newNode;
        p.prev = newNode;         
        theSize++;
        modCount++;
    }   
    
    
    /**
     * Returns the item at position idx.
     * @param idx the index to search in.
     * @throws IndexOutOfBoundsException if index is out of range.
     */
    public AnyType get( int idx )
    {
        return getNode( idx ).data;
    }
        
    /**
     * Changes the item at position idx.
     * @param idx the index to change.
     * @param newVal the new value.
     * @return the old value.
     * @throws IndexOutOfBoundsException if index is out of range.
     */
    public AnyType set( int idx, AnyType newVal )
    {
        Node<AnyType> p = getNode( idx );
        AnyType oldVal = p.data;
        
        p.data = newVal;   
        return oldVal;
    }
    
    /**
     * Gets the Node at position idx, which must range from 0 to size( ) - 1.
     * @param idx index to search at.
     * @return internal node corresponding to idx.
     * @throws IndexOutOfBoundsException if idx is not between 0 and size( ) - 1, inclusive.
     */
    private Node<AnyType> getNode( int idx )
    {
        return getNode( idx, 0, size( ) - 1 );
    }

    /**
     * Gets the Node at position idx, which must range from lower to upper.
     * @param idx index to search at.
     * @param lower lowest valid index.
     * @param upper highest valid index.
     * @return internal node corresponding to idx.
     * @throws IndexOutOfBoundsException if idx is not between lower and upper, inclusive.
     */    
    private Node<AnyType> getNode( int idx, int lower, int upper )
    {
        Node<AnyType> p;
        
        if( idx < lower || idx > upper )
            throw new IndexOutOfBoundsException( "getNode index: " + idx + "; size: " + size( ) );
            
        if( idx < size( ) / 2 )
        {
            p = beginMarker.next;
            for( int i = 0; i < idx; i++ )
                p = p.next;            
        }
        else
        {
            p = endMarker;
            for( int i = size( ); i > idx; i-- )
                p = p.prev;
        } 
        
        return p;
    }
    
    /**
     * Removes an item from this collection.
     * @param idx the index of the object.
     * @return the item was removed from the collection.
     */
    public AnyType remove( int idx )
    {
        return remove( getNode( idx ) );
    }
    
    /**
     * Removes the object contained in Node p.
     * @param p the Node containing the object.
     * @return the item was removed from the collection.
     */
    private AnyType remove( Node<AnyType> p )
    {
        p.next.prev = p.prev;
        p.prev.next = p.next;
        theSize--;
        modCount++;
        
        return p.data;
    }
    
    /**
     * Returns a String representation of this collection.
     */
    public String toString( )
    {
        StringBuilder sb = new StringBuilder( "[ " );

        for( AnyType x : this )
            sb.append( x + " " );
        sb.append( "]" );

        return new String( sb );
    }

    /**
     * Obtains an Iterator object used to traverse the collection.
     * @return an iterator positioned prior to the first element.
     */
    public java.util.Iterator<AnyType> iterator( )
    {
        return new LinkedListIterator( );
    }

    /**
     * This is the implementation of the LinkedListIterator.
     * It maintains a notion of a current position and of
     * course the implicit reference to the MyLinkedList.
     */
    private class LinkedListIterator implements java.util.Iterator<AnyType>
    {
        private Node<AnyType> current = beginMarker.next;
        private int expectedModCount = modCount;
        private boolean okToRemove = false;
        
        public boolean hasNext( )
        {
            return current != endMarker;
        }
        
        public AnyType next( )
        {
            if( modCount != expectedModCount )
                throw new java.util.ConcurrentModificationException( );
            if( !hasNext( ) )
                throw new java.util.NoSuchElementException( ); 
                   
            AnyType nextItem = current.data;
            current = current.next;
            okToRemove = true;
            return nextItem;
        }
        
        public void remove( )
        {
            if( modCount != expectedModCount )
                throw new java.util.ConcurrentModificationException( );
            if( !okToRemove )
                throw new IllegalStateException( );
                
            MyLinkedList.this.remove( current.prev );
            expectedModCount++;
            okToRemove = false;       
        }
    }
    
    /**
     * This is the doubly-linked list node.
     */
    private static class Node<AnyType>
    {
        public Node( AnyType d, Node<AnyType> p, Node<AnyType> n )
        {
            data = d; prev = p; next = n;
        }
        public AnyType data;
        public Node<AnyType>   prev;
        public Node<AnyType>   next;
    }
    
    private int theSize;
    private int modCount = 0;
    private Node<AnyType> beginMarker;
    private Node<AnyType> endMarker;   
   
    public void swap(int index1, int index2){
        
        if(index1 < 0 || index1 > this.size()-1 || index2<0 || index2 > this.size() -1)
        {
            System.out.println("Provided wrong index values");
        }
        Node<AnyType> node1 = getNode(index1);
        Node<AnyType> node2 = getNode(index2);
        
        node1.prev.next = node2;
        node2.prev.next = node1;
        node2.next.prev = node1;
        node1.next.prev = node2;
        
        Node<AnyType> tmpNode =node1.next;
        node1.next = node2.next;
        node2.next = tmpNode;       
        tmpNode = node1.prev;
        node1.prev = node2.prev;
        node2.prev = tmpNode;
        
    }
    
      public void shiftList(int n){
        
             Node<AnyType> currentFirstNode = beginMarker.next;
           
        if(n>0){
            int firstElementIndex = this.size() - n ;
            Node<AnyType> futureFirstNode = getNode(firstElementIndex);
            Node<AnyType> futureLastNode = getNode(firstElementIndex -1 );
            Node<AnyType> lastNode = null;
            lastNode = futureFirstNode;
            futureLastNode.next = endMarker;
            endMarker.prev = futureLastNode;
            futureFirstNode.prev = beginMarker;
            beginMarker.next = futureFirstNode;
            lastNode.next = currentFirstNode;
            currentFirstNode.prev = lastNode;
            
        }
        if(n<0){
            int firstElementIndex = 0 - n;
            Node<AnyType> futureFirstNode = getNode(firstElementIndex);
            Node<AnyType> futureLastNode = getNode(firstElementIndex -1 );
            Node<AnyType> lastNode = endMarker.prev;
            beginMarker.next = futureFirstNode;
            futureFirstNode.prev = beginMarker;
            endMarker.prev = futureLastNode;
            futureLastNode.next = endMarker;
            lastNode.next = currentFirstNode;
            currentFirstNode.prev = lastNode;
            
        }
        
    }
    
    public void erase(int index , int numberOfElements){
        if(index < 0 || index > size()-1 || numberOfElements<=0 || 
                index + numberOfElements  -1 > size() -1){
            System.out.println("Wrong values entered for erase operation");
            return;
        }
        
        Node<AnyType> nodeBeforeIndex = getNode(index).prev;
        Node<AnyType> nodeAfterLastDeletedNode = getNode(index + numberOfElements -1).next;
        
        nodeBeforeIndex.next = nodeAfterLastDeletedNode;
        nodeAfterLastDeletedNode.prev = nodeBeforeIndex;   
    }
    
    public void insertList(int index , MyLinkedList<AnyType> listToInsert){
        
        if(index <0 || index > size()-1){
            System.out.println("Invalid index value");
            return;
        }
        Node<AnyType> nodeBeforeNewList = null;
        Node<AnyType> nodeAfterNewList = null;
        if(index ==0) {
        nodeBeforeNewList = this.beginMarker;
        nodeAfterNewList = this.getNode(index);
       
        }else if(index == this.size() -1 ){
        nodeBeforeNewList = this.endMarker.prev;
        nodeAfterNewList = this.endMarker;
       
        }else{
        nodeBeforeNewList = this.getNode(index-1); 
         nodeAfterNewList = this.getNode(index);
        }
        
        listToInsert.beginMarker.next.prev = nodeBeforeNewList;
        listToInsert.endMarker.prev.next = nodeAfterNewList;
        nodeBeforeNewList.next = listToInsert.beginMarker.next;
        nodeAfterNewList.prev = listToInsert.endMarker.prev;
        
        this.theSize += listToInsert.size();
        listToInsert.clear();
    }
   
}

class TestLinkedList
{
    public static void main( String [ ] args )
    {
        MyLinkedList<Integer> lst = new MyLinkedList<>( );
        for( int i = 0; i < 10; i++ )
                lst.add( i );
        for( int i = 20; i < 30; i++ )
                lst.add( 0, i );

        System.out.println("The list before any modification looks like : ");
        System.out.println(lst);
        
        System.out.println("Choose the operation you wish to perform :");
        System.out.println("1) Swap");
        System.out.println("2) Shift");
        System.out.println("3) Erase");
        System.out.println("4) Insert List");
        
        Scanner sc = new Scanner(System.in);
        int operation=sc.nextInt();
        
        switch (operation) {
        	case 1:
        /* a.  swap
        receives two index positions as parameters, and swaps the nodes at
        these positions, provided both positions are within the current size */
        			System.out.println("Input the nodes which need to be swapped");
        			int one=sc.nextInt();
        			int two=sc.nextInt();
        			lst.swap(one, two);
        			break;
               
        	case 2:
        			/* b.  shift
        receives an integer (positive or negative) and shifts the list this
        many positions forward (if positive) or backward (if negative). */
        			System.out.println("Input either '1' or '-1' for left shifting or right shifting respectively");
        			int three=sc.nextInt();	
        			lst.shiftList(three);    
        			break;
       
        	case 3:
        /* c.  erase 
        receives an index position and number of elements as parameters, and
        removes elements beginning at the index position for the number of 
        elements specified, provided the index position is within the size
        and together with the number of elements does not exceed the size */
        			System.out.println("Input the index and the number of elements which need to be removed at that point");
        			int four=sc.nextInt();
        			int five=sc.nextInt();
        			lst.erase(four, five);
        			break;
   
        	case 4:
        /* d.  insertList
        receives another MyLinkedList and an index position as parameters, and 
        moves the list from the passed list into the list at the specified
        position, provided the index position does not exceed the size.
        This should be done in constant time and the passed list should
        become empty. */       
        //Creation of a new list to be passed into a method
        			MyLinkedList<Integer> insertList = new MyLinkedList<>();
        		    for(int i = 0 ; i<5; i++){
        				insertList.add(0,i);
        				}
        			System.out.println("Insert the node where you want your linkedlist to be added");
        			int six=sc.nextInt();
        			lst.insertList(six, insertList);
        			break;
        			
        	default: System.out.println("Invalid Input");
            		 break;
        }
        
        //Printing out the modified list
        System.out.println("The list after the chosen operation :");
        System.out.println(lst);
    }
}
