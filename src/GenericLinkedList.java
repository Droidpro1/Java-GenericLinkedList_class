import java.util.*;

class GenericLinkedList<T>{
    private node<T> first = null;
    private int size = 0;

    public int getSize(){
        return this.size;
    }
    public GenericLinkedList(){}
    public void addFront(T item) {
        //currently O(1)
        node<T> newFirst = new node<>(item);
        if(this.first == null){
            this.first = newFirst;
        }
        else {
            newFirst.setNext(this.first);
            this.first = newFirst;
        }
        this.size++;
    }
    public void addEnd(T item){
        //currently O(N)
        node<T> newLast = new node<>(item);
        if(this.first == null){
            this.first = newLast;
        }
        else {
            node<T> iter = this.first;
            while(iter.getNext() != null){
                iter = iter.getNext();
            }
            iter.setNext(newLast);
        }
        this.size++;
    }
    public void removeFront(){
        //currently O(1)
        if(this.first == null)
            throw new NoSuchElementException("The list is empty");
        this.first = this.first.getNext();
        this.size--;
    }
    public void removeEnd(){
        //currently O(N)
        if(this.first == null)
            throw new NoSuchElementException("The list is empty");
        node<T> iter = this.first;
        while(iter.getNext().getNext() != null){
            iter = iter.getNext();
        }
        iter.setNext(null);
        this.size--;
    }
    public void set(T item, int pos){
        //currently O(N)
        if(pos>=this.size || pos<0)
            throw new IndexOutOfBoundsException("That index doesn't exist");
        node<T> iter = this.first;
        for(int i = pos; i>0; i--){
            iter = iter.getNext();
        }
        iter.setData(item);
    }
    public T get(int pos){
        //currently O(N)
        if(pos>=this.size || pos<0)
            throw new IndexOutOfBoundsException("That index doesn't exist");
        node<T> iter = this.first;
        for(int i = pos; i>0; i--){
            iter = iter.getNext();
        }
        return iter.getData();
    }
    public void swap(int a, int b){
        //currently O(N)
        if(a>=this.size || b>=this.size || a<0 || b<0)
            throw new IndexOutOfBoundsException("That index doesn't exist");
        if(a==b)
            return;
        node<T> nodeA = null;
        node<T> nodeB = null;
        node<T> iter = this.first;
        for(int i = 0; i<this.size; i++){
            if(i==a)
                nodeA = iter;
            else if(i==b)
                nodeB = iter;
            if(nodeA != null && nodeB != null)
                break;
            else
                iter = iter.getNext();
        }
        T temp = nodeA.getData();
        nodeA.setData(nodeB.getData());
        nodeB.setData(temp);
    }
    public void shift(int shift){
        //currently O(2N)

        /*This algorithm finds the true shift
        by doing shift=shift%size, pushes the last
        shift nodes to a stack, pops them and adds
        them to the front, then deletes them from the
        end of the list. All shift integers will be
        positive because I realized my shift was coded
        in the wrong direction and its too late to change
        all of that.*/

        if(this.size == 0 || this.size == 1)
            return;
        //Force shift to be positive
        shift = shift % this.size;
        if(shift>0){
            shift = (-1)*(shift-this.size);
        }
        else if(shift<0){
            shift = shift*(-1);
        }
        else
            return;

        node<T> iter = this.first;
        node<T> newEnd = this.first;
        Stack<T> tempStack = new Stack<>();

        /*find where we want to start moving
        nodes to the stack*/
        for(int i = size-shift; i>0;i--){
            if(i==1)
                newEnd = iter;
            /*newEnd is to determine where
            the new end of the list is
            to prevent having to loop
            again*/
            iter = iter.getNext();
        }
        //push all of those nodes
        while (iter != null){
            tempStack.push(iter.getData());
            iter = iter.getNext();
        }
        //pop them to the front of the list
        while (!tempStack.isEmpty()){
            this.addFront(tempStack.pop());
        }
        this.size-=shift; //because addfront changes the size
        //but shift in general shouldnt change the size

        //delete the duplicate end nodes that were shifted
        newEnd.setNext(null);
    }
    public void removeMatching(T item){
        //currently O(N)
        boolean foundAndDeleted = false;
        if(this.size == 0)
            return;
        node<T> iter = this.first;
        if(iter.getData() == item) {
            this.removeFront();
            return;
        }
        while(iter.getNext()!=null){
            if(iter.getNext().getData()==item) {
                iter.setNext(iter.getNext().getNext());
                this.size--;
                foundAndDeleted = true;
            }
            else
                iter = iter.getNext();
        }
        if(!foundAndDeleted)
            throw new NoSuchElementException("The item is not in the list");
    }
    public void erase(int index, int numElements){
        //currently O(N)
        if(index >=size || index+numElements-1>=size)
            throw new IndexOutOfBoundsException("That index doesn't exist");
        //if index dne or index+(numElements-1) DNE
        node<T> beginning = this.first;
        //this should end BEFORE the first node to delete
        for(int i = 0; i<index-1; i++){
            beginning = beginning.getNext();
        }
        //this should end AFTER the last node to delete
        node<T> end = beginning.getNext();
        for(int i = 0; i<numElements; i++){
            end = end.getNext();
        }
        beginning.setNext(end);
        this.size-=numElements;
    }
    public void insertList(List<T> list, int index){
        //currently O(N+K) where K is num of elems in list
        if(index>=this.size || index<0)
            throw new IndexOutOfBoundsException("That index doesn't exist");
        if(list.size()==0)
            return;
        node<T> beginning = this.first;
        //this should end at the index position
        for(int i = 0; i<index; i++){
            beginning = beginning.getNext();
        }
        node<T> end = beginning.getNext();
        ListIterator<T> listIter = list.listIterator();
        while(listIter.hasNext()){
            beginning.setNext(new node<T>(listIter.next()));
            beginning = beginning.getNext();
        }
        beginning.setNext(end);
        this.size+=list.size();
    }
    public void printList(){
        //currently O(N)
        node<T> iter = this.first;
        System.out.print("[");
        while(iter != null) {
            System.out.print(iter.getData() + ",");
            iter = iter.getNext();
        }
        System.out.println("]");
    }

