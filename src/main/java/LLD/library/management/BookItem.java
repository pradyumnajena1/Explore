package LLD.library.management;

import LLD.library.management.enums.BookFormat;
import LLD.library.management.enums.BookStatus;
import java.util.Date;

public class BookItem extends Book {
  private String barcode;
  private Date borrowedDate;
  private Date dueDate;
  private String price;
  private BookFormat format;
  private BookStatus status;
  private boolean isReferenceOnly;
  private Date publishedDate;
  private Date dateOfPurchase;
  private Rack placedAt;

  public boolean checkOut(Member member) throws CheckoutException {
      if(isReferenceOnly){
        return false;
      }
     if( !BookLending.lendBook(this,member)){
        return false;
    }
     borrowedDate = new Date();
     dueDate = new Date(borrowedDate.getTime() +  5 * 24 * 60 * 60 * 1000);
     return true;
  }

  public boolean isReferenceOnly() {
    return isReferenceOnly;
  }

  public BookStatus getStatus() {
    return status;
  }
}
