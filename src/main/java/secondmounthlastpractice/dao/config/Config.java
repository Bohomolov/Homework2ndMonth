package secondmounthlastpractice.dao.config;

public class Config {
    public final static String LOGIN = "postgres";
    public final static String PASSWORD = "1234";
    public final static String DRIVER = "org.postgresql.Driver";
    public final static String URL = "jdbc:postgresql://localhost:5432/postgres";

    public static class Query {
        public static String SELECT_QUERY_ALL_CUSTOMER_BY_PRODUCT_NAME = "SELECT c.name AS customer_name, p.name FROM market.customer c INNER JOIN market.\"order\" o on o.customer_id = c.id INNER JOIN market.order_product op ON op.order_id = o.id INNER JOIN market.products p ON op.product_id = p.id WHERE p.name = 'hummer'";
        public static String SELECT_QUERY_ALL_PRODUCTS_WITH_CUSTOMER_NAME = "SELECT p.name AS product_name, c.name as customer_name FROM market.products p INNER JOIN market.order_product op ON p.id = op.product_id INNER JOIN market.\"order\" o ON o.id = op.order_id INNER JOIN market.customer c ON o.customer_id = c.id";
        public static String UPDATE = "update market.products set description = 'xz' where id = (select c.id from market.customer c where c.name = ?)";
    }

}