package com.jimmypop.proitpractice.repos;

import com.jimmypop.proitpractice.model.entity.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.*;

public interface OrganizationRepo extends JpaRepository<Organization, UUID> {
    Optional<Organization> findById(UUID orgid);
    Optional<Organization> findByName(String orgname);
}
