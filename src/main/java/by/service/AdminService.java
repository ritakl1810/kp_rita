package by.service;

import by.model.Admin;
import by.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {
    private final AdminRepository adminRepository;

    @Autowired
    public AdminService(AdminRepository adminRepository){
        this.adminRepository = adminRepository;
    }

    public Admin findById(Integer id){
        return adminRepository.getOne(id);
    }

    public List<Admin> findAll(){
        return adminRepository.findAll();
    }

    public Admin saveAdmin(Admin admin){
        return adminRepository.save(admin);
    }

    public void deleteById(Integer id){
        adminRepository.deleteById(id);
    }

    public List<Admin> findByNameAndAccess(String name, String access){return adminRepository.findByNameAndAccess(name, access);}
}
