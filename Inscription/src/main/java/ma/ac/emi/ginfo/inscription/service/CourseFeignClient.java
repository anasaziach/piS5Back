package ma.ac.emi.ginfo.inscription.service;

import ma.ac.emi.ginfo.inscription.dto.CourseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Service
@FeignClient(name = "course-service")
public interface CourseFeignClient {
    @GetMapping("/api/courses/{id}")
    CourseDTO getCourseById(@PathVariable Long id);
}
