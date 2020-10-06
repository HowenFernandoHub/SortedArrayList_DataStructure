import java.util.AbstractList;

/*********
 * SortedArrayList Generic class:
 *   - Array based Data Structure that adds functionality to the standard array
 *   - Adds dynamic growing and shrinking, and sorts the contents in natural order (Smallest to Largest)
 * @author Howen Anthony Fernando
 * @param <E> extends Comparable interface
 * @extends AbstractList
 */

public class SortedArrayList<E extends Comparable<E>> extends AbstractList<E> {


    private int size;
    private int initialCapacity;
    private int capacity;
    private E[] innerArray;


    /**
     * Default constructor
     * Sets private fields size, capacity, initialCapacity and innerArray
     */
    public SortedArrayList() {

        size = 0;       // no objects in innerArr yet
        capacity = 10;
        initialCapacity = capacity;

        innerArray = (E[]) new Comparable[capacity];      // instantiating array of Objects

    }


    /**
     * Second Constructor - Sets private fields size, capacity, initialCapacity, and innerArray
     *
     * @param initCapacity - capacity and initial capacity get set to this
     */
    public SortedArrayList(int initCapacity) {

        size = 0;       // no objects in innerArr yet
        capacity = initCapacity;
        initialCapacity = capacity;
        innerArray = (E[]) new Comparable[capacity];       // instantiating array of Objects
    }


    /**
     * add - Adds Object of type E into the array in natural order
     * - First checks if it needs to allocate more space
     * - Then calls placeInArray which finds the correct spot in the array and inserts the item
     *
     * @param item - Object of generic type E that is to be added to the SortedArrayList
     * @return true if the add was successfully completed
     */
    public boolean add(E item) {

        // Checking if capacity needs to be increased
        if (size == capacity) {
            increaseCapacity(capacity);
        }

        if (placeInArray(item)) {       // This executes if it successfully places item in the innerArray
            size++;
            return true;
        }

        return false;
    }


    /**
     * placeInArray - Places each item given into the array in natural order (Smallest to Largest)
     * - Starts at the back and indexes through elements one by one until it finds the correct spot
     *
     * @param item - Object of type E that will be added into the innerArray
     * @return - true if successfully placed
     */
    private boolean placeInArray(E item) {

        if (innerArray[0] == null) {     // Checks if item is first element to be added
            innerArray[0] = item;
            //System.out.println("Added first element: " + innerArray[0]);
            return true;
        }

        for (int i = (size - 1); i >= 0; i--) {

            if (item.compareTo(innerArray[i]) <= 0) {      // executes if item is still smaller than element at i
                innerArray[i + 1] = innerArray[i];
                if (i == 0) {       // innerArray's 0th element gets item if it has already checked the last element
                    innerArray[i] = item;
                    return true;
                }
            } else if (item.compareTo(innerArray[i]) > 0) {   // executes if item is bigger than or equal to innerArr[i]
                innerArray[i + 1] = item;       // innerArr element right after the one checked gets item
                //System.out.println("Added " + (i+1) + "th element: " + innerArray[i+1]);
                return true;
            }

        }

        return false;
    }


    /**
     * capacity - returns the largest number of items that can currently be stored in the Data Struct
     *
     * @return - private field capacity
     */
    public int capacity() {
        return capacity;
    }


    /**
     * increaseCapacity - increases capacity by doubling itself
     *
     * @param cap - added to the capacity and the sum is stored back into capacity
     */
    private void increaseCapacity(int cap) {

        E[] tempArr = (E[]) new Comparable[capacity];
        System.arraycopy(innerArray, 0, tempArr, 0, cap);
        capacity = capacity + cap;
        innerArray = (E[]) new Comparable[capacity];
        System.arraycopy(tempArr, 0, innerArray, 0, cap);

    }


    /**
     * clear - Sets all elements to null and then shrinks the innerArray back to the initial capacity
     */
    public void clear() {
        innerArray = (E[]) new Comparable[initialCapacity];
        size = 0;
    }


    /**
     * get - returns element at a certain index without removing it
     * - before doing so, it must first cast the element to type E
     *
     * @param index of element to be removed
     * @return element at index
     */
    public E get(int index) {
        return innerArray[index];
    }


    /**
     * isEmpty - returns true if the size == 0
     *
     * @return
     */
    public boolean isEmpty() {
        return (size() == 0);
    }


    /**
     * remove - Returns element at given index but also removes that element
     * - Decreases size by one and then checks if the size is less than 1/4 of the capacity
     * - If so, then it calls the shrinkCapacity method which cuts capacity by half
     *
     * @param index of element to be removed
     * @return element to be removed
     */
    public E remove(int index) {
        E tempObj = innerArray[index];         // Stores the index to be removed for return statement

        // Scooting all elements over
        for (int i = index; i < size - 1; i++) {
            innerArray[i] = innerArray[i + 1];
        }

        innerArray[size - 1] = null;        // Old last element gets null;

        size--;     // decrement size

        if ((size <= capacity / 4) && (capacity > initialCapacity)) {
            shrinkCapacity();
        }

        return tempObj;
    }


    /**
     * Decreases capacity by half
     */
    private void shrinkCapacity() {
        E[] tempArr = (E[]) new Comparable[capacity / 2];
        System.arraycopy(innerArray, 0, tempArr, 0, size);
        innerArray = tempArr;
        capacity = tempArr.length;
    }


    /**
     * size - Returns number of elements stored in the Data Struct
     * @return - private field
     */
    public int size() {
        return size;
    }

}