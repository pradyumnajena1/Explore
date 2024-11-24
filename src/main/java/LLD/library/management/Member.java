package LLD.library.management;

import LLD.library.management.enums.BookStatus;
import LLD.library.management.enums.ReservationStatus;

import java.util.Date;

public class Member extends Account{
    // Member specific attributes and methods
    private Date dateOfMembership;
    private int  totalNumberOfLoanedBooks;
    public int getTotalNumberOfLoanedBooks() {
        return totalNumberOfLoanedBooks;
    }

    public void setTotalNumberOfLoanedBooks(int i) {
        totalNumberOfLoanedBooks = i;
    }

    public boolean checkOut(BookItem bookItem) throws CheckoutException{

        if(bookItem.isReferenceOnly()){
            throw new CheckoutException("Book is References Only");
        }
        if(BookStatus.AVAILABLE!=bookItem.getStatus()){
            throw new CheckoutException("Book is not available");
        }
        if(this.getTotalNumberOfLoanedBooks()>=5){
            throw new CheckoutException("max num of loaned books reached");
        }

        // update reservation
        BookReservation bookReservation = BookReservation.fetchBookReservationDetails(bookItem);
        if(bookReservation!=null|| bookReservation.getMember()!=this){
            throw new CheckoutException("book is reserved for another member");
        }else if(bookReservation.getStatus()== ReservationStatus.PENDING){
            bookReservation.setStatus(ReservationStatus.COMPLETED);
        }


      if(!  bookItem.checkOut(this)){
         return false;
      };
        totalNumberOfLoanedBooks++;
        return true;


    }
}
