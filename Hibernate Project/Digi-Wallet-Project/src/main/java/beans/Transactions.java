package beans;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "Transactions")
public class Transactions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wallet_id", nullable = false)
    private Wallet wallet;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @NotNull(message = "Transaction amount cannot be null")
    @DecimalMin(value = "0.01", message = "Transaction amount must be greater than 0")
    private Double amount;

    @NotBlank(message = "Transaction type cannot be blank")
    @Pattern(regexp = "Credit|Debit", message = "Transaction type must be either 'Credit' or 'Debit'")
    private String type;

    @Size(max = 255, message = "Description cannot exceed 255 characters")
    private String description;

    @NotNull(message = "Transaction date cannot be null")
    @PastOrPresent(message = "Transaction date must be in the past or present")
    private Date date;

    @NotBlank(message = "Transaction recipient/payee cannot be blank")
    @Size(max = 100, message = "MadeTo field cannot exceed 100 characters")
    private String madeTo;

    public String getMadeTo() {
        return madeTo;
    }

    public void setMadeTo(String madeTo) {
        this.madeTo = madeTo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return String.format("Transaction ID: %d\n" +
                        "Amount: %.2f\n" +
                        "Type: %s\n" +
                        "Description: %s\n" +
                        "Date: %s\n" +
                        "Made To: %s\n" +
                        "Category: %s\n" +
                        "Wallet ID: %d\n",
                id, amount, type, description,
                sdf.format(date), madeTo,
                category != null ? category.getName() : "N/A",
                wallet != null ? wallet.getId() : "N/A");
    }
}
