// -*- Java -*-
/*
 * <copyright>
 * 
 *  Copyright (c) 2002
 *  Institute for Information Processing and Computer Supported New Media (IICM),
 *  Graz University of Technology, Austria.
 * 
 * </copyright>
 * 
 * <file>
 * 
 *  Name:    Alphabetizer.java
 * 
 *  Purpose: Sorts circular shifts alphabetically
 * 
 *  Created: 23 Sep 2002 
 * 
 *  $Id$
 * 
 *  Description:
 *    Sorts circular shifts alphabetically
 * </file>
*/



/*
 * $Log$
*/

import java.util.ArrayList;
import java.util.Collections;

/**
 *  An object of the Alphabetizer class sorts all lines, that it gets
 *  from CircularShifter. Methods to access sorted lines are provided.
 *  @author  dhelic
 *  @version $Id$
*/

class Sorter implements Comparable<Sorter>{
  String line;
  int index;

  Sorter(String line, int index) {
    this.index = index;
    this.line = line;
  }

  @Override
  public int compareTo(Sorter o) {
    if (line.compareTo(o.line) == 0) {
      return (index < o.index) ? -1 : 1;
    }
    return line.compareTo(o.line);
  }
}

public class Alphabetizer {

//----------------------------------------------------------------------
/**
 * Fields
 *
 */
//----------------------------------------------------------------------

  /**
   * Array holding sorted indices of lines
   */

  private int sorted_[];

  /**
   * CircularShifter that provides lines
   */

  private CircularShifter shifter_;

  private ArrayList<Sorter> sorter = new ArrayList<>();

//----------------------------------------------------------------------
/**
 * Constructors
 *
 */
//----------------------------------------------------------------------

//----------------------------------------------------------------------
/**
 * Methods
 *
 */
//----------------------------------------------------------------------

//----------------------------------------------------------------------

  /**
   * Sorts all lines from the shifter.
   *
   * @param shifter the source of lines
   */

  public void alpha(CircularShifter shifter) {
    shifter_ = shifter;
    sorted_ = new int[shifter.getLineCount()];
/*
      for (int i = 0 ; i < shifter.getLineCount(); ++i) {
      String line = shifter.getLineAsString(i);
      int count = 0;
      for (int j = 0; j < shifter.getLineCount(); ++j) {
        if (j == i) {
          continue;
        }
        String other = shifter.getLineAsString(j);
        int cmp = line.compareTo(other);
        if (cmp > 0) {
          count++;
        }
      }
      sorted_[count] = i;
    }
    */
    for (int i = 0; i < shifter.getLineCount(); ++i) {
      sorter.add(new Sorter(shifter.getLineAsString(i), i));
    }
    Collections.sort(sorter);
    for (int i = 0; i < shifter.getLineCount(); ++i) {
      sorted_[i] = sorter.get(i).index;
    }
  }

//----------------------------------------------------------------------

  /**
   * This method builds and reconstructs the heap for the heap sort algorithm.
   *
   * @param root   heap root
   * @param bottom heap bottom
   */

  private void siftDown(int root, int bottom) {

  }

//----------------------------------------------------------------------

  /**
   * Gets the line from the specified position.
   * String array representing the line is returned.
   *
   * @param line line index
   * @return String[]
   * @see #getLineAsString
   */

  public String[] getLine(int line) {
    return shifter_.getLine(sorted_[line]);
  }

//----------------------------------------------------------------------

  /**
   * Gets the line from the specified position.
   * String representing the line is returned.
   *
   * @param line line index
   * @return String[]
   * @see #getLine
   */

  public String getLineAsString(int line) {
    return shifter_.getLineAsString(sorted_[line]);
  }

//----------------------------------------------------------------------

  /**
   * Gets the number of lines.
   *
   * @return int
   */

  public int getLineCount() {
    return shifter_.getLineCount();
  }

//----------------------------------------------------------------------
/**
 * Inner classes
 *
 */
//----------------------------------------------------------------------

}