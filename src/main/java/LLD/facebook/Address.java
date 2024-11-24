package LLD.facebook;

public class Address {
  private String street;
  private String city;
  private String state;
  private String country;
  private String postalCode;

  public Address(String street, String city, String state, String country, String postalCode) {
    this.street = street;
    this.city = city;
    this.state = state;
    this.country = country;
    this.postalCode = postalCode;
  }
}
