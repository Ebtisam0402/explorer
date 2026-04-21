import static org.junit.Assert.*;
import org.junit.Test;

public class ExplorerSearchTest {
    @Test
    public void testReachableArea_someUnreachable() {
        int[][] island = {
            {1,1,1,3,1,1},
            {3,2,3,1,3,1},
            {1,1,1,1,3,3},
            {3,1,2,1,0,1},
            {1,1,1,2,1,1},
        };
        int actual = ExplorerSearch.reachableArea(island);
        assertEquals(14, actual);
    }

    @Test
    public void testReachableArea_mostUnreachable() {
        int[][] island = {
            {2,2,3,3,3,3},
            {3,2,3,1,3,1},
            {3,1,3,0,3,3},
            {3,1,2,2,3,1},
            {1,3,3,2,3,2},
        };
        int actual = ExplorerSearch.reachableArea(island);
        assertEquals(2, actual);
    }
    // Add more tests here!
    // Come up with varied cases
    @Test
    public void testExplorerLocation_topLeftCorner(){
        int[][] island = {
            {0,1,1,2},
            {1,1,1,3},
            {1,1,1,1}
        };
        int[] expected = {0,0};
        assertArrayEquals(expected,ExplorerSearch.explorerLocation(island));
    }

        @Test
    public void testExplorerLocation_middle(){
        int[][] island = {
            {1,1,1,2,2},
            {1,1,1,3,1},
            {1,1,0,1,1},
            {1,1,1,1,1}
        };
        int[] expected = {2,2};
        assertArrayEquals(expected,ExplorerSearch.explorerLocation(island));
    }

       @Test
    public void testExplorerLocation_notFound_throwsException() {
        int[][] island = {
            {3, 2, 2,3},
            {3, 2, 2,3},
            {3, 2, 2,3}
        };
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            ExplorerSearch.explorerLocation(island);
        });
        assertEquals("No land area to explore", exception.getMessage());
    }
}
