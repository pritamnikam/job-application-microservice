package com.spring.companyms.company.impl;

import com.spring.companyms.company.Company;
import com.spring.companyms.company.CompanyRepository;
import com.spring.companyms.company.CompanyService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyRepository;
    private long nextId = 1L;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public List<Company> getAllCompanies() {
        return this.companyRepository.findAll();
    }

    @Override
    public void createCompany(Company company) {
        company.setId(nextId++);
        this.companyRepository.save(company);
    }

    @Override
    public Company getCompany(Long id) {
        return this.companyRepository.findById(id).orElse(null);
    }

    @Override
    public boolean updateCompany(Long id, Company updatedCompany) {
        Optional<Company> companyOptional = this.companyRepository.findById(id);
        if (companyOptional.isPresent()) {
            Company company = companyOptional.get();
            company.setName(updatedCompany.getName());
            company.setDescription(updatedCompany.getDescription());
            this.companyRepository.save(company);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteCompanyById(Long id) {
        try {
            this.companyRepository.deleteById(id);
            return true;
        } catch(Exception e) {
            return false;
        }
    }
}