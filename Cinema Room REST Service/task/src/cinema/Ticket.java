package cinema;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonRootName("returned_ticket")
@Builder
public class Ticket {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer row;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer column;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer price;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String error;
}
