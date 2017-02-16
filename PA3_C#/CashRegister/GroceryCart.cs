using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CashRegister
{
    class GroceryCart : ArrayList
    {
        public decimal getSubTotal()
        {
            decimal total = 0m;
            foreach (var item in this)
            {
                Item t = item as Item;
                total += (t.getPrice() * t.getQuantity());
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
