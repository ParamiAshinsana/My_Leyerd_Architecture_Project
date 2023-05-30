package bo;

import dao.Custom.Impl.ItemDAOImpl;
import dao.Custom.ItemDAO;
import model.CustomerDTO;
import model.ItemDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class ItemBOImpl {
    ItemDAO itemDAO = new ItemDAOImpl();


    public ArrayList<ItemDTO> getAllItems() throws SQLException, ClassNotFoundException {
        ItemDAO itemDAO = new ItemDAOImpl();
        return itemDAO.getAll();
    }
    public boolean addItems(ItemDTO dto) throws SQLException, ClassNotFoundException {
        ItemDAO itemDAO = new ItemDAOImpl();
        return itemDAO.add(dto);
    }
    public boolean updateItems(ItemDTO dto) throws SQLException, ClassNotFoundException {
        ItemDAO itemDAO = new ItemDAOImpl();
        return itemDAO.update(dto);
    }
    public boolean exist(String dto) throws SQLException, ClassNotFoundException{
        ItemDAO itemDAO = new ItemDAOImpl();
        return itemDAO.exist(dto);
    }
    public String generateNewID() throws SQLException, ClassNotFoundException{
        ItemDAO itemDAO = new ItemDAOImpl();
        return itemDAO.generateNewID();
    }
    public boolean delete(String dto) throws SQLException, ClassNotFoundException{
        ItemDAO itemDAO = new ItemDAOImpl();
        return itemDAO.delete(dto);
    }

}
