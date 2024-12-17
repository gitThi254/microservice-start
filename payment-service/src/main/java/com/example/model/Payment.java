package com.example.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Table(name="payment")
@SuperBuilder
@Entity
public class Payment extends BaseEntity{
    private String userId;
    private Long bookingId;
    private BigDecimal amount;
    private String transaction_id;
    private String payment_method;
    private String status;
}