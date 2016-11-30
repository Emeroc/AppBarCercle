package fr.esstin.baresstin.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;

import fr.esstin.baresstin.BalanceHistory;
import fr.esstin.baresstin.Client;
import fr.esstin.baresstin.Order;
import fr.esstin.baresstin.OrderItem;
import fr.esstin.baresstin.StatProductTranscient;
import fr.esstin.baresstin.dao.MenuDao;
import fr.esstin.baresstin.dao.ClientDao;
import fr.esstin.baresstin.dao.OrderDao;
import fr.esstin.baresstin.dao.ProductDao;
import fr.esstin.baresstin.dto.CADto;
import fr.esstin.baresstin.dto.OrderDto;
import fr.esstin.baresstin.dto.OrderItemDto;
import fr.esstin.baresstin.dto.ProductDto;
import fr.esstin.baresstin.service.exceptions.OrderDoesntExistException;

@Stateless
public class OrderService implements OrderServiceLocal {

	@EJB
	private ProductServiceLocal productService;

	@EJB
	private MenuServiceLocal menuService;

	@Inject
	private OrderDao orderDao;

	@Inject
	private ProductDao productDao;

	@Inject
	private MenuDao menuDao;

	@EJB
	private MailServiceLocal mailService;

	@Inject
	private ClientDao clientDao;

	@Override
	public void add(OrderDto o, String admin) {
		Order e = new Order();
		fromDto(o, e);
		orderDao.save(e);

		// the generated id is needed to save the order's items
		// o.setId(e.getId());

		// Client remove account
		if (e.getClientId() != -1) {
			Client c = clientDao.findById(e.getClientId());
			BalanceHistory b = new BalanceHistory();
			b.setDate(new Date());
			b.setOperationId(e.getId());
			BigDecimal totalPrice = new BigDecimal(0);
			totalPrice.setScale(2);
			// System.out.println("ItemList " + e.getOrderItems());
			for (OrderItem oi : e.getOrderItems()) {
				// System.out.println("price " + oi.getOrderItemPrice());
				totalPrice = totalPrice.add(oi.getOrderItemPrice());
			}
			totalPrice = totalPrice.negate();
			b.setPrice(totalPrice);
			b.setStatus(1);
			b.setAdmin(admin);
			// System.out.println("balancetot " + b.getPrice());
			// System.out.println("client somme : " + c.getFirstname());
			clientDao.addBalance(c, b);
			if (c.getBalance().compareTo(new BigDecimal(0)) < 0)
				if (c.getMail() != null)
					if (c.getMail().length() > 0)
						mailService
								.sendMail(
										c.getMail(),
										c.getName(),
										"Bar ESSTIN : Merci de recharger le compte bar.",
										"Bonjour "
												+ c.getFirstname()
												+ ",\n\nMerci de venir recharger votre Compte Bar\n\nL'équipe Bar",null);

			// Order e = orderDao.findById(o.getId());
		}
		for (OrderItem oi : e.getOrderItems()) {
			if (oi.getDelivered())
				if (orderDao.isOrderCompleted(e.getId())) {
					e.setStatus("completed");
					break;
				} else {
					e.setStatus("partial");
				}
		}
		e.setAdmin(admin);
		orderDao.save(e);

	}

	/**
	 * TODO Gestion
	 */
	/*
	 * public void update(OrderDto o) { Order existingOrder =
	 * orderDao.findById(o.getId());
	 * 
	 * if (existingOrder == null) { throw new OrderDoesntExistException("Order "
	 * + o.getId() + " doesn't exist"); }
	 * 
	 * fromDto(o, existingOrder);
	 * 
	 * orderDao.save(existingOrder); }
	 */

	@Override
	public List<OrderDto> list() {
		List<OrderDto> results = new ArrayList<OrderDto>();
		List<Order> entities = orderDao.list();

		for (Order o : entities) {
			results.add(fromEntity(o));
		}
		return results;
	}

	@Override
	public List<OrderDto> listHistory(int offset, int limit) {
		List<OrderDto> results = new ArrayList<OrderDto>();
		List<Order> entities = orderDao.list(offset, limit);

		for (Order o : entities) {
			results.add(fromEntity(o));
		}
		return results;
	}

