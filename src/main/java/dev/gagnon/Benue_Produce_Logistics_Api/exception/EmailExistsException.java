package dev.gagnon.Benue_Produce_Logistics_Api.exception;

public class EmailExistsException extends BdicBaseException{
    public EmailExistsException(String message) {
        super(message);
    }
}
