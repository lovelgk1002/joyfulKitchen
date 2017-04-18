package com.app.joyfulkitchen.model;

/**
 * 用户详细信息表
 * Created by Administrator on 2017/4/10.
 */

public class UserInfo {

    private int id; //编号
    private int userInfoUser; //用户
    private String userPhoto; //头像
    private int height; //身高
    private int weight; //体重
    private int age; //年龄
    private String sex; //性别
    private String nickName; //昵称
    private String target; //目标(target)：减脂，增肌，维持体重
    private String workingStrength; //工作强度(working_strength)：脑力劳动，较体力劳动，中度体力劳动
    private int heatId; //每日所需热量 heat_id(关联热量表)

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserInfoUser() {
        return userInfoUser;
    }

    public void setUserInfoUser(int userInfoUser) {
        this.userInfoUser = userInfoUser;
    }

    public String getUserPhoto() {
        return userPhoto;
    }

    public void setUserPhoto(String userPhoto) {
        this.userPhoto = userPhoto;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getWorkingStrength() {
        return workingStrength;
    }

    public void setWorkingStrength(String workingStrength) {
        this.workingStrength = workingStrength;
    }

    public int getHeatId() {
        return heatId;
    }

    public void setHeatId(int heatId) {
        this.heatId = heatId;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "id=" + id +
                ", userInfoUser=" + userInfoUser +
                ", userPhoto='" + userPhoto + '\'' +
                ", height=" + height +
                ", weight=" + weight +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                ", nickName='" + nickName + '\'' +
                ", target='" + target + '\'' +
                ", workingStrength='" + workingStrength + '\'' +
                ", heatId=" + heatId +
                '}';
    }
}
