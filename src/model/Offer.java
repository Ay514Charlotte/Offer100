package model;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import db.OfferDA;


public class Offer implements Serializable {
       /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
       private String CompanyName;
       private String JobName;
       private String WorkSkill;
       private String MonthSalary;
       private String Contact;
       private String OtherInformation;
       private String Time;
       private String AllString;
       
       private String IndustryCatalog;//ְҵ���ࣺIT������������
       private String JobType;//�������ͣ�ȫְ��ʵϰ��Ӧ��
       private String Adress;//��ַ;
       
       /***************************���췽��*****************************************/
       public Offer(){
    	   super();
       }
	    
	    public Offer(String companyName, String jobName, String workSkill, String monthSalary, String contact,
			String otherInformation, String time, String allString, String industryCatalog, String jobType,
			String adress) {
		super();
		CompanyName = companyName;
		JobName = jobName;
		WorkSkill = workSkill;
		MonthSalary = monthSalary;
		Contact = contact;
		OtherInformation = otherInformation;
		Time = time;
		AllString = allString;
		IndustryCatalog = industryCatalog;
		JobType = jobType;
		Adress = adress;
	    }
        /************************get��set����******************************************/
		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getCompanyName() {
			return CompanyName;
		}

		public void setCompanyName(String companyName) {
			CompanyName = companyName;
		}

		public String getJobName() {
			return JobName;
		}

		public void setJobName(String jobName) {
			JobName = jobName;
		}

		public String getWorkSkill() {
			return WorkSkill;
		}

		public void setWorkSkill(String workSkill) {
			WorkSkill = workSkill;
		}

		public String getMonthSalary() {
			return MonthSalary;
		}

		public void setMonthSalary(String monthSalary) {
			MonthSalary = monthSalary;
		}

		public String getContact() {
			return Contact;
		}

		public void setContact(String contact) {
			Contact = contact;
		}

		public String getOtherInformation() {
			return OtherInformation;
		}

		public void setOtherInformation(String otherInformation) {
			OtherInformation = otherInformation;
		}

		public String getTime() {
			return Time;
		}

		public void setTime(String time) {
			Time=time;
		}

		public String getAllString() {
			return AllString;
		}

		public void setAllString(String allString) {
			AllString = allString;
		}

		public String getIndustryCatalog() {
			return IndustryCatalog;
		}

		public void setIndustryCatalog(String industryCatalog) {
			IndustryCatalog = industryCatalog;
		}

		public String getJobType() {
			return JobType;
		}

		public void setJobType(String jobType) {
			JobType = jobType;
		}

		public String getAdress() {
			return Adress;
		}

		public void setAdress(String adress) {
			Adress = adress;
		}
		/***********************************��������
		 * @throws InvocationTargetException 
		 * @throws IllegalArgumentException 
		 * @throws IllegalAccessException 
		 * @throws SecurityException 
		 * @throws NoSuchMethodException ***************************************/
		//����һ����Ƹ��Ϣ�������Ƕ���
		public void add(Offer offer,String mail) throws NoSuchMethodException, 
		                                                 SecurityException, 
		                                                 IllegalAccessException, 
		                                                 IllegalArgumentException, 
		                                                 InvocationTargetException{
			OfferDA.getConnection();
			OfferDA.add(offer,mail);
			OfferDA.terminate();
		}
		//��ѯ������Ƹ��Ϣ���ض���list��������mail��
		public  List<Offer> find(String mail){
			List<Offer> offerList=new ArrayList<Offer>();
			OfferDA.getConnection();
			offerList=OfferDA.find(mail);
			OfferDA.terminate();
			return offerList;
		}
		//ɾ��һ����Ƹ��Ϣ
		public void delete(int id,String mail){
			OfferDA.getConnection();
			OfferDA.delete(id, mail);
			OfferDA.terminate();
		}	
}