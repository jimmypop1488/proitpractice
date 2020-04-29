package com.jimmypop.proitpractice.services;

import com.jimmypop.proitpractice.model.dto.StaffDTO;
import com.jimmypop.proitpractice.model.entity.Organization;
import com.jimmypop.proitpractice.model.entity.Staff;
import com.jimmypop.proitpractice.model.mappers.StaffMapper;
import com.jimmypop.proitpractice.repos.OrganizationRepo;
import com.jimmypop.proitpractice.repos.StaffRepo;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class StaffService {
    @Autowired
    OrganizationRepo organizationRepo;

    @Autowired
    StaffRepo staffRepo;

    private StaffMapper staffMapper = Mappers.getMapper(StaffMapper.class);

    public List<StaffDTO> getAll(){
        return staffRepo.findAll().stream()
                .map(x -> staffMapper.staffToDTO(x))
                .collect(Collectors.toList())
                ;
    }

    public StaffDTO getById(UUID staffid){
        Optional<Staff> staff = staffRepo.findById(staffid);
        if (!staff.isPresent())
            return null;
        return staffMapper.staffToDTO(staff.get());
    }

    public void add(StaffDTO dto){
        Optional<Organization> organization = organizationRepo.findByName(dto.getOrgName());
        if (!organization.isPresent())
            return;
        if(dto.getHeadStaffName()){
            Optional<Staff> staff = staffRepo.findByName(dto.getHeadStaffName());
            if (!staff.isPresent())
                return;
            staffRepo.save(staffMapper.staffFromDTO(dto, organization.get(), staff.get()));
        }
        staffRepo.save(staffMapper.staffFromDTO(dto, organization.get(), null));
    }
}
