package model;

/**
 * Class to model the line & column number of an error if a ParseException is thrown
 * @author Conor
 */
public class ErrorLocation {
    private int lineNumber;
    private int columnNumber;

    /**
     * @param lineNumber Linenumber to be set
     * @param columnNumber Columnnumber to be set
     */

    public ErrorLocation(int lineNumber, int columnNumber) {
        this.lineNumber = lineNumber;
        this.columnNumber = columnNumber;
    }

    /**
     * Returns the lineNumber of the error
     * @return lineNumber
     *
     */

    public int getLineNumber() {
        return lineNumber;
    }

    /**
     * Returns the columnNumber of the error
     * @return columnNumber
     *
     */

    public int getColumnNumber() {
        return columnNumber;
    }
}
