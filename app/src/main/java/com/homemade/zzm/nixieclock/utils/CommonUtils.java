package com.homemade.zzm.nixieclock.utils;

import com.homemade.zzm.nixieclock.R;

/**
 * Created by 92010 on 2017/6/7.
 */

public class CommonUtils {
    public static int getPictureResource(char ca) {
        String str = String.valueOf(ca);
        switch (str) {
            case "0":
                return R.mipmap.zero;
            case "1":
                return R.mipmap.one;
            case "2":
                return R.mipmap.two;
            case "3":
                return R.mipmap.three;
            case "4":
                return R.mipmap.four;
            case "5":
                return R.mipmap.five;
            case "6":
                return R.mipmap.six;
            case "7":
                return R.mipmap.seven;
            case "8":
                return R.mipmap.eight;
            case "9":
                return R.mipmap.nine;
            case ":":
                return R.mipmap.colon;
            case ".":
                return R.mipmap.point;
            default:
                return R.mipmap.ic_launcher;
        }
    }
}
