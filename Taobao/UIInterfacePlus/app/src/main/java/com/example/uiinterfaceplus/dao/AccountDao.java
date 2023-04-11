package com.example.uiinterfaceplus.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.uiinterfaceplus.bean.Account;

import java.util.ArrayList;
import java.util.List;


public class AccountDao {
	private MyHelper helper;
	public AccountDao(Context context) {
		helper = new MyHelper(context); // 创建Dao时, 创建Helper
	}
	public void insert(Account account) {
		SQLiteDatabase db = helper.getWritableDatabase(); // 获取数据库对象
            // 用来装载要插入的数据的 Map<列名, 列的值>
		ContentValues values = new ContentValues();
		values.put("icon",account.getIcon());
		values.put("dianjia", account.getDianjia());
		values.put("name", account.getName());
		values.put("price", account.getPrice());
		values.put("sum", account.getSum());
		values.put("number",account.getNumber());
		values.put("flag",account.getFlag());
		long id = db.insert("account", null, values); // 向account表插入数据values,
		account.setId(id);  // 得到id
		db.close();         // 关闭数据库
	}
     //根据id 删除数据
	public int delete(long id) {
		SQLiteDatabase db = helper.getWritableDatabase();
		// 按条件删除指定表中的数据, 返回受影响的行数
		int count = db.delete("account", "_id=?", new String[] { id + "" });
		db.close();
		return count;
	}
     //更新数据
	public int update(Account account) {
		SQLiteDatabase db = helper.getWritableDatabase();
		ContentValues values = new ContentValues(); // 要修改的数据
		values.put("icon",account.getIcon());
		values.put("dianjia", account.getDianjia());
		values.put("name", account.getName());
		values.put("price", account.getPrice());
		values.put("sum", account.getSum());
		values.put("number",account.getNumber());
		values.put("flag",account.getFlag());
		int count = db.update("account", values, "_id=?",
				new String[] { account.getId() + "" }); // 更新并得到行数
		db.close();
		return count;
	}
    //查询所有数据倒序排列
	public List<Account> queryAll() {
		SQLiteDatabase db = helper.getReadableDatabase();
		Cursor c = db.query("account", null, null, null, null, null,
				"dianjia DESC");
		List<Account> list = new ArrayList<Account>();
		while (c.moveToNext()) {
			@SuppressLint("Range") long id = c.getLong(c.getColumnIndex("_id")); // 可以根据列名获取索引
			int icon=c.getInt(1);
			String dianjia = c.getString(2);
			String name = c.getString(3);
			double price = c.getDouble(4);
			double sum = c.getDouble(5);
			Integer number = c.getInt(6);
			int flag = c.getInt(7);
			list.add(new Account(id,icon,dianjia, name, price,sum,number,flag));
		}
		c.close();
		db.close();
		return list;
	}

	//查询被选择的数据倒序排列
	public List<Account> queryisselected() {
		SQLiteDatabase db = helper.getReadableDatabase();
		Cursor c = db.query("account", null, "flag=1", null, null, null,
				"dianjia DESC");
		List<Account> list = new ArrayList<Account>();
		while (c.moveToNext()) {
			@SuppressLint("Range") long id = c.getLong(c.getColumnIndex("_id")); // 可以根据列名获取索引
			int icon=c.getInt(1);
			String dianjia = c.getString(2);
			String name = c.getString(3);
			double price = c.getDouble(4);
			double sum = c.getDouble(5);
			Integer number = c.getInt(6);
			int flag = c.getInt(7);
			list.add(new Account(id,icon,dianjia, name, price,sum,number,flag));
		}
		c.close();
		db.close();
		return list;
	}

	//查询所有数据倒序排列
	public Account queryone(String name) {
		SQLiteDatabase db = helper.getReadableDatabase();
		//查询 name = 张三 并且 age > 23 的数据  并按照id 降序排列
		Cursor c = db.query("account",null,"name=?",new String[]{name},null,null,"name desc");
//		Cursor c = db.query("account", null, null, null, null, null,
//				"dianjia DESC");
		Account res ;
		if (c.moveToNext()) {
			@SuppressLint("Range") long id = c.getLong(c.getColumnIndex("_id")); // 可以根据列名获取索引
			int icon=c.getInt(1);
			String dianjia = c.getString(2);
			String name1 = c.getString(3);
			double price = c.getDouble(4);
			double sum = c.getDouble(5);
			Integer number = c.getInt(6);
			int flag = c.getInt(7);
			res = new  Account(id,icon,dianjia, name1, price,sum,number,flag);
		} else {
			res = null;
		}
		c.close();
		db.close();
		return res;
	}

	//查询所有数据倒序排列
	public Double querySum() {
		SQLiteDatabase db = helper.getReadableDatabase();
		Cursor c = db.query("account", null, null, null, null, null,
				"dianjia DESC");
		Double p = 0.0;
		while (c.moveToNext()) {
			@SuppressLint("Range") long id = c.getLong(c.getColumnIndex("_id")); // 可以根据列名获取索引
			Double sum = c.getDouble(5);
			p+=sum;
		}
		c.close();
		db.close();
		return p;
	}

}


