/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package canvas.model;

/**
 *
 * @author jianqing
 */
public enum GradingType {

    PERCENT,PASS_FAIL,LETTER_GRADE,GPA_SCALE,POINTS,UNDEFINED;

    private GradingType() {
    }
    
    /**
     * Parse a string to a normal grading type.
     * @param gradingType
     * @return 
     */
    public static GradingType parseGradingType(String gradingType)
    {
        switch(gradingType)
        {
            case "percent":return PERCENT; 
            case "pass_fail":return PASS_FAIL;
            case "letter_grade":return LETTER_GRADE;
            case "gpa_scale":return GPA_SCALE;
            case "points":return POINTS;
            default:return UNDEFINED;
        }
    }
    
    /**
     * This parses a grading type into a string.
     * @param gradingType
     * @return
     */
    public static String toString(GradingType gradingType)
    {
        switch(gradingType)
        {
            case PERCENT:return "percent";
            case PASS_FAIL: return "pass_fail";
            case LETTER_GRADE : return "letter_grade";
            case GPA_SCALE: return "gpa_scale";
            case POINTS: return "points";
            default: return null;
        }
    }
    
}
