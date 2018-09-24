package pkgHelper;

import pkgHelper.LatinSquare;
import java.util.Arrays;

public class Sudoku extends LatinSquare {
	private int iSize;
	private int iSqrtSize;
	
	public Sudoku (int iSize) throws java.lang.Exception {
		
		try {
			Double tempiSize = (Double)Math.sqrt(iSize);
			if (Math.sqrt(iSize) % 1 != 0 || tempiSize.isNaN()) {
				throw new java.lang.Exception();
			} else {
				this.iSize = iSize;
				this.iSqrtSize = (int)Math.sqrt(iSize);
			}
		}
		catch (Exception e) {
			throw e;
		}
		
	}
	
	public Sudoku (int[][] puzzle) throws java.lang.Exception {
		try {
			Sudoku mySudoku = new Sudoku(puzzle.length);
		}
		catch (Exception e) {
			throw e;
		}
		
	}
	
	public int[][] getPuzzle() {
		return super.getLatinSquare();
	}
	
	public int[] getRegion(int iCol, int iRow) {
		int i = (iCol / iSqrtSize) + ((iRow / iSqrtSize)*iSqrtSize);
		return getRegion(i);
	}
	
	public int[] getRegion(int r) {
		int[] region = new int[super.getLatinSquare().length];
		int i = (r % iSqrtSize) * iSqrtSize;
		int j = (r / iSqrtSize) * iSqrtSize;
		int iMax = i + iSqrtSize;
		int jMax = j + iSqrtSize;
		int count = 0;
		
		for (; j < jMax ; j++) {
			for (i = (r % iSqrtSize) * iSqrtSize; i < iMax; i++) {
				region[count++] = super.getLatinSquare()[j][i];
			}
		}
		return region;
	}
	
	public boolean isPartialSudoku() {
		if (!super.isLatinSquare()) {
			return false;
		}
		
		for (int s = 0; s < this.getPuzzle().length; s++) {
			if (super.hasDuplicates(getRegion(s))) {
				return false;
			}
			
			if (!super.hasAllValues(getRow(0),getRegion(s))) {
				return false;
			}
		}
		
		boolean hasZero = false;
		for (int row = 0; row < this.getPuzzle().length; row++) {
			for (int col = 0; col < this.getPuzzle()[row].length; col++) {
				if (this.getPuzzle()[row][col] == 0) {
					hasZero = true;
					break;
				}
			}
		}
		if (!hasZero) {
			return false;
		}
		
		return true;
	}
	
	public boolean isSudoku() {
		if (!super.isLatinSquare()) {
			return false;
		}
		
		for (int s = 0; s <this.getPuzzle().length; s++) {
			if (super.hasDuplicates(getRegion(s))) {
				return false;
			}
			
			if (!super.hasAllValues(getRow(0),getRegion(s))) {
				return false;
			}
		}
		return true;
	}
	
	public boolean isValidValue(int iCol, int iRow, int iValue) {
		
		int[][] copyPuzzle = new int[this.getPuzzle().length][this.getPuzzle()[0].length];
		
		//copy a 2-dimension array (prevent from automatically adding value to puzzle)
		for (int row = 0; row < this.getPuzzle().length; row++) {
			for (int col = 0; col < this.getPuzzle()[row].length; col++) {
				copyPuzzle[row][col] = this.getPuzzle()[row][col];
			}
		}
		copyPuzzle[iCol][iRow] = iValue;
		
		for (int i = 0; i < copyPuzzle.length; i++) {
			if (hasDuplicates(getRow(i)))
				return false;
			if (hasDuplicates(getColumn(i)))
				return false;
		}

		return true;
	}
}
