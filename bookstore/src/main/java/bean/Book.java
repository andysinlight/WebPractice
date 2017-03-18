package bean;

/**
 * Created by Administrator on 2017/3/17.
 */
public class Book {
    String id;
    String name;
    String des;
    String category;
    String path;
    String img_name;
    float price;


    public Book(String id, String name, String des, String category, String path, String img_name, float price) {
        this.id = id;
        this.name = name;
        this.des = des;
        this.category = category;
        this.path = path;
        this.img_name = img_name;
        this.price = price;
    }

    public Book() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getImg_name() {
        return img_name;
    }

    public void setImg_name(String img_name) {
        this.img_name = img_name;
    }
}
