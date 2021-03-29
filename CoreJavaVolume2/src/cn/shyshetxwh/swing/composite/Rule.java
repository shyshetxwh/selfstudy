package cn.shyshetxwh.swing.composite;

import java.awt.*;

public class Rule {
    private String name;
    private String porterDuff1;
    private String porterDuff2;

    public Rule(String name, String porterDuff1, String porterDuff2) {
        this.name = name;
        this.porterDuff1 = porterDuff1;
        this.porterDuff2 = porterDuff2;
    }

    public String getExplanation()
    {
        StringBuilder r = new StringBuilder("Source ");
        if (porterDuff2.equals("  ")) r.append("clears");
        if (porterDuff2.equals(" S")) r.append("overwrites");
        if (porterDuff2.equals("DS")) r.append("blends with");
        if (porterDuff2.equals(" D")) r.append("alpha modifies");
        if (porterDuff2.equals("D ")) r.append("alpha complement modifies");
        if (porterDuff2.equals("DD")) r.append("does not affect");
        r.append(" destination");
        if (porterDuff1.equals(" S")) r.append(" and overwrites empty pixels");
        r.append(".");
        return r.toString();
    }

    @Override
    public String toString() {
        return name;
    }

    public int getValue()
    {
        try {
            return (Integer) AlphaComposite.class.getField(name).get(null);
        } catch (Exception e) {
            return -1;
        }
    }
}
