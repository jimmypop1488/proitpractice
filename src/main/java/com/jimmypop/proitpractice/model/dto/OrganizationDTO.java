package com.jimmypop.proitpractice.model.dto;

import lombok.*;

@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class OrganizationDTO {
    private String orgid;
    private String orgname;
    private String headorgname;
}
