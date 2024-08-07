package telran.range;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class BrokenFloorTest {

private int getMinimalBrokenFloor(BallBrokenFloor bbf){
int startFloor = 0;
int endFloor = bbf.getMaxBuildingFloor();
while (startFloor < endFloor){
    int mid = startFloor + (endFloor - startFloor) / 2;
    try {
        bbf.checkFloor(mid);
        startFloor = mid + 1;
    } catch (Exception e) {
        endFloor = mid;
    }
}
return startFloor;
}
@Test
void minimalBrokenFloorTest(){
int[] floors = {200, 17, 1001, 2000};
for (int i = 0; i < floors.length; i++){
BallBrokenFloor bbf = new BallBrokenFloor(floors[i]);
assertEquals(bbf.getMinBrokenFloor(),getMinimalBrokenFloor(bbf));
}
}
}
