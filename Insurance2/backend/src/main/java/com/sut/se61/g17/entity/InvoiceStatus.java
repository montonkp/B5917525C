package com.sut.se61.g17.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
public class InvoiceStatus {
    @Id
    @SequenceGenerator(name = "invoice_status_seq",sequenceName = "invoice_status_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "invoice_status_seq")
    @Column(name = "INVOICE_STATUS_ID")
    private Long id;

    private String status;

    public InvoiceStatus(String status) {
        this.status = status;
    }
}
