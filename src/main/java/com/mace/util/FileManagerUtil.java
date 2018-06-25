package com.mace.util;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

/**
 * description: Java 操作文件工具类
 * <br />
 * Created by mace on 15:12 2018/6/13.
 */
public class FileManagerUtil {

    public static void main(String[] args) {

        List<String> lists = new ArrayList<>();

        getAllDirectoryPath("D:\\BONC\\tj\\201806119", lists);

        mergeFiles(lists);
    }


    /**
     * description: 获取指定目录下所有文件路径
     * <br /><br />
     * create by mace on 2018/6/13 15:13.
     * @param rootPath
     * @param lists
     * @return: void
     */
    public static void getAllFilePath(String rootPath, List<String> lists){

        File root = new File(rootPath);

        String[] list = root.list();

        if(list != null && list.length>0){

            for(String filename : list){

                File elementFile = new File(rootPath, filename);

                if(elementFile.isDirectory())
                    getAllFilePath(rootPath + File.separator + filename, lists);
                else
                    lists.add(rootPath + File.separator + filename);
            }
        }

        System.out.println("该目录不存在：" + rootPath);
    }

    /**
     * description: 获取指定目录下所有文件名
     * <br /><br />
     * create by mace on 2018/6/13 15:49.
     * @param rootPath
     * @param lists
     * @return: void
     */
    public static void getPaths(String rootPath,List<String> lists){

        File root = new File(rootPath);

        String[] list = root.list();

        if(list != null && list.length>0){

            for(String filename : list){

                File elementFile = new File(rootPath, filename);

                if(elementFile.isDirectory())
                    getPaths(rootPath + File.separator + filename, lists);
                else
                    lists.add(filename);
            }
        }

        System.out.println("该目录不存在：" + rootPath);
    }


    /**
     * description: 获取指定目录下所有目录路径
     * <br /><br />
     * create by mace on 2018/6/13 15:24.
     * @param rootPath
     * @param lists
     * @return: void
     */
    public static void getAllDirectoryPath(String rootPath, List<String> lists){

        File root = new File(rootPath);

        String[] list = root.list();

        if(list != null && list.length>0){

            for(String filename : list){

                File elementFile = new File(rootPath, filename);

                if(elementFile.isDirectory()){
                    lists.add(rootPath + File.separator + filename);
                    getAllDirectoryPath(rootPath + File.separator + filename, lists);
                }
            }
        }

        System.out.println("该目录不存在：" + rootPath);
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
     * description:
     * <br /><br />
     * create by mace on 2018/6/14 16:02.
     * @param lists  路径集合
     * @return: void
     */
    public static void mergeFiles(List<String> lists){

        for (String filename : lists) {

            File root = new File(filename);

            //文件名数组
            String[] list = root.list();

            mergeFile(list, filename + File.separator + "part-r", filename + File.separator);

        }
    }


    /**
     * description: 合并文件
     * <br /><br />
     * create by mace on 2018/6/13 15:55.
     * @param fpaths 文件名数组
     * @param resultPath 合并后的文件完整路径
     * @param prefix 属于一组的文件路径前缀 不带文件名
     * @return: boolean
     */
    public static boolean mergeFile(String[] fpaths, String resultPath, String prefix) {

        if (fpaths == null || fpaths.length < 1 || StringUtils.isEmpty(resultPath)) {
            return false;
        }

        if (fpaths.length == 1) {
            return new File(fpaths[0]).renameTo(new File(resultPath));
        }

        File resultFile = new File(resultPath);

        try {
            FileChannel resultFileChannel = new FileOutputStream(resultFile, true).getChannel();

            for (int i = 0; i < fpaths.length; i ++) {
                FileChannel blk = new FileInputStream(prefix+fpaths[i]).getChannel();
                resultFileChannel.transferFrom(blk, resultFileChannel.size(), blk.size());
                blk.close();
            }
            resultFileChannel.close();

            for (int i = 0; i < fpaths.length; i ++) {
                File file = new File(prefix+fpaths[i]);
                file.delete();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


    /**
     * description: 把文件路径中的分隔符转换为unix的格式，也就是"/"
     * <br /><br />
     * create by mace on 2018/6/14 16:07.
     * @param path                  文件完整路径
     * @return: java.lang.String    转换后的路径
     */
    public static String separatorsToUnix(String path) {

        return FilenameUtils.separatorsToUnix(path);
    }

    /**
     * description: 把文件路径中的分隔符转换为window的格式，也就是"\"
     * <br /><br />
     * create by mace on 2018/6/14 16:06.
     * @param path                  文件完整路径
     * @return: java.lang.String    转换后的路径
     */
    public static String separatorsToWindows(String path) {

        return FilenameUtils.separatorsToWindows(path);
    }


    /**
     * description: 把文件路径中的分隔符转换当前系统的分隔符
     * <br /><br />
     * create by mace on 2018/6/14 16:06.
     * @param path                  文件完整路径
     * @return: java.lang.String    转换后的路径
     */
    public static String separatorsToSystem(String path) {

        return FilenameUtils.separatorsToSystem(path);
    }


    /**
     * description: 判断文件是否有某扩展名
     * <br /><br />
     * create by mace on 2018/6/14 16:09.
     * @param filename      文件完整路径
     * @param extension     扩展名名称
     * @return: boolean     若是，返回true，否则返回false
     */
    public static boolean isExtension(String filename, String extension) {

        return FilenameUtils.isExtension(filename, extension);
    }


    /**
     * description: 判断文件的扩展名是否是扩展名数组中的一个
     * <br /><br />
     * create by mace on 2018/6/14 16:09.
     * @param filename      文件完整路径
     * @param extensions    扩展名名称
     * @return: boolean     若是，返回true，否则返回false
     */
    public static boolean isExtension(String filename, String[] extensions) {

        return FilenameUtils.isExtension(filename, extensions);
    }


    /**
     * description: 提取文件的扩展名
     * <br /><br />
     * create by mace on 2018/6/14 16:10.
     * @param filename              文件名称
     * @return: java.lang.String    文件扩展名，若没有扩展名，则返回空字符串
     */
    public static String getExtension(String filename) {

        return FilenameUtils.getExtension(filename);
    }



    /**
     * description: 清空一个目录的内容，但不删除此目录
     * <br /><br />
     * create by mace on 2018/6/14 16:12.
     * @param directory     需要清空的目录
     * @return: boolean     true:清除成功 false:清除失败
     */
    public static boolean cleanDirectory(File directory) {

        try {
            FileUtils.cleanDirectory(directory);
            return true;
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            System.err.println("清除目录出错");
        }
        return false;
    }

}
