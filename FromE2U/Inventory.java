package FromE2U;
import java.util.ArrayList;
import java.io.*;
/**
 * @author Bryce Kurek & Artur Nascimento
 */
public class Inventory implements Serializable
{
    private ArrayList<Product> products;
    double profit, revenue, cost;
    
    public Inventory()
    {
        products = new ArrayList();
        profit = 0;
        revenue = 0;
        cost = 0;
        readFile();
    }
    /**
     * Reads profit, revenue, and cost from file to this object
     * as well as Product ArrayList.
     */
    public void readFile()
    {
        try
        {
            FileInputStream readData = new FileInputStream("profReveCos.ser");
            ObjectInputStream readStream = new ObjectInputStream(readData);
            
            Inventory x;
            x = (Inventory) readStream.readObject();
            this.profit = x.profit;
            this.revenue = x.revenue;
            this.cost = x.cost;
            readStream.close();
            readData.close();
        }
        catch(Exception e) { }
        try
        {
            FileInputStream readData = new FileInputStream("inventory.ser");
            ObjectInputStream readStream = new ObjectInputStream(readData);
            
            products = (ArrayList<Product>) readStream.readObject();
            System.out.println(products.get(0).getName());
            readStream.close();
            readData.close();
            
        }
        catch(Exception e) { }
        try
        {
            FileInputStream readData = new FileInputStream("profReveCosNew.ser");
            ObjectInputStream readStream = new ObjectInputStream(readData);
            
            double x = (Double) readStream.readObject();
            if (revenue == revenue + x) { }
            else { revenue += x; }

            readStream.close();
            readData.close();
        }
        catch(Exception e) { } 
    }
    /**
     * writes profit, revenue, and cost, and product ArrayList object.
     */
    public void writeFile()
    {
        try
        {
            FileOutputStream writeData = new FileOutputStream("inventory.ser");
            ObjectOutputStream writeStream = new ObjectOutputStream(writeData);
            
            writeStream.writeObject(products);
            writeStream.flush();
            writeStream.close();
            writeData.close();
        }
        catch(IOException e) { }
        try
        {
            FileOutputStream writeData = new FileOutputStream("profReveCos.ser");
            ObjectOutputStream writeStream = new ObjectOutputStream(writeData);
            
            writeStream.writeObject(this);
            writeStream.flush();
            writeStream.close();
            writeData.close();
        }
        catch(IOException e) { } 
    }
    /**
     * Calculate the analytic report.
     */
    public void calculateReport()
    {
        for(int i = 0; i < products.size(); i++)
        {
            cost += products.get(i).getInvoice() * products.get(i).getQuantity();
            profit = revenue - cost;
        }
    }
    /**
     * removes matching product and adds the updated product to ArrayList.
     * @param product 
     */
    public void updateProduct(Product product)
    {
        if(findProduct(product.getProductID()) != null)
        {
            for(int i = 0; i < products.size(); i++)
            {
                if(products.get(i).getProductID().equals(product.getProductID()))
                {
                    products.remove(products.get(i));
                    addProduct(product);
                }
            }
        }
        writeFile();
    }
    /**
     * adds product object to ArrayList.
     * @param product 
     */
    public void addProduct(Product product)
    {
        Product p = product;
        products.add(p);
        
        writeFile();
    }
    /**
     * returns product with matching productid.
     * @param productid
     * @return 
     */
    public Product findProduct(String productid)
    {
        for(int i = 0; i < products.size(); i++)
        {
            if(products.get(i).getProductID().equals(productid))
                return products.get(i);
        }
        
        return null;
    }
    /**
     * returns JSON string for this object.
     * @return 
     */
    @Override
    public String toString()
    {
        return "Inventory{profit=" + profit 
                + ". revenue=" + revenue
                + ". cost=" + cost 
                + "}\n";
    }
    //accessor functions    
    public double getProfit()
    {
        return profit;
    }
    public double getRevenue()
    {
        return revenue;
    }
    public double getCost()
    {
        return cost;
    }
    public ArrayList getInventory()
    {
        return products;
    }
    public void setProfit(double profit)
    {
        this.profit = profit;
        writeFile();
    }
    public void setRevenue(double revenue)
    {
        this.revenue = revenue;
        writeFile();
    }
    public void setCost(double cost)
    {
        this.cost = cost;
        writeFile();
    }
    //end mutator functions
}
