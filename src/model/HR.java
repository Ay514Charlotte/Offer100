package model;
import java.io.*;
import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;

import db.hrDA;
import db.NotFoundException;

public class HR implements Serializable{
     /**
	 * 
	 */
	 private static final long serialVersionUID = 1L;
	 private String mail;        //企业邮箱
     private String password;   //密码
     /*用来存放招聘信息对象*/
     //private List<Offer> list=new ArrayList<Offer>();
     /****************************两种构造方法***************************/
     public HR(){
    	 super();
     }
     
     public HR(String mail, String password) {
		super();
		this.mail = mail;
		this.password = password;
	}
	/*************************get和set方法******************************/
     public void setMail(String mail ){
    	  this.mail=mail;
      }
     public void setPassword(String password){
    	  this.password=password;
      }
     public String getMail(){
    	  return mail;
      }
     public String getPassword(){
    	  return password;
      }
     /****************************其他方法****************************/
     //添加新用户
     private void add(){
    	 hrDA.getConnection();
    	 hrDA.add(this);
    	 hrDA.terminate();
     }
     //根据邮箱查找用户信息
     private HR find(String mail) throws NotFoundException{
    	 hrDA.getConnection();
    	 HR aHR=hrDA.find(mail);
    	 hrDA.terminate();
    	 return aHR;
     }
     //检查用户登录帐号密码是否一致，并防止sql注入
     private boolean check(HR aHR) throws SQLException{
    	 hrDA.getConnection();
    	 boolean gotIt=hrDA.checkUser(aHR);
    	 hrDA.terminate();
    	 return gotIt;
     }
     
}
