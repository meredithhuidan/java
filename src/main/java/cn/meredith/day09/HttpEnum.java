package cn.meredith.day09;

/**
 * 枚举
 * rpc通讯 返回code msg
 *
 * @author Meredith
 * @date
 */
enum HttpEnum {

     HTTP_200(200,"请求成功"),HTTP_500(500,"请求失败");

     HttpEnum(Integer httpCode,String httpMsg){
         //执行几次 一次
         System.out.println("HttpEnum初始化");
         this.httpCode=httpCode;
         this.httpMsg=httpMsg;
     }

    private Integer httpCode;
    private String httpMsg;

    public Integer getHttpCode() {
        return httpCode;
    }

    public String getHttpMsg() {
        return httpMsg;
    }

    public void setHttpCode(Integer httpCode) {
        this.httpCode = httpCode;
    }

    public void setHttpMsg(String httpMsg) {
        this.httpMsg = httpMsg;
    }

    public static void main(String[] args) {

        System.out.println(HttpEnum.HTTP_500.httpCode);
        System.out.println(HttpEnum.HTTP_500.httpCode);
        System.out.println(HttpEnum.HTTP_500.httpMsg);
    }
}
