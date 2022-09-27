package cinema;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CinemaRoom {
    private int total_rows;
    private int total_columns;
    private List<Seat> available_seats;

    public CinemaRoom(int total_rows, int total_columns) {
        this.total_rows = total_rows;
        this.total_columns = total_columns;

        available_seats = new ArrayList<>();
        setAvailable_seats();
    }

    private void setAvailable_seats() {
        if (total_columns == 0 && total_rows == 0) return;
        for (int i = 0; i < total_rows; i++) {
            for (int j = 0; j < total_columns; j++) {
                UUID token = UUID.randomUUID();
                Seat seat = new Seat(i + 1, j + 1);
                seat.setRowPrice();
                available_seats.add(seat);
            }
        }
    }

    public int getTotal_rows() {
        return total_rows;
    }

    public void setTotal_rows(int total_rows) {
        this.total_rows = total_rows;
    }

    public int getTotal_columns() {
        return total_columns;
    }

    public void setTotal_columns(int total_columns) {
        this.total_columns = total_columns;
    }

    public List<Seat> getAvailable_seats() {
        return available_seats;
    }

    @Override
    public String toString() {
        return "CinemaRoom{" +
                "total_rows=" + total_rows +
                ", total_columns=" + total_columns +
                ", available_seats=" + available_seats +
                '}';
    }
}
