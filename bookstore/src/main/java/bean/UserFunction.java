package bean;

/**
 * Created by Administrator on 2017/3/18.
 */
public class UserFunction {
    long id;
    String name;
    String des;
    String action;


    public UserFunction(long id, String name, String des, String action) {
        this.id = id;
        this.name = name;
        this.des = des;
        this.action = action;
    }

    public UserFunction(String name, String des, String action) {
        this.name = name;
        this.des = des;
        this.action = action;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
