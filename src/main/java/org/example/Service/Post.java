package org.example.Service;

import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.example.controller.Controller;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class Post extends Thread {
    CloseableHttpAsyncClient client = HttpAsyncClients.createDefault();
    private  int  postNumber =1;
    private static Logger logger = Logger.getLogger(Controller.class);
    @Override
    public void run(){
            client.start();
            for(int i=1;i<=postNumber;i++){
                HttpPost post = new HttpPost("http://127.0.0.1:8080/test.do?number="+i+"");
                RequestConfig config = RequestConfig.custom()
                        .setConnectionRequestTimeout(i*1000) //从连接池中取的连接的最长时间
                        .build();
                post.setConfig(config);
                int finalI = i;
                client.execute(post,new FutureCallback<HttpResponse>(){

                    @Override
                    public void completed(HttpResponse httpResponse) {
                        try {
                            String content = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
                            System.out.println(content);
                            logger.info("第"+ finalI +" 条请求执行成功");
                         } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    @Override
                    public void failed(Exception e) {
                        logger.error(e);
                    }
                    @Override
                    public void cancelled() {
                        logger.error("请求取消");

                    }
                });
            }
    }
    public void sendPosts(int number) throws InterruptedException {
        this.postNumber = number;
        start();
        join();
     }
}
