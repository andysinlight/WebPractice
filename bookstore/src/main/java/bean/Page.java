package bean;

import java.util.List;

/**
 * Created by Administrator on 2017/3/26.
 */
public class Page<T> {
    List<T> data;
    int recordSum;
    int pageSize = 5;
    int pageNum;
    int currentPage;
    boolean more;
    int startIndex;


    public Page(int currentPage, int recordSum) {
        if (currentPage == 0) currentPage = 1;
        this.data = data;
        this.currentPage = currentPage;
        this.recordSum = recordSum;
        startIndex = (currentPage-1) * pageSize;
        pageNum = recordSum % pageSize == 0 ? recordSum / pageSize : recordSum / pageSize + 1;
        more = pageSize * currentPage < recordSum;
    }

    public Page(int pageSize, int currentPage, int recordSum) {
        if (currentPage == 0) currentPage = 1;
        this.data = data;
        this.pageSize = pageSize;
        this.currentPage = currentPage;
        this.recordSum = recordSum;
        startIndex = (currentPage-1) * pageSize;
        pageNum = recordSum % pageSize == 0 ? recordSum / pageSize : recordSum / pageSize + 1;
        more = pageSize * currentPage < recordSum;

    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public int getEndIndx() {
        return startIndex + pageSize;
    }

}
