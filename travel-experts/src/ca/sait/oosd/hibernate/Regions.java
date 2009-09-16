package ca.sait.oosd.hibernate;
// Generated Aug 31, 2009 2:44:29 PM by Hibernate Tools 3.2.1.GA


import ca.sait.oosd.business.TEObject;
import java.util.HashSet;
import java.util.Set;

/**
 * Regions generated by hbm2java
 */
public class Regions extends TEObject implements java.io.Serializable {


     private String regionid;
     private String regionname;
     private Set<Bookingdetails> bookingdetailses = new HashSet<Bookingdetails>(0);

    public Regions() {
    }

	
    public Regions(String regionid) {
        this.regionid = regionid;
    }
    public Regions(String regionid, String regionname, Set<Bookingdetails> bookingdetailses) {
       this.regionid = regionid;
       this.regionname = regionname;
       this.bookingdetailses = bookingdetailses;
    }
   
    public String getRegionid() {
        return this.regionid;
    }
    
    public void setRegionid(String regionid) {
        this.regionid = regionid;
    }
    public String getRegionname() {
        return this.regionname;
    }
    
    public void setRegionname(String regionname) {
        this.regionname = regionname;
    }
    public Set<Bookingdetails> getBookingdetailses() {
        return this.bookingdetailses;
    }
    
    public void setBookingdetailses(Set<Bookingdetails> bookingdetailses) {
        this.bookingdetailses = bookingdetailses;
    }

    @Override
    public String toString() {
        throw new UnsupportedOperationException("Not supported yet.");
    }




}

