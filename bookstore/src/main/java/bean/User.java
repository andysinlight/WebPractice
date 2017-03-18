package bean;

/**
 * Created by Administrator on 2017/3/18.
 */
public class User {
    long id;
    String name;
    String phone;
    String address;
    boolean active;
    String email;

    public User() {
    }

    public User(String name, String phone, String address, String email, boolean active) {
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.active = active;
        this.email = email;
    }

    public User(long id, String name, String phone, String address, String email, boolean active) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.active = active;
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
