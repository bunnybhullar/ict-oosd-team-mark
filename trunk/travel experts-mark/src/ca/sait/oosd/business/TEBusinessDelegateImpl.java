package ca.sait.oosd.business;

import ca.sait.oosd.hibernate.Agencies;
import ca.sait.oosd.hibernate.Agents;
import ca.sait.oosd.hibernate.Packages;
import ca.sait.oosd.hibernate.Products;
import ca.sait.oosd.hibernate.ProductsSuppliers;
import ca.sait.oosd.hibernate.Suppliers;
import ca.sait.oosd.util.TENullValueException;
import java.util.Collection;

import ca.sait.oosd.travel.packages.PackageProductSuppliers;
import ca.sait.oosd.util.ArgumentAssertionChecker;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author duminda
 * This is the concrete implementation of the business delegate interface
 *
 */
public class TEBusinessDelegateImpl implements TEBusinessDelegate {

    private TEBusinessService teBusinessService;

    public TEBusinessDelegateImpl() {
        teBusinessService = new TEBusinessServiceImpl();
        
    }
    
	@Override
	public Collection<Agents> getAgentCollection() {
		return teBusinessService.getAgentCollection();
	}

	@Override
	public Collection<Packages> getPackageCollection() {
		return teBusinessService.getPackageCollection();
	}

	@Override
	public Collection<PackageProductSuppliers> getPackageProductSupplierCollection() {
		return null;
	}

	@Override
	public Collection<ProductsSuppliers> getProductSuppliersCollection() {
		return teBusinessService.getProductSuppliersCollection();
	}

	@Override
	public Collection<Products> getProductsCollection() {
		return teBusinessService.getProductsCollection();
        
	}

	@Override
	public Collection<Suppliers> getSuppliersCollection() {
		return teBusinessService.getSuppliersCollection();
	}

	@Override
	public TEObject save(TEObject teObject) throws TEBusinessException {
        try {
            ArgumentAssertionChecker.assertNotNull(teObject);

            return teBusinessService.save(teObject);
            
        } catch (TENullValueException ex) {
            Logger.getLogger(TEBusinessDelegateImpl.class.getName()).log(Level.SEVERE, "Null values received", ex);
            throw new TEBusinessException("Null values received.", ex);
        }

	}

    public void delete(TEObject teObject) throws TEBusinessException {
        try {
            ArgumentAssertionChecker.assertNotNull(teObject);

            teBusinessService.delete(teObject);

        } catch (TENullValueException ex) {
            Logger.getLogger(TEBusinessDelegateImpl.class.getName()).log(Level.SEVERE, "Null values received", ex);
            throw new TEBusinessException("Null values received.", ex);
        }
    }

    public TEObject update(TEObject teObject) throws TEBusinessException {
        try {
            ArgumentAssertionChecker.assertNotNull(teObject);

            return teBusinessService.update(teObject);

        } catch (TENullValueException ex) {
            Logger.getLogger(TEBusinessDelegateImpl.class.getName()).log(Level.SEVERE, "Null values received", ex);
            throw new TEBusinessException("Null values received.", ex);
        }
    }

	@Override
	public Collection<Agencies> getAgenciesCollection() {
		
		return teBusinessService.getAgenciesCollection();
	}

	
}
