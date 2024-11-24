package LLD.carrental;

import LLD.library.management.enums.ReservationStatus;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class VehicleReservation {
  private String reservationNumber;
  private ReservationStatus status;
  private Vehicle vehicle;
  private Account account;
  private Date creationDate;
  private Date dueDate;
  private Date returnDate;
  private String pickupAddress;
  private String returnAddress;

  private List<Notifications> notifications = new ArrayList<Notifications>();
  private List<Service> services = new ArrayList<Service>();
  private List<Equipment> equipments = new ArrayList<Equipment>();
  private List<RentalInsurance> insurances = new ArrayList<RentalInsurance>();

  public VehicleReservation(
      String reservationNumber,
      ReservationStatus status,
      Vehicle vehicle,
      Account account,
      Date creationDate,
      Date dueDate) {
    this.reservationNumber = reservationNumber;
    this.status = status;
    this.vehicle = vehicle;
    this.account = account;
    this.creationDate = creationDate;
    this.dueDate = dueDate;
  }
}
