package com.example.sisecevirmece.data;

import android.database.Cursor;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.Collections;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class SoruDao_Impl implements SoruDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Soru> __insertionAdapterOfSoru;

  public SoruDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfSoru = new EntityInsertionAdapter<Soru>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `SoruDatabase` (`soru`,`id`) VALUES (?,nullif(?, 0))";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Soru value) {
        if (value.getSoru() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getSoru());
        }
        stmt.bindLong(2, value.getId());
      }
    };
  }

  @Override
  public void addSorular(final List<Soru> sorular) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfSoru.insert(sorular);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public Soru getSoruById(final int id) {
    final String _sql = "SELECT * FROM SoruDatabase WHERE id=?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfSoru = CursorUtil.getColumnIndexOrThrow(_cursor, "soru");
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final Soru _result;
      if(_cursor.moveToFirst()) {
        final String _tmpSoru;
        if (_cursor.isNull(_cursorIndexOfSoru)) {
          _tmpSoru = null;
        } else {
          _tmpSoru = _cursor.getString(_cursorIndexOfSoru);
        }
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        _result = new Soru(_tmpSoru,_tmpId);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
