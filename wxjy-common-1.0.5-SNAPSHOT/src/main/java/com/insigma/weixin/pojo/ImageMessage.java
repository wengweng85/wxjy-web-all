package com.insigma.weixin.pojo;

/**
 * ImageMessage
 * ΢��ͼƬ��Ϣ
 */
public class ImageMessage extends BaseMessage{

    private Image Image;//

    public Image getImage() {
        return Image;
    }

    public void setImage(Image image) {
        Image = image;
    }

    @Override
    public String toString() {
        return "ImageMessage [Image=" + Image + "]";
    }
    
}
