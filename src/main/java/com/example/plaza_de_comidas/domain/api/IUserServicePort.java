package com.example.plaza_de_comidas.domain.api;


import com.example.plaza_de_comidas.domain.model.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface IUserServicePort {
     User createAdminAccount(User user);
     User getRolUserById(int id);

     User findByGmail(String gmail);
}
