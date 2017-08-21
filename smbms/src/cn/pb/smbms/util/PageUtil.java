package cn.pb.smbms.util;

/**
 * 分页的工具类
 */
public class PageUtil {

	private Integer totalCount;// 总记录数 从数据库获取
	private Integer pageSize = 3;// 页大小
	private Integer pageCount;// 总页数
	private Integer pageIndex;// 当前页

	public Integer getTotalCount() {
		return totalCount;
	}

	/**
	 * 在我们得到总记录数的时候 因为我们已经知道了pageSize 所以我们可以求出总页数pageCount
	 * 
	 * @param totalCount
	 *            总记录数
	 * 
	 */
	public void setTotalCount(Integer totalCount) {
		if (totalCount > 0) {
			this.totalCount = totalCount;
			// 总页数
			this.pageCount = (totalCount % pageSize == 0) ? (totalCount / pageSize)
					: (totalCount / pageSize + 1);
		}

	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getPageCount() {
		return pageCount;
	}

	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}

	public Integer getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}

	public PageUtil() {
		super();
	}

	public PageUtil(Integer totalCount, Integer pageSize, Integer pageCount,
			Integer pageIndex) {
		super();
		this.totalCount = totalCount;
		this.pageSize = pageSize;
		this.pageCount = pageCount;
		this.pageIndex = pageIndex;
	}

	@Override
	public String toString() {
		return "PageUtil [totalCount=" + totalCount + ", pageSize=" + pageSize
				+ ", pageCount=" + pageCount + ", pageIndex=" + pageIndex + "]";
	}

}
