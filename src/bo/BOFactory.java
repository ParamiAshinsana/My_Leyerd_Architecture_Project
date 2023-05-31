package bo;

public class BOFactory {
    private static BOFactory boFactory;
    private BOFactory(){
    }
    public static BOFactory getBoFactory(){
        return (boFactory==null)? boFactory=new BOFactory() : boFactory;
    }

    public enum BOTypes{
        CUSTOMER,ITEM,PO
    }

    //Object creation logic for BO objects
    public SuperBo getBO(BOTypes types){
        switch (types){
            case CUSTOMER:
                return  new CustomerBOImpl();
            case ITEM:
                return (SuperBo) new ItemBOImpl();
            case PO:
                return (SuperBo) new PlaceOrderBOImpl();
            default:
                return null;
        }
    }
}
