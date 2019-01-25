package com.insigma.common.util;

import com.insigma.resolver.AppException;
import net.coobird.thumbnailator.Thumbnails;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;

import javax.imageio.ImageIO;

public class ImageUtil {

	/**
	 * �õ�ͼƬ����
	 * @param file
	 * @return
	 */
	public static int getImgWidth(File file){
		try{
			BufferedImage bufferedImage = ImageIO.read(file);
			return bufferedImage.getWidth();
		}catch (IOException e){
			return 0;
		}
	}

	/**
	 * �õ�ͼƬ���
	 * @param file
	 * @return
	 */
	public static int getImgHeight(File file) {
		try{
			BufferedImage bufferedImage = ImageIO.read(file);
			return bufferedImage.getHeight();
		}catch (IOException e){
			return 0;
		}
	}


	/**
	 * ͼ���и�ģ�     *
	 * @param srcImageFile            Դͼ���ַ
	 * @param dirImageFile            ��ͼ���ַ
	 * @param x                       Ŀ����Ƭ���x����
	 * @param y                      Ŀ����Ƭ���y����
	 * @param destWidth              Ŀ����Ƭ���
	 * @param destHeight             Ŀ����Ƭ�߶�
	 */
	public static boolean abscut(String srcImageFile,String dirImageFile, int x, int y, int destWidth, int destHeight) throws AppException{
		try {
			//������
			Image img;
			ImageFilter cropFilter;
			// ��ȡԴͼ��
			BufferedImage bi = ImageIO.read(new File(srcImageFile));
			int srcWidth = bi.getWidth(); // Դͼ���
			int srcHeight = bi.getHeight(); // Դͼ�߶�
			if (srcWidth >= destWidth && srcHeight >= destHeight) {
				Image image = bi.getScaledInstance(srcWidth, srcHeight, Image.SCALE_DEFAULT);
				// �Ľ����뷨:�Ƿ���ö��̼߳ӿ��и��ٶ�
				// �ĸ������ֱ�Ϊͼ���������Ϳ��
				// ��: CropImageFilter(int x,int y,int width,int height)
				cropFilter = new CropImageFilter(x, y, destWidth, destHeight);
				img = Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(image.getSource(), cropFilter));
				BufferedImage tag = new BufferedImage(destWidth, destHeight, BufferedImage.TYPE_INT_RGB);
				Graphics g = tag.getGraphics();
				g.drawImage(img, 0, 0, null); // ������С���ͼ
				g.dispose();
				// ���Ϊ�ļ�
				ImageIO.write(tag, "JPEG", new File(dirImageFile));
				return true;
			}else{
				return false;
			}
		} catch (Exception e) {
			throw new AppException(e);
		}
	}

	/**
	 * ����ͼ��
	 *
	 * @param srcImageFile       Դͼ���ļ���ַ
	 * @param result             ���ź��ͼ���ַ
	 * @param scale              ���ű���
	 * @param flag               ����ѡ��:true �Ŵ�; false ��С;
	 */
	public static void scale(String srcImageFile, String result, float scale, boolean flag) {
		try {
			BufferedImage src = ImageIO.read(new File(srcImageFile)); // �����ļ�
			int width = src.getWidth(); // �õ�Դͼ��
			int height = src.getHeight(); // �õ�Դͼ��
			if (flag) {
				// �Ŵ�
				width = new Float(width * scale).intValue();
				height = new Float(height * scale).intValue();
			} else {
				// ��С
				width = new Float(width * scale).intValue();
				height = new Float(height * scale).intValue();
			}
			Image image = src.getScaledInstance(width, height,Image.SCALE_DEFAULT);
			BufferedImage tag = new BufferedImage(width, height,BufferedImage.TYPE_INT_RGB);
			Graphics g = tag.getGraphics();
			g.drawImage(image, 0, 0, null); // ������С���ͼ
			g.dispose();
			ImageIO.write(tag, "JPEG", new File(result));// ������ļ���
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * �������ɰ�ָ����Ⱥ͸߶ȵ�ͼ��
	 * @param srcImageFile       Դͼ���ļ���ַ
	 * @param result             �µ�ͼ���ַ
	 * @param new_width             �����µ�ͼ����
	 * @param new_height            �����µ�ͼ��߶�
	 */
	public static void scale(String srcImageFile, String result, int new_width,int new_height) {
		scale(srcImageFile,result,new_width,new_height,0,0);
	}

	/**
	 * �������ɰ�ָ����Ⱥ͸߶ȵ�ͼ��
	 * @param srcImageFile       Դͼ���ļ���ַ
	 * @param result             �µ�ͼ���ַ
	 * @param new_width             �����µ�ͼ����
	 * @param new_height            �����µ�ͼ��߶�
	 * @param x                  x
	 * @param y                  y
	 */
	public static void scale(String srcImageFile, String result, int new_width,int new_height,int x,int y) {
		try {

			BufferedImage src = ImageIO.read(new File(srcImageFile)); // �����ļ�

			int width = src.getWidth(); // �õ�Դͼ��
			int height = src.getHeight(); // �õ�Դͼ��

			if (width > new_width) {
				width = new_width;
			}
			if (height > new_height) {
				height = new_height;
			}
			Image image = src.getScaledInstance(width, height,Image.SCALE_DEFAULT);
			BufferedImage tag = new BufferedImage(width, height,BufferedImage.TYPE_INT_RGB);
			Graphics g = tag.getGraphics();
			g.drawImage(image, x, y, null); // ������С���ͼ
			g.dispose();
			ImageIO.write(tag, "JPEG", new File(result));// ������ļ���
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * ͼ������ת�� GIF->JPG GIF->PNG PNG->JPG PNG->GIF(X)
	 */
	public static void convert(String source, String result) {
		try {
			File f = new File(source);
			f.canRead();
			f.canWrite();
			BufferedImage src = ImageIO.read(f);
			ImageIO.write(src, "JPG", new File(result));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * ����ָ����С��ָ������ѹ��ͼƬ
	 *
	 * @param srcPath ԴͼƬ��ַ
	 * @param desPath Ŀ��ͼƬ��ַ
	 * @param desFileSize ָ��ͼƬ��С����λkb
	 * @param accuracy ���ȣ��ݹ�ѹ���ı��ʣ�����С��0.9
	 * @return
	 */
	public static String commpressPicForScale(String srcPath, String desPath, long desFileSize, double accuracy) {
		if (!new File(srcPath).exists()) {
			return null;
		}
		try {
			File srcFile = new File(srcPath);
			long srcFileSize = srcFile.length();
			System.out.println("ԴͼƬ��" + srcPath + "����С��" + srcFileSize / 1024 + "kb");

			// 1����ת����jpg
			Thumbnails.of(srcPath).scale(1f).toFile(desPath);
			// �ݹ�ѹ����ֱ��Ŀ���ļ���СС��desFileSize
			commpressPicCycle(desPath, desFileSize, accuracy);

			File desFile = new File(desPath);
			System.out.println("Ŀ��ͼƬ��" + desPath + "����С" + desFile.length()
					/ 1024 + "kb");
			System.out.println("ͼƬѹ����ɣ�");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return desPath;
	}

	/**
	 *
	 * @param desPath
	 * @param desFileSize
	 * @param accuracy
	 * @throws IOException
	 */
	private static void commpressPicCycle(String desPath, long desFileSize, double accuracy) throws IOException {
		File srcFileJPG = new File(desPath);
		long srcFileSizeJPG = srcFileJPG.length();
		// 2���жϴ�С�����С��500kb����ѹ����������ڵ���500kb��ѹ��
		if (srcFileSizeJPG <= desFileSize * 1024) {
			return;
		}
		// ������
		BufferedImage bim = ImageIO.read(srcFileJPG);
		int srcWdith = bim.getWidth();
		int srcHeigth = bim.getHeight();
		int desWidth = new BigDecimal(srcWdith).multiply(new BigDecimal(accuracy)).intValue();
		int desHeight = new BigDecimal(srcHeigth).multiply(new BigDecimal(accuracy)).intValue();

		Thumbnails.of(desPath).size(desWidth, desHeight)
				.outputQuality(accuracy).toFile(desPath);
		commpressPicCycle(desPath, desFileSize, accuracy);
	}

	public static void main(String[] args) {

		commpressPicForScale("C:\\Users\\123\\Desktop\\1.png",
				"C:\\Users\\123\\Desktop\\12.jpg", 500, 0.8); // ͼƬС��500kb
	}



}
