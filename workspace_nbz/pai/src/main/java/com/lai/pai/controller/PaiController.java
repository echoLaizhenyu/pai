package com.lai.pai.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
public class PaiController {
    @GetMapping("/pai/gua")
    public Object gua(){
        return setString();
    }

    @GetMapping("/pai/doubleBall")
    public Object doubleBall(){
        return getdoubleball();
    }

    @GetMapping("/pai/happyBall")
    public Object happyBall(){
        return gethappyball();
    }

    @GetMapping("/pai/qixingcai")
    public Object qixingcai(){
        return getQiXingCai();
    }
    public static void main(String args[])
    {
        setString();
        getdoubleball();
//        gethappyball();
//        getQiXingCai();
    }

    public static String saveNumber(String str)
    {
        String reg = "[\u4E00-\u9FA5]";
        Pattern pat = Pattern.compile(reg);
        Matcher mat = pat.matcher(str);
        String repickStr = mat.replaceAll("");
        repickStr = repickStr.replaceAll("[^0-9^.]", "");
        return repickStr;
    }

    public static String setString()
    {
        String str = "";
        List list = new ArrayList();
        StringBuffer sb = new StringBuffer();
        String jj[] = new String[54];
        int u = 0;
        String huase[] = {
                "\u65B9\u7247", "\u7EA2\u6843", "\u9ED1\u6843", "\u6885\u82B1"
        };
        for(int o = 4; o > 0; o--)
        {
            for(int p = 1; p < 14; p++)
            {
                list.add((new StringBuilder(String.valueOf(p))).append(huase[o - 1]).toString());
                u++;
            }

        }

        list.add("\u5927\u738B");
        list.add("\u5C0F\u738B");
        for(int i = 53; i >= 0; i--)
        {
            int b = (int)(Math.random() * (double)i);
            jj[i] = list.get(b).toString();
            list.remove(b);
        }

        List listb = new ArrayList();
        int qq = 0;
        int ww = 53;
        for(int i = 0; i < 27; i++)
        {
            if(saveNumber(jj[qq]).equals(saveNumber(jj[ww])))
            {
                sb.append((new StringBuilder("\u76F8\u540C\u7684---")).append(jj[qq]).append(",").append(jj[ww]).append("<br>").toString());
                System.out.println((new StringBuilder("\u76F8\u540C\u7684---")).append(jj[qq]).append(",").append(jj[ww]).append("<br>").toString());
            } else
            {
                listb.add(jj[ww]);
                listb.add(jj[qq]);
            }
            qq++;
            ww--;
        }

        List listc = new ArrayList();
        qq = 0;
        ww = listb.size() - 1;
        for(int i = 0; i < listb.size() / 2; i++)
        {
            if(saveNumber(listb.get(qq).toString()).equals(saveNumber(listb.get(ww).toString())))
            {
                sb.append((new StringBuilder("\u76F8\u540C\u7684---")).append(listb.get(qq)).append(",").append(listb.get(ww)).append("<br>").toString());
                System.out.println((new StringBuilder("\u76F8\u540C\u7684---")).append(listb.get(qq)).append(",").append(listb.get(ww)).append("<br>").toString());
            } else
            {
                listc.add(listb.get(ww));
                listc.add(listb.get(qq));
            }
            qq++;
            ww--;
        }

        List listd = new ArrayList();
        qq = 0;
        ww = listc.size() - 1;
        for(int i = 0; i < listc.size() / 2; i++)
        {
            if(saveNumber(listc.get(qq).toString()).equals(saveNumber(listc.get(ww).toString())))
            {
                sb.append((new StringBuilder("\u76F8\u540C\u7684---")).append(listc.get(qq)).append(",").append(listc.get(ww)).append("<br>").toString());
                System.out.println((new StringBuilder("\u76F8\u540C\u7684---")).append(listc.get(qq)).append(",").append(listc.get(ww)).append("<br>").toString());
            } else
            {
                listd.add(listc.get(ww));
                listd.add(listc.get(qq));
            }
            qq++;
            ww--;
        }

        int cc = listd.size() - 1;
        for(int i = 0; i < 3; i++)
        {
            int b = (int)(1.0D + Math.random() * (double)cc);
            sb.append((new StringBuilder("\u62BD\u51FA\u7684\u7B2C")).append(i).append("\u5F20\u724C\u3002\u3002\u3002").append(listd.get(b).toString()).append("<br>").toString());
            System.out.println((new StringBuilder("\u62BD\u51FA\u7684\u7B2C")).append(i).append("\u5F20\u724C\u3002\u3002\u3002").append(listd.get(b).toString()).append("<br>").toString());
            cc--;
            listd.remove(b);
        }

        return sb.toString();
    }

