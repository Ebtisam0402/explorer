import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

      @Test
    public void testPossibleMoves_allDirectionsOpen() {
        int[][] island = {
            {1, 1, 1},
            {1, 0, 1},
            {1, 1, 1},
            {1, 1, 1}
        };
        int[] location = {1, 1};
        List<int[]> moves = ExplorerSearch.possibleMoves(island, location);
        Set<String> moveSet = toSet(moves);

        assertEquals(4, moves.size());
        assertTrue(moveSet.contains("0,1")); // up
        assertTrue(moveSet.contains("2,1")); // down
        assertTrue(moveSet.contains("1,0")); // left
        assertTrue(moveSet.contains("1,2")); // right
    }

        @Test
    public void testPossibleMoves_topLeftCornerWithOneOpen() {
        int[][] island = {
            {0, 3},
            {3, 1}
        };
        int[] location = {0, 0};
        List<int[]> moves = ExplorerSearch.possibleMoves(island, location);

        assertTrue(moves.isEmpty()); // surrounded by walls/edges
    }

     @Test
    public void testPossibleMoves_rightEdgeWithOpenLeft() {
        int[][] island = {
            {1, 1, 0}
        };
        int[] location = {0, 2};
        List<int[]> moves = ExplorerSearch.possibleMoves(island, location);
        Set<String> moveSet = toSet(moves);

        assertEquals(1, moves.size());
        assertTrue(moveSet.contains("0,1"));
    }

        @Test
    public void testPossibleMoves_blockedRightByWall() {
        int[][] island = {
            {1, 1, 1},
            {1, 0, 3},
            {1, 1, 1}
        };
        int[] location = {1, 1};
        Set<String> moveSet = toSet(ExplorerSearch.possibleMoves(island, location));

        assertTrue(moveSet.contains("0,1"));  // up open
        assertTrue(moveSet.contains("2,1"));  // down open
        assertTrue(moveSet.contains("1,0"));  // left open
        assertFalse(moveSet.contains("1,2")); // right blocked
    }




     private Set<String> toSet(List<int[]> list) {
        Set<String> set = new HashSet<>();
        for (int[] arr : list) {
            set.add(arr[0] + "," + arr[1]);
        }
        return set;
    }
}
