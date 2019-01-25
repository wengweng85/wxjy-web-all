package com.insigma.mvc.model;

import java.util.List;

public class CmsInfo extends PageInfo {

    private int f_info_id;
    private int f_org_id;
    private int f_creator_id;
    private int f_site_id;
    private int f_node_id;
    private String f_publish_date;
    private String f_off_date;
    private String f_priority;
    private String f_is_with_image;
    private String f_views;
    private String f_downloads;
    private String f_comments;
    private String f_diggs;
    private String f_score;
    private String f_status;
    private String f_p1;
    private String f_p2;
    private String f_p3;
    private String f_p4;
    private String f_p5;
    private String f_p6;
    private String f_html_status;

    private String f_title;
    private String f_link;
    private String f_html;
    private String f_subtitle;
    private String f_full_title;
    private String f_meta_description;
    private String f_is_new_window;
    private String f_color;
    private String f_is_strong;
    private String f_is_em;
    private String f_info_path;
    private String f_info_template;
    private String f_source;
    private String f_source_url;
    private String f_author;
    private String f_small_image;
    private String f_large_image;
    private String f_video;
    private String f_video_name;
    private String f_video_length;
    private String f_video_time;
    private String f_file;
    private String f_file_name;
    private String f_file_length;
    private String f_doc;
    private String f_doc_name;
    private String f_doc_length;
    private String f_doc_pdf;
    private String f_doc_swf;
    private String f_is_allow_comment;
    private String f_step_name;

    private String f_key;
    private String f_value;

    private String url;

    private String keyword;

    private List<String> imageList;

    private String video_url = "";


    private String video_url_jpg = "";


    public String getVideo_url_jpg() {
        return video_url_jpg;
    }

    public void setVideo_url_jpg(String video_url_jpg) {
        this.video_url_jpg = video_url_jpg;
    }

    public int getF_info_id() {
        return f_info_id;
    }

    public void setF_info_id(int f_info_id) {
        this.f_info_id = f_info_id;
    }

    public int getF_org_id() {
        return f_org_id;
    }

    public void setF_org_id(int f_org_id) {
        this.f_org_id = f_org_id;
    }

    public int getF_creator_id() {
        return f_creator_id;
    }

    public void setF_creator_id(int f_creator_id) {
        this.f_creator_id = f_creator_id;
    }

    public int getF_site_id() {
        return f_site_id;
    }

    public void setF_site_id(int f_site_id) {
        this.f_site_id = f_site_id;
    }

    public int getF_node_id() {
        return f_node_id;
    }

    public void setF_node_id(int f_node_id) {
        this.f_node_id = f_node_id;
    }

    public String getF_publish_date() {
        return f_publish_date;
    }

    public void setF_publish_date(String f_publish_date) {
        this.f_publish_date = f_publish_date;
    }

    public String getF_off_date() {
        return f_off_date;
    }

    public void setF_off_date(String f_off_date) {
        this.f_off_date = f_off_date;
    }

    public String getF_priority() {
        return f_priority;
    }

    public void setF_priority(String f_priority) {
        this.f_priority = f_priority;
    }

    public String getF_is_with_image() {
        return f_is_with_image;
    }

    public void setF_is_with_image(String f_is_with_image) {
        this.f_is_with_image = f_is_with_image;
    }

    public String getF_views() {
        return f_views;
    }

    public void setF_views(String f_views) {
        this.f_views = f_views;
    }

    public String getF_downloads() {
        return f_downloads;
    }

    public void setF_downloads(String f_downloads) {
        this.f_downloads = f_downloads;
    }

    public String getF_comments() {
        return f_comments;
    }

    public void setF_comments(String f_comments) {
        this.f_comments = f_comments;
    }

    public String getF_diggs() {
        return f_diggs;
    }

    public void setF_diggs(String f_diggs) {
        this.f_diggs = f_diggs;
    }

    public String getF_score() {
        return f_score;
    }

    public void setF_score(String f_score) {
        this.f_score = f_score;
    }

    public String getF_status() {
        return f_status;
    }

    public void setF_status(String f_status) {
        this.f_status = f_status;
    }

    public String getF_p1() {
        return f_p1;
    }

    public void setF_p1(String f_p1) {
        this.f_p1 = f_p1;
    }

    public String getF_p2() {
        return f_p2;
    }

    public void setF_p2(String f_p2) {
        this.f_p2 = f_p2;
    }

    public String getF_p3() {
        return f_p3;
    }

    public void setF_p3(String f_p3) {
        this.f_p3 = f_p3;
    }

    public String getF_p4() {
        return f_p4;
    }

    public void setF_p4(String f_p4) {
        this.f_p4 = f_p4;
    }

    public String getF_p5() {
        return f_p5;
    }

    public void setF_p5(String f_p5) {
        this.f_p5 = f_p5;
    }

    public String getF_p6() {
        return f_p6;
    }

    public void setF_p6(String f_p6) {
        this.f_p6 = f_p6;
    }

    public String getF_html_status() {
        return f_html_status;
    }

    public void setF_html_status(String f_html_status) {
        this.f_html_status = f_html_status;
    }

    public String getF_title() {
        return f_title;
    }

    public void setF_title(String f_title) {
        this.f_title = f_title;
    }

