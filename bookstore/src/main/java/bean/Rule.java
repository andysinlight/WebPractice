package bean;

/**
 * Created by Administrator on 2017/3/18.
 */
public class Rule {
    long id;
    String name;
    String des;

    public Rule() {
    }

    public Rule(String name, String des) {
        this.name = name;
        this.des = des;
    }

    public Rule(long id, String name, String des) {
        this.id = id;
        this.name = name;
        this.des = des;
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
}
