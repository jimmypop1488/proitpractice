package com.jimmypop.proitpractice.model.entity;

import lombok.*;
import javax.persistence.*;
import java.util.UUID;

@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@RequiredArgsConstructor
@Entity(name="organization")
public class Organization {
    @Id
    @GeneratedValue
    @Column(name = "orgid")
    private UUID orgid;

    @NonNull
    @Column(name = "orgname", nullable = false, unique = true)
    @OrderBy
    private String orgname;

    @ManyToOne
    @JoinColumn(name = "headorgid")
    private Organization organization;

    public Organization(String orgname, Organization organization){
        this.orgname = orgname;
        this.organization = organization;
    }
}
