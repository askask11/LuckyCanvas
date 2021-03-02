/*
Author: Jianqing Gao
Responsible for communicating with the canvas system.
 */
package canvas.connector;

//import org.json.simple.parser.ParseException;
import canvas.model.Assignment;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import canvas.model.Course;
import canvas.model.GradingType;
import java.util.HashMap;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author jianqing
 */
public class CanvasReader
{

    public static final String CANVAS_PROFILE_PREFIX = "https://thevillageschool.instructure.com/api/v1/";///courses?access_token=";

    public static ArrayList<Course> readUserCourses(String token) throws IOException, ParseException, InterruptedException
    {
        try
        {
            ArrayList<Course> courseList = new ArrayList<>();
            JSONParser parser = new JSONParser();
//HttpURLConnection.setFollowRedirects(true);
//open a network socket to avoid directly handshake.
//openConnection.connect();
            String urls = "https://thevillageschool.instructure.com/api/v1/courses?per_page=100&access_token=" + token;
//            URL url = new URL(urls);
//            URLConnection openConnection = url.openConnection();
            //URL netURL = new URL(url);

//InputStream openConnectionCheckRedirects = sun.net.www.protocol.http.HttpURLConnection.openConnectionCheckRedirects(netURL.openConnection()); //= conn.openStream();
            //BufferedReader reader = new BufferedReader(new InputStreamReader(openConnection.getInputStream()));
            Object parse = parser.parse(Commander.executeCommand("curl " + urls)/*reader*/);
//Course course;
//Convert it into a JSON array

//parse the array
            JSONArray classJSONArray = (JSONArray) parse;

            for (int i = 0; i < classJSONArray.size(); i++)
            {
                JSONObject courseJSONObject = (JSONObject) classJSONArray.get(i);
                JSONObject enrollmentJSONObject;
                JSONArray enrollmentJSONArray = (JSONArray) courseJSONObject.get("enrollments");
                //System.out.println("enrollment json array " + enrollmentJSONArray);

                if (enrollmentJSONArray == null || enrollmentJSONArray.isEmpty())
                {
                    System.out.println("An empty entry detected #" + (i + 1) + ", skip entry");
                } else
                {
                    enrollmentJSONObject = (JSONObject) enrollmentJSONArray.get(0);
                    //System.out.println(courseJSONObject.get("created_at"));
                    courseList.add(new Course(courseJSONObject.get("name").toString(),//courseName
                            courseJSONObject.get("course_code").toString(),//course code
                            LocalDateTime.ofInstant(Instant.from(DateTimeFormatter.ISO_INSTANT.parse((String) courseJSONObject.get("created_at"))), ZoneId.of("UTC")),//when the course begins
                            Integer.parseInt(courseJSONObject.get("id").toString()),//the id of the course
                            Integer.parseInt(enrollmentJSONObject.get("user_id").toString())));//the user id, very useful later on
                }
            }

            //System.out.println("SUCCESSFUL!!!!!!!! YOU GOT" + courseList);
            return courseList;
        } catch (IOException | InterruptedException | NumberFormatException | ParseException ex)
        {
            Logger.getLogger(CanvasReader.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;

        }

    }

    /**
     * Read assigment from reader by
     *
     * @param user_id
     * @param course_id
     * @param token
     * @return
     * @throws IOException
     * @throws ParseException
     * @throws InterruptedException
     */
    public static ArrayList<Assignment> readUserAssignments(int user_id, int course_id, String token) throws IOException, ParseException, InterruptedException
    {
        String url = CANVAS_PROFILE_PREFIX + "users/" + user_id + "/courses/" + course_id + "/assignments?per_page=100&access_token=" + token;
        JSONParser parser = new JSONParser();
        ArrayList<Assignment> assignments = new ArrayList<>();
        Assignment assignment;
        Object parse = parser.parse(Commander.executeCommand("curl " + url));

        JSONArray jSONArray = (JSONArray) parse;

        String assignment_id, description, due_at, unlock_at, type, position, html_url, name;//, course_id;
        for (int i = 0; i < jSONArray.size(); i++)
        {
            JSONObject assignmentJSONObject = (JSONObject) jSONArray.get(i);
            assignment_id = (Long) assignmentJSONObject.get("id") + "";
            //creates a new instance of assignment
            assignment = new Assignment(Integer.parseInt(assignment_id));
            //get description
            description = (String) assignmentJSONObject.get("description");
            assignment.setDescription(description);

            //get due date
            due_at = (String) assignmentJSONObject.get("due_at");
            //check if there is a due date(some assignments are undated)
            if (due_at == null)
            {
                assignment.setDue_at(LocalDateTime.MAX);
            } else
            {
                assignment.setDue_at(getDateTime(due_at));
            }

            //get unlock date
            unlock_at = (String) assignmentJSONObject.get("unlock_at");
            if (unlock_at == null)
            {
                assignment.setUnlock_at(LocalDateTime.MIN);
            } else
            {
                assignment.setUnlock_at(getDateTime(unlock_at));
            }

            //get the grading_type
            type = (String) assignmentJSONObject.get("grading_type");

            assignment.setType(GradingType.parseGradingType(type));

            //get position
            position = assignmentJSONObject.get("position").toString();

            assignment.setPosition(Integer.parseInt(position));

            //get html url
            html_url = (String) assignmentJSONObject.get("html_url");
            assignment.setHtml_url(html_url);

            //get course
            assignment.setCourse_id(Integer.parseInt(assignmentJSONObject.get("course_id") + ""));

            //get name
            name = (String) assignmentJSONObject.get("name");
            assignment.setName(name);

            //get the points possible if the grading type is "points"
            switch (assignment.getType())
            {
                case POINTS:
                    assignment.setPoints_possible((Double) assignmentJSONObject.get("points_possible"));
                    break;
                default:
                    assignment.setPoints_possible(100);
                    break;
            }

            //add the assignment back into the arraylist
            assignments.add(assignment);
        }
        return assignments;
    }

    public static HashMap<String, String> getGrade(String course_id, String assignment_id, String user_id, String token) throws InterruptedException, IOException, ParseException
    {
        String url = CANVAS_PROFILE_PREFIX + "courses/" + course_id + "/assignments/" + assignment_id + "/submissions/" + user_id + "?access_token=" + token;
        JSONParser parser = new JSONParser();
        HashMap<String, String> map = new HashMap<>();

//        JSONArray jSONArray;
        //System.out.println(Commander.executeCommand("curl " + url));
        JSONObject gradeObject = (JSONObject) parser.parse(Commander.executeCommand("curl " + url));
        map.put("grade", (String) gradeObject.get("grade"));
        map.put("workflow_state", (String) gradeObject.get("workflow_state"));
        return map;
    }

    private static LocalDateTime getDateTime(String timeStamp)
    {
        return LocalDateTime.ofInstant(Instant.from(DateTimeFormatter.ISO_INSTANT.parse(timeStamp)), ZoneId.of("UTC"));
    }

    public static void main(String[] args)
    {
        //test fetch from internet
        //JSONParser parser = new JSONParser();
        
        try
        {
            System.out.println(getGrade("6164", "84052", "8074", ""));
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}
