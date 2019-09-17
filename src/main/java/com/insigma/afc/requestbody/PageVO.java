/* 
 * 日期：2018年12月4日
 *  
 * 版权所有：浙江浙大网新众合轨道交通工程有限公司
 */
/**
 * 
 */
package com.insigma.afc.requestbody;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Ticket:  分页实体类
 * @author  zengziqiang
 *
 */
@ApiModel(value = "PageBean", description = "分页类")
public class PageVO {

	private static Log log = LogFactory.getLog(PageVO.class);
	/**
	 * 当前页码
	 */
	@ApiModelProperty(value = "当前页码", required = true)
	@JsonInclude(value = Include.NON_NULL)
	@JsonProperty("page_num")
	@NotNull(message = "页码数不能为空")
	@Min(value = 1, message = "页码数必须为整数")
	private Integer pageNumber;
	/**
	 * 页面大小
	 */
	@ApiModelProperty(value = "页面大小", required = true)
	@JsonInclude(value = Include.NON_NULL)
	@JsonProperty("page_size")
	@NotNull
	@Min(value = 1, message = "页面大小必须为正整数")
	private Integer pageSize;
	/**
	 * 总记录数
	 */
	@ApiModelProperty("总记录数")
	@JsonInclude(value = Include.NON_NULL)
	@JsonProperty("total_count")
	private Long totalCount;

	public Integer getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
}
