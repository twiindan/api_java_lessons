package basics.models;

public class CreateUserBody {

    public static String GetCreateUserBody(String username){

        String createUserBody = "{\n" +
                "  \"name\": \"Toni1\",\n" +
                "  \"username\": \"" + username + "\",\n" +
                "  \"password\": \"expert\",\n" +
                "  \"role\": \"DEVELOPER\",\n" +
                "  \"email\": \"twiindan@gmail.com\"\n" +
                "}";
        return createUserBody;
    }
}
