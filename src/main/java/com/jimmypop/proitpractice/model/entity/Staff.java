package com.jimmypop.proitpractice.model.entity;

import lombok.*;
import javax.persistence.*;
import java.util.UUID;

@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@RequiredArgsConstructor
@Entity(name="staff")
public class Staff {
    @Id
    @GeneratedValue
    @Column(name = "staffid")
    private UUID staffid;

    @NonNull
    @Column(name = "staffname", nullable = false)
    @OrderBy
    private String staffname;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "stafforgid", nullable = false)
    private Organization organization;

    @ManyToOne
    @JoinColumn(name = "headstaffid")
    private Staff staff;

    public Staff(String staffname, Organization organization, Staff staff){
        this.staffname = staffname;
        this.organization = organization;
        this.staff = staff;
    }
}
