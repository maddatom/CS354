using System;

public class Item
{
    private string name;
    private int quantity;
    private double unitPrice;

    public Item(string name, int quantity, double unitPrice)
    {
        this.name = name;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public Item(string name)
    {
        this.name = name;
    }

    public string GetName()
    {
        return name;
    }

    public double GetPrice()
    {
        return unitPrice;
    }

    public int GetQuantity()
    {
        return quantity;
    }

    public void SetPrice(double price)
    {
        this.unitPrice = price;
    }

    public void SetQuantity(int q)
    {
        quantity = q;
    }

    override
    public string ToString()
    {
        return name + ":\n  price: $" + unitPrice + "\n  In-stock: " + quantity;
    }

    // unit testing
    static void Main()
    {
        Item p = new Item("Milk", 5, 5.00);
        Console.WriteLine("Item's name: " + p.GetName());
        Console.WriteLine("Item's unit price: " + p.GetPrice());
        Console.WriteLine("In-stock: " + p.GetQuantity());

        p.SetPrice(10);
        p.SetQuantity(25);
        Console.WriteLine(p.ToString());
    }
}