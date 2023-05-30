package bo;

import bo1.CustomerBO;
import dao.Custom.CustomerDAO;
import dao.Custom.Impl.CustomerDAOImpl;
import model.CustomerDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerBOImpl implements CustomerBO {

    CustomerDAO customerDAO = new CustomerDAOImpl();

    @Override
    public ArrayList<CustomerDTO> getAllCustomers() throws SQLException, ClassNotFoundException {
        return customerDAO.getAll();
    }

    @Override
    public boolean addCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException {
        return customerDAO.add(dto);
    }

    @Override
    public boolean updateCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException {
        return customerDAO.update(dto);
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return customerDAO.exist(id);
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return customerDAO.delete(id);
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        return customerDAO.generateNewID();
    }

////////////////////////////////////////////////////////////////////////////////////////////////////////
//    public ArrayList<CustomerDTO> getAllCustomers() throws SQLException, ClassNotFoundException {
//        CustomerDAO customerDAO = new CustomerDAOImpl();
//        return customerDAO.getAll();
//    }
//    public boolean saveCustomers(CustomerDTO dto) throws SQLException, ClassNotFoundException {
//        CustomerDAO customerDAO = new CustomerDAOImpl();
//        return customerDAO.add(dto);
//    }
//    public boolean updateCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException {
//        CustomerDAO customerDAO = new CustomerDAOImpl();
//        return customerDAO.update(dto);
//    }
//    public boolean exist(String dto) throws SQLException, ClassNotFoundException{
//        CustomerDAO customerDAO = new CustomerDAOImpl();
//        return customerDAO.exist(dto);
//    }
//    public String generateNewID() throws SQLException, ClassNotFoundException{
//        CustomerDAO customerDAO = new CustomerDAOImpl();
//        return customerDAO.generateNewID();
//    }
//    public boolean delete(String dto) throws SQLException, ClassNotFoundException{
//        CustomerDAO customerDAO = new CustomerDAOImpl();
//        return customerDAO.delete(dto);
//    }
//    public CustomerDTO search(String dto) throws SQLException, ClassNotFoundException{
//        CustomerDAO customerDAO = new CustomerDAOImpl();
//        return customerDAO.search(dto);
//    }
}
