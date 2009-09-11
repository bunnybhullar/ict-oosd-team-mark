package ca.sait.oosd.hibernate;
// Generated Aug 31, 2009 2:44:29 PM by Hibernate Tools 3.2.1.GA


import ca.sait.oosd.business.TEObject;
import java.util.HashSet;
import java.util.Set;

/**
 * Customers generated by hbm2java
 */
public class Customers extends TEObject implements java.io.Serializable {


     private long customerid;
     private Agents agents;
     private String custfirstname;
     private String custlastname;
     private String custaddress;
     private String custcity;
     private String custprov;
     private String custpostal;
     private String custcountry;
     private String custhomephone;
     private String custbusphone;
     private String custemail;
     private Set<Bookings> bookingses = new HashSet<Bookings>(0);
     private Set<Creditcards> creditcardses = new HashSet<Creditcards>(0);
     private Set<CustomersRewards> customersRewardses = new HashSet<CustomersRewards>(0);

    public Customers() {
    }

	
    public Customers(long customerid, String custfirstname, String custlastname, String custaddress, String custcity, String custprov, String custpostal, String custbusphone, String custemail) {
        this.customerid = customerid;
        this.custfirstname = custfirstname;
        this.custlastname = custlastname;
        this.custaddress = custaddress;
        this.custcity = custcity;
        this.custprov = custprov;
        this.custpostal = custpostal;
        this.custbusphone = custbusphone;
        this.custemail = custemail;
    }
    public Customers(long customerid, Agents agents, String custfirstname, String custlastname, String custaddress, String custcity, String custprov, String custpostal, String custcountry, String custhomephone, String custbusphone, String custemail, Set<Bookings> bookingses, Set<Creditcards> creditcardses, Set<CustomersRewards> customersRewardses) {
       this.customerid = customerid;
       this.agents = agents;
       this.custfirstname = custfirstname;
       this.custlastname = custlastname;
       this.custaddress = custaddress;
       this.custcity = custcity;
       this.custprov = custprov;
       this.custpostal = custpostal;
       this.custcountry = custcountry;
       this.custhomephone = custhomephone;
       this.custbusphone = custbusphone;
       this.custemail = custemail;
       this.bookingses = bookingses;
       this.creditcardses = creditcardses;
       this.customersRewardses = customersRewardses;
    }
   
    public long getCustomerid() {
        return this.customerid;
    }
    
    public void setCustomerid(long customerid) {
        this.customerid = customerid;
    }
    public Agents getAgents() {
        return this.agents;
    }
    
    public void setAgents(Agents agents) {
        this.agents = agents;
    }
    public String getCustfirstname() {
        return this.custfirstname;
    }
    
    public void setCustfirstname(String custfirstname) {
        this.custfirstname = custfirstname;
    }
    public String getCustlastname() {
        return this.custlastname;
    }
    
    public void setCustlastname(String custlastname) {
        this.custlastname = custlastname;
    }
    public String getCustaddress() {
        return this.custaddress;
    }
    
    public void setCustaddress(String custaddress) {
        this.custaddress = custaddress;
    }
    public String getCustcity() {
        return this.custcity;
    }
    
    public void setCustcity(String custcity) {
        this.custcity = custcity;
    }
    public String getCustprov() {
        return this.custprov;
    }
    
    public void setCustprov(String custprov) {
        this.custprov = custprov;
    }
    public String getCustpostal() {
        return this.custpostal;
    }
    
    public void setCustpostal(String custpostal) {
        this.custpostal = custpostal;
    }
    public String getCustcountry() {
        return this.custcountry;
    }
    
    public void setCustcountry(String custcountry) {
        this.custcountry = custcountry;
    }
    public String getCusthomephone() {
        return this.custhomephone;
    }
    
    public void setCusthomephone(String custhomephone) {
        this.custhomephone = custhomephone;
    }
    public String getCustbusphone() {
        return this.custbusphone;
    }
    
    public void setCustbusphone(String custbusphone) {
        this.custbusphone = custbusphone;
    }
    public String getCustemail() {
        return this.custemail;
    }
    
    public void setCustemail(String custemail) {
        this.custemail = custemail;
    }
    public Set<Bookings> getBookingses() {
        return this.bookingses;
    }
    
    public void setBookingses(Set<Bookings> bookingses) {
        this.bookingses = bookingses;
    }
    public Set<Creditcards> getCreditcardses() {
        return this.creditcardses;
    }
    
    public void setCreditcardses(Set<Creditcards> creditcardses) {
        this.creditcardses = creditcardses;
    }
    public Set<CustomersRewards> getCustomersRewardses() {
        return this.customersRewardses;
    }
    
    public void setCustomersRewardses(Set<CustomersRewards> customersRewardses) {
        this.customersRewardses = customersRewardses;
    }

    @Override
    public String toString() {
        return this.custfirstname + ", " + this.custlastname;
    }




}


