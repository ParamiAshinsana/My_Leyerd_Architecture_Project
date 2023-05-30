package bo1;

import model.ItemDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ItemBO {
    public ArrayList<ItemDTO> getAllItems() throws SQLException, ClassNotFoundException;

    public boolean delete(String code) throws SQLException, ClassNotFoundException ;

    public boolean addItems(ItemDTO dto) throws SQLException, ClassNotFoundException;

    public boolean updateItems(ItemDTO dto) throws SQLException, ClassNotFoundException;

    public boolean exist(String code) throws SQLException, ClassNotFoundException ;

    public String generateNewID() throws SQLException, ClassNotFoundException ;
}

