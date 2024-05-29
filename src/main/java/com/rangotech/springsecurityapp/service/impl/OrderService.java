package com.rangotech.springsecurityapp.service.impl;

import com.rangotech.springsecurityapp.exceptions.ApiException;
import com.rangotech.springsecurityapp.exceptions.ResourceNotFoundException;
import com.rangotech.springsecurityapp.mapper.OrderDtoMapper;
import com.rangotech.springsecurityapp.mapper.OrderProductDtoMapper;
import com.rangotech.springsecurityapp.persistence.entity.*;
import com.rangotech.springsecurityapp.persistence.repository.*;
import com.rangotech.springsecurityapp.service.ICartService;
import com.rangotech.springsecurityapp.service.IOrderService;

import com.rangotech.springsecurityapp.service.dto.OrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Transactional
@Service
@RequiredArgsConstructor
public class OrderService implements IOrderService {

    private final CartRepository cartRepository;
    private final PaymentRepository paymentRepository;
    private final OrderRepository orderRepository;
    private final OrderProductRepository orderProductRepository;
    private final ICartService cartService;
    private final OrderDtoMapper orderDtoMapper;
    private final OrderProductDtoMapper orderProductDtoMapper;

    @Override
    public OrderDto placeOrder(String usernameId, Long cartId, String paymentMethod) {

        Cart cart = cartRepository.findCartByEmailAndCartId(usernameId, cartId)
                .orElseThrow(()->new ResourceNotFoundException("El carrito de compras no estra registrado en la bd"));

        Order order = new Order();

        order.setUsername(usernameId);
        order.setOrderDate(LocalDateTime.now());
        order.setTotalAmount(cart.getTotalPrice());
        order.setOrderStatus("Orden Aceptada !");

        Payment payment = new Payment();
        payment.setOrder(order);
        payment.setPaymentMethod(paymentMethod);

        payment = paymentRepository.save(payment);

        order.setPayment(payment);

        Order savedOrder = orderRepository.save(order);

        List<CartProduct> cartProducts = cart.getCartProducts();

        if (cartProducts.isEmpty()) {
            throw new ApiException("El carrito esta vacio");
        }

        List<OrderProduct> orderProducts = new ArrayList<>();

        for (CartProduct cartProduct : cartProducts) {
            OrderProduct orderProduct = new OrderProduct();

            orderProduct.setProduct(cartProduct.getProduct());
            orderProduct.setQuantity(cartProduct.getQuantity());
            orderProduct.setOrderedProductPrice(cartProduct.getProduct().getPrice());
            orderProduct.setOrder(savedOrder);

            orderProducts.add(orderProduct);
        }

        orderProducts = orderProductRepository.saveAll(orderProducts);

        cart.getCartProducts().forEach(cp -> {
            int quantity = cp.getQuantity();

            Product product = cp.getProduct();

            cartService.deleteProductFromCart(cartId, cp.getProduct().getProductId());

            product.setQuantity(product.getQuantity() - quantity);
        });

        OrderDto orderDto = orderDtoMapper.map(savedOrder);


        //orderProducts.forEach(op -> orderDto.getOrderProducts().add(orderProductDtoMapper.map(op)));

        return orderDto;
    }

    @Override
    public List<OrderDto> getOrdersByUser(String emailId) {
        return null;
    }

    @Override
    public OrderDto updateOrder(String emailId, Long orderId, String orderStatus) {
        return null;
    }
}
