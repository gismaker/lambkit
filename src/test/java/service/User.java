package service;

import com.jfinal.plugin.activerecord.Model;

public class User extends Model<User> {


    public User() {
    }

    public User(int id, String name) {
        setId(id);
        setName(name);
    }

    public int getId() {
        return get("id");
    }

    public void setId(int id) {
        set("id", id);
    }

    public String getName() {
        return get("name");
    }

    public void setName(String name) {
        set("name", name);
    }

   
}
