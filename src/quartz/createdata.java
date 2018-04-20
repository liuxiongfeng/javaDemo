package quartz;

import java.io.*;
import java.net.SocketTimeoutException;
import java.sql.*;
import java.util.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class createdata {
    //定义常量
    public static String mysqlDriver = "com.mysql.jdbc.Driver";
    public static String mysqlurl = "jdbc:mysql://localhost:3306/datatest?useUnicode=true&characterEncoding=UTF-8";
    //public static String mysqlurl = "jdbc:mysql://localhost:3306/increasedata?useUnicode=true&characterEncoding=UTF-8";

    public static String base = "abcdefghijklmnopqrstuvwxyz0123456789";
    private static String firstName="赵钱孙李周吴郑王冯陈褚卫蒋沈韩杨朱秦尤许何吕施张孔曹严华金魏陶姜戚谢邹喻柏水窦章云苏潘葛奚范" +
            "彭郎鲁韦昌马苗凤花方俞任袁柳酆鲍史唐费廉岑薛雷贺倪汤滕殷罗毕郝邬安常乐于时傅皮卞齐康伍余元卜顾孟平黄和穆萧尹姚邵湛汪祁" +
            "毛禹狄米贝明臧计伏成戴谈宋茅庞熊纪舒屈项祝董梁杜阮蓝闵席季麻强贾路娄危江童颜郭梅盛林刁钟徐邱骆高夏蔡田樊胡凌霍虞万支柯" +
            "咎管卢莫经房裘缪干解应宗宣丁贲邓郁单";
    private static String secondName="元胜学祥才发武新利清飞彬富顺信子杰涛昌成康星光天达安岩中茂进林有坚和彪博诚先敬震振壮会思" +
            "群豪心邦承乐绍功松善厚庆磊民友裕河哲江超浩亮政谦亨奇固之轮翰朗伯宏言若鸣朋斌梁栋维启克伦翔旭鹏泽晨辰士以建家致树炎德" +
            "行时泰盛雄琛钧冠策腾楠榕风航弘筠柔竹霭凝晓欢霄枫芸菲寒伊亚宜可姬舒影荔枝思丽秀娟英华慧巧美娜静淑惠珠翠雅芝玉萍娥玲芬" +
            "芳燕彩春菊勤珍素云莲真环雪荣爱妹霞香月莺媛 艳瑞凡佳嘉琼璐娅琦晶妍茜秋珊莎锦黛青倩婷姣婉娴瑾颖露瑶怡婵雁蓓纨仪荷丹蓉" +
            "眉君琴蕊薇菁梦岚苑婕馨瑗琰韵融园艺";
    private static final String[] email_suffix=("@gmail.com,@yahoo.com,@msn.com,@hotmail.com,@aol.com,@ask.com,@live.com," +
            "@qq.com,@0355.net,@163.com,@163.net,@263.net,@3721.net,@yeah.net,@googlemail.com,@126.com,@sina.com," +
            "@sohu.com,@yahoo.com.cn").split(",");
    private static String[] telFirst="134,135,136,137,138,139,150,151,152,157,158,159,130,131,132,155,156,133,153".split(",");

    //主函数
    public static void main(String[] args) throws IOException, Exception{
        long start = System.currentTimeMillis(); //开始时间
        Date date1 = new Date(System.currentTimeMillis());
        System.out.println(date1);
        //连接数据库
        Class.forName(mysqlDriver);//jvm查找并加载类，执行该类的静态方法ManangerDriver.registerDriver(...)
        Connection mysqlconn = DriverManager.getConnection(mysqlurl, "root", "root");//连接数据库，用户名，密码
        mysqlconn.setAutoCommit(false);//sql语句由应用程序执行
        //Statement stmt = mysqlconn.createStatement();//创建一个执行SQL语句的对象stmt
        PreparedStatement pst = null;//不用statement，用PrepareStatement作为执行SQL语句的对象

        //SQL
        String sql = "insert into datamillion2(sname,description,creat_time,asset,weight,address,email,moblie,habit,school) " +
                "values(?,?,?,?,?,?,?,?,?,?)";
        pst = mysqlconn.prepareStatement(sql);
        //循环处理
        for (int i = 0; i < 2000000; i++) {
            String name1 = getChineseName();
            String description2 = getRandomJianHan(10);
            java.util.Date date = new java.util.Date();//获得系统时间.
            Timestamp create_time3 = new Timestamp(date.getTime());
            float asset4 = (float)getNum(100,2000000);
            float weight5 = (float)getNum(100,200);
            String address6 = getRoad();
            String email7 = getEmail(15,18);
            String mobile8 = getTel();
            String habit9 = getHabit();
            String school0 = getSchool();
            pst.setString(1, name1);
            pst.setString(2, description2);
            pst.setTimestamp(3, create_time3);
            pst.setFloat(4, asset4);
            pst.setFloat(5, weight5);
            pst.setString(6, address6);
            pst.setString(7, email7);
            pst.setString(8, mobile8);
            pst.setString(9, habit9);
            pst.setString(10, school0);
            pst.addBatch();//添加需要批量处理的SQL语句

            if ((i + 1) % 10000 == 0) {
                pst.executeBatch();//执行批量处理语句
                mysqlconn.commit();//事务提交
                pst.clearBatch();//清除处理过的SQL语句
            }
        }
        long end = System.currentTimeMillis();//结束时间
        Date date2 = new Date(System.currentTimeMillis());
        System.out.println(date2);
        System.out.println((end-start) / 1000.0 + "秒");//运行时间
        System.out.println((end-start)/60000.0+"分钟");//运行时间
        //关闭连接
        //stmt.close();
        pst.close();
        mysqlconn.close();
    }
    /**
     * 返回Email
     * @param lMin 最小长度
     * @param lMax 最大长度
     * @return
     */
    public static String getEmail(int lMin,int lMax) {
        int length=getNum(lMin,lMax);
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = (int)(Math.random()*base.length());
            sb.append(base.charAt(number));
        }
        sb.append(email_suffix[(int)(Math.random()*email_suffix.length)]);
        return sb.toString();
    }
    /**
     * 返回手机号码
     */
    private static String getTel() {
        int index=getNum(0,telFirst.length-1);
        String first=telFirst[index];
        String second=String.valueOf(getNum(1,888)+10000).substring(1);
        String thrid=String.valueOf(getNum(1,9100)+10000).substring(1);
        return first+second+thrid;
    }/**
     * 返回aihao号码
     */
    private static String[] habitstr=("打麻将,打游戏,打DOTA,钓鱼,掼蛋,赛车,开飞机,打篮球,踢足球,看书,听歌,美食,旅游,投资," +
                    "看电视,玩电脑,玩手机,编程,做题").split(",");
    private static String getHabit() {
        int index=getNum(0,habitstr.length-1);
        String first=habitstr[index];
        return first;
    }/**
     * 返回school号码
     */
    private static String[] schoolstr=("安大,科大,工大,医大,农大,师大,汉大,复旦,交大,清华,北大,蓝翔技校,卫校,警官学院," +
                    "交通学校,电脑学校,师范学院,西大,南大").split(",");
    private static String getSchool() {
        int index=getNum(0,schoolstr.length-1);
        String first=schoolstr[index];
        return first;
    }
    /**
     * 返回中文姓名
     */
    private static String getChineseName() {
        int index=getNum(0, firstName.length()-1);
        String first=firstName.substring(index, index+1);
        int index2=getNum(0, secondName.length()-1);
        String second=secondName.substring(index2, index2+1);
        return first+second;
    }
    /**
     * 返回地址
     * @return
     */
    private static String[] road=("重庆大厦,黑龙江路,十梅庵街,遵义路,湘潭街,瑞金广场,仙山街,仙山东路,仙山西大厦,白沙河路," +
            "赵红广场,机场路,民航街,长城南路,流亭立交桥,虹桥广场,长城大厦,礼阳路,风岗街,中川路,白塔广场,兴阳路,文阳街,绣城路," +
            "河城大厦,锦城广场,崇阳街,华城路,康城街,正阳路,和阳广场,中城路,江城大厦,顺城路,安城街,山城广场,春城街,国城路," +
            "泰城街,德阳路,明阳大厦,春阳路,艳阳街,秋阳路,硕阳街,青威高速,瑞阳街,丰海路,双元大厦,惜福镇街道,夏庄街道," +
            "古庙工业园,中山街,太平路,广西街,潍县广场,博山大厦,湖南路,济宁街,芝罘路,易州广场,荷泽四路,荷泽二街,荷泽一路," +
            "荷泽三大厦,观海二广场,广西支街,观海一路,济宁支街,莒县路,平度广场,明水路,蒙阴大厦,青岛路,湖北街,江宁广场," +
            "郯城街,天津路,保定街,安徽路,河北大厦,黄岛路,北京街,莘县路,济南街,宁阳广场,日照街,德县路,新泰大厦,荷泽路," +
            "山西广场,沂水路,肥城街,兰山路,四方街,平原广场,泗水大厦,浙江路,曲阜街,寿康路,河南广场,泰安路,大沽街,红山峡支路," +
            "西陵峡一大厦,台西纬一广场,台西纬四街,台西纬二路,西陵峡二街,西陵峡三路,台西纬三广场,台西纬五路,明月峡大厦,青铜峡路," +
            "台西二街,观音峡广场,瞿塘峡街,团岛二路,团岛一街,台西三路,台西一大厦,郓城南路,团岛三街,刘家峡路,西藏二街,西藏一广场," +
            "台西四街,三门峡路,城武支大厦,红山峡路,郓城北广场,龙羊峡路,西陵峡街,台西五路,团岛四街,石村广场,巫峡大厦,四川路,寿张街," +
            "嘉祥路,南村广场,范县路,西康街,云南路,巨野大厦,西江广场,鱼台街,单县路,定陶街").split(",");
    private static String getRoad() {
        int index=getNum(0,road.length-1);
        String first=road[index];
        String second=String.valueOf(getNum(11,150))+"号";
        String third="-"+getNum(1,20)+"-"+getNum(1,10);
        return first+second+third;
    }
    /**
     * 获取指定长度随机简体中文
     * @param len int
     * @return String
     */
    public static String getRandomJianHan(int len) {
        String ret="";
        for(int i=0;i<len;i++){
            String str = null;
            int hightPos, lowPos; // 定义高低位
            Random random = new Random();
            hightPos = (176 + Math.abs(random.nextInt(39))); //获取高位值
            lowPos = (161 + Math.abs(random.nextInt(93))); //获取低位值
            byte[] b = new byte[2];
            b[0] = (new Integer(hightPos).byteValue());
            b[1] = (new Integer(lowPos).byteValue());
            try
            {
                str = new String(b, "GBk"); //转成中文
            }
            catch (UnsupportedEncodingException ex)
            {
                ex.printStackTrace();
            }
            ret+=str;
        }
        return ret;
    }
    /**
     * 数据封装
     * @return
     */
    public static Map getPersonInfo() {
        Map map=new HashMap();
        map.put("name", getChineseName());
        map.put("road", getRoad());
        map.put("tel", getTel());
        map.put("email", getEmail(6,9));
        return map;
    }
    public static int getNum(int start,int end) {
        return (int)(Math.random()*(end-start+1)+start);
    }
    //public static String excelPath = "e:\\tables.xlsx";



}

