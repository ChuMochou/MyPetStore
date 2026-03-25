package org.csu.petstore.controller;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

@Controller
@RequestMapping("/captcha")
public class CaptchaController {

    @Autowired
    HttpSession session;

    @GetMapping("/newCaptcha")
    public void newCaptcha(HttpServletResponse resp) throws IOException {
        Random random = new Random();

        resp.setContentType("image/jpeg");
        resp.setHeader("Pragma", "no-cache");
        resp.setHeader("Cache-Control", "no-cache");
        resp.setDateHeader("Expires", 0);

        int width = 120, height = 40;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = image.createGraphics();

        try {
            // 背景
            g2d.setColor(Color.WHITE);
            g2d.fillRect(0, 0, width, height);

            // 干扰线
            g2d.setColor(Color.GRAY);
            for (int i = 0; i < 10; i++) {
                int x1 = random.nextInt(width);
                int y1 = random.nextInt(height);
                int x2 = random.nextInt(width);
                int y2 = random.nextInt(height);
                g2d.drawLine(x1, y1, x2, y2);
            }

            // 噪点
            g2d.setColor(Color.DARK_GRAY);
            for (int i = 0; i < 30; i++) {
                int x = random.nextInt(width);
                int y = random.nextInt(height);
                g2d.fillOval(x, y, 2, 2);
            }

            // 验证码
            String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
            StringBuilder captcha = new StringBuilder();
            g2d.setFont(new Font("Arial", Font.BOLD, 27));

            for (int i = 0; i < 4; i++) {
                char c = chars.charAt(random.nextInt(chars.length()));
                captcha.append(c);

                AffineTransform old = g2d.getTransform();
                float shearX = (random.nextFloat() - 0.5f) * 0.4f;
                g2d.shear(shearX, 0);

                g2d.setColor(new Color(
                        random.nextInt(180) + 20,
                        random.nextInt(180) + 20,
                        random.nextInt(180) + 20
                ));
                g2d.drawString(String.valueOf(c), 20 + i * 22, 30);
                g2d.setTransform(old);
            }

            session.setAttribute("trueCaptcha", captcha.toString());

            ImageIO.write(image, "jpeg", resp.getOutputStream());
            resp.getOutputStream().flush();
        } finally {
            g2d.dispose();
        }
    }
}