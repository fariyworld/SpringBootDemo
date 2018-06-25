package com.mace.util;

import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

/**
 * description:
 * <br />
 * Created by mace on 9:07 2018/5/7.
 */
public class FileUtil {

    public static void main(String[] args) {

        rename("D:\\BONC\\tj\\20180607\\mrpoint_bak");
        deleteEmptyDirectory("D:\\BONC\\tj\\20180607\\mrpoint_bak");
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
                        FileManagerUtil.modifyFileContent(newPath, "0 ", StringUtils.EMPTY);
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
