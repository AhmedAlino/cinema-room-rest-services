package cinema;

import org.springframework.http.ResponseEntity;

public interface ICinemaRoomService {

    /**
     * provide all the availble seats in the cinema room
     * @return CinemaRoom
     */
    CinemaRoom getCinema();

    /**
     * provide the response from the request
     * @param seat seat requested
     * @return
     */
    ResponseEntity<ResponseTicket> bookSeat(Seat seat);

    /**
     *
     * @return
     */
    ResponseEntity<ResponseTicket> returnedTicket(Token token);

    /**
     * return certain stats of the cinema room
     * @param password
     * @return
     */
    ResponseEntity<Statistics> getStats(String password);
}
