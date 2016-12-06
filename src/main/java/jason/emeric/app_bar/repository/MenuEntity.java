package jason.emeric.app_bar.repository;


import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.eclipse.persistence.annotations.PrivateOwned;

@Entity
public class MenuEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	private String name;
	
	@Column(precision = 19, scale = 2)
	private BigDecimal price;
	
	@OneToMany(mappedBy="menu", cascade={CascadeType.ALL})
	@PrivateOwned
	private List<MenuPart> menuParts;
	
	private boolean enable;

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the price
	 */
	public BigDecimal getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	/**
	 * @return the menuParts
	 */
	public List<MenuPartEntity> getMenuParts() {
		return menuParts;
	}

	/**
	 * @param menuParts the menuParts to set
	 */
	public void setMenuParts(List<MenuPartEntity> menuParts) {
		this.menuParts = menuParts;
	}

	/**
	 * @return the enable
	 */
	public boolean isEnable() {
		return enable;
	}

	/**
	 * @param enable the enable to set
	 */
	public void setEnable(boolean enable) {
		this.enable = enable;
	}
	
	
}
