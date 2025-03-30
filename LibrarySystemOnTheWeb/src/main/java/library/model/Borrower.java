package library.model;
import java.sql.Date;


/**
 *
 * @author Dylan Jennings
 */
public class Borrower {
    private int borrowerId;
    private String name;
    private String email;
    private String phone;
    private Date membershipDate; // maybe use java.sql.Date?

    // Constructors
    public Borrower() {
    }

    public Borrower(int borrowerId, String name, String email, String phone, Date membershipDate) {
        this.borrowerId = borrowerId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.membershipDate = membershipDate;
    }

    // Setters and getters
    public int getBorrowerId() {
        return borrowerId;
    }
    public void setBorrowerId(int borrowerId) {
        this.borrowerId = borrowerId;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getMembershipDate() {
        return membershipDate;
    }
    public void setMembershipDate(java.sql.Date membershipDate) {
        this.membershipDate = membershipDate;
    }
    
    
    
}
