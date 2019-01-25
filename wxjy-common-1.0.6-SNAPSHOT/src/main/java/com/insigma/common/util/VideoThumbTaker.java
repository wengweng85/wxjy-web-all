package com.insigma.common.util;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;

/**
 * ��Ƶ����
 */
public class VideoThumbTaker {

    /**
     * ��ȡָ����Ƶ��֡������ΪͼƬ��ָ��Ŀ¼
     *
     * @param filePath       Դ��Ƶ�ļ�·��
     * @param targerFilePath ��ȡ֡��ͼƬ���·��
     * @throws Exception
     */
    public static void fetchFrame(String filePath, String targerFilePath) throws Exception {
        FFmpegFrameGrabber ff = FFmpegFrameGrabber.createDefault(filePath);
        ff.start();
        int ffLength = ff.getLengthInFrames();
        Frame f = null;
        int i = 0;
        while (i < ffLength) {
            // ����ǰ100֡
            f = ff.grabFrame();
            if ((i > 100) && (f.image != null)) {
                break;
            }
            i++;
        }
        doExecuteFrame(f, targerFilePath);
        ff.stop();
    }

    public static void doExecuteFrame(Frame f, String targerFilePath) {
        if (null == f || null == f.image) {
            return;
        }

        Java2DFrameConverter converter = new Java2DFrameConverter();

        String imageMat = "jpg";
        BufferedImage bi = converter.getBufferedImage(f);
        File output = new File(targerFilePath);
        try {
            ImageIO.write(bi, imageMat, output);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        // ����
        fetchFrame("c:/webroot/fileroot/020203/201712/201712201017353sxhse.mp4",
                "c:/webroot/fileroot/020203/201712/201712201017353sxhse.jpg");
    }


}