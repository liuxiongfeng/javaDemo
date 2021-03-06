import com.google.gson.Gson;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.*;
import java.util.Date;


/**
*@Author:   LXF
*@Date:   13:59 2018/3/12
*@Description:     $将数据库中的数据按照行政级别导出成json文件
*/
public class toJson2 {
    //定义常量
    public static String mysqlDriver = "com.mysql.jdbc.Driver";
    public static String mysqlurl = "jdbc:mysql://10.1.65.12:3306/ci_bem_d_question?useUnicode=true&characterEncoding=UTF-8";
    //主函数
    public static void main(String[] args) throws IOException, Exception{
        long start = System.currentTimeMillis(); //开始时间
        Date date1 = new Date(System.currentTimeMillis());
        System.out.println(date1);

        findProvince();
        findCity();
        findDistrict();
        findStreet();
        findCommittee();

        long end = System.currentTimeMillis();//结束时间
        Date date2 = new Date(System.currentTimeMillis());
        System.out.println(date2);
        System.out.println((end-start) / 1000.0 + "秒");//运行时间
        System.out.println((end-start)/60000.0+"分钟");//运行时间
    }
    public static void findProvince() throws Exception {
        String sqlstr = "select DISTINCT code,name,jpinyin,pinyin from DIC_PROVINCE where name is not null order by code";
        //连接数据库
        Class.forName(mysqlDriver);//jvm查找并加载类，执行该类的静态方法ManangerDriver.registerDriver(...)
        Connection mysqlconn = DriverManager.getConnection(mysqlurl, "root", "root");//连接数据库，用户名，密码
        Statement st = mysqlconn.createStatement();

        ResultSet rs = st.executeQuery(sqlstr);

        ArrayList<Map<String, Object>> province = new ArrayList<>();

        while (rs.next()) {
            Map<String, Object> map = new HashMap<>();
            map.put("code", rs.getString("code"));
            map.put("label", rs.getString("name"));
            map.put("jpinyin", rs.getString("jpinyin"));
            map.put("pinyin", rs.getString("pinyin"));
            province.add(map);
        }
        mapPrintln(province,"D:\\AAA\\province\\0.json");// 输出
        mysqlconn.close();
        st.close();
        rs.close();
        System.out.println("success province");
    }

    public static void findCity() throws Exception {
        //连接数据库
        Class.forName(mysqlDriver);//jvm查找并加载类，执行该类的静态方法ManangerDriver.registerDriver(...)
        Connection mysqlconn = DriverManager.getConnection(mysqlurl, "root", "root");//连接数据库，用户名，密码

        String sqlstr = "select DISTINCT code,name,jpinyin,pinyin from DIC_CITY order by code";
        Statement st = mysqlconn.createStatement();
        ResultSet rs = st.executeQuery(sqlstr);
        ArrayList<Map<String, Object>> city = new ArrayList<>();
        String code = "";
        while (rs.next()) {
            if (code.equals("")){
                code = rs.getString("code").substring(0,2);
            }
            if (rs.getString("code").substring(0,2).equals(code)){
                Map<String, Object> map = new HashMap<>();
                map.put("code", rs.getString("code"));
                map.put("label", rs.getString("name"));
                map.put("jpinyin", rs.getString("jpinyin"));
                map.put("pinyin", rs.getString("pinyin"));
                city.add(map);
            }else {
                if (city.size() != 0){
                    mapPrintln(city,"D:\\AAA\\city\\" + code + ".json");// 输出
                    code = rs.getString("code").substring(0,2);
                    city.clear();
                    Map<String, Object> map = new HashMap<>();
                    map.put("code", rs.getString("code"));
                    map.put("label", rs.getString("name"));
                    map.put("jpinyin", rs.getString("jpinyin"));
                    map.put("pinyin", rs.getString("pinyin"));
                    city.add(map);
                }
            }

        }
        mapPrintln(city,"D:\\AAA\\city\\" + code + ".json");// 输出
        st.close();
        rs.close();
        System.out.println("success city");
    }

    public static void findDistrict() throws Exception {
        //连接数据库
        Class.forName(mysqlDriver);//jvm查找并加载类，执行该类的静态方法ManangerDriver.registerDriver(...)
        Connection mysqlconn = DriverManager.getConnection(mysqlurl, "root", "root");//连接数据库，用户名，密码

        String sqlstr = "select DISTINCT code,name,jpinyin,pinyin from DIC_DISTRICT WHERE LENGTH(code) = 6 order by code";
        Statement st = mysqlconn.createStatement();
        ResultSet rs = st.executeQuery(sqlstr);
        ArrayList<Map<String, Object>> district = new ArrayList<>();
        String code = "";
        while (rs.next()) {
            if (code.equals("")){
                code = rs.getString("code").substring(0,4);
            }
            if (rs.getString("code").substring(0,4).equals(code)){
                Map<String, Object> map = new HashMap<>();
                map.put("code", rs.getString("code"));
                map.put("label", rs.getString("name"));
                map.put("jpinyin", rs.getString("jpinyin"));
                map.put("pinyin", rs.getString("pinyin"));
                district.add(map);
            }else {
                if (district.size() != 0){
                    mapPrintln(district,"D:\\AAA\\district\\" + code + ".json");// 输出
                    code = rs.getString("code").substring(0,4);
                    district.clear();
                    Map<String, Object> map = new HashMap<>();
                    map.put("code", rs.getString("code"));
                    map.put("label", rs.getString("name"));
                    map.put("jpinyin", rs.getString("jpinyin"));
                    map.put("pinyin", rs.getString("pinyin"));
                    district.add(map);
                }
            }

        }
        mapPrintln(district,"D:\\AAA\\district\\" + code + ".json");// 最后一段记录输出
        st.close();
        rs.close();
        System.out.println("success district");
    }

