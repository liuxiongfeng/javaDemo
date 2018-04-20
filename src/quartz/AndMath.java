package quartz;

import java.util.*;

public class AndMath {
    static String vo1 = "李沐晓\t女性\t2017-08-06\t下应街道下应社区居委会\t宁波明州医院\t2018-01-23 12:01:14\t已匹配\n" +
            "吴奕翰\t男性\t2017-06-16\t中河街道东湖社区居委会\t中河街道社区卫生服务中心\t2018-02-25 13:37:46\t已匹配\n" +
            "陈芷涵\t女性\t2017-05-15\t下应街道下应社区居委会\t宁波明州医院\t2018-01-21 17:01:58\t已新增\n" +
            "丁颢迪\t男性\t2017-02-16\t钟公庙街道\t鄞州第二医院\t2018-01-07 17:01:59\t待落实\n" +
            "刘康\t男性\t2016-03-01\t钟公庙街道\t中河街道社区卫生服务中心\t2018-01-18 12:56:35\t已新增\n" +
            "何抒芸\t女性\t2016-01-17\t姜山镇杨家弄村委会\t姜山镇茅山卫生院\t2018-03-05 11:16:30\t待落实\n" +
            "何抒芸\t女性\t2016-01-17\t姜山镇杨家弄村委会\t姜山镇茅山卫生院\t2018-03-06 11:24:42\t已新增\n" +
            "叶芋翔\t男性\t2015-10-05\t姜山镇井亭村委会\t鄞州区第三医院\t2018-03-15 08:42:45\t已匹配\n" +
            "吴熙罗\t女性\t2015-08-24\t中河街道兴裕社区居委会\t鄞州第二医院\t2018-03-31 11:03:55\t待落实\n" +
            "徐怿然\t女性\t2015-04-13\t中河街道金馨社区居委会\t鄞州人民医院\t2018-03-01 00:00:00\t已匹配\n";


    //static String vo = vo1.replaceAll("Street","TuberHospitalStreetVO");
    static String vo = "";
    public static void main(String[] args) {

        //String ss = gg("type",1);
        String vo = vo1.replaceAll("\\t|\\n",",");
        System.out.println(toAsList(vo));


    }

    public static String gg(String name1,Integer num1,String name2,Integer num2,String name3,Integer num3){
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i <= num1; i++) {
            for (int j = 0; j <= num2; j++) {
                for (int k = 0; k <= num3; k++) {
                    if (i == 0 && j == 0 && k == 0){
                        String st = "if(fo.get"+bigName(name1)+"() == "+i+" && fo.get"+bigName(name2)+"() == "+j+" && fo.get"+bigName(name3)+"() == "+k+"){\n" +
                                vo+"\n" +
                                "}";
                        sb.append(st);
                    }else {
                        String st = "else if(fo.get"+bigName(name1)+"() == "+i+" && fo.get"+bigName(name2)+"() == "+j+" && fo.get"+bigName(name3)+"() == "+k+"){\n" +
                                vo+"\n" +
                                "}";
                        sb.append(st);
                    }
                }
            }
        }
        return sb.toString();
    }


    public static String gg(String name1,Integer num1,String name2,Integer num2){
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i <= num1; i++) {
            for (int j = 0; j <= num2; j++) {
                if (i == 0 && j == 0) {
                    String st = "if(fo.get" + bigName(name1) + "() == " + i + " && fo.get" + bigName(name2) + "() == " + j + "){\n" +
                            vo+"\n" +
                            "}";
                    sb.append(st);
                } else {
                    String st = "else if(fo.get" + bigName(name1) + "() == " + i + " && fo.get" + bigName(name2) + "() == " + j + "){\n" +
                            vo+"\n" +
                            "}";
                    sb.append(st);
                }

            }
        }
        return sb.toString();
    }
    public static String gg(String name1,Integer num1) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i <= num1; i++) {
            if (i == 0) {
                String st = "if(fo.get" + bigName(name1) + "() == " + i + "){\n" +
                        vo+"\n" +
                        "}";
                sb.append(st);
            } else {
                String st = "else if(fo.get" + bigName(name1) + "() == " + i + "){\n" +
                        vo+"\n" +
                        "}";
                sb.append(st);
            }
        }
        return sb.toString();
    }

    public static String bigName(String name) {
        name = name.substring(0,1).toUpperCase() + name.substring(1);
        return  name;
    }



    public  static  String toAsList(String data){
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Arrays.asList(");
        String[] ss = data.split(",");
        for (int i = 0; i < ss.length; i+=7) {
            stringBuffer.append("Arrays.asList(\""+ss[i].substring(0,ss[i].length()-1)+"*\",\""+ss[i+1]+"\",\""+ss[i+2]+"\",\""+ss[i+3]+"\",\""+ss[i+4]+"\",\""+ss[i+5]+"\",\""+ss[i+6]+"\"),\n");
        }

        return stringBuffer.substring(0,stringBuffer.length()-2)+")";
    }

}

