package ma.ac.emi.ginfo.etudiant.repository;

import ma.ac.emi.ginfo.etudiant.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}