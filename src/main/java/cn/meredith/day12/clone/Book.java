package cn.meredith.day12.clone;

import java.util.ArrayList;
import java.util.List;

public class Book extends Object implements Cloneable{

    //名称
    private String title;

    //页数
    private int page;

    //书里的图片
    private ArrayList<String> listImg=new ArrayList<String>();

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getListImg() {
        return listImg;
    }

    public void setListImg(ArrayList<String> listImg) {
        this.listImg = listImg;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void showBook(){
        System.out.println("-------------start-------------");
        System.out.println("title:"+title);
        System.out.println("page:"+page);
        for (String img:listImg) {
            System.out.println("img name:"+img);
        }
        System.out.println("-------------end----------------");
    }

    public void addImg(String imgName){
        listImg.add(imgName);
    }

    /**
     * 默认是浅克隆
     *
     * @return
     * @throws CloneNotSupportedException
     */
    @Override
    protected Object clone() throws CloneNotSupportedException {

        //浅克隆 深克隆 默认浅拷贝
        Book book= (Book) super.clone();

        //改成深拷贝
        book.listImg= (ArrayList<String>) this.listImg.clone();
        //String 没有clone()方法，违背唯一性
        //this.title.clone();
        return book;
    }


}
