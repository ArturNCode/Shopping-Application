package FromE2U;
import java.io.*;
/**
 * @author Bryce Kurek.
 */
public class Product implements Serializable
{
    private String name, description, productid;
    private double price, invoice;
    private int quantity;
    
    //constructor
    public Product(String name, String description, String productid, double price, 
            double invoice, int quantity)
    {
        this.name = name;
        this.description = description;
        this.productid = productid;
        this.price = price;
        this.invoice = invoice;
        this.quantity = quantity;
    }
    //copy constructor
    /**
     * makes deep copy of product
     * @param product 
     */
    public Product(Product product)
    {
        name = product.getName();
        description = product.getDescription();
        productid = product.getProductID();
        price = product.getPrice();
        invoice = product.getInvoice();
        quantity = product.getQuantity();
    }
    /**
     * returns JSON string for this object
     * @return 
     */
    @Override
    public String toString()
    {
        return "Product{name='" + name + '\'' 
                + ". description='" + description + '\'' 
                + ". productid='" + productid + '\'' 
                + ". price=" + price 
                + ". invoice=" + invoice 
                + ". quantity=" + quantity 
                + "}";
    }
    //accessor functions
    public String getName()
    {
        return name;
    }
    public String getDescription()
    {
        return description;
    }
    public String getProductID()
    {
        return productid;
    }
    public double getPrice()
    {
        return price;
    }
    public double getInvoice()
    {
        return invoice;
    }
    public int getQuantity()
    {
        return quantity;
    }
    //mutator functions
    public void setName(String name)
    {
        this.name = name;
    }
    public void setDescription(String description)
    {
        this.description = description;
    }
    public void setProductID(String productid)
    {
        this.productid = productid;
    }
    public void setPrice(double price)
    {
        this.price = price;
    }
    public void setInvoice(double invoice)
    {
        this.invoice = invoice;
    }
    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }
}