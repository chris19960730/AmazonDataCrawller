package com.amazon;

import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args){
        IdManager idManager=new IdManager("/Users/chris/Desktop/AmazonXMLCrawller/id/t4.txt");//读取的txt文件，test.txt有10行，可供测试。
        XMLDownloader xmlDownloader=new XMLDownloader();

        String id=null;
        while((id=idManager.getNewId())!=null){
            try{
                xmlDownloader.downloadXML(id,"/Users/chris/Desktop/AmazonXMLCrawller/xmlFiles");//下载xml文件目录
                idManager.writeLeftIds("/Users/chris/Desktop/AmazonXMLCrawller/t4left.txt",false);
            }
            catch (Exception e){
                idManager.writeErrorId("/Users/chris/Desktop/AmazonXMLCrawller/t4error.txt");//有报错的id存起来，无该文件自动建立，有该文件追加
                idManager.writeLeftIds("/Users/chris/Desktop/AmazonXMLCrawller/t4left.txt",true);//剩余的没爬id存起来，复写。第二个参数表示是否把当前id也存进去。
            }finally {
                try {
                    System.out.println("剩余"+idManager.getLeftIdSize()+"个id");
                    TimeUnit.SECONDS.sleep(1);//间隔1秒
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }

        }
    }
}
