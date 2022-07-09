package cinema;

public class Seat {
    private int row;
    private int column;

    private final int price;

    //private final int seatID;
    public Seat(int row, int column) {
        this.row = row;
        this.column = column;
        price = (row <= 4) ? 10 : 8;
        //seatID = row * 10 + column;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Seat seat = (Seat) o;

        if (row != seat.row) return false;
        return column == seat.column;
    }
    /*public int getSeatID() {
        return seatID;
    }*/
}
