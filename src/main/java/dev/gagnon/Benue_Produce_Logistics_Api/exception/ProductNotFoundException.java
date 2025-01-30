package dev.gagnon.Benue_Produce_Logistics_Api.exception;

public class ProductNotFoundException extends BdicBaseException{
    public ProductNotFoundException(String message) {
        super(message);
    }
}
