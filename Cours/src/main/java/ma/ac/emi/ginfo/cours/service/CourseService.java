    package ma.ac.emi.ginfo.cours.service;

    import ma.ac.emi.ginfo.cours.dto.CourseDTO;
    import ma.ac.emi.ginfo.cours.dto.UserDTO;
    import ma.ac.emi.ginfo.cours.entity.Course;

    import java.util.List;
    import java.util.Optional;

    public interface CourseService {
        CourseDTO createCourse(CourseDTO courseDTO);
        List<CourseDTO> getAllCourses();
        CourseDTO getCourseById(Long id);
        CourseDTO updateCourse(Long id, CourseDTO updatedCourseDTO);
        void deleteCourse(Long id);
    }