package com.ydp.controller;

import cn.hutool.core.util.HexUtil;
import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.crypto.digests.SM3Digest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/coupon_xm")
public class CouponXmController {
    /***
     * @param data 需要加密的字符串
     * @return 加密后的字符串
     */
    public static String encrypt(String data) {
        // 将返回的hash值转换成16进制字符串
        String resultHexString = null;
        try {
            // 将字符串转换成byte数组
            if (null == data || data.equals("")) {
                return null;
            }
            byte[] srcData = data.getBytes("UTF-8");
            SM3Digest digest = new SM3Digest();
            digest.update(srcData, 0, srcData.length);
            byte[] result = new byte[digest.getDigestSize()];
            digest.doFinal(result, 0);
            // 将返回的hash值转换成16进制字符串
            resultHexString = HexUtil.encodeHexStr(result);
        } catch (Exception e) {
            log.error("加密异常", e);
        } finally {
            return resultHexString;
        }
    }

   /* public static void main(String[] args) {
        String result = encrypt("1" + "_" + "142330199912090719");
        if (null != result) {
            System.out.println(result);
            System.out.println(result.length());
        } else {
            System.out.println("加密失败");
        }
    }*/

    //    @GetMapping("/testEncrypt")
    //用户信息加密
    @PostMapping("/testEncrypt")
    public String testEncrypt(String idNumber) {
        //格式：资格码_序列号_种类编码
        //中华人民共和国居民身份证   证件类型：1
        String result = encrypt("1" + "_" + idNumber);
        System.out.println("输入参数："+"1" + "_" + idNumber);
        if (null != result) {
            System.out.println("结果：" + result);
           // System.out.println("长度：" + result.length());
            return result;
        } else {
            log.warn("加密失败");
            return "加密失败";
        }
    }

}
