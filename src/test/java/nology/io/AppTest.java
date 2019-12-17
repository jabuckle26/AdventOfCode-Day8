package nology.io;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Unit test for Advent of Code: Day 8.
 */
public class AppTest
{
    @Test
    public void givenSimpleGridDimensions_calculateTotalNumberOfGridCellsAsAProperty(){
        int noRows = 2;
        int noCols = 3;
        Solution solution = new Solution( noRows, noCols, "TESTSTRING" );
        assertEquals(6, solution.getNolayerCells());
    }

    @Test
    public void givenAGridSetup_thenCreateArraysForCounting(){
        Solution solution = new Solution( 3, 3, "TESTSTRING" );
        solution.generateNewLayerIndex();
        assertEquals(1, solution.zeroesPerLayer.size());
        assertEquals(1, solution.onesPerLayer.size());
        assertEquals(1, solution.twosPerLayer.size());
    }

    @Test
    public void givenTheFirstLayerOfGrid_whenProcessingAValueThenAssignCorrectFlagsToArraysDependingOnTargetCellValue(){
        Solution solution = new Solution(2,3,"201210");
        solution.generateNewLayerIndex();
        solution.handleDataLoop();
        assertFalse(solution.filledArrayCheck.get(0));
        assertTrue(solution.filledArrayCheck.get(1));
        assertTrue(solution.filledArrayCheck.get(2));
        assertFalse(solution.filledArrayCheck.get(3));
        assertTrue(solution.filledArrayCheck.get(4));
        assertTrue(solution.filledArrayCheck.get(5));
        assertEquals(6,solution.filledArrayCheck.size());
        assertEquals(6,solution.visibleArray.size());
    }

    @Test
    public void givenAParsedDigit_thenProcessTheCountCorrectlyByUpdatingTheCorrectArray() {
        Solution solution = new Solution(2, 3, "201210");
        solution.generateNewLayerIndex();
        solution.processDigit(0);
        assertEquals(1, solution.zeroesPerLayer.size());
    }
}


