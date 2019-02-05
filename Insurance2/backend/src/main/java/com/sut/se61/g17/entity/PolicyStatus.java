package com.sut.se61.g17.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@Entity
public class PolicyStatus {
    @Id
    @SequenceGenerator(name = "policy_status_seq",sequenceName = "policy_status_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "policy_status_seq")
    @Column(name = "POLICYSTATUS_ID")
    private Long policyStatusID;

    @NotNull
    private String policyStatusName;

    @OneToMany(mappedBy = "policystatus")
    private Set<PolicyStatus> policystatus;
}
