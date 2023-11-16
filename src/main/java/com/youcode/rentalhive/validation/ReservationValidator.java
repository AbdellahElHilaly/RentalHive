package com.youcode.rentalhive.validation;

import com.youcode.rentalhive.dao.model.Reservation;
import com.youcode.rentalhive.helper.ValidationHelper;

public class ReservationValidator {

    private final ValidationHelper validationHelper = new ValidationHelper();


    public  void validate(Reservation reservation) throws IllegalArgumentException{
        validateStartDate(reservation.getStartDate());
        validateEndDate(reservation.getEndDate());
        validateQuanity(String.valueOf(reservation.getEquipment().getQuantity()));

    }

    private void validateQuanity(String quantity) {
        if(!validationHelper.isNumber(quantity)){
            throw new IllegalArgumentException("Quantity must be a number");
        }
        if( Integer.parseInt(quantity) <= 0){
            throw new IllegalArgumentException("Quantity must be greater than 0");
        }
    }

    private void validateEndDate(String endDate) {
        if (endDate == null || endDate.isEmpty()) {
            throw new IllegalArgumentException("End date is required");
        }
        if(validationHelper.isToday(endDate)){
            throw new IllegalArgumentException("End date can't be today");
        }
    }

    private void validateStartDate(String startDate) {
        if (startDate == null || startDate.isEmpty()) {
            throw new IllegalArgumentException("Start date is required");
        }
        if(validationHelper.isToday(startDate)){
            throw new IllegalArgumentException("Start date can't be today");
        }
    }
}
