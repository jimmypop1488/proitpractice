package com.jimmypop.proitpractice.services;

import com.jimmypop.proitpractice.model.dto.OrganizationDTO;
import com.jimmypop.proitpractice.model.entity.Organization;
import com.jimmypop.proitpractice.model.mappers.OrganizationMapper;
import com.jimmypop.proitpractice.repos.OrganizationRepo;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class OrganizationService {
    @Autowired
    OrganizationRepo organizationRepo;

    private OrganizationMapper organizationMapper = Mappers.getMapper(OrganizationMapper.class);

    public List<OrganizationDTO> getAll(){
        return organizationRepo.findAll().stream()
                .map(x -> organizationMapper.organizationToDTO(x))
                .collect(Collectors.toList())
                ;
    }

    public OrganizationDTO getById(UUID orgid){
        Optional<Organization> organization = organizationRepo.findById(orgid);
        if (!organization.isPresent())
            return null;
        return organizationMapper.organizationToDTO(organization.get());
    }

    public void add(OrganizationDTO dto){
        if (dto.getHeadOrgName()){
            Optional<Organization> organization = organizationRepo.findByName(dto.getHeadOrgName());
            if (!organization.isPresent())
                return;
            organizationRepo.save(organizationMapper.organizationFromDTO(dto, organization.get()));
            return;
        }
        organizationRepo.save(organizationMapper.organizationFromDTO(dto, null));
    }

    public OrganizationDTO update(UUID orgid, OrganizationDTO dto){
        Optional<Organization> organization = organizationRepo.findById(orgid);
        if (dto.getHeadOrgName()) {
            Optional<Organization> headOrganization = organizationRepo.findByName(dto.getHeadOrgName());
            if (!organization.isPresent() || !headOrganization.isPresent())
                return null;
            Organization org;
            org = organizationMapper.organizationFromDTO(dto, headOrganization.get());
            org.setId(organization.get().getId());
            organizationRepo.save(org);
            return organizationMapper.organizationToDTO(org);
        }
        if (!organization.isPresent())
            return null;
        Organization org;
        org = organizationMapper.organizationFromDTO(dto, null);
        org.setId(organization.get().getId());
        organizationRepo.save(org);
        return organizationMapper.organizationToDTO(org);
    }

    public boolean delete(UUID orgid){
        if (!organizationRepo.findById(orgid).isPresent())
            return false;
        organizationRepo.deleteById(orgid);
        return true;
    }
}
