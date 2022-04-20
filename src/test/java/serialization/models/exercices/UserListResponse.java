package serialization.models.exercices;

import java.util.List;

public class UserListResponse {

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    List<User> users;



}
