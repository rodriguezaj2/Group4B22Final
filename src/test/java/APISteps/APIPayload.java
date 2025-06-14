package APISteps;

public class APIPayload {

    public static String createEmployeePayload(){
        String createdEmployee="{\n" +
                "  \"emp_firstname\": \"Jon\",\n" +
                "  \"emp_lastname\": \"Jones\",\n" +
                "  \"emp_middle_name\": \"Bones\",\n" +
                "  \"emp_gender\": \"M\",\n" +
                "  \"emp_birthday\": \"1985-05-25\",\n" +
                "  \"emp_status\": \"Employed\",\n" +
                "  \"emp_job_title\": \"Banker\"\n" +
                "}";
        return createdEmployee;
    }
}
