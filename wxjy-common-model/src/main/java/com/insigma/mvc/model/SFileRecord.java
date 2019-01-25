package com.insigma.mvc.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


/**
 *  文件记录表
 * 
 */
public class SFileRecord extends PageInfo implements java.io.Serializable  {
	/**
 	 *
 	 */
	private static final long serialVersionUID = 1L;
	private String file_uuid;
	private String file_name;
	private long file_length;
	private String file_length_str;
	private String file_addtime_date;
	private String file_status;
	private String file_path;
	private String file_rel_path; //相对路径
	private String path;//全路径
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date file_addtime;
	private String file_type;
	private String file_bus_id;
	private String file_bus_type;
	private String file_bus_name;


	private String file_bus_type_name;
	private String file_addtime_str;

	private String bus_uuid;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date bus_addtime;
	private String selectnodes;
	private String [] ids;
	private String upload_callback;
	private String file_bus_description;

	private String file_rel_path_jpg;
	/**
	 * 文件访问网址
	 */
	private String file_web_path;
	private String fileModule;
	private String file_rel_small_path;

	private String file_md5;

	private String bus_status;

	public String getFile_rel_small_path() {
		return file_rel_small_path;
	}

	public void setFile_rel_small_path(String file_rel_small_path) {
		this.file_rel_small_path = file_rel_small_path;
	}

	/**
	 * 文件大小（将字节转换成KB或MB）
	 */
	private String filesize;


	private String file_app_path;

	public String getFile_app_path() {
		return file_app_path;
	}

	public void setFile_app_path(String file_app_path) {
		this.file_app_path = file_app_path;
	}

	public String getFile_addtime_date() {
		return file_addtime_date;
	}

	public void setFile_addtime_date(String file_addtime_date) {
		this.file_addtime_date = file_addtime_date;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getFile_uuid() {
		return file_uuid;
	}

	public void setFile_uuid(String file_uuid) {
		this.file_uuid = file_uuid;
	}

	public String getFile_name() {
		return file_name;
	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

	public long getFile_length() {
		return file_length;
	}

	public void setFile_length(long file_length) {
		this.file_length = file_length;
	}

	public String getFile_length_str() {
		return file_length_str;
	}

	public void setFile_length_str(String file_length_str) {
		this.file_length_str = file_length_str;
	}

	public String getFile_status() {
		return file_status;
	}

	public void setFile_status(String file_status) {
		this.file_status = file_status;
	}

	public String getFile_path() {
		return file_path;
	}

	public void setFile_path(String file_path) {
		this.file_path = file_path;
	}

	public String getFile_rel_path() {
		return file_rel_path;
	}

	public void setFile_rel_path(String file_rel_path) {
		this.file_rel_path = file_rel_path;
	}

	public Date getFile_addtime() {
		return file_addtime;
	}

	public void setFile_addtime(Date file_addtime) {
		this.file_addtime = file_addtime;
	}

	public String getFile_type() {
		return file_type;
	}

	public void setFile_type(String file_type) {
		this.file_type = file_type;
	}

	public String getFile_bus_id() {
		return file_bus_id;
	}

	public void setFile_bus_id(String file_bus_id) {
		this.file_bus_id = file_bus_id;
	}

	public String getFile_bus_type() {
		return file_bus_type;
	}

	public void setFile_bus_type(String file_bus_type) {
		this.file_bus_type = file_bus_type;
	}

	public String getFile_bus_name() {
		return file_bus_name;
	}

	public void setFile_bus_name(String file_bus_name) {
		this.file_bus_name = file_bus_name;
	}

	public String getFile_bus_type_name() {
		return file_bus_type_name;
	}

	public void setFile_bus_type_name(String file_bus_type_name) {
		this.file_bus_type_name = file_bus_type_name;
	}

	public String getFile_addtime_str() {
		return file_addtime_str;
	}

	public void setFile_addtime_str(String file_addtime_str) {
		this.file_addtime_str = file_addtime_str;
	}

	public String getBus_uuid() {
		return bus_uuid;
	}

	public void setBus_uuid(String bus_uuid) {
		this.bus_uuid = bus_uuid;
	}

	public Date getBus_addtime() {
		return bus_addtime;
	}

	public void setBus_addtime(Date bus_addtime) {
		this.bus_addtime = bus_addtime;
	}

	public String getSelectnodes() {
		return selectnodes;
	}

	public void setSelectnodes(String selectnodes) {
		this.selectnodes = selectnodes;
	}

	public String[] getIds() {
		return ids;
	}

	public void setIds(String[] ids) {
		this.ids = ids;
	}

	public String getUpload_callback() {
		return upload_callback;
	}

	public void setUpload_callback(String upload_callback) {
		this.upload_callback = upload_callback;
	}

	public String getFile_bus_description() {
		return file_bus_description;
	}

	public void setFile_bus_description(String file_bus_description) {
		this.file_bus_description = file_bus_description;
	}

	public String getFile_rel_path_jpg() {
		return file_rel_path_jpg;
	}

	public void setFile_rel_path_jpg(String file_rel_path_jpg) {
		this.file_rel_path_jpg = file_rel_path_jpg;
	}

	public String getFile_web_path() {
		return file_web_path;
	}

	public void setFile_web_path(String file_web_path) {
		this.file_web_path = file_web_path;
	}

	public String getFileModule() {
		return fileModule;
	}

	public void setFileModule(String fileModule) {
		this.fileModule = fileModule;
	}

	public String getFilesize() {
		return filesize;
	}

	public void setFilesize(String filesize) {
		this.filesize = filesize;
	}

	public String getFile_md5() {
		return file_md5;
	}

	public void setFile_md5(String file_md5) {
		this.file_md5 = file_md5;
	}

	public String getBus_status() {
		return bus_status;
	}

	public void setBus_status(String bus_status) {
		this.bus_status = bus_status;
	}
}
