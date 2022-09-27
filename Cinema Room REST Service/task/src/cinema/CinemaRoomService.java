package cinema;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class CinemaRoomService implements ICinemaRoomService {

    CinemaRoom cinemaRoom = new CinemaRoom(9, 9);
    StoreTicket storeTicket = new StoreTicket(cinemaRoom);

    private long numberPurchaseTicket = 0;
    private long currentIncome = 0;
    private long availableSeat = storeTicket.getBookTickets().size();

    @Override
    public CinemaRoom getCinema() {
        return cinemaRoom;
    }

    @Override
    public ResponseEntity<ResponseTicket> bookSeat(Seat seat) {

        if (seat.getRow() <= 0 || seat.getRow() > 9 || seat.getColumn() <= 0 || seat.getColumn() > 9) {
            ResponseTicket badResponse = ResponseTicket.builder()
                    .error("The number of a row or a column is out of bounds!")
                    .build();
            return new ResponseEntity<>(badResponse, HttpStatus.BAD_REQUEST);
        }

        Optional<BookTicket> ticketReturned = storeTicket.getBookTickets()
                .stream()
                .filter(bookTicket -> bookTicket.getTicket().getRow() == seat.getRow() && bookTicket.getTicket().getColumn() == seat.getColumn())
                .findFirst();

        if (ticketReturned.isEmpty()) {
            ResponseTicket badResponse = ResponseTicket.builder()
                    .error("The ticket has been already purchased!")
                    .build();
            return new ResponseEntity<>(badResponse, HttpStatus.BAD_REQUEST);
        }


        ticketReturned.ifPresent(bookTicket -> {storeTicket.delete(bookTicket);});

        currentIncome += ticketReturned.get().getTicket().getPrice();
        availableSeat--;
        numberPurchaseTicket++;

        ResponseTicket goodResponse = ResponseTicket.builder()
                .token(ticketReturned.get().getToken())
                .ticket(ticketReturned.get().getTicket())
                .build();
        return new ResponseEntity<>(goodResponse, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseTicket> returnedTicket(Token token) {
        Optional<BookTicket> ticket = storeTicket.getBookTicketsForReturn()
                .stream()
                .filter(bTicket -> Objects.equals(bTicket.getToken(), token.getToken()))
                .findFirst();

        if(ticket.isEmpty()) {
            return new ResponseEntity<>(ResponseTicket.builder().error("Wrong token!").build(), HttpStatus.BAD_REQUEST);
        }

        availableSeat++;
        currentIncome -= ticket.get().getTicket().getPrice();
        numberPurchaseTicket--;

        storeTicket.getBookTicketsForReturn().removeIf(bookTicket ->
                bookTicket.getTicket().getRow().equals(ticket.get().getTicket().getRow())
                        &&
                        bookTicket.getTicket().getColumn().equals(ticket.get().getTicket().getColumn())
        );

        return new ResponseEntity<>(
                ResponseTicket.builder()
                        .returned_ticket(ticket.get().getTicket())
                        .build(),
                HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Statistics> getStats(String password) {
        final String KNOWN_PASSWORD = "super_secret";

        if (password == null) {
            return new ResponseEntity<>(Statistics.builder().error("The password is wrong!").build(), HttpStatus.UNAUTHORIZED);
        }

        if (!password.equalsIgnoreCase(KNOWN_PASSWORD)) {
            return new ResponseEntity<>(Statistics.builder().error("The password is wrong!").build(), HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity<>(
                new Statistics(
                        currentIncome,
                        availableSeat,
                        numberPurchaseTicket,
                        null),
                HttpStatus.OK);
    }
}
