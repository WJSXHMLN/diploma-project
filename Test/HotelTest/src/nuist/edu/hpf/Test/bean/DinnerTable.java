package nuist.edu.hpf.Test.bean;

import java.util.Date;

public class DinnerTable {

	private Integer id;// PRIMARY KEY AUTO_INCREMENT, 
	private String tableName;// VARCHAR(20), 表名
	private Integer tableStatus;// INT DEFAULT 0, 餐桌状态：0表示未使用  1表示正在使用
	private Date createDate;
	private Date updateDate;
	private Integer disabled;//餐桌是否被删除 0没有删除   1已删除
	private Integer useUserId;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public Integer getTableStatus() {
		return tableStatus;
	}
	public void setTableStatus(Integer tableStatus) {
		this.tableStatus = tableStatus;
	}
	
	
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public Integer getDisabled() {
		return disabled;
	}
	public void setDisabled(Integer disabled) {
		this.disabled = disabled;
	}
	public Integer getUseUserId() {
		return useUserId;
	}
	public void setUseUserId(Integer useUserId) {
		this.useUserId = useUserId;
	}
	@Override
	public String toString() {
		return "DinnerTable [id=" + id + ", tableName=" + tableName + ", tableStatus=" + tableStatus + ", createDate="
				+ createDate + ", updateDate=" + updateDate + ", disabled=" + disabled + ", useUserId=" + useUserId
				+ "]";
	}
	
}
