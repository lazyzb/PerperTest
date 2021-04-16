package org.example.controller;

import org.apache.log4j.Logger;
import org.example.Service.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class Controller {
    @Autowired
    public Post post;
    public static String REQUEST_SUCCESS= "请求成功";
    public static String REQUEST_FAILED = "请求失败";

    private static Logger logger = Logger.getLogger(Controller.class);
    @RequestMapping("/test.do")
    public  void  test(HttpServletRequest request){
        try {
            int number = Integer.valueOf(request.getParameter("number"));
            logger.info("正在执行第"+number+"条请求");
         } catch (NullPointerException e){
            logger.error("并行请求过程中 参数未获取到，执行失败");
         } catch (NumberFormatException e){
            logger.error("并行请求过程中，参数的数据类型错误，请给与整形的参数");
         }
    }
    @RequestMapping("/post.do")
    public String  post(HttpServletRequest request){
        try {
            int count = Integer.valueOf(request.getParameter("number"));
            post.sendPosts(count);
            logger.info(REQUEST_SUCCESS);
            return REQUEST_SUCCESS;
        }catch (NullPointerException e){
            logger.error("没有给与请求的数量参数");
            return  REQUEST_FAILED;

        }catch (InterruptedException e){
            logger.error("请求过程中出现错误");
            return REQUEST_FAILED;

        }catch (NumberFormatException e){
            logger.error("参数的数据类型错误，请给与整形的参数");
            return REQUEST_FAILED;
        }
     }
}
