package cn.itcast.jd.util;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

@Component
public class HttpUtils {

    private PoolingHttpClientConnectionManager cm;

    public HttpUtils() {
        this.cm = new PoolingHttpClientConnectionManager();

        //设置最大连接数
        this.cm.setMaxTotal(100);

        //设置每个主机的最大连接数
        this.cm.setDefaultMaxPerRoute(10);
    }

    /**
     * 根据请求地址下载页面数据
     *
     * @param url
     * @return 页面数据
     */
    public String doGetHtml(String url) {
        //获取HttpClient对象
        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(this.cm).build();

        //创建httpGet请求对象，设置url地址
        HttpGet httpGet = new HttpGet(url);


        httpGet.addHeader("cookie","shshshfpa=3a82429a-4ff8-576e-a977-94c6b38047cd-1567523583; shshshfpb=paN8YRRLzlGV3Or3hwLuzOg%3D%3D; __jdu=1409480500; TrackID=1v_QV6PuUNOqi4lGUQLXzbwM3ot3QGl4eSVmI09EvTkvSINt9cfAIRt4taCNSMqeI5g5UGy8hy70Vg-QYT1e0NfmKTGyhCMCzTwFVKHlRFVA; pinId=8h_5UUhT7CvC-tCnPEI62w; qrsc=3; __jdv=122270672|baidu|-|organic|not set|1585548815522; areaId=19; ipLoc-djd=19-1607-3155-0; xtest=5328.cf6b6759; PCSYCityID=CN_440000_440300_440305; shshshfp=8d2385ed0edd959c7bcc8bc0cbbb08c9; 3AB9D23F7A4B3C9B=5BCYFCAV3CFW5DR6JRBMMLEJIZ2YCSSMYDD2YU3EIDQXMARMCO75VHW4QWTAVBMUTJU6JSZOTRVYBRJMYQX6P2YN4M; __jda=122270672.1409480500.1567523572.1585548816.1585979886.10; __jdc=122270672; rkv=V0900; __jdb=122270672.2.1409480500|10.1585979886; shshshsID=10bce6e2c41c18a9753a92790f840b8d_2_1585979897056");
        httpGet.addHeader("accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3");
        httpGet.addHeader("user-agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/76.0.3809.132 Safari/537.36");

        //设置请求信息
        httpGet.setConfig(this.getConfig());


        CloseableHttpResponse response = null;


        try {
            //使用HttpClient发起请求，获取响应
            response = httpClient.execute(httpGet);

            //解析响应，返回结果
            if (response.getStatusLine().getStatusCode() == 200) {
                //判断响应体Entity是否不为空，如果不为空就可以使用EntityUtils
                if (response.getEntity() != null) {
                    String content = EntityUtils.toString(response.getEntity(), "utf8");
                    return content;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //关闭response
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        //返回空串
        return "";
    }


    /**
     * 下载图片
     *
     * @param url
     * @return 图片名称
     */
    public String doGetImage(String url) {
        //获取HttpClient对象
        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(this.cm).build();

        //创建httpGet请求对象，设置url地址
        HttpGet httpGet = new HttpGet(url);

        httpGet.addHeader("cookie","shshshfpa=3a82429a-4ff8-576e-a977-94c6b38047cd-1567523583; shshshfpb=paN8YRRLzlGV3Or3hwLuzOg%3D%3D; __jdu=1409480500; TrackID=1v_QV6PuUNOqi4lGUQLXzbwM3ot3QGl4eSVmI09EvTkvSINt9cfAIRt4taCNSMqeI5g5UGy8hy70Vg-QYT1e0NfmKTGyhCMCzTwFVKHlRFVA; pinId=8h_5UUhT7CvC-tCnPEI62w; qrsc=3; __jdv=122270672|baidu|-|organic|not set|1585548815522; areaId=19; ipLoc-djd=19-1607-3155-0; xtest=5328.cf6b6759; PCSYCityID=CN_440000_440300_440305; shshshfp=8d2385ed0edd959c7bcc8bc0cbbb08c9; 3AB9D23F7A4B3C9B=5BCYFCAV3CFW5DR6JRBMMLEJIZ2YCSSMYDD2YU3EIDQXMARMCO75VHW4QWTAVBMUTJU6JSZOTRVYBRJMYQX6P2YN4M; __jda=122270672.1409480500.1567523572.1585548816.1585979886.10; __jdc=122270672; rkv=V0900; __jdb=122270672.2.1409480500|10.1585979886; shshshsID=10bce6e2c41c18a9753a92790f840b8d_2_1585979897056");
        httpGet.addHeader("accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3");
        httpGet.addHeader("user-agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/76.0.3809.132 Safari/537.36");

        //设置请求信息
        httpGet.setConfig(this.getConfig());

        CloseableHttpResponse response = null;


        try {
            //使用HttpClient发起请求，获取响应
            response = httpClient.execute(httpGet);

            //解析响应，返回结果
            if (response.getStatusLine().getStatusCode() == 200) {
                //判断响应体Entity是否不为空
                if (response.getEntity() != null) {
                    //下载图片
                    //获取图片的后缀
                    String extName = url.substring(url.lastIndexOf("."));

                    //创建图片名，重命名图片
                    String picName = UUID.randomUUID().toString() + extName;

                    //下载图片
                    //声明OutPutStream
                    OutputStream outputStream = new FileOutputStream(new File("C:\\Users\\guoyongliang\\Desktop\\images\\" +
                            picName));
                    response.getEntity().writeTo(outputStream);

                    //返回图片名称
                    return picName;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //关闭response
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        //如果下载失败，返回空串
        return "";
    }

    //设置请求信息
    private RequestConfig getConfig() {
        RequestConfig config = RequestConfig.custom()
                .setConnectTimeout(1000)    //创建连接的最长时间
                .setConnectionRequestTimeout(500)  // 获取连接的最长时间
                .setSocketTimeout(10000)    //数据传输的最长时间
                .build();

        return config;
    }
}
