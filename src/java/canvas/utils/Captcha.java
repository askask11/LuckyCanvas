/*
 * Author: jianqing
 * Date: Apr 26, 2020
 * Description: This document is created for generating captcha.
The code in this class was optimized from https://www.jianshu.com/p/009914797af2.
Original author: SamGroves.
Code base and parameters were changed to fit this application. Code body was completely understood by Jianqing Gao.
 */
package canvas.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.time.LocalDateTime;
import java.util.Random;
import static canvas.utils.Randomizer.getRandomColor;

/**
 *
 * @author jianqing
 */
public class Captcha {

    public static final char[] MAP_TABLE = {
        '0', '1', '2', '3', '4', '5',
        '6', '7', '8', '9', '0', '1',
        '2', '3', '4', '5', '6', '7',
        '8', '9'};
    private String body;//the body of the code
    private LocalDateTime expireTime;//use UTC time zone.
    private BufferedImage image;

    public Captcha(String body, LocalDateTime expireTime) {
        this.body = body;
        this.expireTime = expireTime;
    }

    public Captcha() {
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public LocalDateTime getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(LocalDateTime expireTime) {
        this.expireTime = expireTime;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public static Captcha generateCaptcha(int width, int height, int length) {
        if (width <= 0) {
            width = 60;
        }
        if (height <= 0) {
            height = 20;
        }
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        // get the image graphics
        Graphics g = image.getGraphics();
        //creates a random object
        Random random = new Random();
        //creates an instance of captcha class.
        Captcha captcha = new Captcha();
        // set background color
        g.setColor(getRandomColor(200, 250));
        g.fillRect(0, 0, width, height);
        //set the font
        g.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        // generate 169 random lines, so it is less possible to be read by bots.
        g.setColor(getRandomColor(160, 200));
        for (int i = 0; i < 168; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(12);
            int yl = random.nextInt(12);
            g.drawLine(x, y, x + xl, y + yl);
        }
        //get the randomly generated code
        String strEnsure = "";
        for (int i = 0; i < 4; ++i) {
            strEnsure += MAP_TABLE[(int) (MAP_TABLE.length * Math.random())];
            // show the code on the image
            g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));
            // append the character to the string
            String str = strEnsure.substring(i, i + 1);
            // draw the code on the image
            g.drawString(str, 13 * i + 20, 25);
        }
        // output image
        g.dispose();//stop drawing
        captcha.setImage(image);
        captcha.setBody(strEnsure);
        return captcha;
    }

}
