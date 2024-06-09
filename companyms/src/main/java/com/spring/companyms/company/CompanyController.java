package com.spring.companyms.company;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {
    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public ResponseEntity<List<Company>> getAllCompanies() {
        return ResponseEntity.ok(this.companyService.getAllCompanies());
    }

    @PostMapping
    public ResponseEntity<String> createCompany(@RequestBody Company company) {
        this.companyService.createCompany(company);
        return new ResponseEntity<>("New company added successfully!", HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<Company> getCompany(@PathVariable Long id) {
        Company company = this.companyService.getCompany(id);
        if (company != null) {
            return ResponseEntity.ok(company);
        }

        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PutMapping("{id}")
    public ResponseEntity<String> updateCompany(@PathVariable Long id, @RequestBody Company updatedCompany) {
        boolean updated = this.companyService.updateCompany(id, updatedCompany);
        if (updated) {
            return ResponseEntity.ok("Company updated successfully!");
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteCompanyById(@PathVariable Long id) {
        boolean deleted = this.companyService.deleteCompanyById(id);
        if (deleted) {
            return ResponseEntity.ok("Company deleted successfully!");
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}