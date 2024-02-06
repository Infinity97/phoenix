package com.pheonix.core.utils.helper;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.aspectj.weaver.ast.Call;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
public class QrUtil {

	public static byte[] generateQRCode(String data) throws WriterException, IOException {
		int width = 256;
		int height = 256;
		String format = "png";
		Map<EncodeHintType, Object> hints = new HashMap<>();
		hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");

		QRCodeWriter writer = new QRCodeWriter();
		BitMatrix bitMatrix = writer.encode(data, BarcodeFormat.QR_CODE, width, height, hints);

		BufferedImage image = MatrixToImageWriter.toBufferedImage(bitMatrix);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write(image, format, baos);
		byte[] imageBytes = baos.toByteArray();

		FileUtils.convertByteArrayToFile(imageBytes);
		return imageBytes;
	}
}
