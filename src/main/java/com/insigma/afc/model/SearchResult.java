/* 
 * 日期：2018年12月17日
 *  
 * 版权所有：浙江浙大网新众合轨道交通工程有限公司
 */
/**
 * 
 */
package com.insigma.afc.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Ticket:  查询结果实体类
 * @author  zengziqiang
 */
@ApiModel(value = "SearchResult", description = "列表查询结果类")
public class SearchResult<T> {

	@ApiModelProperty("页码")
	@JsonProperty("page_num")
	private Integer pageNumber;

	@ApiModelProperty("页面大小")
	@JsonProperty("page_size")
	private Integer pageSize;

	@ApiModelProperty("总数")
	@JsonProperty("total_count")
	private Long totalCount;

	@ApiModelProperty("列表内容")
	@JsonProperty("content")
	private List<T> content;

	/**
	 * @return the pageNumber
	 */
	public Integer getPageNumber() {
		return pageNumber;
	}

	/**
	 * @param pageNumber the pageNumber to set
	 */
	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}

	/**
	 * @return the pageSize
	 */
	public Integer getPageSize() {
		return pageSize;
	}

	/**
	 * @param pageSize the pageSize to set
	 */
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * @return the totalCount
	 */
	public Long getTotalCount() {
		return totalCount;
	}

	/**
	 * @param totalCount the totalCount to set
	 */
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}

	/**
	 * @return the content
	 */
	public List<T> getContent() {
		return content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(List<T> content) {
		this.content = content;
	}
}
