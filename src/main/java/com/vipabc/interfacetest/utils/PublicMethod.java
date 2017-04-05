package com.vipabc.interfacetest.utils;

import shelper.db.Pg;

public class PublicMethod {

	public static String queryCode(Pro pro,String phone)
	{
    	Pg pg=new Pg(pro.getEnvPropties("pg.url","url"), pro.getEnvPropties("pg.username","url"), pro.getEnvPropties("pg.password","url"));
    	String code=pg.query("select verification_code from mobilephone_verifycation where mobile_phone='"+phone+"' order by update_time desc limit 1");
    	return code;
	}

}
