package noiseremoving;

/**
 *
 * @author vuong
 */
public class SortArray <E extends Comparable<E>>{
    
    E[] array;
    // Constructor to initialize the array
    public SortArray(E[] array)
    {
        this.array = array;
    }
     // Method to set a new array
    public void setArray(E[] array)
    {
        this.array = array;
    }
    
    public void quickSort()
    {
        //quickSort parameter
        quickSort( 0, array.length - 1);
    }
    
    private void quickSort( int start, int end)
    {
        if( start < end){//if first index smaller than last index
           
        int partitionIndex = partition( start,  end);
        quickSort( start, partitionIndex - 1 );//start and end of our left partition
        quickSort(partitionIndex + 1, end );//the end of our array
        }
    }
    
    //this ,ethod will sort array and find the pivot
    //all elements on the left are smaller than pivot
    //all elements on the right are bigger than pivot
     private int partition( int start, int end) 
    {
        E pivot = array[end]; //take the last element as pivot
        int i = (start - 1);//i is on less than the start of the array
        
        for( int j = start; j < end; j++)
        { // if j less than pivot we increase i then swap i and j
            if( array[j].compareTo(pivot) < 0)
            {
                i++;
                swap(i, j);
            }
        }
         // Place the pivot element in its correct position
         swap(i + 1, end);
        //all elements on the left are smaller than pivot
        //all elements on the right are bigger than pivot
        
        return i + 1;
    
    }
    
    private void swap(int i, int j)// Swap elements in the array
    {
       E swap = array[i];
       array[i] = array[j];
       array[j] = swap;
    }    
}
