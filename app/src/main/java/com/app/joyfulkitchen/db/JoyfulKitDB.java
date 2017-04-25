package com.app.joyfulkitchen.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2017/4/18.
 */
public class JoyfulKitDB extends SQLiteOpenHelper{
    private static final String DB_NAME = "Nutrition.db"; //数据库名称(营养数据库)
    private static final int version =1; // 数据库版本

    //调用父类构造器
    public JoyfulKitDB(Context context) {
        super(context, DB_NAME, null, version);
    }

     //营养表创建语句
    public static final String CREATE_Nutrition = "" +
            "create table Nutrition(" +
            "id integer primary key autoincrement ," + //主键
            "foodName varchar(20) not null ," +   // 食物名称

            "foodProtein varchar(20) not null," +  //蛋白质
            "foodFat varchar(20) not null ," +     //脂肪
            "foodCarbon varchar(20) not null ," +   //碳水化合物
            "foodHeat  varchar(20) not null ," +    //热量
            "foodSalt varchar(20) not null ," +      //盐类
            "foodCalcium varchar(20) not null," +    //钙
            "foodPhosphorus varchar(20) not null," +  //磷
            "foodIron varchar(20) not null ," +      //铁
            "foodType integer not null" +             //类型 0、1、2、4、5、6
            ")";

