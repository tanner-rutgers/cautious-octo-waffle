/*
 * Copyright 2014 Intuit Inc. All rights reserved. Unauthorized reproduction
 * is a violation of applicable law. This material contains certain
 * confidential or proprietary information and trade secrets of Intuit Inc.
 */
package ElasticPathChallenge;

/**
 * $DateTime$
 *
 * @Version $Header$
 * @Author $Author$
 */
public class Cell {

    private String mazeGuid;
    private String note;
    private boolean atEnd;
    private boolean previouslyVisited;
    private PathState north;
    private PathState east;
    private PathState south;
    private PathState west;
    private String x;
    private String y;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cell cell = (Cell) o;

        if (atEnd != cell.atEnd) return false;
        if (previouslyVisited != cell.previouslyVisited) return false;
        if (mazeGuid != null ? !mazeGuid.equals(cell.mazeGuid) : cell.mazeGuid != null) return false;
        if (note != null ? !note.equals(cell.note) : cell.note != null) return false;
        if (north != cell.north) return false;
        if (east != cell.east) return false;
        if (south != cell.south) return false;
        if (west != cell.west) return false;
        if (x != null ? !x.equals(cell.x) : cell.x != null) return false;
        return y != null ? y.equals(cell.y) : cell.y == null;

    }

    @Override
    public int hashCode() {
        int result = mazeGuid != null ? mazeGuid.hashCode() : 0;
        result = 31 * result + (note != null ? note.hashCode() : 0);
        result = 31 * result + (atEnd ? 1 : 0);
        result = 31 * result + (previouslyVisited ? 1 : 0);
        result = 31 * result + (north != null ? north.hashCode() : 0);
        result = 31 * result + (east != null ? east.hashCode() : 0);
        result = 31 * result + (south != null ? south.hashCode() : 0);
        result = 31 * result + (west != null ? west.hashCode() : 0);
        result = 31 * result + (x != null ? x.hashCode() : 0);
        result = 31 * result + (y != null ? y.hashCode() : 0);
        return result;
    }

    public String getMazeGuid() {
        return mazeGuid;
    }

    public void setMazeGuid(String mazeGuid) {
        this.mazeGuid = mazeGuid;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public boolean isAtEnd() {
        return atEnd;
    }

    public void setAtEnd(boolean atEnd) {
        this.atEnd = atEnd;
    }

    public boolean isPreviouslyVisited() {
        return previouslyVisited;
    }

    public void setPreviouslyVisited(boolean previouslyVisited) {
        this.previouslyVisited = previouslyVisited;
    }

    public PathState getNorth() {
        return north;
    }

    public void setNorth(PathState north) {
        this.north = north;
    }

    public PathState getEast() {
        return east;
    }

    public void setEast(PathState east) {
        this.east = east;
    }

    public PathState getSouth() {
        return south;
    }

    public void setSouth(PathState south) {
        this.south = south;
    }

    public PathState getWest() {
        return west;
    }

    public void setWest(PathState west) {
        this.west = west;
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }
}