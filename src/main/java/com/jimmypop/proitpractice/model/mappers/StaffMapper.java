package com.jimmypop.proitpractice.model.mappers;

import com.jimmypop.proitpractice.model.dto.StaffDTO;
import com.jimmypop.proitpractice.model.entity.Organization;
import com.jimmypop.proitpractice.model.entity.Staff;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface StaffMapper {
    @Mappings({
            @Mapping(target = "staffid", expression = "java(st.getId().toString())"),
            @Mapping(target = "staffname", source = "st.staffname"),
            @Mapping(target = "orgname", source = "st.organization.orgname"),
            @Mapping(target = "headstaffname", source = "st.staff.staffname")
    })
    StaffDTO staffToDTO(Staff st);

    @Mappings({
            @Mapping(target = "staffid", ignore = true),
            @Mapping(target = "staffname", source = "dto.staffname"),
            @Mapping(target = "organization", source = "org"),
            @Mapping(target = "staff", source = "st")
    })
    Staff staffFromDTO(StaffDTO dto, Organization org, Staff st);
}
