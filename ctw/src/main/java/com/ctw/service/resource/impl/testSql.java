package com.ctw.service.resource.impl;



import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by Ocean lin on 2017/7/21.
 */
public class testSql {
    static int i = 386;

    public static void main(String[] args) {
        String[] treg = new String[]{"TREG-1", "TREG-2", "TREG-3", "TREG-4", "TREG-5", "TREG-6", "TREG-7","NAS"};
        String[] slg = new String[]{"DSO"};
        String[] tims_lev = new String[]{"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
        String type="SLATICKET";
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String times = sf.format(new Date());
        List<String> tregL = Arrays.asList(treg);
        List<String> slgl = Arrays.asList(slg);
        List<String> tims_levl = Arrays.asList(tims_lev);

        for (String t : tims_levl ) {
            for (String s : tregL) {
               /* for (String tl : tims_levl) {*/
                    i = i + 1;
                    String sqls = "insert into tb_ioc_config_echars (ECHARS_ID, ECHARS_ZONE, ECHARS_LABLE, ECHARS_LEGEND, ECHARS_TYPE, ECHARS_ORDER, TS)\n" +
                            "values (" + i + ", '', '" +t + "', '" + s+ "', '"+type+"', " + i + ", '" + times + "');";
                    System.out.println(sqls);

               /* }*/
            }

        }
    }
}
