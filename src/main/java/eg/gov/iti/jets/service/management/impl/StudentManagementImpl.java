package eg.gov.iti.jets.service.management.impl;

import eg.gov.iti.jets.persistence.entity.User;
import eg.gov.iti.jets.service.management.StudentManagement;
import eg.gov.iti.jets.service.management.UserManagement;
import eg.gov.iti.jets.service.model.UserAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public class StudentManagementImpl implements UserDetailsService, StudentManagement {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
