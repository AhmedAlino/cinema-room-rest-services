package cinema;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseTicket {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String error;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private UUID token;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer row;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer column;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer price;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Ticket ticket;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Ticket returned_ticket;
}
