package com.learn.firstjobapp.company;

import java.util.List;

public interface CompanyService {
    List<Company> getAllCompanies();

    void createCompany(Company company);

    Company getCompanyById(long id);

    boolean deleteCompanyById(long id);

    boolean updateCompany(long id, Company updatedCompany);
}
