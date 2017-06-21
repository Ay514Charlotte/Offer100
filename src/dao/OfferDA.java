package db;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import model.Offer;
import model.Link_offer_hr;
public class OfferDA {
//	static String[] industry_catalog=new String[]{"IT","����","����"};
//	static String[] job_type=new String[]{"ʵϰ","ȫְ","Ӧ��"};
//	static String[] adress_province
	static List<String> industryCatalog=new ArrayList<String>(Arrays.asList("IT","����","����"));
	static List<String> jobType=new ArrayList<String>(Arrays.asList("ʵϰ","ȫְ","Ӧ��"));
	static List<String> adress=new ArrayList<String>(Arrays.asList("�人","����","�Ϻ�","����","����","����"));
	
	static List<Offer> offerList;
	static Offer offer;
	static Link_offer_hr link=new Link_offer_hr();
	static Connection connection=null;
	static Statement statement=null;
	static PreparedStatement pps=null;
	
	static String driver="com.mysql.jdbc.Driver";
	static String jdbcUrl="jdbc:mysql://localhost:3306/offer100";	  
	static String user="root";
	static String password="wasd";
	/*****************offer�����ӵĳ�ʼ��**********************/
	public static Connection getConnection(){
		try{
			Class.forName(driver);
			connection=DriverManager.getConnection(jdbcUrl,user,password);
			System.out.println("���ݿ����ӳɹ���");
			statement=connection.createStatement();
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return connection;
	}
	/***********************���ݿ����ӹر�***************************/
	public static void terminate(){
		try {
			statement.close();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	/***********************����һ����Ƹ��Ϣ,�����¹�����link_hr_offer
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException ******************************/
	public static void add(Offer offer,String mail) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		String sql="insert into jobOffer_table (JobType,IndustryCatalog,CompanyName,JobName,WorkSkill,MonthSalary,Contact,Time,Adress,OtherInformation,AllString) values('"
				                                      +offer.getJobType()+"','"
				                                      +offer.getIndustryCatalog()+"','"
				                                      +offer.getCompanyName()+"','"
				                                      +offer.getJobName()+"','"
				                                      +offer.getWorkSkill()+"','"
				                                      +offer.getMonthSalary()+"','"
				                                      +offer.getContact()+"','"
				                                      +java.sql.Date.valueOf(offer.getTime())+"','"
				                                      +offer.getAdress()+"','"
				                                      +offer.getOtherInformation()+"','"
				                                      +offer.getAllString()+"')";
		
		System.out.println(sql);
		try{
			pps=connection.prepareStatement(sql,0x1);
					//Statement.RETURN_GENERATED_KEYS);
			pps.executeUpdate();
			ResultSet rs=pps.getGeneratedKeys();
			System.out.println("����ɹ�");
			if(rs.next()){
				int num=rs.getInt(1);
				System.out.println(num);
				link.add(num, mail);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	/************************��ѯ������Ƹ��Ϣ����list*****************************/
	public static List<Offer> find(String mail){
		offerList=new ArrayList<Offer>();
		String sql="select * from jobOffer_table where id in (select id from link_hr_offer where mail='"+mail+"')order by Time desc";
		System.out.println(sql);
		ResultSet rs;
		try {
			rs = statement.executeQuery(sql);
			while(rs.next()){
			offer=new Offer();
			offer.setId(rs.getInt(1));
			offer.setJobType(rs.getString(2));
			offer.setIndustryCatalog(rs.getString(3));
			offer.setCompanyName(rs.getString(4));
			offer.setJobName(rs.getString(5));
			offer.setWorkSkill(rs.getString(6));
			offer.setMonthSalary(rs.getString(7));
			offer.setContact(rs.getString(8));
			offer.setTime(rs.getDate(9).toString());
			offer.setAdress(rs.getString(10));
			offer.setOtherInformation(rs.getString(11));
			offer.setAllString(rs.getString(12));
			offerList.add(offer);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return offerList;
	}
	/*****************************************************/
	public static void delete(int id,String mail){		
	}

}
