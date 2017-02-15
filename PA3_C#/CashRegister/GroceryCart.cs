using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CashRegister
{
    class GroceryCart:ArrayList
    {
        public double getTotal()
        {
            double total;
            foreach (var item in this)
            {
                Item t = item as Item;
                total += t.getPrice();
            }
            return total;
        }
        override
        public string ToString()
        {
            StringBuilder builder = new StringBuilder("[");
            foreach (var item in this)
            {
                builder.Append(item.ToString()).Append(", ");
            }
            builder.Append("]");
            return builder.ToString();
        }
    }
}
