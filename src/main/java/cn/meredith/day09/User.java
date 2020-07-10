package cn.meredith.day09;

/**
 * 枚举创建单例
 */
public class User {

    private User(){

    }

    public static User getInsatnce(){
        return SingletonUserEnum.INSTANCE.getInsatnce();
    }

     static enum SingletonUserEnum{
        INSTANCE;
        private User user;
        private SingletonUserEnum(){
            user=new User();
        }
         public User getInsatnce(){
             return this.user;
         }
    }

    public static void main(String[] args) {
        User u1=User.getInsatnce();
        User u2=User.getInsatnce();
        System.out.println(u1==u2);
    }
}
