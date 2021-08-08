package com.revature.registry.service;

import java.util.List;
import java.util.Optional;

import com.revature.registry.model.Organization;
import com.revature.registry.repository.OrganizationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrganizationService {

    private OrganizationRepository oRepo;

    public OrganizationService() {

    }

    @Autowired
    public OrganizationService(OrganizationRepository oRepo) {
        this.oRepo = oRepo;
    }

    public List<Organization> getAllOrganizations() {
        return oRepo.findAll();

    }

    public Organization getOrganizationById(int id) {
        return oRepo.findById(id).orElse(null);
    }

    public Organization registerOrganization(Organization o) {
        try {
            return oRepo.save(o);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Organization updateOrganizationById(int id, Organization newOrg) {
        Optional<Organization> organization = oRepo.findById(id);
        if (organization.isPresent()) {
            newOrg.setId(id);
            try {
                return oRepo.save(newOrg);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    // noContent() for ResponseEntity
    public Boolean deleteOrganizationById(int id) {
        Optional<Organization> org = oRepo.findById(id);
        if (org.isPresent()) {
            oRepo.deleteById(id);
            return true;
        }
        return false;
    }
}
