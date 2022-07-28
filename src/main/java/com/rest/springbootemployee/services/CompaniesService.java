package com.rest.springbootemployee.services;

import com.rest.springbootemployee.enities.Company;
import com.rest.springbootemployee.enities.Employee;
import com.rest.springbootemployee.exception.CompanyNotFindException;
import com.rest.springbootemployee.mapper.CompaniesRepository;
import com.rest.springbootemployee.mapper.CompanyDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompaniesService {


    @Autowired
    private CompanyDao companyDao;
    @Autowired
    private CompaniesRepository companiesRepository;
    public List<Company> queryAllCompanies() {
        return companyDao.findAll();
    }

    public Company queryCompanyById(String companyId) {
        Optional<Company> optionalCompany = companyDao.findById(companyId);
        if (optionalCompany.isPresent()) {
            return optionalCompany.get();
        }
        throw new CompanyNotFindException();
    }

    public List<Employee> queryEmployeesInCompanyById(String companyId) {

        Optional<Company> optionalCompany = companyDao.findById(companyId);
        if (optionalCompany.isPresent()) {
            return optionalCompany.get().getEmployees();
        }
        throw new CompanyNotFindException();
    }

    public List<Company> queryCompanyPage(int page, int pageSize) {
        return companiesRepository.queryCompanyPage(page, pageSize);
    }

    public Boolean insertCompany(Company company) {
        return companiesRepository.insertCompany(company);
    }

    public Boolean updateCompany(String id,Company company) {
        return companiesRepository.updateCompany(id, company);
    }

    public Boolean deleteCompanyById(String id) {
        return companiesRepository.deleteCompanyById(id);
    }
}
