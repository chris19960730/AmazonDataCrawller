package com.amazon;

import java.io.*;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class IdManager {
    private String filePath;
    private Set<String> leftIds;
    private String currentId;

    public IdManager(String fileName){
        currentId=null;
        filePath=fileName;
        initLeftIds();
    }

    private void initLeftIds(){
        leftIds=new HashSet<String>();
        try {
            File file=new File(filePath);
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line=null;
            while ((line=br.readLine())!=null){
                leftIds.add(line.trim());
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public String getNewId() {
        Iterator itor=leftIds.iterator();
        if(itor.hasNext()){
            currentId=itor.next().toString();
            itor.remove();
            return currentId;
        }
        return null;
    }

    public String getCurrentId(){
        return currentId;
    }

    public int getLeftIdSize(){
        return leftIds.size();
    }

    public void writeLeftIds(String filePath, boolean writeCurrentId){
        File file=new File(filePath);
        try {
            file.createNewFile();
            FileOutputStream os=new FileOutputStream(file);
            if(writeCurrentId && currentId!=null){
                os.write((currentId+"\n").getBytes("GBK"));
            }
            Iterator itor=leftIds.iterator();
            while (itor.hasNext()){
                String id=itor.next().toString();
                os.write((id+"\n").getBytes("GBK"));
                //itor.remove();
            }
            os.close();
            System.out.println("文件写入完成");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void writeErrorId(String filePath) {
        BufferedWriter out = null;
        try {
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath, true)));
            out.write(currentId+"\n");
            System.out.println("Error文件写入完成");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
