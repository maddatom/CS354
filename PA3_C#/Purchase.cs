public class Purchase
{
  private string name;
  private int quantity;
  private double unitPrice;

  public Purchase(string name, int quantity, double unitPrice)
  {
      this.name = name;
      this.quantity = quantity;
      this.unitPrice = unitPrice;
  }

  public string GetName(){
    return name;
  }

  public double GetPrice(){
    return unitPrice;
  }

  public int GetQuantity() {
    return quantity;
  }

  public void SetPrice(double price){
    this.price = price;
  }

  public void SetQuantity(int q){
    quantity = q;
  }

  @override
  public string ToString() {
    name + "\n\tprice: $" + unitPrice + "\n\tIn-stock: " + quantity;
  }

  static void Main(){
    Console.WriteLine("hello wrld");
  }
  // unit testing
}
