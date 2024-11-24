package LLD.library.management;

import LLD.library.management.enums.AccountStatus;

public class Account {
  private int accountId;
  private String username;
  private String password;
  private AccountStatus accountStatus;
  private Person person;
  private LibraryCard card;
}
