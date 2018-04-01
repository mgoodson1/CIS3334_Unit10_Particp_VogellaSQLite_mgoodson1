package edu.css.mgoodson1.cis3334_unit10_particp_vogellasqlite;

/**
 * Created by mgoodson on 3/31/2018.
 */

/** This class defines the object Comment which has an id and the comment
 *
 * @author Matt Goodson
 * @version 2018-03-30
 *
 */
public class Comment {
    private long id;
    private String comment;

    /**
     * Return the comment Id
     * @return long
     *
     */
    public long getId() {
        return id;
    }

    /**
     * Set the Id
     *
     *
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Return the comment
     * @return String
     *
     */
    public String getComment() {
        return comment;
    }

    /**
     * Set the comment
     *
     *
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * Return the comment using toString
     * @return string
     *
     */
    // Will be used by the ArrayAdapter in the ListView
    @Override
    public String toString() {
        return comment;
    }
}
