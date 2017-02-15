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
        private double price;
        string name;

        public Item(string name, int quantity, double price)
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
        public void setPrice(double price)
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

        public double getPrice()
        {
            return price;
        }

        public int getQuantity()
        {
            return quantity;
        }

        public string guiString()
        {
            return getName() + ":\n\t" + getPrice().ToString() +"\t" + getQuantity().ToString();
        }

        override
        public string ToString()
        {
            return getName() + "\t" + getPrice().ToString() + "\t" + getQuantity().ToString();
        }
    }
}