	@Override
	public List<OrderDto> listPendingOrders() {
		List<OrderDto> results = new ArrayList<OrderDto>();
		List<Order> entities = orderDao.listPendingOrders();

		for (Order o : entities) {
			results.add(fromEntity(o));
		}
		return results;
	}

	@Override
	public List<OrderItemDto> listPendingOrderItems() {
		List<OrderItemDto> results = new ArrayList<OrderItemDto>();
		List<OrderItem> entities = orderDao.listPendingOrderItems();

		for (OrderItem oi : entities) {
			results.add(fromEntity(oi));
		}
		return results;
	}

	@Override
	public OrderDto findOrderById(long id) {
		Order existingOrder = orderDao.findById(id);

		if (existingOrder == null) {
			return null;
		}
		return fromEntity(existingOrder);
	}

	@Override
	public void delete(long id) {
		Order existingOrder = orderDao.findById(id);

		if (existingOrder == null) {
			throw new OrderDoesntExistException("Order " + id
					+ " doesn't exist");
		}
		orderDao.delete(existingOrder);
	}

	/*
	 * @Override public void addItemForOrder(long orderId, OrderItemDto item) {
	 * Order o = orderDao.findById(orderId); OrderItem oi = new OrderItem();
	 * 
	 * fromDto(item, oi);
	 * 
	 * o.getOrderItems().add(oi); oi.setOrder(o);
	 * 
	 * orderDao.save(o);
	 * 
	 * // the generated id is needed by the OrderItemDto item.setId(oi.getId());
	 * }
	 */
	@Override
	public void deliverItem(OrderDto o, long itemId) {
		OrderItem oi = orderDao.findItemById(itemId);
		oi.setDelivered(true);

		Order e = orderDao.findById(o.getId());
		if (orderDao.isOrderCompleted(e.getId())) {
			e.setStatus("completed");
		} else {
			e.setStatus("partial");
		}
		orderDao.save(e);
	}

	@Override
	public void cancelDeliverItem(OrderDto o, long itemId) {
		OrderItem oi = orderDao.findItemById(itemId);
		oi.setDelivered(false);

		Order e = orderDao.findById(o.getId());
		e.setStatus("partial");
		orderDao.save(e);
	}

	@Override
	public void removeItem(OrderDto o, long itemId, String admin) {
		OrderItem oi = orderDao.findItemById(itemId);
		// oi.setOrder(null);
		if (oi.isCancelled() == false) {
			oi.setCancelled(true);
			if (!oi.getOrderItemPrice().equals(BigDecimal.ZERO)
					&& oi.getOrder().getClientId() > 0) {
				BalanceHistory b = new BalanceHistory();
				b.setAdmin(admin);
				b.setDate(new Date());
				b.setOperationId(0);
				b.setPrice(oi.getOrderItemPrice());
				b.setStatus(5);

				clientDao.addBalance(
						clientDao.findById(oi.getOrder().getClientId()), b);

			}
			orderDao.save(oi);
			deliverItem(o, itemId);
		}

		// Order e = orderDao.findById(o.getId());
		// e.getOrderItems().get(oi);
		/*
		 * if (e.getOrderItems().size() == 0) { orderDao.delete(e); } else { if
		 * (orderDao.isOrderCompleted(o.getId())) { e.setStatus("completed"); }
		 * orderDao.save(e); }
		 */
	}

	private OrderDto fromEntity(Order e) {
		OrderDto dto = new OrderDto();
		dto.setId(e.getId());
		dto.setDatePlaced(e.getDatePlaced());
		dto.setStatus(e.getStatus());
		dto.setClientId(e.getClientId());
		dto.setClientName(e.getClientName());
		dto.setAdmin(e.getAdmin());
		BigDecimal totalPrice = new BigDecimal("0");
		for (OrderItem oi : e.getOrderItems()) {
			dto.getOrderItems().add(fromEntity(oi));
			totalPrice = totalPrice.add(oi.getOrderItemPrice());
		}
		dto.setTotalPrice(totalPrice);

		return dto;
	}

