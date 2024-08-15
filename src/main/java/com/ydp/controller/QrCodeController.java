package com.ydp.controller;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/qr")
public class QrCodeController {

    private final String qrcodePath = Objects.requireNonNull(ClassLoader.getSystemClassLoader().getResource("static/qrcode")).getPath();

    // 生成二维码
    @GetMapping("create")
    public void createQrCode() {
        String randName = UUID.randomUUID().toString();
//        String qrcodePath = Objects.requireNonNull(ClassLoader.getSystemClassLoader().getResource("static/qrcode")).getPath();
//        qrcodePath="/Users/new/Desktop/javaroot/miaosha/src/main/resources/static/qrcode/";
        System.out.println("路径：" + qrcodePath);

        int height = 300;
        int width = 300;
        String format = "png";
        String content = "http://www.baidu.com/";

        // 定义二维码参数
        HashMap hints = new HashMap<>();
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");//编码
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);//容错级别
        hints.put(EncodeHintType.MARGIN, 1); //边框

        try {
            BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);
            Path file = new File(qrcodePath + "/" + randName + ".png").toPath();
            MatrixToImageWriter.writeToPath(bitMatrix, format, file);
        } catch (WriterException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("生成二维码成功：" + randName + ".png");
    }

    @GetMapping("readQr")
    public void readQrCode() {
        try {
            MultiFormatReader formatReader = new MultiFormatReader();
            File file = new File(qrcodePath + "/1fd2c2ed-09d3-4288-b8d4-c51daf30b238.png");
            BufferedImage image = ImageIO.read(file);
            // 定义二维码参数
            HashMap hints = new HashMap<>();
            hints.put(EncodeHintType.CHARACTER_SET, "utf-8");//编码

            BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(image)));
            Result result = formatReader.decode(binaryBitmap, hints);

            System.out.println("解析结果：" + result.toString());//http://www.baidu.com/
            System.out.println("格式：" + result.getBarcodeFormat());//格式：QR_CODE
            System.out.println("内容：" + result.getText());//内容：http://www.baidu.com/
            System.out.println("时间：" + result.getTimestamp());// 当前请求的时间戳
            System.out.println("点数：" + Arrays.toString(result.getResultPoints()));//[(85.0,240.0), (85.0,60.0), (265.0,60.0), (235.0,210.0)]
            System.out.println("data：" + result.getResultMetadata());//{BYTE_SEGMENTS=[[B@141f56e3], ERROR_CORRECTION_LEVEL=M, ERRORS_CORRECTED=0, SYMBOLOGY_IDENTIFIER=]Q2}
            System.out.println("所在类：" + result.getClass());//class com.google.zxing.Result
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (NotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
