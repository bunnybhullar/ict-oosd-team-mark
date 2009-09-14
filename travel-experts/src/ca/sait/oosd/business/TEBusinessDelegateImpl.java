package ca.sait.oosd.business;

import ca.sait.oosd.dao.CustomerReassignDAO;
import ca.sait.oosd.hibernate.Agents;
import ca.sait.oosd.hibernate.Customers;
import ca.sait.oosd.hibernate.Packages;
import ca.sait.oosd.hibernate.PackagesProductsSuppliers;
import ca.sait.oosd.hibernate.Products;
import ca.sait.oosd.hibernate.ProductsSuppliers;
import ca.sait.oosd.hibernate.Suppliers;
import ca.sait.oosd.util.TENullValueException;
import java.util.Collection;

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
	public Collection<PackagesProductsSuppliers> getPackageProductSupplierCollection() {
		return teBusinessService.getPackageProductSupplierCollection();
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

            //Do not delete an agent, instead remove the link between customers and
            //set the agency id to null
            if(teObject instanceof Agents) {
                deleteAgent((Agents) teObject);
            } else {
                teBusinessService.delete(teObject);
                
            }
            

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

    public ProductsSuppliers makeProductSupplierRelationship(TEObject sourceSelection, TEObject tagetSelection) throws TEBusinessException{
        try{
            ArgumentAssertionChecker.assertNotNull(sourceSelection);
            ArgumentAssertionChecker.assertNotNull(tagetSelection);

            Products products = (Products) sourceSelection;
            Suppliers suppliers = (Suppliers) tagetSelection;

            ProductsSuppliers productsSuppliers = new ProductsSuppliers();
            productsSuppliers.setProducts(products);
            productsSuppliers.setSuppliers(suppliers);

            return teBusinessService.makeProductSupplierRelationship(productsSuppliers);

        } catch (TENullValueException ex) {
            Logger.getLogger(TEBusinessDelegateImpl.class.getName()).log(Level.SEVERE, "Null values received", ex);
            throw new TEBusinessException("Null values received.", ex);
        }


        
    }

    public PackagesProductsSuppliers makePackagesProductsSuppliersRelationship(TEObject sourceSelection, TEObject tagetSelection)
            throws TEBusinessException {

        try{
            ArgumentAssertionChecker.assertNotNull(sourceSelection);
            ArgumentAssertionChecker.assertNotNull(tagetSelection);

            Packages packages = (Packages) sourceSelection;
            ProductsSuppliers productsSuppliers = (ProductsSuppliers) tagetSelection;

            PackagesProductsSuppliers packagesProductsSuppliers = new PackagesProductsSuppliers();
            packagesProductsSuppliers.setPackageId(packages);
            packagesProductsSuppliers.setProductsSuppliersid(productsSuppliers);

            return teBusinessService.makePackagesProductsSuppliersRelationship(packagesProductsSuppliers);

        } catch (TENullValueException ex) {
            Logger.getLogger(TEBusinessDelegateImpl.class.getName()).log(Level.SEVERE, "Null values received", ex);
            throw new TEBusinessException("Null values received.", ex);
        }
    }

    private void deleteAgent(Agents agent) throws TEBusinessException, TENullValueException{
        ArgumentAssertionChecker.assertNotNull(agent);

        //get all customers that belongs to this agent
        Collection<Customers> customersAssigned = getCustomersForAgent(agent);
        
        for (Customers customer : customersAssigned) {
            customer.setAgents(null);
            update(customer);
            
        }

        /*
         * Assumption :
         * It assumes that a Agent without a agency id is inactive. There is no status field for the
         * agent to indicate that he is active or inactive. So when reassigning customers to the agents,
         * it is possible to get the list of agents without a agency id. But this is not a good idea though,
         * since there is no audit trail for this change.
         */
        agent.setAgencies(null);
        update(agent);
        
    }

    public Collection<Customers> getCustomersForAgent(Agents agent) {
        return teBusinessService.getCustomersForAgent(agent);
        
    }

    public Collection<Customers> getCustomersCollection() {
        return teBusinessService.getCustomersCollection();
        
    }

	@Override
	public Collection<CustomerReassignDAO> getAgentCustomerCollection() {
		return teBusinessService.getAgentCustomerCollection();
	}

	@Override
	public void reassignCustomerToAgent(Customers customer, Agents agent) {
		customer.setAgents(agent);
		teBusinessService.reassignCustomerToAgent(customer);
		
	}

	
}
