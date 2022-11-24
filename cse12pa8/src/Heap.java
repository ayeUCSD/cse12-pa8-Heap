import java.util.List;
import java.util.NoSuchElementException;
import java.util.ArrayList;
import java.util.Comparator;

public class Heap<K, V> {
    public List<Entry<K, V>> entries;
    public Comparator<K> comparator;

    public Heap(Comparator<K> c){
        this.comparator = c;
        entries = new ArrayList<>();
    }

    
    public void add(K k, V v){
        entries.add(new Entry<K,V>(k, v));
        bubbleUp(entries.size()-1);

    }
    
    public Entry<K, V> poll(){
        swap(0,size()-1);
        Entry<K,V> output = entries.remove(size()-1);
        bubbleDown(0);
        return output;
    }

    //TODO
    public Entry<K, V> peek() throws NoSuchElementException{
        if(size() == 0){throw new NoSuchElementException("Size was zero");}
        return entries.get(0);
    }

    public List<Entry<K,V>> toArray(){
        return entries;
    }

    public boolean isEmpty(){
        return entries.isEmpty();
    }

    //returns parent index
    public int parent(int index){
        //parent = 5
        //left child = 11 ==>> 11 - 1) /2 == 5
        //right = 12 ==>>> 11/2 = 5.5 = 5
        return (index-1) /2;
    }
    
    //returns left child index
    public int left(int index){
        return index*2 +1;
    }
    
    //returns right child index
    public int right(int index){
        return index*2 +2;
    }
    
    public void swap(int i1, int i2){
        Entry<K,V> e = entries.get(i1); // store i1
        entries.set(i1,entries.get(i2));//set i1 to i2
        entries.set(i2, e); //set i2 to i1
    }

    public void bubbleUp(int index){
        int parentIndex = parent(index);
        //if parent is more priority than me
        if(comparator.compare(entries.get(parentIndex).getKey(), entries.get(index).getKey()) < 0){
            swap(parentIndex, index);
            bubbleUp(parentIndex);
        }
    }   

    public void bubbleDown(int index){
        int lChild = left(index);
        int rChild = right(index);
        if(existsAndGreater(lChild, rChild)){
            swap(index,lChild);
        }
        else if(existsAndGreater(rChild, lChild)){
            swap(index, rChild);
        }


    }
    
    public boolean existsAndGreater(int index1, int index2){
        try{
            Entry<K,V> e1 = entries.get(index1);
            Entry<K,V> e2 = entries.get(index2);
            return ( comparator.compare(e1.getKey(), e2.getKey()) < 0 );

        }catch(Exception E){
            return false;
        }

    }
    
    public int size(){
        return entries.size();
    }

    public String toString(){
        String output = "";
        for(Entry<K,V> e : entries){
            output += "[" + e.toString() +"] ";
        }
        return output;
    }
}
