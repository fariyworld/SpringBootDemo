package com.mace.test;

import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * description:
 * <br />
 * Created by mace on 9:09 2018/5/28.
 */
public class FileOpsTest {

    public static void main(String[] args) {

        rename("C:\\Users\\15257\\Desktop\\wh_mr_bak");
        deleteEmptyDirectory("C:\\Users\\15257\\Desktop\\wh_mr_bak");
        System.out.println("rename and replace and deleteEmptyDirectory success");
    }

    /**
     * description: 把当前目录下的所有文件重命名为.txt
     * <br /><br />
     * create by mace on 2018/5/5 9:46.
     * @param path
     * @return: void
     */
    public static void rename(String path){

        File root = new File(path);

        String[] list = root.list();

        if(list != null && list.length>0){

            for(String oldname : list){

//                System.out.println(oldname);
                File elementFile = new File(path, oldname);

                if(elementFile.isDirectory()){
                    //目录 回调
                    rename(path+File.separator+oldname);
                }else{
                    //不是目录
                    //修改文件名包含part且有内容的文件
                    if(oldname.contains("part") && elementFile.length()>0){
                        //符合 repalce(0 )为"" 并重命名为txt
                        //重命名 .txt
                        String newPath = path+File.separator+oldname+".txt";
                        elementFile.renameTo(new File(newPath));
                        //修改文件内容
                        modifyFileContent(newPath, "0\t", StringUtils.EMPTY);
                    }else {
                        //不符合
                        //删除
                        elementFile.delete();
                    }
                }
            }
        }
    }

    /**
     * description: 使用随机流 修改文件内容
     * <br /><br />
     * create by mace on 2018/5/6 12:56.
     * @param filePath
     * @param target          0\t
     * @param replacement     ""
     * @return: void
     */
    public static void modifyFileContent(String filePath, String target, String replacement){

        RandomAccessFile raf = null;

        try {
            raf = new RandomAccessFile(filePath, "rw");
            String lineTxt = null;
            long lastPoint = 0;//上一次的偏移量
            while ((lineTxt = raf.readLine())!=null){
                long filePointer = raf.getFilePointer();
                if(lineTxt.contains(target)){
                    String str = lineTxt.replace(target, replacement);
                    raf.seek(lastPoint);
                    raf.writeBytes(str);
                }
                lastPoint = filePointer;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //释放资源
            try {
                raf.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * description: 删除空目录
     * <br /><br />
     * create by mace on 2018/5/8 9:16.
     * @param path
     * @return: void
     */
    public static void deleteEmptyDirectory(String path){

        File root = new File(path);

        String[] list = root.list();

        if(list != null && list.length>0){

            for(String elementName : list){

                File elementFile = new File(path, elementName);

                if(elementFile.isDirectory()){

                    deleteEmptyDirectory(path + File.separator + elementName);
                }
            }

        }else{
//            System.out.println(list.length == 0);
//            System.out.println(path+"是空目录");
            root.delete();
        }
    }
}
