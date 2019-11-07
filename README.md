   A generic LinkedList class

   Parameters are checked and exceptions are thrown where 
   appropriate.

   The linked list is singly-linked, no sentinel nodes. 

   The implementation of these methods should be as efficient as possible

   a.  addFront
        receives an item to add as a parameter, and adds to the front of the list.

   b.  addEnd
        receives an item to add as a parameter, and adds to the end of the list.

   c.  removeFront
        removes a node from the front of the list.

   d.  removeEnd
        removes a node from the end of the list.

   e.  set
        receives a position and item as parameters, sets the element at this
        position, provided it is within the current size

   f.  get
        receives a position as a parameter, returns the item at this position,
        provided it is within the current size

   g.  swap
        receives two index positions as parameters, and swaps the nodes at
        these positions, provided both positions are within the current size

   h.  shift
        receives an integer as a parameter, and shifts the list forward or
        backward this number of nodes, provided it is within the current size
        
   i.  removeMatching
        receives a value of the generic type as a parameter and removes all
        occurrences of this value from the list.

   j.  erase 
        receives an index position and number of elements as parameters, and
        removes elements beginning at the index position for the number of 
        elements specified, provided the index position is within the size
        and together with the number of elements does not exceed the size

   k.  insertList
        receives a generic List (a Java List) and an index position as parameters, 
        and copies each value of the passed list into the current list starting
        at the index position, provided the index position does not exceed the size.
        For example, if list has a,b,c and another list having 1,2,3 is inserted at
        position 2, the list becomes a,b,1,2,3,c
