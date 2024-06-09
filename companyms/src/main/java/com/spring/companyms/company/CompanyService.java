package com.spring.companyms.company;

import java.util.List;

public interface CompanyService {
    List<Company> getAllCompanies();
    void createCompany(Company company);
    Company getCompany(Long id);
    boolean updateCompany(Long id, Company updatedCompany);
    boolean deleteCompanyById(Long id);
}