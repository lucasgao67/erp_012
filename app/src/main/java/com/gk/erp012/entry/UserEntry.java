package com.gk.erp012.entry;


import com.gk.erp012.utils.SprefUtils;
import com.gk.erp012.utils.StringUtils;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by pc_home on 2017/7/23.
 */

public class UserEntry {
    private String account="";
    private String password="";
    private String type="";//0 监督者 1 管理者 2 执行者 3 领导
    private String name="";
    private String telNum="";
    public UserEntry() {
    }

    public UserEntry(String account, String password, String type, String telNum, String name) {
        this.account = account;
        this.name = name;
        this.password = password;
        this.telNum = telNum;
        this.type = type;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        if(StringUtils.isSpace(name))
            return getTypeStr();
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelNum() {
        return telNum;
    }

    public void setTelNum(String telNum) {
        this.telNum = telNum;
    }

    public void writeToSpref(SprefUtils sprefUtils){
        sprefUtils.put("account",account);
        sprefUtils.put("password",password);
        sprefUtils.put("type",type);
        sprefUtils.put("name",name);
        sprefUtils.put("telNum",telNum);
        sprefUtils.commit();
        sprefUtils.apply();
    }

    public static UserEntry getFromSpref(SprefUtils sprefUtils){
        String account = sprefUtils.getString("account","");
        String password = sprefUtils.getString("password","");
        String type = sprefUtils.getString("type","");
        String name = sprefUtils.getString("name","");
        String telNum = sprefUtils.getString("telNum","");
        if(StringUtils.isListSpace(account,password,type)){
            return null;
        }
        return new UserEntry(account,password,type,telNum,name);
    }

    public static UserEntry emptyUser= new UserEntry();

    public static void clearSpref(SprefUtils sprefUtils){
        emptyUser.writeToSpref(sprefUtils);
    }

    public static UserEntry getFromJson(JSONObject jsonObject){

        if(jsonObject.has("error")) return null;
        UserEntry userEntry=null;
        try {
            String account = jsonObject.getString("account");
            String password = jsonObject.getString("password");
            String type = jsonObject.getString("type");
            String name = jsonObject.getString("name");
            String telNum = jsonObject.getString("telNum");
            if(!StringUtils.isListSpace(account,password,type)){
                userEntry = new UserEntry(account,password,type,telNum,name);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            userEntry = null;
        }
        return userEntry;

    }

    public String getTypeStr(){
        String result ="";
        switch (type){
            case "0"://管理员
                result = "管理员";
                break;
            case "1":
                result="监督者";
                break;
            case "2":
                result="执行者";
                break;
            case "3":
                result="领导";
                break;
        }
        return result;
    }

    public boolean isAdmin(){
        return type.equals("0");
    }

    @Override
    public String toString() {
        return "UserEntry{" +
                "account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", telNum='" + telNum + '\'' +
                '}';
    }
}
