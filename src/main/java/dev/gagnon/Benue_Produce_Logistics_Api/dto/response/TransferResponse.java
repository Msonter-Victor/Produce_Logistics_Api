package dev.gagnon.Benue_Produce_Logistics_Api.dto.response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TransferResponse {
    private boolean status;
    private String message;
    private Data data;

    @Getter
    @Setter
    public static class Data {
        private String authorizationUrl;
        private String credoReference;
        private String reference;
    }
}
