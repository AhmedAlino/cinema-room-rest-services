package cinema;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class StoreTicket {
    private List<BookTicket> bookTickets = new ArrayList<>();
    private List<BookTicket> bookTicketsForReturn;
    private CinemaRoom cinemaRoom;

    public StoreTicket(CinemaRoom cinemaRoom) {
        this.cinemaRoom = cinemaRoom;
        buildTicket();
        bookTicketsForReturn = new ArrayList<>(bookTickets);
    }

    public List<BookTicket> getBookTickets() {
        return bookTickets;
    }

    public List<BookTicket> getBookTicketsForReturn() {
        return bookTicketsForReturn;
    }

    private void buildTicket() {
        cinemaRoom.getAvailable_seats().forEach(
                seat -> {
                    UUID uuid = UUID.randomUUID();
                    bookTickets.add(new BookTicket(uuid,
                           new Ticket( seat.getRow(), seat.getColumn(), seat.getPrice(), null))
                    );
                }
        );
    }

    public void delete(BookTicket bookTicket) {
        bookTickets.remove(bookTicket);
    }

    public void deleteReturn(BookTicket bookTicket) {
        bookTicketsForReturn.remove(bookTicket);
    }
}
