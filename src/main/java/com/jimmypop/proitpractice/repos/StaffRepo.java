package com.jimmypop.proitpractice.repos;

import com.jimmypop.proitpractice.model.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.*;

public interface StaffRepo extends JpaRepository<Staff, UUID> {
    Optional<Staff> findById(UUID staffid);
    Optional<Staff> findByName(String staffname);
}
