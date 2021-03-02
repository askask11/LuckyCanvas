/*
 * Author: jianqing
 * Date: May 6, 2020
 * Description: This document is created for
 */
package canvas.utils;

/**
 *
 * @author jianqing
 */
public class CalcOffset
{

    private int hours, minutes;
    private int offset;

    public CalcOffset(int offset)
    {
        this.offset = offset;
        calculate();
    }

    public void calculate()
    {
        hours = -1 * (offset / 60);
        minutes = -1 * (offset % 60);
    }

    public int getHours()
    {
        return hours;
    }

    public void setHours(int hours)
    {
        this.hours = hours;
    }

    public int getMinutes()
    {
        return minutes;
    }

    public void setMinutes(int minutes)
    {
        this.minutes = minutes;
    }

    public int getOffset()
    {
        return offset;
    }

    public void setOffset(int offset)
    {
        this.offset = offset;
    }

    @Override
    public String toString()
    {
        return "CalcOffset{" + "hours=" + hours + ", minutes=" + minutes + ", offset=" + offset + '}';
    }

    
    public static void main(String[] args)
    {
        System.out.println(new CalcOffset(-480));
    }
}
