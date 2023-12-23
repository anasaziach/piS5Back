package ma.ac.emi.ginfo.cours.repository;

import ma.ac.emi.ginfo.cours.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}