package ma.ac.emi.ginfo.cours.service.impl;

import ma.ac.emi.ginfo.cours.dto.CourseDTO;
import ma.ac.emi.ginfo.cours.dto.UserDTO;
import ma.ac.emi.ginfo.cours.entity.Course;
import ma.ac.emi.ginfo.cours.repository.CourseRepository;
import ma.ac.emi.ginfo.cours.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public CourseDTO createCourse(CourseDTO courseDTO) {
        Course course = new Course();
        course.setName(courseDTO.getName());
        // Set other fields as needed
        Course savedCourse = courseRepository.save(course);
        return convertToDTO(savedCourse);
    }

    @Override
    public List<CourseDTO> getAllCourses() {
        List<Course> courses = courseRepository.findAll();
        return courses.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CourseDTO getCourseById(Long id) {
        return courseRepository.findById(id)
                .map(this::convertToDTO)
                .orElseThrow(()->new RuntimeException("user not fround"));
    }

    @Override
    public CourseDTO updateCourse(Long id, CourseDTO updatedCourseDTO) {
        Course course = courseRepository.findById(id)
                .orElseThrow(()->new RuntimeException("course not found!"));
        course.setName(updatedCourseDTO.getName());
        // Set other fields as needed
        Course updatedCourse = courseRepository.save(course);
        return convertToDTO(updatedCourse);
    }

    @Override
    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }

    private CourseDTO convertToDTO(Course course) {
        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setId(course.getId());
        courseDTO.setName(course.getName());
        // Set other fields as needed
        return courseDTO;
    }
}