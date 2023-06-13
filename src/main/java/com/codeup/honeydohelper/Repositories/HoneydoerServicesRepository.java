package com.codeup.honeydohelper.Repositories;

import com.codeup.honeydohelper.Models.HoneydoerServices;
import com.codeup.honeydohelper.Models.Honeydoers;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HoneydoerServicesRepository extends JpaRepository<HoneydoerServices, Integer> {
    List<HoneydoerServices> findAllByHoneydoers_Id(int honeydoerId);
    List<HoneydoerServices> findAllByServices_Id(int serviceId);
    HoneydoerServices findByServices_IdAndHoneydoers_Id(int serviceId, int honeydoerId);

}
