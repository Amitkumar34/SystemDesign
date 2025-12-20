package LLD.Chess;

public enum PieceType {
    PAWN,
    KING,
    QUEEN,
    BISHOP,
    KNIGHT,
    ROOK;

    @Override
    public String toString() {
        if (this == PieceType.KNIGHT) return "N";
        return String.valueOf(this.name().charAt(0));
    }
}
