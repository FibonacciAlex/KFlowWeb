package com.model.util;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;


public class PageTag extends TagSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int pageNo;  
	private int recordCount;  
	private int pageSize;  
	private String url; 
	private String serverID;
	
	
	@Override
	public int doStartTag() throws JspException {
		
		int pageCount = (this.recordCount+this.pageSize-1)/this.pageSize;
		StringBuilder sb = new StringBuilder();
		sb.append("<div class='cutpage' >");
		if(this.recordCount != 0){
			if(this.pageNo > pageCount){
				this.pageNo = pageCount;
			}
			if(this.pageNo < 1){
				this.pageNo = 1;
			}
			HttpServletRequest request = (HttpServletRequest) this.pageContext.getRequest();
			Enumeration<String> e = request.getParameterNames();
			String name = null;
			String value = null;
			while (e.hasMoreElements()) {
				name = (String) e.nextElement();
				value = request.getParameter(name);
				if(name.equals("serverID")){
					this.serverID = value;
				}
				if(name.equals("page")){
					if(value != null && !"".equals(value)){
						this.pageNo = Integer.parseInt(value);
					}else{
						sb.append("<input type='hidden' name=\"").append(name).append("\" value=\"").append(value).append("\"/>");
					}
				}
			}
			if(this.pageNo==1){
				sb.append("<a><span>上一页</span></a>");
			}else{
				sb.append("<a href='"+this.url+"?pageNo="+(this.pageNo-1)+" &recordCount=" + this.recordCount+ "&serverID="+this.serverID+"'><span>上一页</span></a>");
			}
			int start = 1;
			if(this.pageNo > 4){
				start = this.pageNo - 1;
				sb.append("<a href='"+this.url+"?pageNo=1&recordCount=" + this.recordCount+ "&serverID="+this.serverID+"'><span>1</span></a>");
				sb.append("  ");
				sb.append("<a href='"+this.url+"?pageNo=2&recordCount=" + this.recordCount+ "&serverID="+this.serverID+"'><span>2</span></a>");
				sb.append("  ");
				sb.append("<a href='"+this.url+"?pageNo=3&recordCount=" + this.recordCount+ "&serverID="+this.serverID+"'><span>3</span></a>");
				sb.append("  ");
				sb.append("<a href='"+this.url+"?pageNo=4&recordCount=" + this.recordCount+ "&serverID="+this.serverID+"'><span>4</span></a>");
				sb.append("...");
			}
			int end = this.pageNo + 1;
			if (end > pageCount) {
				end = pageCount;
			}
			for (int i = start; i <= end; i++) {
				if (this.pageNo == i){
					sb.append("<a href='javascript:void(0);' class=\"current\">").append("<span>"+i+"</span>").append("</a>   ");
				}else{
					sb.append("<a href='"+this.url+"?pageNo="+i+"&recordCount=" + this.recordCount+ "&serverID="+this.serverID+"'>").append("<span>"+i+"</span>").append("</a>  ");
				}
			}
			if (end < pageCount - 2) {
				sb.append("...");
			}
			if (end < pageCount - 1) {
				sb.append("<a href='"+this.url+"?pageNo="+(pageCount - 1)+"&recordCount=" + this.recordCount+ "&serverID="+this.serverID+"'>").append("<span>"+(pageCount - 1)+"</span></a>  ");
			}
			if (end < pageCount) {
				sb.append("<a href='"+this.url+"?pageNo="+pageCount+"&recordCount=" + this.recordCount+ "&serverID="+this.serverID+"'>").append("<span>"+pageCount+"</span></a>  ");
			}
			if (this.pageNo == pageCount)
				sb.append("<a><span>下一页").append("</a></span>");
			else {
				sb.append("<a href='"+this.url+"?pageNo="+(this.pageNo+1)+"&recordCount=" + this.recordCount+ "&serverID="+this.serverID+"'><span>下一页</span></a>");
			}
		}
		sb.append("</div>");
		try {
			this.pageContext.getOut().println(sb.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}


	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getRecordCount() {
		return recordCount;
	}
	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public void setUrl(String url) {
		this.url = url;
	}

}

