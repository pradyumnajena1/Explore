package LLD.library.management;

import LLD.library.management.enums.ReservationStatus;

import java.util.Date;

public class BookLending {
    private Date creationDate;
    private Date dueDate;
    private Date returnDate;
    private Member member;
    private BookItem book;

    public BookLending(Date creationDate, Date dueDate , Member member, BookItem book) {
        this.creationDate = creationDate;
        this.dueDate = dueDate;

        this.member = member;
        this.book = book;
    }

    private Fine fine;

    public static boolean lendBook(BookItem bookItem, Member member) {
        return false;
    }
}
