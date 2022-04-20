package basics.models;

public class CreateMessageForumBody {

    public static String GetCreateMessageForumBody(String theme, String number){

        String createForumMessageBody = " {\"theme\": \"" + theme + "\",\n" +
                "     \"subject\": \"Message Subject ID = " + number + "\",\n" +
                "     \"message\": \"Message Body ID = " + number + "\"}";
        return createForumMessageBody;
    }

}
