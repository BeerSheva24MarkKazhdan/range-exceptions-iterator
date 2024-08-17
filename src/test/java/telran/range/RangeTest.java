package telran.range;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

import java.util.Iterator;

import org.junit.jupiter.api.Test;

import telran.range.exceptions.OutOfRangeMaxValueException;
import telran.range.exceptions.OutOfRangeMinValueException;

public class RangeTest {
    private static final int MIN = 50;
    private static final int MAX = 100;
    Range range = Range.getRange(MIN, MAX);
@Test
void wrongRangeCreatingTest (){
    assertThrowsExactly(IllegalArgumentException.class, () -> Range.getRange(MAX, MIN));
}
@Test
void rightNumberTest() throws Exception{
    range.checkNumber(55);
}
@Test
void wrongNumberTest() throws Exception{
assertThrowsExactly(OutOfRangeMaxValueException.class, () -> range.checkNumber(MAX + 1)); 
assertThrowsExactly(OutOfRangeMinValueException.class, () -> range.checkNumber(MIN-1));
}
@Test
void iteratorTest(){
Range rangIt = Range.getRange(0,2);
rangIt.setPredicate(null);
Iterator<Integer> it = rangIt.iterator();
Integer[] expected = {0, 1, 2};
Integer[] actual = new Integer[expected.length];
int index = 0;
while(it.hasNext()){
actual[index++] = it.next();
}
assertArrayEquals(expected, actual);
}

@Test 
void iteratorTestEven(){
    Range rangIt = Range.getRange(0,10);
    rangIt.setPredicate(x -> x%2 == 0);
    Iterator<Integer> it = rangIt.iterator();
    Integer[] expected = {0, 2, 4, 6, 8, 10};
    Integer[] actual = new Integer[expected.length];
    int index = 0;
    while(it.hasNext()){
    actual[index++] = it.next();
    }
    assertArrayEquals(expected, actual);
}

@Test 
void iteratorTestOdd(){
    Range rangIt = Range.getRange(0,10);
    rangIt.setPredicate(x -> x%2 != 0);
    Iterator<Integer> it = rangIt.iterator();
    Integer[] expected = {1, 3, 5, 7, 9};
    Integer[] actual = new Integer[expected.length];
    int index = 0;
    Integer value;
    while ((value = it.next()) != null) {
        actual[index++] = value;
    }
    assertArrayEquals(expected, actual);
}
}
