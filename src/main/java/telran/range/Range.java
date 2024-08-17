package telran.range;

import java.util.Iterator;
import java.util.function.Predicate;

import telran.range.exceptions.OutOfRangeMaxValueException;
import telran.range.exceptions.OutOfRangeMinValueException;

public class Range implements Iterable<Integer> {
private static final String ERROR_MESSAGE = "Max is lower than min.";
private int min;
private int max;
private Predicate <Integer> predicate;
private Range(int min, int max){
    this.min = min;
    this.max = max;
}

public static Range getRange(int min, int max){
    if (max <= min){
        throw new IllegalArgumentException(ERROR_MESSAGE);
    }
    return new Range(min, max);
}

void setPredicate(Predicate<Integer> predicate){
this.predicate = predicate;
}   

public void checkNumber (int number) throws OutOfRangeMaxValueException, OutOfRangeMinValueException{
    if (number > max){
        throw new OutOfRangeMaxValueException(max, number);
    }
    if (number < min) {
        throw new OutOfRangeMinValueException(min, number);
    }
    
}
@Override
public Iterator<Integer> iterator() {
return new RangeIterator();

}
private class RangeIterator implements Iterator<Integer>{
    int current = min;
   
    @Override
    public boolean hasNext() {
        return current <= max; 
           }

    @Override
    public Integer next() {
        while (hasNext()){
            if (predicate == null || predicate.test(current)) {
                return current++;
            } 
            current++;
        }
            return null;
        }
     
    }
}
