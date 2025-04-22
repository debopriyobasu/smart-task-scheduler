package in.co.debo.smarttaskscheduler.repository;

import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.co.debo.smarttaskscheduler.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
