package com.sut.se61.g17.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class PropertyPolicy {
    @Id
    @SequenceGenerator(name = "propolicy_seq",sequenceName = "propolicy_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "propolicy_seq")
    @Column(name = "PRO_POLICY_ID")
    private Long proPolicyID ;

    private String proPolicyName ;

    private String detail ;

    private String proPolicyprice ;

    public PropertyPolicy(String proPolicyName, String detail, String proPolicyprice) {
        this.proPolicyName = proPolicyName;
        this.detail = detail;
        this.proPolicyprice = proPolicyprice;
    }
}
