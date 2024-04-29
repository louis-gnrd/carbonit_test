import org.junit.Test;

import static com.treasuremap.model.adventurer.DirectionEnum.*;
import static org.junit.Assert.assertEquals;

public class DirectionEnumTest {

    @Test
    public void testNext(){
        assertEquals(EAST, NORTH.next());
        assertEquals(SOUTH, EAST.next());
        assertEquals(WEST, SOUTH.next());
        assertEquals(NORTH, WEST.next());
    }

    @Test
    public void testPrevious(){
        assertEquals(WEST, NORTH.previous());
        assertEquals(SOUTH, WEST.previous());
        assertEquals(EAST, SOUTH.previous());
        assertEquals(NORTH, EAST.previous());
    }
}
