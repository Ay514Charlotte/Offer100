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
	 private String mail;        //��ҵ����
     private String password;   //����
     /*���������Ƹ��Ϣ����*/
     //private List<Offer> list=new ArrayList<Offer>();
     /****************************���ֹ��췽��***************************/
     public HR(){
    	 super();
     }
     
     public HR(String mail, String password) {
		super();
		this.mail = mail;
		this.password = password;
	}
	/*************************get��set����******************************/
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
     /****************************��������****************************/
     //������û�
     private void add(){
    	 hrDA.getConnection();
    	 hrDA.add(this);
    	 hrDA.terminate();
     }
     //������������û���Ϣ
     private HR find(String mail) throws NotFoundException{
    	 hrDA.getConnection();
    	 HR aHR=hrDA.find(mail);
    	 hrDA.terminate();
    	 return aHR;
     }
     //����û���¼�ʺ������Ƿ�һ�£�����ֹsqlע��
     private boolean check(HR aHR) throws SQLException{
    	 hrDA.getConnection();
    	 boolean gotIt=hrDA.checkUser(aHR);
    	 hrDA.terminate();
    	 return gotIt;
     }
     
}
