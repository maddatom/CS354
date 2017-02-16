using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Text.RegularExpressions;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Navigation;
using System.Windows.Shapes;

namespace CashRegister
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
        public readonly decimal SALES_TAX = 0.06m;
        private GroceryCart cart;
        public MainWindow()
        {
            InitializeComponent();
            cart = new GroceryCart();
            CartListView.ItemsSource = cart;
        }

        private void AddButtonClick(object sender, RoutedEventArgs e)
        {
            string name = NameTextBox.Text;
            int quantity;
            decimal price;

            // try to parse the quantity entered by the user, stored that # to quantity
            // store the result of TryParse to the bool parseQuantityResult
            bool parseQuantityResult = int.TryParse(QuantityTextBox.Text, out quantity);
            bool parsePriceResult = decimal.TryParse(PriceTextBox.Text, out price);

            Item t = new Item(name, quantity, price);
            // Update the binding properties for the GUI
            t.Name = name; t.Price = price; t.Quantity = quantity;

            // Add item to the cart
            cart.Add(t);

            // update price & tax
            updateInvoice();
            // refresh the GUI
            CartListView.Items.Refresh();

            // Get the forms ready for the next item
            NameTextBox.Clear();
            PriceTextBox.Clear();
            QuantityTextBox.Clear();
        }

        //
        // Summary:
        //     The actions performed when the REMOVE button is clicked in the GUI
        //
        // Parameters:
        //   send:
        //     The generic handler / delegate implementation to be invoked.
        //
        //   e:
        //     The routed event identifier for this instance of the System.Windows.RoutedEventArgs
        //     class.
        private void RemoveButtonClick(object sender, RoutedEventArgs e)
        {
            foreach (var item in CartListView.SelectedItems)
            {
                cart.Remove(item);
            }
            updateInvoice();
            CartListView.Items.Refresh();
        }

        //
        // Summary:
        //      Update the price information for the cart
        private void updateInvoice()
        {
            decimal subTotal = cart.getSubTotal();
            SubtotalTextBox.Text = subTotal.ToString();

            decimal tax = subTotal * SALES_TAX;
            TaxTextBox.Text = tax.ToString();

            TotalTextBox.Text = (subTotal + tax).ToString();
        }

        //
        // Summary:
        //     The actions performed when the REMOVE ALL button is clicked in the GUI
        //
        // Parameters:
        //   send:
        //     The generic handler / delegate implementation to be invoked.
        //
        //   e:
        //     The routed event identifier for this instance of the System.Windows.RoutedEventArgs
        //     class.
        private void RemoveAllButtonClick(object send, RoutedEventArgs e)
        {
            cart.Clear();
            updateInvoice();
            CartListView.Items.Refresh();
        }

        //
        // Summary:
        //     Limit the Quantity input box to just whole numbers
        private void QuantityTextBoxValidation(object sender, TextCompositionEventArgs e)
        {
            Regex regex = new Regex("[^0-9]+");
            e.Handled = regex.IsMatch(e.Text);
        }

        //
        // Summary:
        //       Limit the Name input box to just letters
        private void NameTextBoxValidation(object sender, TextCompositionEventArgs e)
        {
            Regex regex = new Regex("[^a-zA-Z]");
            e.Handled = regex.IsMatch(e.Text);
        }

        //
        // Summary: 
        //     Limit the Price input box to just numbers (including fractions)
        private void PriceTextBoxValidation(object sender, TextCompositionEventArgs e)
        {
            Regex regex = new Regex("^[.][^0-9]+");
            e.Handled = regex.IsMatch(e.Text);
        }
    }
}
