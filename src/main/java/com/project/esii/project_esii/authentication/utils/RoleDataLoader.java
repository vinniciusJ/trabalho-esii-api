package com.project.esii.project_esii.authentication.utils;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
//
//@Component
//public class RoleDataLoader implements ApplicationRunner {
////
////    private final RoleRepository roleRepository;
////
////    @Autowired
////    public RoleDataLoader(RoleRepository roleRepository) {
////        this.roleRepository = roleRepository;
////    }
////
////    @Override
////    public void run(ApplicationArguments args) {
////        for (RoleName roleName : RoleName.values()) {
////            if (roleRepository.findByName(roleName).isEmpty()) {
////                Role role = new Role();
////                role.setName(roleName);
////                roleRepository.save(role);
////            }
////        }
////    }
//}