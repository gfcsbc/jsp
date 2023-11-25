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
@Table(name = "user_message")
public class Message {
	private int id;
	private User fromUser;
	private User toUser;
	private String content;
	private String addDate;
	private boolean allUser;

	private String recontent;

	public String getRecontent() {
		return recontent;
	}

	public void setRecontent(String recontent) {
		this.recontent = recontent;
	}

	public boolean isAllUser() {
		return allUser;
	}

	public void setAllUser(boolean allUser) {
		this.allUser = allUser;
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

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "from_userid")
	public User getFromUser() {
		return fromUser;
	}

	public void setFromUser(User fromUser) {
		this.fromUser = fromUser;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "to_userid")
	public User getToUser() {
		return toUser;
	}

	public void setToUser(User toUser) {
		this.toUser = toUser;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAddDate() {
		return addDate;
	}

	public void setAddDate(String addDate) {
		this.addDate = addDate;
	}

}
