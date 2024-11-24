package LLD.library.management;

import java.util.Date;
import java.util.List;

public class BarcodeReader {
    private String id;
    private boolean active;
    private Date registerAt;

    public boolean readCard(LibraryCard card) {
        return true;
    }
}
