package wifi.wifim8;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class SpotDataSource {
	private SQLiteDatabase database;
	private MySQLiteHelper dbHelper;
	private String[] allColumns = { MySQLiteHelper.COLUMN_ID,MySQLiteHelper.COLUMN_SPOT};
	
	public SpotDataSource(Context context){
		dbHelper = new MySQLiteHelper(context);
	}
	
	public void open() throws SQLException{
		database = dbHelper.getWritableDatabase();
	}
	
	public void close(){
		dbHelper.close();
	}
	
	public Spot createspot(String spot){
		ContentValues values = new ContentValues();
		values.put(MySQLiteHelper.COLUMN_SPOT,spot);
		long insertId = database.insert(MySQLiteHelper.TABLE_SPOTS, null, values);
		Cursor cursor = database.query(MySQLiteHelper.TABLE_SPOTS, allColumns,
				MySQLiteHelper.COLUMN_ID + "=" + insertId, null, null, null, null);
		cursor.moveToFirst();
		Spot newSpot = cursorToSpot(cursor);
		cursor.close();
		return newSpot;
	}
	public void emptySpots(){
		database.delete(MySQLiteHelper.TABLE_SPOTS, null, null);
	}
	
	public List<Spot> getAllSpots(){
		List<Spot> spots = new ArrayList<Spot>();
		Cursor cursor = database.query(MySQLiteHelper.TABLE_SPOTS, allColumns, null, null, null, null, null);
		cursor.moveToFirst();
		while(!cursor.isAfterLast()){
			Spot spot = cursorToSpot(cursor);
			spots.add(spot);
			cursor.moveToNext();
		}
		return spots;
	}
	
	private Spot cursorToSpot(Cursor cursor){
		Spot spot = new Spot();
		spot.setId(cursor.getLong(0));
		spot.setSpot(cursor.getString(1));
		return spot;
	}	
}