    public static void findStreet() throws Exception {
        //连接数据库
        Class.forName(mysqlDriver);//jvm查找并加载类，执行该类的静态方法ManangerDriver.registerDriver(...)
        Connection mysqlconn = DriverManager.getConnection(mysqlurl, "root", "root");//连接数据库，用户名，密码

        String sqlstr = "select DISTINCT code,name,jpinyin,pinyin from DIC_STREET WHERE LENGTH(code) = 9 order by code";
        Statement st = mysqlconn.createStatement();
        ResultSet rs = st.executeQuery(sqlstr);
        ArrayList<Map<String, Object>> street = new ArrayList<>();
        String code = "";
        while (rs.next()) {
            if (code.equals("")){
                code = rs.getString("code").substring(0,6);
            }
            if (rs.getString("code").substring(0,6).equals(code)){
                Map<String, Object> map = new HashMap<>();
                map.put("code", rs.getString("code"));
                map.put("label", rs.getString("name"));
                map.put("jpinyin", rs.getString("jpinyin"));
                map.put("pinyin", rs.getString("pinyin"));
                street.add(map);
            }else {
                if (street.size() != 0){
                    mapPrintln(street,"D:\\AAA\\street\\" + code + ".json");// 输出
                    code = rs.getString("code").substring(0,6);
                    street.clear();
                    Map<String, Object> map = new HashMap<>();
                    map.put("code", rs.getString("code"));
                    map.put("label", rs.getString("name"));
                    map.put("jpinyin", rs.getString("jpinyin"));
                    map.put("pinyin", rs.getString("pinyin"));
                    street.add(map);
                }
            }

        }
        mapPrintln(street,"D:\\AAA\\street\\" + code + ".json");// 最后一段记录输出
        st.close();
        rs.close();
        System.out.println("success street");
    }
    public static void findCommittee() throws Exception {
        //连接数据库
        Class.forName(mysqlDriver);//jvm查找并加载类，执行该类的静态方法ManangerDriver.registerDriver(...)
        Connection mysqlconn = DriverManager.getConnection(mysqlurl, "root", "root");//连接数据库，用户名，密码

        String sqlstr = "select DISTINCT code,name,jpinyin,pinyin from DIC_COMMITTEE WHERE LENGTH(code) = 12 order by code";
        Statement st = mysqlconn.createStatement();
        ResultSet rs = st.executeQuery(sqlstr);
        ArrayList<Map<String, Object>> committee = new ArrayList<>();
        String code = "";
        while (rs.next()) {
            if (code.equals("")){
                code = rs.getString("code").substring(0,9);
                Map<String, Object> map = new HashMap<>();
                map.put("code", rs.getString("code"));
                map.put("label", rs.getString("name"));
                map.put("jpinyin", rs.getString("jpinyin"));
                map.put("pinyin", rs.getString("pinyin"));
                committee.add(map);
            }else if (rs.getString("code").substring(0,9).equals(code)){
                Map<String, Object> map = new HashMap<>();
                map.put("code", rs.getString("code"));
                map.put("label", rs.getString("name"));
                map.put("jpinyin", rs.getString("jpinyin"));
                map.put("pinyin", rs.getString("pinyin"));
                committee.add(map);
            }else {
                if (committee.size() != 0){
                    mapPrintln(committee,"D:\\AAA\\committee\\" + code + ".json");// 输出
                    code = rs.getString("code").substring(0,9);
                    committee.clear();
                    Map<String, Object> map = new HashMap<>();
                    map.put("code", rs.getString("code"));
                    map.put("label", rs.getString("name"));
                    map.put("jpinyin", rs.getString("jpinyin"));
                    map.put("pinyin", rs.getString("pinyin"));
                    committee.add(map);
                }
            }
        }
        mapPrintln(committee,"D:\\AAA\\committee\\" + code + ".json");// 最后一段记录输出
        st.close();
        rs.close();
        System.out.println("success committee");
    }
    /**
     *@Author:   LXF
     *@Date:   10:26 2018/3/9
     *@Description:     将List<Map<String, Object>>对象保存在.json文件中
     */
    private static void mapPrintln(List<Map<String, Object>> list,String path) {
        if (list == null && list.size() == 0) {
            return;
        }
        Gson gson = new Gson(); //需要导入com.google.code.gson的jar包
        String jsonString = gson.toJson(list);
        inputFile(jsonString,path);// json文件
        //System.out.println(jsonString);// 打印
    }

    private static void inputFile(final String jsonString, final String path) {
        // TODO Auto-generated method stub
        new Thread(new Runnable() {

            public void run() {
                // TODO Auto-generated method stub
                WriteConfigJson(jsonString,path);
            }
        }).start();
    }

    /**
     * 输出json文件
     *
     * @param args
     */
    public static void WriteConfigJson(String args,String path) {
        //String src = "D:\\file\\province.json";// 自定义文件路径

        File file = new File(path);

        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        try {
            file.delete();
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            FileWriter fw = new FileWriter(file, true);
            fw.write(args);
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
