using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CashRegister
{
    class Item
    {
        private int quantity;
        private decimal price;
        private string name;
        
        // Need for Path binding in MainWindow.xaml
        public string Name { get; set; }
        public decimal Price { get; set; }
        public int Quantity { get; set; }

        public Item(string name, int quantity, decimal price)
        {
            this.name = name;
            this.price = price;
            this.quantity = quantity;
        }

        public Item(string name)
        {
            this.name = name;
        }

        public void setName(string name)
        {
            this.name = name;
        }

        public void setPrice(decimal price)
        {
            this.price = price;
        }

        public void setQuantity(int quantity)
        {
            this.quantity = quantity;
        }

        public string getName()
        {
            return name;
        }

        public decimal getPrice()
        {
            return price;
        }

        public int getQuantity()
        {
            return quantity;
        }
        
        override
        public string ToString()
        {
            return getName() + ", Price:" + getPrice().ToString() + ", In-stock:" + getQuantity().ToString();
        }
    }
}
