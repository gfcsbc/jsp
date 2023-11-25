package bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "express")
public class Express {
	private int id;
	private String phone;
	private String name;
	private String sid;//单号
	private String area;//所在区域
	private String status;//已签收,未签收
	private User addressee;//签收人
	private String finDate;//签收日期

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(unique = true, updatable = false)
	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getFinDate() {
		return finDate;
	}

	public void setFinDate(String finDate) {
		this.finDate = finDate;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "addressee")
	public User getAddressee() {
		return addressee;
	}

	public void setAddressee(User addressee) {
		this.addressee = addressee;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
