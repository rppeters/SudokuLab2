package pkgHelper;
import pkgHelper.LatinSquare;

public class Sudoku extends LatinSquare {
	private int iSize;
	private int iSqrtSize;
	
	public Sudoku (int iSize) throws java.lang.Exception {
		iSqrtSize = (int) Math.sqrt(iSize);
		if (iSqrtSize*iSqrtSize != iSize) {
			throw new Exception("iSize doesn't have a whole number square root.");
		}
	}
	
	public Sudoku (int[][] puzzle) throws java.lang.Exception {
		Sudoku construct = new Sudoku(puzzle.length);
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
	
	public boolean isSudoku() {
		if (!super.isLatinSquare()) {
			return false;
		}
		
		for (int s = 0; s <this.getPuzzle().length; s++) {
			if (super.hasDuplicates(getRegion(s))) {
				return false;
			}
			
			if (super.hasAllValues(getRow(0),getRegion(s))) {
				return false;
			}
		}
		return true;
	}
	
	public boolean isValidValue(int iCol, int iRow, int iValue) {
		int[][] testPuzzle = getPuzzle();
		testPuzzle[iCol][iRow] = iValue;
		
		for (int i = 0; i < testPuzzle.length; i++) {
			if (hasDuplicates(getRow(i)))
				return false;
		}

		for (int j = 0; j < testPuzzle.length; j++) {
			if (hasDuplicates(getColumn(j)))
				return false;
		}

		return true;
	}
}
