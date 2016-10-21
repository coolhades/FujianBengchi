package cn.com.mbmpv.trainingonline.utils;

/**
 * Created by jiuzheyange on 2016/8/13.
 */
public class RegularUtils {
    /**
     * 验证邮箱格式是否正确
     */
    public static boolean emailValidation(String email) {
        String regex = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
        return email.matches(regex);
    }
}
