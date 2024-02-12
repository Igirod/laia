package us.kanddys.laia.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import us.kanddys.laia.entities.UserDTO;
import us.kanddys.laia.repository.UserJpaRepository;
import us.kanddys.laia.services.UserService;

@Service
public class UserServiceImpl implements UserService {

   @Autowired
   private UserJpaRepository userJpaRepository;

   @Override
   public List<UserDTO> findAllUsers() {
      return userJpaRepository.findAll().stream().map(UserDTO::new).collect(Collectors.toList());
   }

}
