package cn.com.mbmpv.trainingonline.utils;

import android.text.TextUtils;

import java.io.File;

/**
 * Created by jiuzheyange on 2016/8/22.
 */
public class FileUtils {

    /**
     * 删除指定目录下文件及目录 
     *
     * @param deleteThisPath
     * @param //filepath
     * @return
     */
    public static void deleteFolderFile(String filePath, boolean deleteThisPath) {
        if (!TextUtils.isEmpty(filePath)) {
            try {
                File file = new File(filePath);
                if (file.isDirectory()) {// 如果下面还有文件  
                    File files[] = file.listFiles();
                    for (int i = 0; i < files.length; i++) {
                        deleteFolderFile(files[i].getAbsolutePath(), true);
                    }
                }
                if (deleteThisPath) {
                    if (!file.isDirectory()) {// 如果是文件，删除  
                        file.delete();
                    } else {// 目录  
                        if (file.listFiles().length == 0) {// 目录下没有文件或者目录，删除  
                            file.delete();
                        }
                    }
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block  
                e.printStackTrace();
            }
        }
    }

}
