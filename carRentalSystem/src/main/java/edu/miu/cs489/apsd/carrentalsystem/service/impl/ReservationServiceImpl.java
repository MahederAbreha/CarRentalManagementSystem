package edu.miu.cs489.apsd.carrentalsystem.service.impl;

import edu.miu.cs489.apsd.carrentalsystem.dto.vehicle.request.ReservationRequest;
import edu.miu.cs489.apsd.carrentalsystem.dto.vehicle.response.ReservationResponse;
import edu.miu.cs489.apsd.carrentalsystem.model.enums.PaymentStatus;
import edu.miu.cs489.apsd.carrentalsystem.model.enums.RoleType;
import edu.miu.cs489.apsd.carrentalsystem.model.enums.VehicleStatus;
import edu.miu.cs489.apsd.carrentalsystem.model.users.Customer;
import edu.miu.cs489.apsd.carrentalsystem.model.users.Role;
import edu.miu.cs489.apsd.carrentalsystem.model.vehicle.PaymentTransaction;
import edu.miu.cs489.apsd.carrentalsystem.model.vehicle.Reservation;
import edu.miu.cs489.apsd.carrentalsystem.model.vehicle.Vehicle;
import edu.miu.cs489.apsd.carrentalsystem.repository.ReservationDAO;
import edu.miu.cs489.apsd.carrentalsystem.repository.UserDAO;
import edu.miu.cs489.apsd.carrentalsystem.repository.VehicleDAO;
import edu.miu.cs489.apsd.carrentalsystem.service.ReservationService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {

    private final ReservationDAO reservationDAO;
    private final UserDAO userDAO;
    private final VehicleDAO vehicleDAO;
    private final ModelMapper mapper;

    public ReservationServiceImpl(ReservationDAO reservationDAO, UserDAO userDAO, VehicleDAO vehicleDAO, ModelMapper mapper) {
        this.reservationDAO = reservationDAO;
        this.userDAO = userDAO;
        this.vehicleDAO = vehicleDAO;
        this.mapper = mapper;
    }
    @Override
    public ReservationResponse createReservation(ReservationRequest reservationRequest) {
        Reservation reservation = mapper.map(reservationRequest, Reservation.class);
        Customer customer = (Customer) userDAO.findUserByEmail(reservationRequest.getCustomer().getEmail())
                .orElse(mapper.map(reservationRequest.getCustomer(), Customer.class));
        customer.setRoles(List.of(new Role(null, RoleType.CUSTOMER, null)));
        reservation.setCustomer(customer);
        Vehicle vehicle = vehicleDAO.findById(reservationRequest.getVehicleId())
                .orElseThrow(() -> new RuntimeException("Vehicle with id " + reservationRequest.getVehicleId() + " is not found"));
        reservation.setVehicle(vehicle);
            PaymentTransaction paymentTransaction = new PaymentTransaction();
            paymentTransaction.setPaymentStatus(PaymentStatus.PAID);
            double totalPrice = calculateTotalPrice(reservation.getVehicle().getRate(), reservation.getPickupDate(), reservation.getReturnDate());
            paymentTransaction.setAmount(totalPrice);
            paymentTransaction.setPaymentDate(LocalDate.now());
            paymentTransaction.setPaymentType(reservationRequest.getPaymentType());
            paymentTransaction.setCustomer(customer);
            reservation.getVehicle().setVehicleStatus(VehicleStatus.RESERVED);
            reservation.setPayment(paymentTransaction);

        Reservation savedReservation = reservationDAO.save(reservation);
        return mapper.map(savedReservation, ReservationResponse.class);
    }
    private double calculateTotalPrice(double rate, LocalDate pickupDate, LocalDate returnDate){
        return rate * pickupDate.until(returnDate).getDays();
    }
    @Override
    public ReservationResponse updateReservation(Long id, ReservationRequest reservationRequest) {
        Reservation reservation = reservationDAO.findById(id)
                .orElseThrow(() -> new RuntimeException("Reservation with id " + id + " is not found"));
        reservation.setReservationDate(reservationRequest.getReservationDate());
        reservation.setPickupDate(reservationRequest.getPickupDate());
        reservation.setReturnDate(reservationRequest.getReturnDate());
        reservation.setCustomer(mapper.map(reservationRequest.getCustomer(), Customer.class));
        reservation.setVehicle(vehicleDAO.findById(reservationRequest.getVehicleId())
                .orElseThrow(() -> new RuntimeException("Vehicle with id " + reservationRequest.getVehicleId() + " is not found")));
        reservation.setPickupDate(reservationRequest.getPickupDate());
        reservation.setReturnDate(reservationRequest.getReturnDate());
        Reservation savedReservation = reservationDAO.save(reservation);
        return mapper.map(savedReservation, ReservationResponse.class);
    }

    @Override
    public ReservationResponse getReservation(Long id) {
        Reservation reservation = reservationDAO.findById(id)
                .orElseThrow(() -> new RuntimeException("Reservation with id " + id + " is not found"));
        return mapper.map(reservation, ReservationResponse.class);
    }

    @Override
    public List<ReservationResponse> getAllReservations() {
        return reservationDAO.findAll().stream().map(reservation -> mapper.map(reservation, ReservationResponse.class)).toList();
    }



    @Override
    public void deleteReservation(Long id) {
        reservationDAO.findById(id).ifPresentOrElse(reservationDAO::delete, () -> {
            throw new RuntimeException("Reservation with id " + id + " is not found");
        });

    }

    @Override
    public ReservationResponse extendReservation(Long id, LocalDate newReturnDate) {
        Reservation reservation = reservationDAO.findById(id)
                .orElseThrow(() -> new RuntimeException("Reservation with id " + id + " is not found"));
        reservation.setReturnDate(newReturnDate);
        double totalPrice = calculateTotalPrice(reservation.getVehicle().getRate(), reservation.getPickupDate(), reservation.getReturnDate());
        double toBePaid = totalPrice - reservation.getPayment().getAmount();
        if(toBePaid > 0){
            PaymentTransaction paymentTransaction = new PaymentTransaction();
            paymentTransaction.setPaymentStatus(PaymentStatus.PAID);
            paymentTransaction.setAmount(toBePaid);
            paymentTransaction.setPaymentDate(LocalDate.now());
            paymentTransaction.setPaymentType(reservation.getPayment().getPaymentType());
            paymentTransaction.setCustomer(reservation.getCustomer());
            reservation.setPayment(paymentTransaction);
        }
        Reservation savedReservation = reservationDAO.save(reservation);
        return mapper.map(savedReservation, ReservationResponse.class);
    }
}
