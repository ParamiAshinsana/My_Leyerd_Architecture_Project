package bo;

import bo1.PurchaseOrderBO;
import dao.Custom.CustomerDAO;
import dao.Custom.Impl.CustomerDAOImpl;
import dao.Custom.Impl.ItemDAOImpl;
import dao.Custom.Impl.OrderDAOImpl;
import dao.Custom.Impl.OrderDetailsDAOImpl;
import dao.Custom.ItemDAO;
import dao.Custom.OrderDAO;
import dao.Custom.OrderDetailsDAO;
import dao.DAOFactory;
import db.DBConnection;
import model.CustomerDTO;
import model.ItemDTO;
import model.OrderDTO;
import model.OrderDetailDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PlaceOrderBOImpl implements PurchaseOrderBO {

    //OrderDAO orderDAO = new OrderDAOImpl();
    //CustomerDAO customerDAO = new CustomerDAOImpl();
    //ItemDAO itemDAO = new ItemDAOImpl();
    //OrderDetailsDAO orderDetailsDAO = new OrderDetailsDAOImpl();

    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);
    ItemDAO itemDAO = (ItemDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ITEM);
    OrderDAO orderDAO = (OrderDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ITEM);
    OrderDetailsDAO orderDetailsDAO = (OrderDetailsDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ITEM);


    @Override
    public CustomerDTO searchCustomer(String id) throws SQLException, ClassNotFoundException {
        return customerDAO.search(id);
    }

    @Override
    public ItemDTO searchItem(String code) throws SQLException, ClassNotFoundException {
        return itemDAO.search(code);
    }

    @Override
    public boolean existItem(String code) throws SQLException, ClassNotFoundException {
        return itemDAO.exist(code);
    }

    @Override
    public boolean existCustomer(String id) throws SQLException, ClassNotFoundException {
        return  customerDAO.exist(id);
    }

    @Override
    public String generateOrderID() throws SQLException, ClassNotFoundException {
        return null;
    }

//    @Override
//    public void generateOrderID() throws SQLException, ClassNotFoundException {
//        //return orderDAO.generateNewId();
//    }

    @Override
    public ArrayList<CustomerDTO> getAllCustomers() throws SQLException, ClassNotFoundException {
        return  customerDAO.getAll();
    }

    @Override
    public ArrayList<ItemDTO> getAllItems() throws SQLException, ClassNotFoundException {
        return itemDAO.getAll();
    }

    @Override
    public boolean purchaseOrder(String orderId, LocalDate orderDate, String customerId, List<OrderDetailDTO> orderDetails) {
        Connection connection = null;
        try {
            connection= DBConnection.getDbConnection().getConnection();

            //Check order id already exist or not
            // OrderDAO orderDAO = new OrderDAOImpl();
            // boolean b1 = orderDAO.existOrder(orderId);
            boolean b1 = orderDAO.exist(orderId);
            //if order id already exist//*
            if (b1) {
                return false;
            }

            connection.setAutoCommit(false);

            //Save the Order to the order table
            OrderDAOImpl orderDAO1 = new OrderDAOImpl();
            boolean b2 = orderDAO1.add(new OrderDTO(orderId, orderDate, customerId));

            if (!b2) {
                connection.rollback();
                connection.setAutoCommit(true);
                return false;
            }


            // add data to the Order Details table
            //OrderDetailsDAOImpl orderDetailsDAO = new OrderDetailsDAOImpl();
            OrderDetailsDAO orderDetailsDAO=new OrderDetailsDAOImpl();
            for (OrderDetailDTO detail : orderDetails) {
                boolean b3 = orderDetailsDAO.add(detail);
                if (!b3) {
                    connection.rollback();
                    connection.setAutoCommit(true);
                    return false;
                }

                //Search & Update Item
                ItemDTO item = findItem(detail.getItemCode());
                item.setQtyOnHand(item.getQtyOnHand() - detail.getQty());

                //update item
//                ItemDAO itemDAO = new ItemDAOImpl();
                boolean b = itemDAO.update(new ItemDTO(item.getCode(), item.getDescription(), item.getUnitPrice(), item.getQtyOnHand()));

                if (!b) {
                    connection.rollback();
                    connection.setAutoCommit(true);
                    return false;
                }
            }

            connection.commit();
            connection.setAutoCommit(true);
            return true;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public ItemDTO findItem(String code) {
        try {
//            ItemDAO itemDAO = new ItemDAOImpl();
            return itemDAO.search(code);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to find the Item " + code, e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
//    public boolean placeOrder(String orderId, LocalDate orderDate, String customerId, List<OrderDetailDTO> orderDetails){
//        /*Transaction*/
//        Connection connection = null;
//        try {
//            connection= DBConnection.getDbConnection().getConnection();
//
//            //Check order id already exist or not
//
//            boolean b1 = orderDAO.exist(orderId);
//            /*if order id already exist*/
//            if (b1) {
//                return false;
//            }
//
//            connection.setAutoCommit(false);
//
//            //Save the Order to the order table
//            boolean b2 = orderDAO.add(new OrderDTO(orderId, orderDate, customerId));
//
//            if (!b2) {
//                connection.rollback();
//                connection.setAutoCommit(true);
//                return false;
//            }
//
//
//            // add data to the Order Details table
//
//            for (OrderDetailDTO detail : orderDetails) {
//                boolean b3 = orderDetailsDAO.add(detail);
//                if (!b3) {
//                    connection.rollback();
//                    connection.setAutoCommit(true);
//                    return false;
//                }
//
//                //Search & Update Item
//                ItemDTO item = findItem(detail.getItemCode());
//                item.setQtyOnHand(item.getQtyOnHand() - detail.getQty());
//
//                //update item
//                boolean b = itemDAO.update(new ItemDTO(item.getCode(), item.getDescription(), item.getUnitPrice(), item.getQtyOnHand()));
//
//                if (!b) {
//                    connection.rollback();
//                    connection.setAutoCommit(true);
//                    return false;
//                }
//            }
//
//            connection.commit();
//            connection.setAutoCommit(true);
//            return true;
//
//        } catch (
//                SQLException throwables) {
//            throwables.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        return false;
//    }
//    public ItemDTO findItem(String code) {
//        try {
//            return itemDAO.search(code);
//        } catch (SQLException e) {
//            throw new RuntimeException("Failed to find the Item " + code, e);
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }



}
