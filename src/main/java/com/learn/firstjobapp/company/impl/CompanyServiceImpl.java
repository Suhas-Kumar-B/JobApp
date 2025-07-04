package com.learn.firstjobapp.company.impl;

import com.learn.firstjobapp.company.Company;
import com.learn.firstjobapp.company.CompanyRepository;
import com.learn.firstjobapp.company.CompanyService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public void createCompany(Company company) {
        companyRepository.save(company);
    }

    @Override
    public Company getCompanyById(long id) {
        return companyRepository.findById(id).orElse(null);
    }

    @Override
    public boolean deleteCompanyById(long id) {
        if (companyRepository.existsById(id)) {
            companyRepository.deleteById(id);
            return true;
        }
        return false;
    }


    @Override
    public boolean updateCompany(long id, Company updatedCompany) {
        Optional<Company> optionalCompany=companyRepository.findById(id);
        if (optionalCompany.isPresent()){
            Company company=optionalCompany.get();
            company.setName(updatedCompany.getName());
            company.setDescription(updatedCompany.getDescription());
            company.setJobs(updatedCompany.getJobs());
            company.setReviews(updatedCompany.getReviews());
            companyRepository.save(company);
            return true;
        }
        return false;
    }
}
