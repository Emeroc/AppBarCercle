package fr.esstin.baresstin.actions.admin.clients;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;

import fr.esstin.baresstin.dto.CADto;
import fr.esstin.baresstin.dto.ProductDto;
import fr.esstin.baresstin.service.OrderServiceLocal;

public class ChartAction {

	@EJB
	private OrderServiceLocal orderService;

	private int year;
	private Date date;
	private BigDecimal ca;

	private List<CADto> l;
	private List<CADto> l2;

	public String execute() {
		if (year != 0){
			l = orderService.getCA(year);
			l2 = orderService.getContributorBonus(year);
		}
		if(date!=null)
			ca =orderService.getCADate(date);
		
		return "success";
	}

	/**
	 * @return the year
	 */
	public int getYear() {
		return year;
	}

	/**
	 * @param year
	 *            the year to set
	 */
	public void setYear(int year) {
		this.year = year;
	}

	/**
	 * @return the l
	 */
	public List<CADto> getL() {
		return l;
	}

	/**
	 * @param l
	 *            the l to set
	 */
	public void setL(List<CADto> l) {
		this.l = l;
	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * @return the ca
	 */
	public BigDecimal getCa() {
		return ca;
	}

	/**
	 * @param ca the ca to set
	 */
	public void setCa(BigDecimal ca) {
		this.ca = ca;
	}

	/**
	 * @return the l2
	 */
	public List<CADto> getL2() {
		return l2;
	}

	/**
	 * @param l2 the l2 to set
	 */
	public void setL2(List<CADto> l2) {
		this.l2 = l2;
	}

}
