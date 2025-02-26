package dev.gagnon.Benue_Produce_Logistics_Api.service;

import dev.gagnon.Benue_Produce_Logistics_Api.dto.request.PaymentRequest;
import dev.gagnon.Benue_Produce_Logistics_Api.dto.response.TransferResponse;

public interface PaymentService {
    TransferResponse initiateTransfer(PaymentRequest request, String email);
}
