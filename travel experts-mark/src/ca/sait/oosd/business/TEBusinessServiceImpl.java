package ca.sait.oosd.business;

import ca.sait.oosd.hibernate.Agencies;
import ca.sait.oosd.hibernate.Agents;
import ca.sait.oosd.hibernate.Packages;
import ca.sait.oosd.hibernate.Products;
import ca.sait.oosd.hibernate.ProductsSuppliers;
import ca.sait.oosd.hibernate.Suppliers;
import ca.sait.oosd.logger.LogLevel;
import ca.sait.oosd.logger.LoggerHelper;
import ca.sait.oosd.travel.packages.PackageProductSuppliers;
import ca.sait.oosd.util.ArgumentAssertionChecker;
import ca.sait.oosd.util.HibernateUtil;
import ca.sait.oosd.util.TENullValueException;
import java.util.Collection;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

public class TEBusinessServiceImpl implements TEBusinessService {

    private LoggerHelper helper = new LoggerHelper(TEBusinessServiceImpl.class.getName());
    private Session session;

    public TEBusinessServiceImpl() {
        session = HibernateUtil.getSessionFactory().openSession();

    }

	@Override
	public TEObject save(TEObject teObject) throws TENullValueException {
		ArgumentAssertionChecker.assertNotNull(teObject);
		
		return doSave(teObject);
	}
	
	//This sync the session objcts and the database through Hibernate
	private TEObject doSave(TEObject teObject) {

        session.beginTransaction();
        
		if(teObject instanceof Agents) {
			Agents agent = (Agents) teObject;
            session.save(agent);
            session.flush();
            session.getTransaction().commit();

            return agent;
			
		} else if(teObject instanceof Packages) {
			Packages packages = (Packages) teObject;
            session.save(packages);
            session.flush();
            session.getTransaction().commit();

            return packages;
			
		} else if(teObject instanceof PackageProductSuppliers) {
			PackageProductSuppliers packageProductSuppliers = (PackageProductSuppliers)teObject;
            session.save(packageProductSuppliers);
            session.flush();
            session.getTransaction().commit();

            return packageProductSuppliers;
			
		} else if(teObject instanceof Products) {
			Products products = (Products)teObject;
            session.save(products);
            session.flush();
            session.getTransaction().commit();

            return products;
			
		} else if(teObject instanceof ProductsSuppliers) {
			ProductsSuppliers productSuppliers = (ProductsSuppliers)teObject;
            session.save(productSuppliers);
            session.flush();
            session.getTransaction().commit();

            return productSuppliers;
			
		} else if(teObject instanceof Suppliers) {
			Suppliers suppliers = (Suppliers)teObject;
            session.save(suppliers);
            session.flush();
            session.getTransaction().commit();
            
            return suppliers;
			
		}

        return null;

	}

    public Collection<Packages> getPackageCollection() {
        session.beginTransaction();

        Query query = session.createQuery("from Packages order by pkgname");
        List resultList = query.list();
        session.getTransaction().commit();

		return resultList;
    }

    public Collection<Agents> getAgentCollection() {
        session.beginTransaction();

        Query query = session.createQuery("from Agents order by agtfirstname");
        List resultList = query.list();
        session.getTransaction().commit();

		return resultList;
    }

    public Collection<Products> getProductsCollection() {
        session.beginTransaction();

        Query query = session.createQuery("from Products order by prodname");
        List resultList = query.list();
        session.getTransaction().commit();

		return resultList;
        
    }

    public Collection<ProductsSuppliers> getProductSuppliersCollection() {
        session.beginTransaction();

        Query query = session.createQuery("from ProductsSuppliers");
        List resultList = query.list();
        session.getTransaction().commit();

		return resultList;
    }

    public Collection<Suppliers> getSuppliersCollection() {
        session.beginTransaction();

        Query query = session.createQuery("from Suppliers order by supname");
        List resultList = query.list();
        session.getTransaction().commit();

		return resultList;
    }

    //this remove a record from the schema
    public void delete(TEObject teObject) throws TENullValueException {
        session.beginTransaction();

		if(teObject instanceof Agents) {
			Agents agent = (Agents) teObject;
            session.delete(agent);

		} else if(teObject instanceof Packages) {
			Packages packages = (Packages) teObject;
            session.delete(packages);

		} else if(teObject instanceof PackageProductSuppliers) {
			PackageProductSuppliers packageProductSuppliers = (PackageProductSuppliers)teObject;
            session.delete(packageProductSuppliers);

		} else if(teObject instanceof Products) {
			Products products = (Products)teObject;
            session.delete(products);

		} else if(teObject instanceof ProductsSuppliers) {
			ProductsSuppliers productSuppliers = (ProductsSuppliers)teObject;
            session.delete(productSuppliers);

		} else if(teObject instanceof Suppliers) {
			Suppliers suppliers = (Suppliers)teObject;
            session.delete(suppliers);

		}

        session.flush();
        session.getTransaction().commit();
    }

    public TEObject update(TEObject teObject) throws TENullValueException {
        session.beginTransaction();

		if(teObject instanceof Agents) {
			Agents agent = (Agents) teObject;
            session.update(agent);
            session.flush();
            session.getTransaction().commit();

            return agent;

		} else if(teObject instanceof Packages) {
			Packages packages = (Packages) teObject;
            session.merge(packages);
            session.flush();
            session.getTransaction().commit();

            return packages;

		} else if(teObject instanceof PackageProductSuppliers) {
			PackageProductSuppliers packageProductSuppliers = (PackageProductSuppliers)teObject;
            session.update(packageProductSuppliers);
            session.flush();
            session.getTransaction().commit();

            return packageProductSuppliers;

		} else if(teObject instanceof Products) {
			Products products = (Products)teObject;
            session.update(products);
            session.flush();
            session.getTransaction().commit();

            return products;

		} else if(teObject instanceof ProductsSuppliers) {
			ProductsSuppliers productSuppliers = (ProductsSuppliers)teObject;
            session.update(productSuppliers);
            session.flush();
            session.getTransaction().commit();

            return productSuppliers;

		} else if(teObject instanceof Suppliers) {
			Suppliers suppliers = (Suppliers)teObject;
            session.update(suppliers);
            session.flush();
            session.getTransaction().commit();

            return suppliers;

		}

        return null;
    }

	@Override
	public Collection<Agencies> getAgenciesCollection() {
        session.beginTransaction();

        Query query = session.createQuery("from Agencies order by agencyid");
        List resultList = query.list();
        session.getTransaction().commit();

		return resultList;
	}

}
