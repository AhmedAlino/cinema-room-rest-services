package cinema;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Statistics {
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private Long current_income;

        @JsonInclude(JsonInclude.Include.NON_NULL)
        private Long number_of_available_seats;

        @JsonInclude(JsonInclude.Include.NON_NULL)
        private Long number_of_purchased_tickets;

        @JsonInclude(JsonInclude.Include.NON_NULL)
        private String error;
}
