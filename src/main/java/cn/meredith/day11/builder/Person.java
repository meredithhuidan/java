package cn.meredith.day11.builder;

/**
 * 游戏人物
 *
 * @author Meredith
 * @date
 */
public class Person {

    //头部
    String head;
    //体部
    String body;
    //尾部
    String foot;

    public void setHead(String head) {
        this.head = head;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setFoot(String foot) {
        this.foot = foot;
    }

    public String getHead() {
        return head;
    }

    public String getBody() {
        return body;
    }

    public String getFoot() {
        return foot;
    }
}