	private void fromDto(OrderDto o, Order e) {
		e.setId(o.getId());
		e.setDatePlaced(o.getDatePlaced());
		e.setStatus(o.getStatus());
		e.setClientId(o.getClientId());
		e.setClientName(o.getClientName());

		if (e.getOrderItems() == null) {
			e.setOrderItems(new ArrayList<OrderItem>());
		} else {
			e.getOrderItems().clear();
		}
		//System.out.println("je suis passé par là");
		for (OrderItemDto oiDto : o.getOrderItems()) {
			//System.out.println("je suis passé par là");
			OrderItem oi = new OrderItem();
			fromDto(oiDto, oi);
			oi.setOrder(e);
			e.getOrderItems().add(oi);
		}
	}

	private OrderItemDto fromEntity(OrderItem e) {
		OrderItemDto dto = new OrderItemDto();
		dto.setId(e.getId());
		dto.setOrderId(e.getOrder().getId());
		dto.setCancelled(e.isCancelled());
		if (e.getProduct() != null) {
			dto.setProduct(productService.findProductById(e.getProduct()
					.getId(), false));
		}
		if (e.getMenu() != null) {
			dto.setMenu(menuService.findById(e.getMenu().getId()));
		}

		dto.setOrderItemPrice(e.getOrderItemPrice());
		dto.setDelivered(e.getDelivered());
		dto.setComment(e.getComment());
		return dto;
	}

	private void fromDto(OrderItemDto oi, OrderItem e) {
		e.setId(oi.getId());
		e.setOrder(orderDao.findById(oi.getOrderId()));
		e.setProduct(productDao.findById(oi.getProduct().getId()));
		if (oi.getMenu() != null)
			e.setMenu(menuDao.findById(oi.getMenu().getId()));
		if (oi.getComment() != null)
			if (oi.getComment().length() > 40)
				oi.setComment(oi.getComment().substring(0, 39));
		e.setComment(oi.getComment());
		System.out.println("prix du orderItem : " + oi.getOrderItemPrice());
		e.setOrderItemPrice(oi.getOrderItemPrice());
		e.setDelivered(oi.getDelivered());
	}

	@Override
	public List<CADto> getCA(int year) {
		List<CADto> l = new ArrayList<CADto>();
		List<Order> orders = orderDao.getCA(year);
		BigDecimal price = new BigDecimal(0);
		int month = 0;
		for (Order o : orders) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(o.getDatePlaced());
			while (cal.get(Calendar.MONTH) != month) {
				CADto ca = new CADto();
				ca.setCa(price);
				ca.setMonth(month);
				l.add(ca);
				price = new BigDecimal(0);
				month++;
			}

			for (OrderItem oi : o.getOrderItems()) {
				if (oi.isCancelled()==false)
				price = price.add(oi.getOrderItemPrice());
			}
		}
		while (month < 12) {
			CADto ca = new CADto();
			ca.setCa(price);
			ca.setMonth(month);
			l.add(ca);
			price = new BigDecimal(0);
			month++;
		}
		return l;
	}

	@Override
	public List<CADto> getContributorBonus(int year) {
		List<CADto> l = new ArrayList<CADto>();
		List<BalanceHistory> balances = orderDao.getContributorBonus(year);
		BigDecimal price = new BigDecimal(0);
		int month = 0;
		for (BalanceHistory o : balances) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(o.getDate());
			while (cal.get(Calendar.MONTH) != month) {
				CADto ca = new CADto();
				ca.setCa(price);
				ca.setMonth(month);
				l.add(ca);
				price = new BigDecimal(0);
				month++;
			}

			price = price.add(o.getPrice());

		}
		while (month < 12) {
			CADto ca = new CADto();
			ca.setCa(price);
			ca.setMonth(month);
			l.add(ca);
			price = new BigDecimal(0);
			month++;
		}
		return l;
	}

	@Override
	public BigDecimal getCADate(Date date) {
		return orderDao.getCADay(date);
	}

	

}