package com.emretest.entities;
import jakarta.persistence.*;
import java.math.BigDecimal;

@Table(name="wallet")
@Entity
public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private BigDecimal money;

    // Getter ve Setter metotları
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public BigDecimal getMoney() { return money; }
    public void setMoney(BigDecimal money) { this.money = money; }
}
