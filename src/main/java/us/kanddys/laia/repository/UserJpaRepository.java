package us.kanddys.laia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import us.kanddys.laia.model.User;

@Repository
public interface UserJpaRepository extends JpaRepository<User, Long> {
}
