package util;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UtilResponse {
    private String TxnId;
    private String referenceId;
}
