package LLD.carrental;

import LLD.carrental.enums.AccountStatus;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Member extends Account {
  private String driverLicense;
  private Date expirationDate;

  private List<VehicleReservation> reservations = new ArrayList<>();

  public Member(
      int id,
      AccountStatus status,
      Person person,
      String accountName,
      String password,
      String driverLicense,
      Date expirationDate) {
    super(id, status, person, accountName, password);
    this.driverLicense = driverLicense;
    this.expirationDate = expirationDate;
  }

  public String getDriverLicense() {
    return driverLicense;
  }

  public void setDriverLicense(String driverLicense) {
    this.driverLicense = driverLicense;
  }

  public Date getExpirationDate() {
    return expirationDate;
  }

  public void setExpirationDate(Date expirationDate) {
    this.expirationDate = expirationDate;
  }

  public List<VehicleReservation> getReservations() {
    return reservations;
  }

  public void addReservation(VehicleReservation reservation) {
    reservations.add(reservation);
  }
}
