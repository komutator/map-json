package com.example;



//      SELECT `l_id`, `case_id`, `imsi`, `imei`, `msisdn`, `channel`, `msd_from`, `msd_to`, `is_valid`, `version`, `has_egts`, `pos_lat`, `pos_long`, `hex_raw`, `msd_len`, `msd_decoded`, `json`, `tm`, `ecs`, `err_text`, `err_len`, `rssi`
//      SELECT  0           1        2       3        4         5          6           7         8           9          10          11         12         13         14           15           16     17    18      19          20         21


public class MsdObject {

    public int l_id;
    public int case_id;
    public String imsi;
    public String msisdn;
    public String channel;
    public String msd_from;
    public String msd_to;
    public int is_valid;
    public int version;
    public int has_egts;
    public long pos_lat;
    public long pos_long;
    public String hex_raw;
    public int msd_len;
    public String msd_decoded;
    public String json;
    public long tm;
    public String ecs;
    public String err_text;
    public int err_len;
    public String rssi;



    public MsdObject(){

    }


}
