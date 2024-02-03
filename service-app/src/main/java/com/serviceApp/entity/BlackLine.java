package com.serviceApp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="black_line")
public class BlackLine {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String status;
    private String tempKey;
    private String tempRisk;
    private String preparer;
    private String tempTeam;
    private String companyCode;
    private String codeDesc;
    private String tempAccount;
    private String tempDesc;
    private String glBalance;
    private String dueData;

}