    @Override
    public void onCreate(SQLiteDatabase db) { //新安装的软件从这里开始
         db.execSQL(CREATE_Nutrition);//Nutrition表
       db.beginTransaction();//手动设置开始事务

        try {


        //谷类1
        db.execSQL( "insert into Nutrition(foodName,foodProtein,foodFat,foodCarbon,foodHeat,foodSalt,foodCalcium,foodPhosphorus,foodIron,foodType) values('大米','7.5','0.5','79','351','0.4','10','100','1.0',0)");
        db.execSQL( "insert into Nutrition(foodName,foodProtein,foodFat,foodCarbon,foodHeat,foodSalt,foodCalcium,foodPhosphorus,foodIron,foodType) values('小米','9.7','1.7','77','362','1.4','21','240','4.7',0)");

        db.execSQL( "insert into Nutrition(foodName,foodProtein,foodFat,foodCarbon,foodHeat,foodSalt,foodCalcium,foodPhosphorus,foodIron,foodType) values('高粱米','8.2','2.2','78','385','0.4','17','230','5.0',0)");
        db.execSQL( "insert into Nutrition(foodName,foodProtein,foodFat,foodCarbon,foodHeat,foodSalt,foodCalcium,foodPhosphorus,foodIron,foodType) values('玉署黍','8.5','4.3','73','365','1.7','22','210','1.6',0)");
        db.execSQL( "insert into Nutrition(foodName,foodProtein,foodFat,foodCarbon,foodHeat,foodSalt,foodCalcium,foodPhosphorus,foodIron,foodType) values('大麦仁','10.5','2.2','66','326','2.6','43','400','4.1',0)");
        db.execSQL( "insert into Nutrition(foodName,foodProtein,foodFat,foodCarbon,foodHeat,foodSalt,foodCalcium,foodPhosphorus,foodIron,foodType) values('面粉','12.0','0.8','70','339','1.5','22','180','7.6',0)");

        // 干豆类+豆类+豆类+豆类制品2
        db.execSQL( "insert into Nutrition(foodName,foodProtein,foodFat,foodCarbon,foodHeat,foodSalt,foodCalcium,foodPhosphorus,foodIron,foodType) values('黄豆','39.2','17.4','25','413','5.0','320','570','5.9',1)");
        db.execSQL( "insert into Nutrition(foodName,foodProtein,foodFat,foodCarbon,foodHeat,foodSalt,foodCalcium,foodPhosphorus,foodIron,foodType) values('青豆','37.3','18.3','30','434','5.0','240','530','5.4',1)");
        db.execSQL( "insert into Nutrition(foodName,foodProtein,foodFat,foodCarbon,foodHeat,foodSalt,foodCalcium,foodPhosphorus,foodIron,foodType) values('黑豆','49.8','12.1','19','384','4.0','250','450','10.5',1)");
        db.execSQL( "insert into Nutrition(foodName,foodProtein,foodFat,foodCarbon,foodHeat,foodSalt,foodCalcium,foodPhosphorus,foodIron,foodType) values('赤小豆','20.7','0.5','58','318','3.3','67','305','5.2',1)");
        db.execSQL( "insert into Nutrition(foodName,foodProtein,foodFat,foodCarbon,foodHeat,foodSalt,foodCalcium,foodPhosphorus,foodIron,foodType) values('绿豆','22.1','0.8','59','332','3.3','34','222','9.7',1)");
        db.execSQL( "insert into Nutrition(foodName,foodProtein,foodFat,foodCarbon,foodHeat,foodSalt,foodCalcium,foodPhosphorus,foodIron,foodType) values('花豇豆','22.6','2.1','58','341','2.5','100','456','7.9',1)");
        db.execSQL( "insert into Nutrition(foodName,foodProtein,foodFat,foodCarbon,foodHeat,foodSalt,foodCalcium,foodPhosphorus,foodIron,foodType) values('豌豆','24.0','1.0','58','339','2.9','57','225','0.8',1)");
        db.execSQL( "insert into Nutrition(foodName,foodProtein,foodFat,foodCarbon,foodHeat,foodSalt,foodCalcium,foodPhosphorus,foodIron,foodType) values('蚕豆','28.2','0.8','49','318','2.7','71','340','7.0',1)");
        db.execSQL( "insert into Nutrition(foodName,foodProtein,foodFat,foodCarbon,foodHeat,foodSalt,foodCalcium,foodPhosphorus,foodIron,foodType) values('青扁豆荚','3.0','0.2','6','38','0.7','132','77','0.9',1)");
        db.execSQL( "insert into Nutrition(foodName,foodProtein,foodFat,foodCarbon,foodHeat,foodSalt,foodCalcium,foodPhosphorus,foodIron,foodType) values('白扁豆荚','3.2','0.3','5','36','0.8','81','68','3.4',1)");
        db.execSQL( "insert into Nutrition(foodName,foodProtein,foodFat,foodCarbon,foodHeat,foodSalt,foodCalcium,foodPhosphorus,foodIron,foodType) values('四季豆','1.9','0.8','4','31','0.7','66','49','1.6',1)");
        db.execSQL( "insert into Nutrition(foodName,foodProtein,foodFat,foodCarbon,foodHeat,foodSalt,foodCalcium,foodPhosphorus,foodIron,foodType) values('豌豆','7.2','0.3','12','80','0.9','13','90','0.8',1)");
        db.execSQL( "insert into Nutrition(foodName,foodProtein,foodFat,foodCarbon,foodHeat,foodSalt,foodCalcium,foodPhosphorus,foodIron,foodType) values('菜豆角','2.4','0.2','4','27','0.6','53','63','1.0',1)");
        db.execSQL( "insert into Nutrition(foodName,foodProtein,foodFat,foodCarbon,foodHeat,foodSalt,foodCalcium,foodPhosphorus,foodIron,foodType) values('黄豆芽','11.5','2.0','7','92','1.4','68','102','6.4',1)");
        db.execSQL( "insert into Nutrition(foodName,foodProtein,foodFat,foodCarbon,foodHeat,foodSalt,foodCalcium,foodPhosphorus,foodIron,foodType) values('豆腐浆','1.6','0.7','1','17','0.2','-','-','-',1)");
        db.execSQL( "insert into Nutrition(foodName,foodProtein,foodFat,foodCarbon,foodHeat,foodSalt,foodCalcium,foodPhosphorus,foodIron,foodType) values('北豆腐','9.2','1.2','6','72','0.9','110','110','3.6',1)");
        db.execSQL( "insert into Nutrition(foodName,foodProtein,foodFat,foodCarbon,foodHeat,foodSalt,foodCalcium,foodPhosphorus,foodIron,foodType) values('豆腐乳','14.6','5.7','5','30','7.8','167','200','12.0',1)");
        db.execSQL( "insert into Nutrition(foodName,foodProtein,foodFat,foodCarbon,foodHeat,foodSalt,foodCalcium,foodPhosphorus,foodIron,foodType) values('绿豆芽','3.2','0.1','4','30','0.4','23','51','0.9',1)");
        db.execSQL( "insert into Nutrition(foodName,foodProtein,foodFat,foodCarbon,foodHeat,foodSalt,foodCalcium,foodPhosphorus,foodIron,foodType) values('豆腐渣','2.6','0.3','7','41','0.7','16','44','4.0',1)");

        //根茎类
        db.execSQL( "insert into Nutrition(foodName,foodProtein,foodFat,foodCarbon,foodHeat,foodSalt,foodCalcium,foodPhosphorus,foodIron,foodType) values('小葱','1.4','0.3','5','28','0.8','63','28','1.0',2)");
        db.execSQL( "insert into Nutrition(foodName,foodProtein,foodFat,foodCarbon,foodHeat,foodSalt,foodCalcium,foodPhosphorus,foodIron,foodType) values('大葱','1.0','0.3','6','31','0.3','12','46','0.6',2)");
        db.execSQL( "insert into Nutrition(foodName,foodProtein,foodFat,foodCarbon,foodHeat,foodSalt,foodCalcium,foodPhosphorus,foodIron,foodType) values('葱头','4.4','0.2','23','111','1.3','5','44','0.4',2)");
        db.execSQL( "insert into Nutrition(foodName,foodProtein,foodFat,foodCarbon,foodHeat,foodSalt,foodCalcium,foodPhosphorus,foodIron,foodType) values('芋头','2.2','0.1','16','74','0.8','19','51','0.6',2)");
        db.execSQL( "insert into Nutrition(foodName,foodProtein,foodFat,foodCarbon,foodHeat,foodSalt,foodCalcium,foodPhosphorus,foodIron,foodType) values('红萝卜','2.0','0.4','5','32','1.4','19','23','1.9',2)");
        db.execSQL( "insert into Nutrition(foodName,foodProtein,foodFat,foodCarbon,foodHeat,foodSalt,foodCalcium,foodPhosphorus,foodIron,foodType) values('荸荠','1.5','0.1','21','91','1.5','5','68','0.5',2)");
        db.execSQL( "insert into Nutrition(foodName,foodProtein,foodFat,foodCarbon,foodHeat,foodSalt,foodCalcium,foodPhosphorus,foodIron,foodType) values('甘薯','2.3','0.2','29','127','0.9','18','20','0.4',2)");
        db.execSQL( "insert into Nutrition(foodName,foodProtein,foodFat,foodCarbon,foodHeat,foodSalt,foodCalcium,foodPhosphorus,foodIron,foodType) values('藕','1.0','0.1','6','29','0.7','19','51','0.5',2)");
        db.execSQL( "insert into Nutrition(foodName,foodProtein,foodFat,foodCarbon,foodHeat,foodSalt,foodCalcium,foodPhosphorus,foodIron,foodType) values('白萝卜','0.6','-','6','26','0.8','49','34','0.5',2)");
        db.execSQL( "insert into Nutrition(foodName,foodProtein,foodFat,foodCarbon,foodHeat,foodSalt,foodCalcium,foodPhosphorus,foodIron,foodType) values('马铃薯','1.9','0.7','28','126','1.2','11','59','0.9',2)");

        //叶菜类
        db.execSQL( "insert into Nutrition(foodName,foodProtein,foodFat,foodCarbon,foodHeat,foodSalt,foodCalcium,foodPhosphorus,foodIron,foodType) values('黄花菜','2.9','0.5','12','64','1.2','73','69','1.4',3)");
        db.execSQL( "insert into Nutrition(foodName,foodProtein,foodFat,foodCarbon,foodHeat,foodSalt,foodCalcium,foodPhosphorus,foodIron,foodType) values('黄花','14.1','0.4','60','300','7.0','463','173','16.5',3)");
        db.execSQL( "insert into Nutrition(foodName,foodProtein,foodFat,foodCarbon,foodHeat,foodSalt,foodCalcium,foodPhosphorus,foodIron,foodType) values('菠菜','2.0','0.2','2','18','2.0','70','34','2.5',3)");
        db.execSQL( "insert into Nutrition(foodName,foodProtein,foodFat,foodCarbon,foodHeat,foodSalt,foodCalcium,foodPhosphorus,foodIron,foodType) values('韭菜','2.4','0.5','4','30','0.9','56','45','1.3',3)");
        db.execSQL( "insert into Nutrition(foodName,foodProtein,foodFat,foodCarbon,foodHeat,foodSalt,foodCalcium,foodPhosphorus,foodIron,foodType) values('苋菜','2.5','0.4','5','34','2.3','200','46','4.8',3)");
        db.execSQL( "insert into Nutrition(foodName,foodProtein,foodFat,foodCarbon,foodHeat,foodSalt,foodCalcium,foodPhosphorus,foodIron,foodType) values('油菜','2.0','0.1','4','25','1.4','140','52','3.4',3)");
        db.execSQL( "insert into Nutrition(foodName,foodProtein,foodFat,foodCarbon,foodHeat,foodSalt,foodCalcium,foodPhosphorus,foodIron,foodType) values('大白菜','1.4','0.3','3','19','0.7','33','42','0.4',3)");
        db.execSQL( "insert into Nutrition(foodName,foodProtein,foodFat,foodCarbon,foodHeat,foodSalt,foodCalcium,foodPhosphorus,foodIron,foodType) values('小白菜','1.1','0.1','2','13','0.8','86','27','1.2',3)");
        db.execSQL( "insert into Nutrition(foodName,foodProtein,foodFat,foodCarbon,foodHeat,foodSalt,foodCalcium,foodPhosphorus,foodIron,foodType) values('洋白菜','1.3','0.3','4','24','0.8','100','56','1.9',3)");
        db.execSQL( "insert into Nutrition(foodName,foodProtein,foodFat,foodCarbon,foodHeat,foodSalt,foodCalcium,foodPhosphorus,foodIron,foodType) values('香菜','2.0','0.3','7','39','1.5','170','49','5.6',3)");
        db.execSQL( "insert into Nutrition(foodName,foodProtein,foodFat,foodCarbon,foodHeat,foodSalt,foodCalcium,foodPhosphorus,foodIron,foodType) values('芹菜茎','2.2','0.3','2','20','1.0','160','61','8.5',3)");

        //菌类
        db.execSQL( "insert into Nutrition(foodName,foodProtein,foodFat,foodCarbon,foodHeat,foodSalt,foodCalcium,foodPhosphorus,foodIron,foodType) values('蘑菇','2.9','0.2','3','25','0.6','8','66','1.3',4)");
        db.execSQL( "insert into Nutrition(foodName,foodProtein,foodFat,foodCarbon,foodHeat,foodSalt,foodCalcium,foodPhosphorus,foodIron,foodType) values('口蘑','35.6','1.4','23','247','16.2','100','162','32.0',4)");
        db.execSQL( "insert into Nutrition(foodName,foodProtein,foodFat,foodCarbon,foodHeat,foodSalt,foodCalcium,foodPhosphorus,foodIron,foodType) values('香菇','13.0','1.8','54','384','4.8','124','415','25.3',4)");


        //海菜类
        db.execSQL( "insert into Nutrition(foodName,foodProtein,foodFat,foodCarbon,foodHeat,foodSalt,foodCalcium,foodPhosphorus,foodIron,foodType) values('木耳','10.6','0.2','65','304','5.8','357','201','185.0',4)");
        db.execSQL( "insert into Nutrition(foodName,foodProtein,foodFat,foodCarbon,foodHeat,foodSalt,foodCalcium,foodPhosphorus,foodIron,foodType) values('海带','8.2','0.1','57','262','12.9','2250','-','150.0',5)");
        db.execSQL( "insert into  Nutrition(foodName,foodProtein,foodFat,foodCarbon,foodHeat,foodSalt,foodCalcium,foodPhosphorus,foodIron,foodType) values('紫菜','24.5','0.9','31','230','30.3','330','440','32.0',5)");



//茄瓜果类

        db.execSQL( "insert into Nutrition(foodName,foodProtein,foodFat,foodCarbon,foodHeat,foodSalt,foodCalcium,foodPhosphorus,foodIron,foodType) values('南瓜','0.8','-','3','15','0.5','27','22','0.2',6)");
        db.execSQL( "insert into Nutrition(foodName,foodProtein,foodFat,foodCarbon,foodHeat,foodSalt,foodCalcium,foodPhosphorus,foodIron,foodType) values('西葫芦','0.6','-','2','10','0.6','17','47','0.2',6)");
        db.execSQL( "insert into Nutrition(foodName,foodProtein,foodFat,foodCarbon,foodHeat,foodSalt,foodCalcium,foodPhosphorus,foodIron,foodType) values('瓠子','0.6','0.1','3','15','0.4','12','17','0.3',6)");
        db.execSQL( "insert into Nutrition(foodName,foodProtein,foodFat,foodCarbon,foodHeat,foodSalt,foodCalcium,foodPhosphorus,foodIron,foodType) values('丝瓜','1.5','0.1','5','27','0.5','28','45','0.8',6)");
        db.execSQL( "insert into Nutrition(foodName,foodProtein,foodFat,foodCarbon,foodHeat,foodSalt,foodCalcium,foodPhosphorus,foodIron,foodType) values('茄子','2.3','0.1','3','22','0.5','22','31','0.4',6)");
        db.execSQL( "insert into Nutrition(foodName,foodProtein,foodFat,foodCarbon,foodHeat,foodSalt,foodCalcium,foodPhosphorus,foodIron,foodType) values('冬瓜','0.4','-','2','10','0.3','19','12','0.3',6)");
        db.execSQL( "insert into Nutrition(foodName,foodProtein,foodFat,foodCarbon,foodHeat,foodSalt,foodCalcium,foodPhosphorus,foodIron,foodType) values('西瓜','1.2','-','4','21','0.2','6','10','0.2',6)");
        db.execSQL( "insert into Nutrition(foodName,foodProtein,foodFat,foodCarbon,foodHeat,foodSalt,foodCalcium,foodPhosphorus,foodIron,foodType) values('甜瓜','0.3','0.1','4','18','0.4','27','12','0.4',6)");
        db.execSQL( "insert into Nutrition(foodName,foodProtein,foodFat,foodCarbon,foodHeat,foodSalt,foodCalcium,foodPhosphorus,foodIron,foodType) values('菜瓜','0.9','-','2','12','0.3','24','11','0.2',6)");
        db.execSQL( "insert into Nutrition(foodName,foodProtein,foodFat,foodCarbon,foodHeat,foodSalt,foodCalcium,foodPhosphorus,foodIron,foodType) values('黄瓜','0.8','0.2','2','13','0.5','25','37','0.4',6)");
        db.execSQL( "insert into Nutrition(foodName,foodProtein,foodFat,foodCarbon,foodHeat,foodSalt,foodCalcium,foodPhosphorus,foodIron,foodType) values('西红柿','0.6','0.3','2','13','0.4','8','32','0.4',6)");



//水果类

        db.execSQL( "insert into Nutrition(foodName,foodProtein,foodFat,foodCarbon,foodHeat,foodSalt,foodCalcium,foodPhosphorus,foodIron,foodType) values('柿','0.7','0.1','11','48','2.9','10','19','0.2',6)");
        db.execSQL( "insert into Nutrition(foodName,foodProtein,foodFat,foodCarbon,foodHeat,foodSalt,foodCalcium,foodPhosphorus,foodIron,foodType) values('枣','1.2','0.2','24','103','0.4','41','23','0.5',6)");
        db.execSQL( "insert into Nutrition(foodName,foodProtein,foodFat,foodCarbon,foodHeat,foodSalt,foodCalcium,foodPhosphorus,foodIron,foodType) values('苹果','0.2','0.6','15','60','0.2','11','9','0.3',6)");
        db.execSQL( "insert into Nutrition(foodName,foodProtein,foodFat,foodCarbon,foodHeat,foodSalt,foodCalcium,foodPhosphorus,foodIron,foodType) values('香蕉','1.2','0.6','20','90','0.7','10','35','0.8',6)");
        db.execSQL( "insert into Nutrition(foodName,foodProtein,foodFat,foodCarbon,foodHeat,foodSalt,foodCalcium,foodPhosphorus,foodIron,foodType) values('梨','0.1','0.1','12','49','0.3','5','6','0.2',6)");
        db.execSQL( "insert into Nutrition(foodName,foodProtein,foodFat,foodCarbon,foodHeat,foodSalt,foodCalcium,foodPhosphorus,foodIron,foodType) values('杏','0.9','-','10','44','0.6','26','24','0.8',6)");
        db.execSQL( "insert into Nutrition(foodName,foodProtein,foodFat,foodCarbon,foodHeat,foodSalt,foodCalcium,foodPhosphorus,foodIron,foodType) values('李','0.5','0.2','9','40','-','17','20','0.5',6)");
        db.execSQL( "insert into Nutrition(foodName,foodProtein,foodFat,foodCarbon,foodHeat,foodSalt,foodCalcium,foodPhosphorus,foodIron,foodType) values('桃','0.8','0.1','7','32','0.5','8','20','1.0',6)");
        db.execSQL( "insert into Nutrition(foodName,foodProtein,foodFat,foodCarbon,foodHeat,foodSalt,foodCalcium,foodPhosphorus,foodIron,foodType) values('樱桃','1.2','0.3','8','40','0.6','6','31','5.9',6)");
        db.execSQL( "insert into Nutrition(foodName,foodProtein,foodFat,foodCarbon,foodHeat,foodSalt,foodCalcium,foodPhosphorus,foodIron,foodType) values('葡萄','0.2','-','10','41','0.2','4','15','0.6',6)");


//干果及硬果类
        db.execSQL( "insert into Nutrition(foodName,foodProtein,foodFat,foodCarbon,foodHeat,foodSalt,foodCalcium,foodPhosphorus,foodIron,foodType) values('花生仁','26.5','44.8','20','589','3.1','71','399','2.0',7)");
        db.execSQL( "insert into Nutrition(foodName,foodProtein,foodFat,foodCarbon,foodHeat,foodSalt,foodCalcium,foodPhosphorus,foodIron,foodType) values('栗子','4.8','1.5','44','209','1.1','15','91','1.7',7)");
        db.execSQL( "insert into Nutrition(foodName,foodProtein,foodFat,foodCarbon,foodHeat,foodSalt,foodCalcium,foodPhosphorus,foodIron,foodType) values('杏仁','25.7','51','9','597','2.5','141','202','3.9',7)");
        db.execSQL( "insert into Nutrition(foodName,foodProtein,foodFat,foodCarbon,foodHeat,foodSalt,foodCalcium,foodPhosphorus,foodIron,foodType) values('菱角','3.6','0.5','24','115','1.7','9','49','0.7',7)");
        db.execSQL( "insert into Nutrition(foodName,foodProtein,foodFat,foodCarbon,foodHeat,foodSalt,foodCalcium,foodPhosphorus,foodIron,foodType) values('红枣','3.3','0.5','73','309','1.4','61','55','1.6',7)");


        //走兽类
        db.execSQL( "insert into Nutrition(foodName,foodProtein,foodFat,foodCarbon,foodHeat,foodSalt,foodCalcium,foodPhosphorus,foodIron,foodType) values('牛肉','20.1','10.2','-','172','1.1','7','170','0.9',8)");
        db.execSQL( "insert into Nutrition(foodName,foodProtein,foodFat,foodCarbon,foodHeat,foodSalt,foodCalcium,foodPhosphorus,foodIron,foodType) values('牛肝','18.9','2.6','9','135','0.9','13','400','9',8)");
        db.execSQL( "insert into Nutrition(foodName,foodProtein,foodFat,foodCarbon,foodHeat,foodSalt,foodCalcium,foodPhosphorus,foodIron,foodType) values('羊肉','11.1','28.8','0.5','306','0.9','11','129','2',8)");
        db.execSQL( "insert into Nutrition(foodName,foodProtein,foodFat,foodCarbon,foodHeat,foodSalt,foodCalcium,foodPhosphorus,foodIron,foodType) values('羊肝','18.5','7.2','4','155','1.4','9','414','6.6',8)");
        db.execSQL( "insert into Nutrition(foodName,foodProtein,foodFat,foodCarbon,foodHeat,foodSalt,foodCalcium,foodPhosphorus,foodIron,foodType) values('猪肉','16.9','29.2','1.1','335','0.9','11','170','0.4',8)");
        db.execSQL( "insert into Nutrition(foodName,foodProtein,foodFat,foodCarbon,foodHeat,foodSalt,foodCalcium,foodPhosphorus,foodIron,foodType) values('猪肝','20.1','4.0','2.9','128','1.8','11','270','25',8)");

//爬虫
        db.execSQL( "insert into Nutrition(foodName,foodProtein,foodFat,foodCarbon,foodHeat,foodSalt,foodCalcium,foodPhosphorus,foodIron,foodType) values('田鸡','11.9','0.3','0.2','51','0.6','22','159','1.3',8)");
        db.execSQL( "insert into Nutrition(foodName,foodProtein,foodFat,foodCarbon,foodHeat,foodSalt,foodCalcium,foodPhosphorus,foodIron,foodType) values('甲鱼','16.5','1','1.5','81','0.9','107','135','1.4',8)");

//飞禽
        db.execSQL( "insert into Nutrition(foodName,foodProtein,foodFat,foodCarbon,foodHeat,foodSalt,foodCalcium,foodPhosphorus,foodIron,foodType) values('鸡肉','23.3','1.2','-','104','1.1','11','190','1.5',8)");
        db.execSQL( "insert into Nutrition(foodName,foodProtein,foodFat,foodCarbon,foodHeat,foodSalt,foodCalcium,foodPhosphorus,foodIron,foodType) values('鸭肉','16.5','7.5','0.1','134','0.9','11','145','4.1',8)");



//乳类
        db.execSQL( "insert into Nutrition(foodName,foodProtein,foodFat,foodCarbon,foodHeat,foodSalt,foodCalcium,foodPhosphorus,foodIron,foodType) values('牛奶','3.1','3.5','4.6','62','0.7','120','90','0.1',9)");
        db.execSQL( "insert into Nutrition(foodName,foodProtein,foodFat,foodCarbon,foodHeat,foodSalt,foodCalcium,foodPhosphorus,foodIron,foodType) values('牛奶粉','25.6','26.7','35.6','48.5','-','900','-','0.8',9)");
        db.execSQL( "insert into Nutrition(foodName,foodProtein,foodFat,foodCarbon,foodHeat,foodSalt,foodCalcium,foodPhosphorus,foodIron,foodType) values('羊奶','3.8','4.1','4.6','71','0.9','140','-','0.7',9)");


//蛋类=10

        db.execSQL( "insert into Nutrition(foodName,foodProtein,foodFat,foodCarbon,foodHeat,foodSalt,foodCalcium,foodPhosphorus,foodIron,foodType) values('鸡蛋','14.8','11.6','-','164','1.1','55','210','2.7',10)");
        db.execSQL( "insert into Nutrition(foodName,foodProtein,foodFat,foodCarbon,foodHeat,foodSalt,foodCalcium,foodPhosphorus,foodIron,foodType) values('鸭蛋','13','14.7','0.5','186','1.8','71','210','3.2',10)");
        db.execSQL( "insert into Nutrition(foodName,foodProtein,foodFat,foodCarbon,foodHeat,foodSalt,foodCalcium,foodPhosphorus,foodIron,foodType) values('咸鸭蛋','11.3','13.2','3.3','178','6','102','214','3.6',10)");



//鱼类=11

        db.execSQL( "insert into Nutrition(foodName,foodProtein,foodFat,foodCarbon,foodHeat,foodSalt,foodCalcium,foodPhosphorus,foodIron,foodType) values('鲫鱼','13','1.1','0.1','62','0.8','54','20.3','2.5',11)");
        db.execSQL( "insert into Nutrition(foodName,foodProtein,foodFat,foodCarbon,foodHeat,foodSalt,foodCalcium,foodPhosphorus,foodIron,foodType) values('鲤鱼','18.1','1.6','0.2','88','1.1','28','17.6','1.3',11)");
        db.execSQL( "insert into Nutrition(foodName,foodProtein,foodFat,foodCarbon,foodHeat,foodSalt,foodCalcium,foodPhosphorus,foodIron,foodType) values('鳝鱼','17.9','0.5','-','76','0.6','27','4.6','4.6',11)");
        db.execSQL( "insert into Nutrition(foodName,foodProtein,foodFat,foodCarbon,foodHeat,foodSalt,foodCalcium,foodPhosphorus,foodIron,foodType) values('带鱼','15.9','3.4','1.5','100','1.1','48','53','2.3',11)");
        db.execSQL( "insert into Nutrition(foodName,foodProtein,foodFat,foodCarbon,foodHeat,foodSalt,foodCalcium,foodPhosphorus,foodIron,foodType) values('黄花鱼','17.2','0.7','0.3','76','0.9','31','204','1.8',11)");


//蛤类12

        db.execSQL( "insert into Nutrition(foodName,foodProtein,foodFat,foodCarbon,foodHeat,foodSalt,foodCalcium,foodPhosphorus,foodIron,foodType) values('河螃蟹','1.4','5.9','7.4','139','1.8','129','145','13.0',12)");
        db.execSQL( "insert into Nutrition(foodName,foodProtein,foodFat,foodCarbon,foodHeat,foodSalt,foodCalcium,foodPhosphorus,foodIron,foodType) values('明虾','20.6','0.7','0.2','90','1.5','35','150','0.1',12)");
        db.execSQL( "insert into Nutrition(foodName,foodProtein,foodFat,foodCarbon,foodHeat,foodSalt,foodCalcium,foodPhosphorus,foodIron,foodType) values('青虾','16.4','1.3','0.1','78','1.2','99','205','0.3',12)");
        db.execSQL( "insert into Nutrition(foodName,foodProtein,foodFat,foodCarbon,foodHeat,foodSalt,foodCalcium,foodPhosphorus,foodIron,foodType) values('虾米','46.8','2','-','205','25.2','882','-','-',12)");
        db.execSQL( "insert into Nutrition(foodName,foodProtein,foodFat,foodCarbon,foodHeat,foodSalt,foodCalcium,foodPhosphorus,foodIron,foodType) values('田螺','10.7','1.2','3.8','69','3.3','357','191','19.8',12)");
        db.execSQL( "insert into Nutrition(foodName,foodProtein,foodFat,foodCarbon,foodHeat,foodSalt,foodCalcium,foodPhosphorus,foodIron,foodType) values('蛤蜊','10.8','1.6','4.8','77','3','37','82','14.2',12)");

//调味油
        db.execSQL( "insert into Nutrition(foodName,foodProtein,foodFat,foodCarbon,foodHeat,foodSalt,foodCalcium,foodPhosphorus,foodIron,foodType) values('猪油','-','99','-','891','-','-','-','-',13)");
        db.execSQL( "insert into Nutrition(foodName,foodProtein,foodFat,foodCarbon,foodHeat,foodSalt,foodCalcium,foodPhosphorus,foodIron,foodType) values('芝麻油','-','100','-','900','-','-','-','-',13)");
        db.execSQL( "insert into Nutrition(foodName,foodProtein,foodFat,foodCarbon,foodHeat,foodSalt,foodCalcium,foodPhosphorus,foodIron,foodType) values('花生油','-','100','-','900','-','-','-','-',13)");
        db.execSQL( "insert into Nutrition(foodName,foodProtein,foodFat,foodCarbon,foodHeat,foodSalt,foodCalcium,foodPhosphorus,foodIron,foodType) values('芝麻酱','20.0','52.9','15','616','5.2','870','530','58',13)");
        db.execSQL( "insert into Nutrition(foodName,foodProtein,foodFat,foodCarbon,foodHeat,foodSalt,foodCalcium,foodPhosphorus,foodIron,foodType) values('豆油','-','100','-','900','-','-','-','-',13)");

            db.setTransactionSuccessful();//设置事务处理成功，不设置会自动回滚不提交
        }catch (Exception e){

        }finally {
            db.endTransaction();//处理完成
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


}
