package edu.miu.cs489.apsd.carrentalsystem.dto.customDTOs.request;

import edu.miu.cs489.apsd.carrentalsystem.model.enums.RentalStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentalReservationReq {
        Long rentalId;
        Double rentalPrice;
        RentalStatus status;

}