    public String getF_link() {
        return f_link;
    }

    public void setF_link(String f_link) {
        this.f_link = f_link;
    }

    public String getF_html() {
        return f_html;
    }

    public void setF_html(String f_html) {
        this.f_html = f_html;
    }

    public String getF_subtitle() {
        return f_subtitle;
    }

    public void setF_subtitle(String f_subtitle) {
        this.f_subtitle = f_subtitle;
    }

    public String getF_full_title() {
        return f_full_title;
    }

    public void setF_full_title(String f_full_title) {
        this.f_full_title = f_full_title;
    }

    public String getF_meta_description() {
        return f_meta_description;
    }

    public void setF_meta_description(String f_meta_description) {
        this.f_meta_description = f_meta_description;
    }

    public String getF_is_new_window() {
        return f_is_new_window;
    }

    public void setF_is_new_window(String f_is_new_window) {
        this.f_is_new_window = f_is_new_window;
    }

    public String getF_color() {
        return f_color;
    }

    public void setF_color(String f_color) {
        this.f_color = f_color;
    }

    public String getF_is_strong() {
        return f_is_strong;
    }

    public void setF_is_strong(String f_is_strong) {
        this.f_is_strong = f_is_strong;
    }

    public String getF_is_em() {
        return f_is_em;
    }

    public void setF_is_em(String f_is_em) {
        this.f_is_em = f_is_em;
    }

    public String getF_info_path() {
        return f_info_path;
    }

    public void setF_info_path(String f_info_path) {
        this.f_info_path = f_info_path;
    }

    public String getF_info_template() {
        return f_info_template;
    }

    public void setF_info_template(String f_info_template) {
        this.f_info_template = f_info_template;
    }

    public String getF_source() {
        return f_source;
    }

    public void setF_source(String f_source) {
        this.f_source = f_source;
    }

    public String getF_source_url() {
        return f_source_url;
    }

    public void setF_source_url(String f_source_url) {
        this.f_source_url = f_source_url;
    }

    public String getF_author() {
        return f_author;
    }

    public void setF_author(String f_author) {
        this.f_author = f_author;
    }

    public String getF_small_image() {
        return f_small_image;
    }

    public void setF_small_image(String f_small_image) {
        this.f_small_image = f_small_image;
    }

    public String getF_large_image() {
        return f_large_image;
    }

    public void setF_large_image(String f_large_image) {
        this.f_large_image = f_large_image;
    }

    public String getF_video() {
        return f_video;
    }

    public void setF_video(String f_video) {
        this.f_video = f_video;
    }

    public String getF_video_name() {
        return f_video_name;
    }

    public void setF_video_name(String f_video_name) {
        this.f_video_name = f_video_name;
    }

    public String getF_video_length() {
        return f_video_length;
    }

    public void setF_video_length(String f_video_length) {
        this.f_video_length = f_video_length;
    }

    public String getF_video_time() {
        return f_video_time;
    }

    public void setF_video_time(String f_video_time) {
        this.f_video_time = f_video_time;
    }

    public String getF_file() {
        return f_file;
    }

    public void setF_file(String f_file) {
        this.f_file = f_file;
    }

    public String getF_file_name() {
        return f_file_name;
    }

    public void setF_file_name(String f_file_name) {
        this.f_file_name = f_file_name;
    }

    public String getF_file_length() {
        return f_file_length;
    }

    public void setF_file_length(String f_file_length) {
        this.f_file_length = f_file_length;
    }

    public String getF_doc() {
        return f_doc;
    }

    public void setF_doc(String f_doc) {
        this.f_doc = f_doc;
    }

    public String getF_doc_name() {
        return f_doc_name;
    }

    public void setF_doc_name(String f_doc_name) {
        this.f_doc_name = f_doc_name;
    }

    public String getF_doc_length() {
        return f_doc_length;
    }

    public void setF_doc_length(String f_doc_length) {
        this.f_doc_length = f_doc_length;
    }

    public String getF_doc_pdf() {
        return f_doc_pdf;
    }

    public void setF_doc_pdf(String f_doc_pdf) {
        this.f_doc_pdf = f_doc_pdf;
    }

    public String getF_doc_swf() {
        return f_doc_swf;
    }

    public void setF_doc_swf(String f_doc_swf) {
        this.f_doc_swf = f_doc_swf;
    }

    public String getF_is_allow_comment() {
        return f_is_allow_comment;
    }

    public void setF_is_allow_comment(String f_is_allow_comment) {
        this.f_is_allow_comment = f_is_allow_comment;
    }

    public String getF_step_name() {
        return f_step_name;
    }

    public void setF_step_name(String f_step_name) {
        this.f_step_name = f_step_name;
    }

    public String getF_key() {
        return f_key;
    }

    public void setF_key(String f_key) {
        this.f_key = f_key;
    }

    public String getF_value() {
        return f_value;
    }

    public void setF_value(String f_value) {
        this.f_value = f_value;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public List<String> getImageList() {
        return imageList;
    }

    public void setImageList(List<String> imageList) {
        this.imageList = imageList;
    }

    public String getVideo_url() {
        return video_url;
    }

    public void setVideo_url(String video_url) {
        this.video_url = video_url;
    }
}