    private static class node<T>{
        private T data;
        private node<T> next;

        node(T item) {
            data = item;
        }
        node<T> getNext() {
            return next;
        }
        void setNext(node<T> node) {
            next = node;
        }
        T getData() {
            return data;
        }
        void setData(T item){
            data = item;
        }
    }

    public static void main(String[] args) {
        GenericLinkedList<Integer> newList = new GenericLinkedList<>();
        newList.addFront((int)(Math.random()*100));
        newList.addFront((int)(Math.random()*100));
        newList.addFront((int)(Math.random()*100));
        newList.addFront((int)(Math.random()*100));
        newList.addFront((int)(Math.random()*100));
        newList.addFront((int)(Math.random()*100));
        newList.addFront((int)(Math.random()*100));
        newList.addFront((int)(Math.random()*100));

        System.out.println("This is the list:");
        newList.printList();
        System.out.println("");

        System.out.println("Demonstrating addfront and addend");
        newList.addFront((int)(Math.random()*100));
        newList.addEnd((int)(Math.random()*100));
        newList.printList();
        System.out.println("");

        int i = (int)(Math.random()*100)-50;

        System.out.println("Shifting the list by " + i);
        newList.shift(i);
        newList.printList();
        System.out.println("");

        System.out.println("Erasing from index 3 for 2 elements including list[3]");
        newList.erase(3,2);
        newList.printList();
        System.out.println("");

        System.out.println("Demonstrating removeFront and removeEnd");
        newList.removeFront();
        newList.removeEnd();
        newList.printList();
        System.out.println("");

        System.out.println("Adding some things to the list...");
        newList.addFront((int)(Math.random()*100));
        newList.addFront((int)(Math.random()*100));
        newList.addFront((int)(Math.random()*100));
        newList.addFront((int)(Math.random()*100));
        newList.printList();
        System.out.println("");

        i = (int)(Math.random()*(newList.getSize()-1));
        System.out.println("Setting list[" + i + "] to 14");
        newList.set(14,i);
        newList.printList();
        System.out.println("");

        i = (int)(Math.random()*(newList.getSize()-1));

        System.out.println("The item at list[" + i + "] is: " + newList.get(i));
        System.out.println("");

        i = (int)(Math.random()*(newList.getSize()-1));
        int j = (int)(Math.random()*(newList.getSize()-1));

        System.out.println("Swapping list[" + i+ "] and list[" + j + "]");
        newList.swap(i,j);
        newList.printList();
        System.out.println("");

        List<Integer> ArrayList = new ArrayList<>();
        ArrayList.add((int)(Math.random()*100));
        ArrayList.add(14);
        ArrayList.add((int)(Math.random()*100));
        ArrayList.add((int)(Math.random()*100));
        ArrayList.add(14);
        ArrayList.add(14);

        i = (int)(Math.random()*(newList.getSize()-1));

        System.out.println("Inserting a newly-created ArrayList to list[" + i + "]");
        newList.insertList(ArrayList,i);
        newList.printList();
        System.out.println("");

        System.out.println("Removing all instances of 14");
        newList.removeMatching(14);
        newList.printList();
    }
}