import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class Main {

    /**
     * A simple class representing a shopping cart of products
     */
    public class ShoppingCart {
        private Map<Product, Integer> products = new HashMap<>();

        /**
         * Adds the given quantity of a product to the shopping cart.
         * The given {@code Product} must be non-null and quantity must be positive.
         *
         * @param product the {@code Product} to add to shopping cart
         * @param quantity a positive integer representing the quantity of the product to add
         */
        public void addProduct(Product product, int quantity) {
            if (product != null && quantity > 0) {
                if (products.get(product) != null) {
                    quantity += products.get(product);
                }
                products.put(product, quantity);
            }
        }

        /**
         * Removes the given quantity of a product from the shopping cart
         *
         * @param product the {@code Product} to remove from shopping cart
         * @param quantity a positive integer representing the quantity of the product to remove
         */
        public void removeProduct(Product product, int quantity) {
            if (products.get(product) != null && quantity > 0) {
                Integer newQuantity = products.get(product) - quantity;
                if (newQuantity <= 0) {
                    products.remove(product);
                } else {
                    products.put(product, newQuantity);
                }
            }
        }

        /**
         * Retrieves the total price of all items in the shopping cart
         *
         * @return the total price as a {@code BigDecimal}
         */
        public BigDecimal getTotalPrice() {
            BigDecimal totalPrice = BigDecimal.ZERO;
            for (Product product : products.keySet()) {
                if (product.getPrice() != null) {
                    BigDecimal quantity = BigDecimal.valueOf(products.get(product));
                    totalPrice.add(product.getPrice().multiply(quantity));
                }
            }
            return totalPrice;
        }
    }

    /**
     * Abstract object representing a product with a name and price
     */
    public abstract class Product {
        /**
         * Returns the identifying name of the product.
         * All concrete implementations must override this method.
         *
         * @return a @{code String} representing the product name
         */
        public abstract String getName();

        /**
         * Returns the price of the product.
         * All concrete implementations must override this method.
         *
         * @return a @{code BigDecimal} representing the product price
         */
        public abstract BigDecimal getPrice();

        @Override
        public boolean equals(Object other) {
            if (!(other instanceof Product)) {
                return false;
            }
            if (other == this) {
                return true;
            }
            Product otherProduct = (Product) other;
            EqualsBuilder equalsBuilder = new EqualsBuilder();
            equalsBuilder.append(getName(), otherProduct.getName());
            equalsBuilder.append(getPrice(), otherProduct.getPrice());
            return equalsBuilder.isEquals();
        }

        @Override
        public int hashCode() {
            HashCodeBuilder hashCodeBuilder = new HashCodeBuilder(17, 31);
            hashCodeBuilder.append(getName());
            hashCodeBuilder.append(getPrice());
            return hashCodeBuilder.toHashCode();
        }
    }

    /**
     * A means or method of processing a payment, such as a debit card,
     * credit card, or gift card.
     */
    public interface IPaymentMethod {

        /**
         * Processes a payment for the given amount.
         *
         * @param amount the amount of currency to process as a @{code BigDecimal}
         */
        void processPayment(BigDecimal amount);
    }

    /**
     * A class representing a credit card payment method, capable of processing payments
     */
    public class CreditCard implements IPaymentMethod {

        /**
         * Processes a payment through credit card for the given amount.
         *
         * @param amount the amount of currency to process as a @{code BigDecimal}
         */
        @Override
        public void processPayment(BigDecimal amount) {
            // process credit card payment
        }
    }

    /**
     * A class representing a gift card payment method, capable of processing payments
     */
    public class GiftCard implements IPaymentMethod {

        /**
         * Processes a payment through gift card for the given amount.
         *
         * @param amount the amount of currency to process as a @{code BigDecimal}
         */
        @Override
        public void processPayment(BigDecimal amount) {
            // process gift card payment
        }
    }

    /**
     * Represents the processing of an order for a shopping cart and payment method
     */
    public class Order {

        private ShoppingCart shoppingCart;
        private IPaymentMethod paymentMethod;

        /**
         * Constructs an instance of Order with the given shopping cart and payment method.
         *
         * @param shoppingCart the @{code ShoppingCart} containing all items for the order
         * @param paymentMethod the @{code IPaymentMethod} with which to process the payment for the order
         * @throws IllegalArgumentException if shoppingCart or paymentMethod are null
         */
        public Order(ShoppingCart shoppingCart, IPaymentMethod paymentMethod) throws IllegalArgumentException {
            setShoppingCart(shoppingCart);
            setPaymentMethod(paymentMethod);
        }

        /**
         * Set the shopping cart containing items relevant to this order.
         *
         * @param shoppingCart the @{code ShoppingCart} containing all items for the order
         * @throws IllegalArgumentException if shoppingCart is null
         */
        public void setShoppingCart(ShoppingCart shoppingCart) throws IllegalArgumentException {
            if (shoppingCart != null) {
                this.shoppingCart = shoppingCart;
            } else {
                throw new IllegalArgumentException("shoppingCart cannot be null");
            }
        }

        /**
         * Set the payment method to be used for this order.
         *
         * @param paymentMethod the @{code IPaymentMethod} to be used
         * @throws IllegalArgumentException if paymentMethod is null
         */
        public void setPaymentMethod(IPaymentMethod paymentMethod) throws IllegalArgumentException {
            if (paymentMethod != null) {
                this.paymentMethod = paymentMethod;
            } else {
                throw new IllegalArgumentException("paymentMethod cannot be null");
            }
        }

        /**
         * Performs all checkout operations for the order such as processing the payment
         */
        public void checkout() {
            paymentMethod.processPayment(shoppingCart.getTotalPrice());
            // process receipt
            // update inventory
            // etc.
            // using similar abstract services/processes to do so
        }
    }
}
