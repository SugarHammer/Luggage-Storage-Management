package io.renren.common.utils;

import java.util.Random;

/**
 * Created by Administrator on 2021/4/25.
 */

public class InvertCodeGenerator{
    /**

     * java生成随机数字和字母组合10位数


     * @return

     */

    public static String getRandomNickname(int length) {
        String val = "";

        Random random = new Random();

        for (int i = 0; i < length; i++) {
// 输出字母还是数字

            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";

// 字符串

            if ("char".equalsIgnoreCase(charOrNum)) {
// 取得大写字母还是小写字母

                int choice = random.nextInt(2) % 2 == 0 ? 65 : 97;

                val += (char) (choice + random.nextInt(26));

            } else if ("num".equalsIgnoreCase(charOrNum)) { // 数字

                val += String.valueOf(random.nextInt(10));

            }

        }

        return val;

    }

    public static void main(String[] args) {
        System.out.println("java生成随机数字和字母组合10位数：" + getRandomNickname(10));

    }

}