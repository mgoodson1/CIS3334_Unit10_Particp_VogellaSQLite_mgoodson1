package edu.css.mgoodson1.cis3334_unit10_particp_vogellasqlite;

/**
 * Created by mgoodson on 3/31/2018.
 */

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/** This class creates the Comment Data Source which is to interface with the Comments database
 *
 * @author Matt Goodson
 * @version 2018-03-30
 *
 */

public class CommentsDataSource {

    // Database fields
    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;
    private String[] allColumns = { MySQLiteHelper.COLUMN_ID,
            MySQLiteHelper.COLUMN_COMMENT };

    public CommentsDataSource(Context context) {
        dbHelper = new MySQLiteHelper(context);
    }

    /**
     * Open the database
     * @return Nothing
     */
    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    /**
     * Close the database
     * @return Nothing
     */
    public void close() {
        dbHelper.close();
    }

    public Comment createComment(String comment) {
        ContentValues values = new ContentValues();
        //put comment into helper
        values.put(MySQLiteHelper.COLUMN_COMMENT, comment);
        //insert the comment into the database, returning the id
        long insertId = database.insert(MySQLiteHelper.TABLE_COMMENTS, null,
                values);
        //create and open cursor, returning the first comment with the insertID set above
        Cursor cursor = database.query(MySQLiteHelper.TABLE_COMMENTS,
                allColumns, MySQLiteHelper.COLUMN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        Comment newComment = cursorToComment(cursor);
        cursor.close();
        return newComment;
    }

    /**
     * Delete a comment based on comment value
     * @return Nothing
     *
     */
    public void deleteComment(Comment comment) {
        long id = comment.getId();
        System.out.println("Comment deleted with id: " + id);
        database.delete(MySQLiteHelper.TABLE_COMMENTS, MySQLiteHelper.COLUMN_ID
                + " = " + id, null);
    }

    /**
     * Returns all comments in the database as a list
     * @return List of comments
     *
     */
    public List<Comment> getAllComments() {
        List<Comment> comments = new ArrayList<Comment>();

        //uses a cursor to populated the array
        Cursor cursor = database.query(MySQLiteHelper.TABLE_COMMENTS,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Comment comment = cursorToComment(cursor);
            comments.add(comment);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return comments;
    }

    /**
     * Returns a comment from a cursor
     * @param cursor
     * @return Comment
     *
     */
    private Comment cursorToComment(Cursor cursor) {
        Comment comment = new Comment();
       // comment.setId(cursor.getLong(0));
        comment.setId(cursor.getLong(cursor.getColumnIndex(MySQLiteHelper.COLUMN_ID)));
        //comment.setComment(cursor.getString(1));
        comment.setComment(cursor.getString(cursor.getColumnIndex(MySQLiteHelper.COLUMN_COMMENT)));
        return comment;
    }
}