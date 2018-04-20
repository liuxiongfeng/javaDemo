package quartz;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.*;
import java.util.Date;


/**
 * @Author:LXF
 * @Date:Created in 14:19 2018/3/8
 * @Description:
 */
public class toJson {

    //定义常量
    public static String mysqlDriver = "com.mysql.jdbc.Driver";
    public static String mysqlurl = "jdbc:mysql://localhost:3306/d_question?useUnicode=true&characterEncoding=UTF-8";
    private static int i = 0;//统计数目


    //主函数
    public static void main(String[] args) throws IOException, Exception{
        long start = System.currentTimeMillis(); //开始时间
        Date date1 = new Date(System.currentTimeMillis());
        System.out.println(date1);



        getProvince();



        long end = System.currentTimeMillis();//结束时间
        Date date2 = new Date(System.currentTimeMillis());
        System.out.println(date2);
        System.out.println((end-start) / 1000.0 + "秒");//运行时间
        System.out.println((end-start)/60000.0+"分钟");//运行时间
    }

    public static void getProvince() throws Exception {
        String sqlstr = "select DISTINCT code,name,jpinyin,pinyin from dic_province where name is not null order by code";
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

            getCity(rs.getString("code"),mysqlconn);
        }
        mapPrintln(province,"D:\\AAA\\province\\0.json");// 输出
        i++;
        System.out.println(i);
        mysqlconn.close();
        st.close();
        rs.close();
        //jsonDataFileService.saveJsonFile('./dic/region/province/0.json', JSON.stringify(province));
    }

    public static void getCity(String code, Connection mysqlconn) throws Exception {
        String sqlstr = "select DISTINCT code,name,jpinyin,pinyin from dic_city where code like '" + code + "__' order by code";

        Statement st = mysqlconn.createStatement();

        ResultSet rs = st.executeQuery(sqlstr);
        ArrayList<Map<String, Object>> city = new ArrayList<>();
        while (rs.next()) {
            Map<String, Object> map = new HashMap<>();
            map.put("code", rs.getString("code"));
            map.put("label", rs.getString("name"));
            map.put("jpinyin", rs.getString("jpinyin"));
            map.put("pinyin", rs.getString("pinyin"));
            city.add(map);
            getDistrict(rs.getString("code"), mysqlconn);
        }
        if (city.size() != 0){  //省级单位下有下一级
            mapPrintln(city,"D:\\AAA\\city\\" + code + ".json");// 输出
            i++;
        }
        st.close();
        rs.close();
    }

    public static void getDistrict(String code, Connection mysqlconn) throws Exception {
        String sqlstr = "select DISTINCT code,name,jpinyin,pinyin from dic_district where code like '" + code + "__' order by code";

        Statement st = mysqlconn.createStatement();
        ResultSet rs = st.executeQuery(sqlstr);
        ArrayList<Map<String, Object>> district = new ArrayList<>();
        while (rs.next()) {
            Map<String, Object> map = new HashMap<>();
            map.put("code", rs.getString("code"));
            map.put("label", rs.getString("name"));
            map.put("jpinyin", rs.getString("jpinyin"));
            map.put("pinyin", rs.getString("pinyin"));
            district.add(map);
            getStreet(rs.getString("code"), mysqlconn);
        }
        if (district.size() != 0){  //市级单位下有一级
            mapPrintln(district,"D:\\AAA\\district\\" + code + ".json");// 输出
            i++;
        }
        st.close();
        rs.close();
        //jsonDataFileService.saveJsonFile('./dic/region/district/' + code + '.json', JSON.stringify(district));
    }

    public static void getStreet(String code, Connection mysqlconn) throws Exception {
        String sqlstr = "select DISTINCT code,name,jpinyin,pinyin from dic_street where code like '" + code + "___' order by code";

        Statement st = mysqlconn.createStatement();
        ResultSet rs = st.executeQuery(sqlstr);
        ArrayList<Map<String, Object>> street = new ArrayList<>();
        while (rs.next()) {
            Map<String, Object> map = new HashMap<>();
            map.put("code", rs.getString("code"));
            map.put("label", rs.getString("name"));
            map.put("jpinyin", rs.getString("jpinyin"));
            map.put("pinyin", rs.getString("pinyin"));
            street.add(map);
            getCommittee(rs.getString("code"), mysqlconn);
        }
        if (street.size() != 0){
            mapPrintln(street,"D:\\AAA\\street\\" + code + ".json");// 输出
            i++;
        }
        st.close();
        rs.close();
        //jsonDataFileService.saveJsonFile('./dic/region/street/' + code + '.json', JSON.stringify(street));
    }

    public static void getCommittee(String code, Connection mysqlconn) throws Exception {
        String sqlstr = "select DISTINCT code,name,jpinyin,pinyin from dic_committee where code like '" + code + "___' order by code";

        Statement st = mysqlconn.createStatement();
        ResultSet rs = st.executeQuery(sqlstr);
        List<Map<String,Object>> committee = new ArrayList<>();
        while (rs.next()) {
            Map<String,Object> map = new HashMap<>();
            map.put("code", rs.getString("code"));
            map.put("label", rs.getString("name"));
            map.put("jpinyin", rs.getString("jpinyin"));
            map.put("pinyin", rs.getString("pinyin"));
            committee.add(map);
        }
        if (committee.size() != 0){
            mapPrintln(committee,"D:\\AAA\\committee\\" + code + ".json");// 输出
            i++;
        }
        st.close();
        rs.close();
        //jsonDataFileService.saveJsonFile('./dic/region/committee/' + code + '.json', JSON.stringify(committee));
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
        Gson gson = new Gson();
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
