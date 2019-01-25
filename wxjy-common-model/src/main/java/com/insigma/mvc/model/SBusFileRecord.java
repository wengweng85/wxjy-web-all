package com.insigma.mvc.model;

public class SBusFileRecord {

    private String bus_uuid;
    private String file_uuid;
    private String file_bus_id;
    private String file_bus_type;
    private String bus_addtime;
    private String bus_comment;
    private String file_bus_description;


    public String getFile_bus_description() {
        return file_bus_description;
    }

    public void setFile_bus_description(String file_bus_description) {
        this.file_bus_description = file_bus_description;
    }

    public String getBus_uuid() {
        return bus_uuid;
    }

    public void setBus_uuid(String bus_uuid) {
        this.bus_uuid = bus_uuid;
    }

    public String getFile_uuid() {
        return file_uuid;
    }

    public void setFile_uuid(String file_uuid) {
        this.file_uuid = file_uuid;
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

    public String getBus_addtime() {
        return bus_addtime;
    }

    public void setBus_addtime(String bus_addtime) {
        this.bus_addtime = bus_addtime;
    }

    public String getBus_comment() {
        return bus_comment;
    }

    public void setBus_comment(String bus_comment) {
        this.bus_comment = bus_comment;
    }
}
