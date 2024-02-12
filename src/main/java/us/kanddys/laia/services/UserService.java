package us.kanddys.laia.services;

import java.util.List;

import us.kanddys.laia.entities.UserDTO;

public interface UserService {
   public List<UserDTO> findAllUsers();
}
