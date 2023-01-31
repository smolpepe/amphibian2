package com.nikita.amphibian;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Color;



public class DatabaseHelper extends SQLiteOpenHelper
{
    private static DatabaseHelper sqLiteManager;
    private static final String DATABASE_NAME = "Lizard.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "Clickables";

    //Table columns
    private static final String ID = "key_id";
    private static final String COLOR_ID = "Color_id";
    private static final String INFO = "Info";

    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLOR_ID + " INTEGER, " + INFO + " TEXT);";

    public DatabaseHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public static DatabaseHelper instanceOfDatabase(Context context)
    {
        if(sqLiteManager == null)
            sqLiteManager = new DatabaseHelper(context);

        return sqLiteManager;
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(CREATE_TABLE);
        // insert default values
        ContentValues values = new ContentValues();
        values.put(COLOR_ID, Color.RED );
        values.put(INFO,"THIS IS MY TAIL! \n\n" +
                "       Lizards are born with a line of weakness in their tail, technically called a fracture plane. " +
                "If a point on the tail is hit or stressed, the muscles along the fracture plane pull away from one another rather than knitting together – " +
                "this is known as a reflex muscle spasm. The pulling apart of the muscles causes the tail to fall off along the line of weakness.\n" +
                "\n" +
                "       Lizard tail autonomy has developed so that when the tail breaks there is no blood loss, and the tail regrows over six months to a year. " +
                "The tail skeleton is replaced by a rod of cartilage with new muscles growing along it, producing a replacement tail that is usually shorter " +
                "and less coloured compared with the original. "+"\n\n"+"       Lizards aren’t the only animals capable of self-amputation. Over 200 species of " +
                "invertebrates are capable of using autotomy for self-defence, and it’s even a behaviour known to be used by mammals – at least two species of " +
                "African spiny mice can release skin upon being captured by a predator, and later regenerate the lost skin (hair follicles, sweat glands, fur, " +
                "cartilage and all) with little or no scarring.");
        db.insert(TABLE_NAME, null, values);
        values.put(COLOR_ID, Color.BLUE );
        values.put(INFO, "THIS IS MY BODY! \n\n     Nothing special about it...");
        db.insert(TABLE_NAME, null, values);
        values.put(COLOR_ID, Color.YELLOW );
        values.put(INFO, "THIS IS MY HEAD! \n\n  " +
                "       The short-horned lizard is a one-reptile wrecking crew with a bizarre self-defense strategy. When defending its " +
                "own life, this lizard squirts blood from the thin blood vessels around its eyes that rupture under pressure.");
        db.insert(TABLE_NAME, null, values);
        values.put(COLOR_ID, Color.GREEN );
        values.put(INFO, "THOSE ARE MY LIBS! \n\n " +
                "       Aside from legless lizards, most lizards are quadrupedal and move using gaits with alternating movement of the right " +
                "and left limbs with substantial body bending. This body bending prevents significant respiration during movement, limiting their endurance, in a mechanism called Carrier's constraint.");
        db.insert(TABLE_NAME, null, values);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME) ;
        onCreate(db);
    }

    public Info getFinalInfo(int __id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME, new String[] { COLOR_ID, INFO}, COLOR_ID + "=?", new String[] {String.valueOf(__id)}, null, null,null,null);
    Info info1 = null;
        if (cursor.moveToFirst()) {
            info1 = new Info(Integer.parseInt(cursor.getString(0)), cursor.getString(1));}
        cursor.close();
        return info1;

    }
}



