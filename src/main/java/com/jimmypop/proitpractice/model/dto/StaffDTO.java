package com.jimmypop.proitpractice.model.dto;

import lombok.*;

@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class StaffDTO {
    private String staffid;
    private String staffname;
    private String orgname;
    private String headstaffname;
}