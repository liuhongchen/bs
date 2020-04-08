package com.liuhongchen.bscommonutils.common;

import com.liuhongchen.bscommondto.common.Dto;
import com.liuhongchen.bscommondto.common.DtoUtil;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ClassName:CheckUtils
 * Package:com.liuhongchen.bscommonutils.common
 * Description:
 *
 * @date:2020-04-08 15:00
 * @author:892698613@qq.com
 */
public class CheckUtils {

    /**
     * 验证手机号码格式
     * @param phone
     * @return 1格式正确, 0长度错误, 2格式错误
     */
    public static int checkPhone(String phone){
        String regex = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(17[013678])|(18[0,5-9]))\\d{8}$";
        if (phone.length() != 11) {
            System.out.println("手机号应为11位数");
            return 0;
        } else {
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(phone);
            boolean isMatch = m.matches();
            if (isMatch) {
                return 1;
            } else {
                return 2;

            }
        }
    }


    public static boolean paramNullCheck(Object... params){
        for (Object param : params) {
            if (!EmptyUtils.isEmpty(param)) {
                return false;
            }
        }
        return true;
    }
}
