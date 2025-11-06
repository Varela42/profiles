package com.mx.marketplace.care.profiles.service.impl;

import com.mx.marketplace.care.profiles.dto.HomeDTO;
import com.mx.marketplace.care.profiles.model.entity.*;
import com.mx.marketplace.care.profiles.repository.*;
import com.mx.marketplace.care.profiles.service.HomeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class HomeServiceImpl implements HomeService {

    private UserRepository userRepository;

    private UserCarreraRepository userCarreraRepository;

    private UserSemestreRepository userSemestreRepository;

    private UserMateriasRepository userMateriasRepository;

    private RoleRepository roleRepository;

    @Override
    public HomeDTO getHomeData(Long userId) {
        User user = userRepository.findById(userId).orElseThrow();
        Role role = roleRepository.findById(Long.parseLong(user.getRole())).orElseThrow();

        if(role.getId() == 1){
            return fillAdminData(user, role);
        }
        return new HomeDTO();
    }

    private HomeDTO fillAdminData(User user, Role role){
        HomeDTO homeDTO = new HomeDTO();
        homeDTO.setName(user.getUserData().getName());
        homeDTO.setLastName(composeLastName(user.getUserData()));
        homeDTO.setRole(role.getName());
        homeDTO.setPermissions(new ArrayList<>());
        for(RolePermission rolePermission : role.getPermissions()){
            homeDTO.getPermissions().add(rolePermission.getAuthority());
        }
        return homeDTO;
    }

    private String composeLastName(UserData userData){
        return userData.getLastName() + " " + userData.getSecondLastName();
    }
}
