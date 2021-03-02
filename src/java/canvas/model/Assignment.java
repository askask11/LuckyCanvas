/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package canvas.model;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author jianqing
 */
public class Assignment {

    private int assignment_id;

    private String description;

    private LocalDateTime due_at;

    private LocalDateTime unlock_at;

    //private LocalDateTime lock
    private GradingType type;

    private int position;

    private String html_url;

    private int course_id;

    private String name;
    
    private double points_possible;

    public Assignment() {

    }

    public Assignment(int assignment_id) {
        this.assignment_id = assignment_id;
    }

    public Assignment(int assignment_id, String description) {
        this(assignment_id);
        this.description = description;
    }

    public Assignment(int assignment_id, String description, LocalDateTime due_at) {
        this(assignment_id, description);
        this.due_at = due_at;
    }

    public Assignment(int assignment_id, String description, LocalDateTime due_at, LocalDateTime unlock_at) {
        this(assignment_id, description, due_at);
        this.unlock_at = unlock_at;
    }

    public Assignment(int assignment_id, String description, LocalDateTime due_at, LocalDateTime unlock_at, GradingType type) {
        this(assignment_id, description, due_at, unlock_at);
        this.type = type;
    }

    public Assignment(int assignment_id, String description, LocalDateTime due_at, LocalDateTime unlock_at, GradingType type, int position) {
        this.assignment_id = assignment_id;
        this.description = description;
        this.due_at = due_at;
        this.unlock_at = unlock_at;
        this.type = type;
        this.position = position;
    }

    public Assignment(int assignment_id, String description, LocalDateTime due_at, LocalDateTime unlock_at, GradingType type, int position, String html_url) {
        this.assignment_id = assignment_id;
        this.description = description;
        this.due_at = due_at;
        this.unlock_at = unlock_at;
        this.type = type;
        this.position = position;
        this.html_url = html_url;
    }

    public Assignment(int assignment_id, String description, LocalDateTime due_at, LocalDateTime unlock_at, GradingType type, int position, String html_url, int course_id) {
        this.assignment_id = assignment_id;
        this.description = description;
        this.due_at = due_at;
        this.unlock_at = unlock_at;
        this.type = type;
        this.position = position;
        this.html_url = html_url;
        this.course_id = course_id;
    }

    public int getAssignment_id() {
        return assignment_id;
    }

    public void setAssignment_id(int assignment_id) {
        this.assignment_id = assignment_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDue_at() {
        return due_at;
    }

    public void setDue_at(LocalDateTime due_at) {
        this.due_at = due_at;
    }

    public LocalDateTime getUnlock_at() {
        return unlock_at;
    }

    public void setUnlock_at(LocalDateTime unlock_at) {
        this.unlock_at = unlock_at;
    }

    public GradingType getType() {
        return type;
    }

    public void setType(GradingType type) {
        this.type = type;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getHtml_url() {
        return html_url;
    }

    public void setHtml_url(String html_url) {
        this.html_url = html_url;
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPoints_possible() {
        return points_possible;
    }

    public void setPoints_possible(double points_possible) {
        this.points_possible = points_possible;
    }

  
    
    /**
     * This determines if the current assignment is due or not compare to the current date.
     * @return <code>true</code> if the due date of this assignment is before the current time.
     * <code>false</code> otherwise.
     */
    public boolean isDue() {
        return this.due_at.isBefore(LocalDateTime.now(ZoneId.of("UTC")));
    }
    
    public String getDuedateCommonFormat()
    {
        return DateTimeFormatter.RFC_1123_DATE_TIME.format(due_at.atZone(ZoneId.of("UTC")));
    }
    

    /**
     * See if this assignment is unlocked. 
     * @return Returns true if this assignment is unlocked.
     */
    public boolean isUnlocked() {
        return LocalDateTime.now(ZoneId.of("UTC")).isAfter(unlock_at);
    }

    @Override
    public String toString() {
        return "Assignment{" + "assignment_id=" + assignment_id + ", description=" + description + ", due_at=" + due_at + ", unlock_at=" + unlock_at + ", type=" + type + ", position=" + position + ", html_url=" + html_url + ", course_id=" + course_id + ", name=" + name + '}';
    }

    public String getGradingTypeAsString()
    {
        return GradingType.toString(type);
    }
    
}
