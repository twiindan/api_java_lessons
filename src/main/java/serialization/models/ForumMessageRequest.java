package serialization.models;

import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ForumMessageRequest {

    private String message;
    private String subject;
    private String theme;
    List<String> topicsList = new ArrayList<>(List.of("Security", "Development", "Automation", "Testing"));
    Faker faker = new Faker();


    public ForumMessageRequest() {
    }

    public ForumMessageRequest(String message, String subject, String theme) {
        this.message = message;
        this.subject = subject;
        this.theme = theme;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public void createQAMessage(String subject, String message){
        this.theme = "QA";
        this.message = message;
        this.subject = subject;
    }

    public void createRandomMessage(){
        Random rand = new Random();
        this.theme = topicsList.get(rand.nextInt(topicsList.size()));
        this.subject = faker.lorem().sentence();
        this.message = faker.lorem().sentence();

    }
}
