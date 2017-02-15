using System;
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


            double price;

            // try to parse the quantity entered by the user, stored that # to quantity
            // store the result of TryParse to the bool parseQuantityResult
            bool parseQuantityResult = int.TryParse(QuantityTextBox.Text, out quantity);
            bool parsePriceResult = double.TryParse(PriceTextBox.Text, out price);
            
            cart.Add(new Item(name, quantity, price));
            TotalTextBox.Text = cart.getTotal().ToString();
            CartListView.Items.Refresh();
        }

        private void RemoveButtonClick(object sender, RoutedEventArgs e)
        {
            int idx = CartListView.SelectedIndex;
            cart.RemoveAt(idx);
            CartListView.Items.Refresh();
        }

        private void RemoveAllButtonClick(object send, RoutedEventArgs e)
        {
            cart.Clear();
            CartListView.Items.Refresh();
        }

        /**
         * Limit the Quantity input box to just whole numbers
         */
        private void QuantityTextBox_PreviewTextInput(object sender, TextCompositionEventArgs e)
        {
            Regex regex = new Regex("[^0-9]+");
            e.Handled = regex.IsMatch(e.Text);
        }

        /**
         * Limit the Name input box to just letters
         */
        private void NameTextBox_PreviewTextInput(object sender, TextCompositionEventArgs e)
        {
            Regex regex = new Regex("[^a-zA-Z]");
            e.Handled = regex.IsMatch(e.Text);
        }

        /**
        * Limit the Price input box to just numbers (including fractions)
        */
        private void PriceTextBox_PreviewTextInput(object sender, TextCompositionEventArgs e)
        {
            Regex regex = new Regex("^[.][^0-9]+");
            e.Handled = regex.IsMatch(e.Text);
        }
    }
}
