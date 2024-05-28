package net.jpa.controller;

import lombok.RequiredArgsConstructor;
import net.jpa.domain.Member;
import net.jpa.domain.OrderSearch;
import net.jpa.domain.Orders;
import net.jpa.domain.item.Item;
import net.jpa.service.ItemService;
import net.jpa.service.MemberService;
import net.jpa.service.OrdersService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final OrdersService orderService;
    private final MemberService memberService;
    private final ItemService itemService;

    /**
     * 상품 주문
     */
    @GetMapping(value = "/order")
    public String createForm(Model model) {
        List<Member> members = memberService.findAll();
        List<Item> items = itemService.findItems();
        model.addAttribute("members", members);
        model.addAttribute("items", items);
        return "order/orderForm";
    }
    @PostMapping(value = "/order")
    public String order(@RequestParam("memberId") Long memberId,
                        @RequestParam("itemId") Long itemId,
                        @RequestParam("count") Long count) {
        orderService.order(memberId, itemId, count);
        return "redirect:/orders";
    }

    /**
     * 주문 목록 검색 및 취소
     */
    @GetMapping(value = "/orders")
    public String orderList(@ModelAttribute("orderSearch") OrderSearch orderSearch, Model model) {
        List<Orders> orders = orderService.findOrders(orderSearch);
        model.addAttribute("orders", orders);
        return "order/orderList";
    }

    @PostMapping(value = "/orders/{orderId}/cancel")
    public String cancelOrder(@PathVariable("orderId") Long orderId) {
        orderService.cancelOrder(orderId);
        return "redirect:/orders";
    }

}
