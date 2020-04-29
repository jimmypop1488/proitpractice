package com.jimmypop.proitpractice.model.mappers;

import com.jimmypop.proitpractice.model.dto.OrganizationDTO;
import com.jimmypop.proitpractice.model.entity.Organization;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface OrganizationMapper {
    @Mappings({
            @Mapping(target = "orgid", expression = "java(org.getId().toString())"),
            @Mapping(target = "orgname", source = "org.orgname"),
            @Mapping(target = "headorgname", source = "org.organization.orgname")
    })
    OrganizationDTO organizationToDTO(Organization org);

    @Mappings({
            @Mapping(target = "orgid", ignore = true),
            @Mapping(target = "orgname", source = "dto.orgname"),
            @Mapping(target = "organization", source = "org")
    })
    Organization organizationFromDTO(OrganizationDTO dto, Organization org);
}
