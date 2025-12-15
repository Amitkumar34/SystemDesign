package LLD.Chess;

public class Cell {
    private char xPos, yPos;
    private PieceType pieceType;
    private Color pieceColor;

    public Cell(char xPos, char yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public Cell(int xPos, int yPos) {
        this.xPos = (char) ('a' + xPos);
        this.yPos = (char) ('1' + yPos);
    }

    public char getXPos() {
        return xPos;
    }

    public char getYPos() {
        return yPos;
    }

    public PieceType getPieceType() {
        return pieceType;
    }

    public Color getPieceColor() {
        return pieceColor;
    }

    public void setPieceType(PieceType pieceType, Color pieceColor) {
        this.pieceType = pieceType;
        this.pieceColor = pieceColor;
    }

    @Override
    public String toString() {
        if (pieceColor == null || pieceType == null) return " __ ";
        return " " + pieceColor.name().charAt(0) + pieceType + " ";
    }
}
