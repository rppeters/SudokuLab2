package pkgHelper;

import static org.junit.Assert.*;

import org.junit.Test;

public class SudokuTest {
	
	private int[][] solvedPuzzle = {{5,3,4,6,7,8,9,1,2},
			{6,7,2,1,9,5,3,4,8},
			{1,9,8,3,4,2,5,6,7},
			{8,5,9,7,6,1,4,2,3},
			{4,2,6,8,5,3,7,9,1},
			{7,1,3,9,2,4,8,5,6},
			{9,6,1,5,3,7,2,8,4},
			{2,8,7,4,1,9,6,3,5},
			{3,4,5,2,8,6,1,7,9}};
	
	private int[][] partialPuzzle = {{5,3,0,0,7,8,9,1,2},
			{6,7,2,1,9,5,0,0,0},
			{1,9,0,3,4,2,5,6,7},
			{8,5,9,0,6,1,4,0,0},
			{4,2,6,8,0,3,7,9,1},
			{7,1,3,9,2,4,8,5,6},
			{0,0,1,5,0,7,2,8,4},
			{2,8,7,4,1,9,6,3,5},
			{0,0,0,0,0,6,1,7,9}};

	@Test
	public void getPuzzle_Test() throws Exception {
		
		int[][] myPuzzle = {{0,0,0,0}, {0,0,0,0}, {0,0,0,0}, {0,0,0,0}};
		Sudoku s = new Sudoku(myPuzzle);
		assertArrayEquals(s.getPuzzle(), myPuzzle);
		
		Sudoku s2 = new Sudoku(partialPuzzle);
		assertArrayEquals(s2.getPuzzle(), partialPuzzle);
		
		Sudoku s3 = new Sudoku(solvedPuzzle);
		assertArrayEquals(s3.getPuzzle(), solvedPuzzle);
	}
	
	@Test
	public void getRegion_Test() throws Exception {
		Sudoku s = new Sudoku(solvedPuzzle);
		int[] region0 = {5,3,4,6,7,2,1,9,8};
		int[] region4 = {7,6,1,8,5,3,9,2,4};
		int[] region8 = {2,8,4,6,3,5,1,7,9};
		System.out.println(s.getRegion(0));
	
		assertEquals(s.getRegion(0), region0);
		assertEquals(s.getRegion(4), region4);
		assertEquals(s.getRegion(8), region8);	
		
		assertEquals(s.getRegion(0,0), region0);
		assertEquals(s.getRegion(4,3), region4);
		assertEquals(s.getRegion(6,8), region8);
	}
	
	@Test
	public void isPartialSudoku_Test() throws Exception {
		Sudoku s = new Sudoku(partialPuzzle);
		assertTrue(s.isPartialSudoku());
		
		Sudoku s2 = new Sudoku(solvedPuzzle);
		assertFalse(s2.isPartialSudoku());
		
		int[][] regionDuplicatesPuzzle = {{1,2,3,4}, {2,3,4,1}, {3,4,1,2}, {4,1,2,3}};
		Sudoku s3 = new Sudoku(regionDuplicatesPuzzle);
		assertFalse(s3.isPartialSudoku());
		
		int[][] columnDuplicatesPuzzle = {{1,2,3,4}, {4,3,1,2}, {1,4,3,2}, {2,3,1,4}};
		Sudoku s4 = new Sudoku(columnDuplicatesPuzzle);
		assertFalse(s4.isPartialSudoku());
	}
	
	@Test
	public void isSudoku_Test() throws Exception {
		Sudoku s = new Sudoku(solvedPuzzle);
		
		assertTrue(s.isSudoku());
		
		int[][] puzzle2x2 = { {1,2,3,4}, {3,4,2,1}, {2,1,4,3}, {4,3,1,2}};
		Sudoku s2 = new Sudoku(puzzle2x2);
		assertTrue(s2.isSudoku());
		
		int[][] zeroPuzzle = { {1,0,0,4}, {3,4,2,1}, {2,1,4,3}, {4,3,1,2}};
		Sudoku s3 = new Sudoku(zeroPuzzle);
		assertFalse(s3.isSudoku());
		
		int[][] wrongPuzzle = { {1,2,4,3}, {3,4,2,1}, {2,1,4,3}, {4,3,1,2}};
		Sudoku s4 = new Sudoku(wrongPuzzle);
		assertFalse(s4.isSudoku());
	}
	
	@Test
	public void isValueValid_Test() throws Exception {
		Sudoku s = new Sudoku(partialPuzzle);
		assertTrue(s.isValidValue(3, 0, 4));
		assertFalse(s.isValidValue(8, 1, 7));
		
		int[][] puzzle2x2 = { {0,0,0,0}, {1,0,0,4}, {3,2,4,1}, {0,0,0,0}};
		Sudoku s2 = new Sudoku(puzzle2x2);
		assertTrue(s2.isValidValue(0, 0, 2));
		assertFalse(s2.isValidValue(3, 3, 1));
	}
}
