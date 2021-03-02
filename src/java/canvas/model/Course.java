/*
 * This is an abstraction class of a course.
 */
package canvas.model;

import java.time.LocalDateTime;

/**
 * This is an class that the user takes.
 * @author Jianqing Gao
 */
public class Course {
    
    private String name, course_code;
    private LocalDateTime start_at;
    private int course_id;
    private int user_id;

    public Course(String name, String course_code, LocalDateTime start_at, int course_id, int user_id) {
        this.name = name;
        this.course_code = course_code;
        this.start_at = start_at;
        this.course_id = course_id;
        this.user_id = user_id;
    }

    public Course() {
       this.name = null;
        this.course_code = null;
        this.start_at = null;
        this.course_id = 0;
        this.user_id = 0;
    }

    
    ///////Get and set methods///////
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCourse_code() {
        return course_code;
    }

    public void setCourse_code(String course_code) {
        this.course_code = course_code;
    }

    public LocalDateTime getStart_at() {
        return start_at;
    }

    public void setStart_at(LocalDateTime start_at) {
        this.start_at = start_at;
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
    
    ////////to string method........

    @Override
    public String toString() {
        return "Course{" + "name=" + name + ", course_code=" + course_code + ", start_at=" + start_at + ", course_id=" + course_id + ", user_id=" + user_id + '}' + '\n';
    }
    
    
    
    
    
    
    
    
    
}
