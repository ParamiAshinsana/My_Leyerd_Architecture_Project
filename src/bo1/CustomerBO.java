package bo1;

import bo.SuperBo;
import model.CustomerDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerBO extends SuperBo {

    public ArrayList<CustomerDTO> getAllCustomers() throws SQLException, ClassNotFoundException;

    public boolean addCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException ;

    public boolean updateCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException ;

    public boolean exist(String id) throws SQLException, ClassNotFoundException;

    public boolean delete(String id) throws SQLException, ClassNotFoundException;

    public String generateNewID() throws SQLException, ClassNotFoundException;

}

