package beans;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "spending_limits")
public class SpendingLimit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "wallet_id", nullable = false)
    @NotNull(message = "Wallet cannot be null")
    private Wallet wallet;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = true)
    private Category category;

    @NotNull(message = "Limit amount cannot be null")
    @DecimalMin(value = "0.01", inclusive = true, message = "Limit amount must be greater than or equal to 0.01")
    private double limitAmount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public double getLimitAmount() {
        return limitAmount;
    }

    public void setLimitAmount(double limitAmount) {
        this.limitAmount = limitAmount;
    }

    @Override
    public String toString() {
        return "SpendingLimit{" +
                "id=" + id +
                ", wallet=" + wallet +
                ", category=" + category +
                ", limitAmount=" + limitAmount +
                '}';
    }
}
