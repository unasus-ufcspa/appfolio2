package com.ufcspa.unasus.appportfolio.Model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by icaromsc on 24/03/2016.
 */
public class Sync {
    private int id_sync;
    private String id_device;
    private String nm_table;
    private int co_id_table;
    private String dt_sync;

    public Sync(String id_device, String nm_table, int co_id_table) {
        this.id_device = id_device;
        this.nm_table = nm_table;
        this.co_id_table = co_id_table;
    }

    public Sync(int id_sync, String id_device, int co_id_table, String nm_table, String dt_sync) {
        this.id_sync = id_sync;
        this.id_device = id_device;
        this.co_id_table = co_id_table;
        this.nm_table = nm_table;
        this.dt_sync = dt_sync;
    }

    public int getId_sync() {
        return id_sync;
    }

    public void setId_sync(int id_sync) {
        this.id_sync = id_sync;
    }

    public String getId_device() {
        return id_device;
    }

    public void setId_device(String id_device) {
        this.id_device = id_device;
    }

    public String getNm_table() {
        return nm_table;
    }

    public void setNm_table(String nm_table) {
        this.nm_table = nm_table;
    }

    public int getCo_id_table() {
        return co_id_table;
    }

    public void setCo_id_table(int co_id_table) {
        this.co_id_table = co_id_table;
    }

    public String getDt_sync() {
        return dt_sync;
    }

    public void setDt_sync(String dt_sync) {
        this.dt_sync = dt_sync;
    }

    @Override
    public String toString() {
        return "Sync{" +
                "id_sync=" + id_sync +
                ", id_device='" + id_device + '\'' +
                ", nm_table='" + nm_table + '\'' +
                ", co_id_table=" + co_id_table +
                ", dt_sync='" + dt_sync + '\'' +
                '}';
    }

    public String toJSON(){
        JSONObject sync = new JSONObject();
        try {
            sync.put("id_device",getId_device());
            sync.put("nm_table",getNm_table());
            sync.put("co_id_table",getCo_id_table());
            sync.put("dt_sync",getDt_sync());

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return sync.toString();
    }

}