    public static String getBase64(String str)
    {
        byte b[] = (byte[])null;
        String s = null;
        try
        {
            b = str.getBytes("utf-8");
        }
        catch(UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
        if(b != null)
            s = (new BASE64Encoder()).encode(b);
        return s;
    }

    public static String getdoubleball()
    {
        String redball[] = {
                "01", "02", "03", "04", "05", "06", "07", "08", "09", "10",
                "11", "12", "13", "14", "15", "16", "17", "18", "19", "20",
                "21", "22", "23", "24", "25", "26", "27", "28", "29", "30",
                "31", "32", "33"
        };
        List list = new ArrayList();
        List lista = new ArrayList();
        StringBuffer sb = new StringBuffer();
        for(int p = 1; p < 34; p++)
            list.add((new StringBuilder(String.valueOf(p))).toString());

        for(int p = 1; p < 17; p++)
            lista.add((new StringBuilder(String.valueOf(p))).toString());

        int cc = list.size() - 1;
        for(int i = 0; i < 6; i++)
        {
            int b = (int)(1.0D + Math.random() * (double)cc);
            sb.append((new StringBuilder("\u62BD\u51FA\u7684\u7B2C")).append(i).append("\u4E2A\u7EA2\u7403\u4E3A").append(list.get(b).toString()).append("<br>").toString());
            System.out.println((new StringBuilder("\u62BD\u51FA\u7684\u7B2C")).append(i).append("\u4E2A\u7EA2\u7403\u4E3A").append(list.get(b).toString()).toString());
            cc--;
            list.remove(b);
        }

        int dd = lista.size() - 1;
        for(int i = 0; i < 1; i++)
        {
            int b = (int)(1.0D + Math.random() * (double)dd);
            sb.append((new StringBuilder("\u62BD\u51FA\u7684\u7B2C")).append(i).append("\u4E2A\u84DD\u7403\u4E3A").append(lista.get(b).toString()).append("<br>").toString());
            System.out.println((new StringBuilder("\u62BD\u51FA\u7684\u7B2C")).append(i).append("\u4E2A\u84DD\u7403\u4E3A").append(lista.get(b).toString()).toString());
            cc--;
            lista.remove(b);
        }

        return sb.toString();
    }

    public static String getQiXingCai()
    {
        StringBuffer sb = new StringBuffer();
        int cc = 10;
        for(int i = 0; i < 7; i++)
        {
            int b = (int)(1.0D + Math.random() * (double)cc);
            sb.append((new StringBuilder("\u62BD\u51FA\u7684\u7B2C")).append(i).append("\u4E2A\u7403\u4E3A").append(b - 1).toString());
            System.out.println((new StringBuilder("\u62BD\u51FA\u7684\u7B2C")).append(i).append("\u4E2A\u7403\u4E3A").append(b - 1).append("<br>").toString());
        }

        return sb.toString();
    }

    public static String gethappyball()
    {
        List list = new ArrayList();
        List lista = new ArrayList();
        StringBuffer sb = new StringBuffer();
        for(int p = 1; p < 36; p++)
            list.add((new StringBuilder(String.valueOf(p))).toString());

        for(int p = 1; p < 13; p++)
            lista.add((new StringBuilder(String.valueOf(p))).toString());

        int cc = list.size() - 1;
        for(int i = 0; i < 5; i++)
        {
            int b = (int)(1.0D + Math.random() * (double)cc);
            sb.append((new StringBuilder("\u62BD\u51FA\u7684\u7B2C")).append(i).append("\u4E2A\u7EA2\u7403\u4E3A").append(list.get(b).toString()).append("<br>").toString());
            System.out.println((new StringBuilder("\u62BD\u51FA\u7684\u7B2C")).append(i).append("\u4E2A\u7EA2\u7403\u4E3A").append(list.get(b).toString()).toString());
            cc--;
            list.remove(b);
        }

        int dd = lista.size() - 1;
        for(int i = 0; i < 2; i++)
        {
            int b = (int)(1.0D + Math.random() * (double)dd);
            sb.append((new StringBuilder("\u62BD\u51FA\u7684\u7B2C")).append(i).append("\u4E2A\u84DD\u7403\u4E3A").append(lista.get(b).toString()).append("<br>").toString());
            System.out.println((new StringBuilder("\u62BD\u51FA\u7684\u7B2C")).append(i).append("\u4E2A\u84DD\u7403\u4E3A").append(lista.get(b).toString()).toString());
            dd--;
            lista.remove(b);
        }

        return sb.toString();
    }

}
