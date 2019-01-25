package com.insigma.mvc.model;

import java.util.Date;


/**
 *  excel文件上传状态表
 */
public class SysExcelBatch extends PageInfo implements java.io.Serializable  {
	private String excel_batch_id;
	private String excel_batch_number;
	private Long  excel_batch_total_count;
	private Long excel_batch_success_count; 
	private Long  excel_batch_error_count;
	private Date excel_batch_begin_time;
	private String excel_batch_begin_time_string;
	private String excel_batch_end_time_string;
	private Date excel_batch_end_time;
	private String excel_batch_status;
	private Long excel_batch_cost ;
	private String excel_batch_file_path;
	private String mincolumns;
	private Long excel_batch_file_length;
	private String excel_batch_excel_type;
	private String upload_callback;
	private String excel_batch_aae011;
	private String excel_batch_rt_code;
	private String excel_batch_rt_msg;
    private String excel_batch_file_name;
    private String excel_error_file_path;
    private String excel_error_file_download;
    private Integer excel_batch_data_status;
    private String excel_batch_baseinfoid;
    private String excel_batch_assistid;
    
	public String getExcel_batch_end_time_string() {
		return excel_batch_end_time_string;
	}
	public void setExcel_batch_end_time_string(String excel_batch_end_time_string) {
		this.excel_batch_end_time_string = excel_batch_end_time_string;
	}
	public Integer getExcel_batch_data_status() {
		return excel_batch_data_status;
	}
	public void setExcel_batch_data_status(Integer excel_batch_data_status) {
		this.excel_batch_data_status = excel_batch_data_status;
	}
	public String getExcel_error_file_path() {
		return excel_error_file_path;
	}
	public void setExcel_error_file_path(String excel_error_file_path) {
		this.excel_error_file_path = excel_error_file_path;
	}
	public String getExcel_error_file_download() {
		return excel_error_file_download;
	}
	public void setExcel_error_file_download(String excel_error_file_download) {
		this.excel_error_file_download = excel_error_file_download;
	}
	public String getExcel_batch_file_name() {
		return excel_batch_file_name;
	}
	public void setExcel_batch_file_name(String excel_batch_file_name) {
		this.excel_batch_file_name = excel_batch_file_name;
	}
	public String getExcel_batch_begin_time_string() {
		return excel_batch_begin_time_string;
	}
	public void setExcel_batch_begin_time_string(
			String excel_batch_begin_time_string) {
		this.excel_batch_begin_time_string = excel_batch_begin_time_string;
	}
	public String getExcel_batch_rt_code() {
		return excel_batch_rt_code;
	}
	public void setExcel_batch_rt_code(String excel_batch_rt_code) {
		this.excel_batch_rt_code = excel_batch_rt_code;
	}
	public String getExcel_batch_rt_msg() {
		return excel_batch_rt_msg;
	}
	public void setExcel_batch_rt_msg(String excel_batch_rt_msg) {
		this.excel_batch_rt_msg = excel_batch_rt_msg;
	}
	public String getExcel_batch_aae011() {
		return excel_batch_aae011;
	}
	public void setExcel_batch_aae011(String excel_batch_aae011) {
		this.excel_batch_aae011 = excel_batch_aae011;
	}
	public String getUpload_callback() {
		return upload_callback;
	}
	public void setUpload_callback(String upload_callback) {
		this.upload_callback = upload_callback;
	}
	public String getMincolumns() {
		return mincolumns;
	}
	public void setMincolumns(String mincolumns) {
		this.mincolumns = mincolumns;
	}
	public String getExcel_batch_excel_type() {
		return excel_batch_excel_type;
	}
	public void setExcel_batch_excel_type(String excel_batch_excel_type) {
		this.excel_batch_excel_type = excel_batch_excel_type;
	}
	
	public Long getExcel_batch_file_length() {
		return excel_batch_file_length;
	}
	public void setExcel_batch_file_length(Long excel_batch_file_length) {
		this.excel_batch_file_length = excel_batch_file_length;
	}
	public String getExcel_batch_file_path() {
		return excel_batch_file_path;
	}
	public void setExcel_batch_file_path(String excel_batch_file_path) {
		this.excel_batch_file_path = excel_batch_file_path;
	}
	public String getExcel_batch_id() {
		return excel_batch_id;
	}
	public void setExcel_batch_id(String excel_batch_id) {
		this.excel_batch_id = excel_batch_id;
	}
	public String getExcel_batch_number() {
		return excel_batch_number;
	}
	public void setExcel_batch_number(String excel_batch_number) {
		this.excel_batch_number = excel_batch_number;
	}
	public Long getExcel_batch_total_count() {
		return excel_batch_total_count;
	}
	public void setExcel_batch_total_count(Long excel_batch_total_count) {
		this.excel_batch_total_count = excel_batch_total_count;
	}
	public Long getExcel_batch_success_count() {
		return excel_batch_success_count;
	}
	public void setExcel_batch_success_count(Long excel_batch_success_count) {
		this.excel_batch_success_count = excel_batch_success_count;
	}
	public Long getExcel_batch_error_count() {
		return excel_batch_error_count;
	}
	public void setExcel_batch_error_count(Long excel_batch_error_count) {
		this.excel_batch_error_count = excel_batch_error_count;
	}
	public Date getExcel_batch_begin_time() {
		return excel_batch_begin_time;
	}
	public void setExcel_batch_begin_time(Date excel_batch_begin_time) {
		this.excel_batch_begin_time = excel_batch_begin_time;
	}
	public Date getExcel_batch_end_time() {
		return excel_batch_end_time;
	}
	public void setExcel_batch_end_time(Date excel_batch_end_time) {
		this.excel_batch_end_time = excel_batch_end_time;
	}
	public String getExcel_batch_status() {
		return excel_batch_status;
	}
	public void setExcel_batch_status(String excel_batch_status) {
		this.excel_batch_status = excel_batch_status;
	}
	public Long getExcel_batch_cost() {
		return excel_batch_cost;
	}
	public void setExcel_batch_cost(Long excel_batch_cost) {
		this.excel_batch_cost = excel_batch_cost;
	}
	public String getExcel_batch_baseinfoid() {
		return excel_batch_baseinfoid;
	}
	public void setExcel_batch_baseinfoid(String excel_batch_baseinfoid) {
		this.excel_batch_baseinfoid = excel_batch_baseinfoid;
	}
	public String getExcel_batch_assistid() {
		return excel_batch_assistid;
	}
	public void setExcel_batch_assistid(String excel_batch_assistid) {
		this.excel_batch_assistid = excel_batch_assistid;
	}
}