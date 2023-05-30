package bo;

import bo1.ItemBO;
import dao.Custom.Impl.ItemDAOImpl;
import dao.Custom.ItemDAO;
import model.CustomerDTO;
import model.ItemDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class ItemBOImpl implements ItemBO {
    ItemDAO itemDAO = new ItemDAOImpl();

    @Override
    public ArrayList<ItemDTO> getAllItems() throws SQLException, ClassNotFoundException {
        return itemDAO.getAll();
    }

    @Override
    public boolean deleteItem(String code) throws SQLException, ClassNotFoundException {
        return itemDAO.delete(code);
    }

    @Override
    public boolean saveItem(ItemDTO dto) throws SQLException, ClassNotFoundException {
        return itemDAO.add(dto);
    }

    @Override
    public boolean updateItem(ItemDTO dto) throws SQLException, ClassNotFoundException {
        return itemDAO.update(dto);
    }

    @Override
    public boolean existItem(String code) throws SQLException, ClassNotFoundException {
        return itemDAO.exist(code);
    }

    @Override
    public String generateNewCode() throws SQLException, ClassNotFoundException {
        return itemDAO.generateNewID();
    }

//    public ArrayList<ItemDTO> getAllItems() throws SQLException, ClassNotFoundException {
//        return itemDAO.getAll();
//    }
//    public boolean addItems(ItemDTO dto) throws SQLException, ClassNotFoundException {
//        return itemDAO.add(dto);
//    }
//    public boolean updateItems(ItemDTO dto) throws SQLException, ClassNotFoundException {
//        return itemDAO.update(dto);
//    }
//    public boolean exist(String dto) throws SQLException, ClassNotFoundException{
//        return itemDAO.exist(dto);
//    }
//    public String generateNewID() throws SQLException, ClassNotFoundException{
//        return itemDAO.generateNewID();
//    }
//    public boolean delete(String dto) throws SQLException, ClassNotFoundException{
//        return itemDAO.delete(dto);
//    }

}
