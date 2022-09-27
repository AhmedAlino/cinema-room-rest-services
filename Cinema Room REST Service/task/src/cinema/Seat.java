package cinema;

public class Seat {

    private int row;
    private int column;
    private int price;

    public Seat() {};

    public Seat(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public Seat(int row, int column, int price) {
        this.row = row;
        this.column = column;
        this.price = price;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getPrice() {
        return price;
    }

    public void setRowPrice() {
        if (row <= 4) price = 10;
        else price = 8;
    }

    @Override
    public String toString() {
        return "{" + "row: " + row + ", column: " + column + ", price " + price + "}";
    }
}
