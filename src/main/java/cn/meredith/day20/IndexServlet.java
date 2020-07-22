package cn.meredith.day20;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * servlet 生命周期
 *
 * @author Meredith
 * @date
 */
public class IndexServlet extends HttpServlet {

    //初始化
    public void init(){
        System.out.println("init");
    }

    //销毁
    public void destroy(){
        System.out.println("destroy");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
         System.out.println("wo shi servlet");
         resp.getWriter().print("success");
    }
}
