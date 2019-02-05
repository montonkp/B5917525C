package com.sut.se61.g17.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
public class ClassPolicy {

    @Id
    @SequenceGenerator(name = "classpolicy_seq",sequenceName = "classpolicy_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "classpolicy_seq")
    @Column(name = "CLASS_ID")
    private @NotNull Long classID ;

    private String className;

    public ClassPolicy(String className) {
        this.className = className;
    }
}
