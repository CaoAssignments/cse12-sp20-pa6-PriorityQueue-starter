import java.util.List;
import java.util.ArrayList;
import java.util.Collection;

public class MyMinHeap<E extends Comparable<E>>
{
    protected List<E> list;

    public MyMinHeap() {
    }

    public MyMinHeap(Collection<? extends E> collection) {
    }

    protected void swap(int from, int to) {
    }

    protected int getParentIdx(int index) {
        return -1;
    }

    protected int getLeftChildIdx(int index) {
        return -1;
    }

    protected int getRightChildIdx(int index) {
        return -1;
    }

    protected int getMinChildIdx(int index) {
        return -1;
    }

    protected void percolateUp(int index) {
    }

    protected void percolateDown(int index) {
    }
    
    protected E deleteIndex(int index) {
        return null;
    }

    public void insert(E element) {
    }

    public E getMin() {
        return null;
    }

    public E remove() {
        return null;
    }

    public int size() {
        return -1;
    }

    public void clear() {
    }		
}
