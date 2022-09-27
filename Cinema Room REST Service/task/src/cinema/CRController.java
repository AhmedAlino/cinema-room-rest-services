package cinema;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CRController {

    private ICinemaRoomService cinemaRoomService;

    @Autowired
    public CRController(ICinemaRoomService cinemaRoomService) {
        this.cinemaRoomService = cinemaRoomService;
    }

    @GetMapping(path = "/seats")
    public CinemaRoom getCinema() {
        return cinemaRoomService.getCinema();
    }

    @PostMapping(value = "/purchase")
    public ResponseEntity<ResponseTicket> bookSeat(@RequestBody Seat seat) {
        return cinemaRoomService.bookSeat(seat);
    }

    @PostMapping(path = "/return")
    public ResponseEntity<ResponseTicket> returnedTicket(@RequestBody Token token) {
        return cinemaRoomService.returnedTicket(token);
    }

    @PostMapping("/stats")
    public ResponseEntity<Statistics> getStats(@RequestParam(value = "password", required = false) String password) {
        return cinemaRoomService.getStats(password);
    }
}
