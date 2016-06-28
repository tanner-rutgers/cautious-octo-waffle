/*
 * Copyright 2014 Intuit Inc. All rights reserved. Unauthorized reproduction
 * is a violation of applicable law. This material contains certain
 * confidential or proprietary information and trade secrets of Intuit Inc.
 */
package ElasticPathChallenge;

import com.google.gson.Gson;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.Stack;

/**
 * $DateTime$
 *
 * @Version $Header$
 * @Author $Author$
 */
public class MazeClient {

    private static final String HOST = "www.epdeveloperchallenge.com";

    static HttpClient httpClient;
    static URIBuilder uriBuilder;

    static Gson gson;

    static Stack<Cell> junctions = new Stack<>();

    public static void main(String[] args) {
        System.out.println("Starting maze...");

        gson = new Gson();
        httpClient = HttpClientBuilder.create().build();
        uriBuilder = new URIBuilder().setScheme("http").setHost(HOST);

        CellResponse response = initializeMaze();
        String completedMessage = traverseMaze(response.getCurrentCell());

        System.out.println("Completed the maze!");
        System.out.println("Message: " + completedMessage);
    }

    private static CellResponse initializeMaze() {
        CellResponse cellResponse = null;
        try {
            uriBuilder.setPath("/api/init");
            URI uri = uriBuilder.build();
            HttpPost post = new HttpPost(uri);
            String response = performRequest(post);
            cellResponse = gson.fromJson(response, CellResponse.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cellResponse;
    }

    private static String traverseMaze(Cell fromCell) {
        while (!fromCell.isAtEnd()) {
            if (canMove(fromCell)) {
                if (canMoveMultiple(fromCell)) {
                    junctions.push(fromCell);
                }
                fromCell = moveToUnexplored(fromCell);
            } else {
                fromCell = jumpToLatestJunction();
            }
        }
        return fromCell.getNote();
    }

    private static Cell jumpToLatestJunction() {
        Cell cell = null;
        Cell junction = junctions.pop();
        uriBuilder.clearParameters();
        uriBuilder.setPath("/api/jump");
        uriBuilder.setParameter("mazeGuid", junction.getMazeGuid());
        uriBuilder.setParameter("x", junction.getX());
        uriBuilder.setParameter("y", junction.getY());
        try {
            HttpPost post = new HttpPost(uriBuilder.build());
            String response = performRequest(post);
            CellResponse cellResponse = gson.fromJson(response, CellResponse.class);
			cell = cellResponse.getCurrentCell();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cell;
    }

    private static Cell moveToUnexplored(Cell fromCell) {
        Cell next = null;
        if (PathState.UNEXPLORED.equals(fromCell.getNorth())) {
            next = move(fromCell, "north");
        } else if (PathState.UNEXPLORED.equals(fromCell.getEast())) {
            next = move(fromCell, "east");
        } else if (PathState.UNEXPLORED.equals(fromCell.getSouth())) {
            next = move(fromCell, "south");
        } else if (PathState.UNEXPLORED.equals(fromCell.getWest())) {
            next = move(fromCell, "west");
        }
        return next;
    }

    private static Cell move(Cell fromCell, String direction) {
        Cell cell = null;
        try {
            uriBuilder.clearParameters();
            uriBuilder.setPath("/api/move");
            uriBuilder.setParameter("mazeGuid", fromCell.getMazeGuid());
            uriBuilder.setParameter("direction", direction.toUpperCase());
            HttpPost post = new HttpPost(uriBuilder.build());
            String response = performRequest(post);
            CellResponse cellResponse = gson.fromJson(response, CellResponse.class);
            cell = cellResponse.getCurrentCell();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cell;
    }

    private static boolean canMoveMultiple(Cell fromCell) {
        int openDirections = 0;
        if (PathState.UNEXPLORED.equals(fromCell.getNorth())) {
            openDirections++;
        }
        if (PathState.UNEXPLORED.equals(fromCell.getEast())) {
            openDirections++;
        }
        if (PathState.UNEXPLORED.equals(fromCell.getSouth())) {
            openDirections++;
        }
        if (PathState.UNEXPLORED.equals(fromCell.getWest())) {
            openDirections++;
        }
        return openDirections > 1;
    }

    private static boolean canMove(Cell fromCell) {
        return PathState.UNEXPLORED.equals(fromCell.getNorth()) ||
                PathState.UNEXPLORED.equals(fromCell.getEast()) ||
                PathState.UNEXPLORED.equals(fromCell.getSouth()) ||
                PathState.UNEXPLORED.equals(fromCell.getWest());
    }

    private static String performRequest(HttpUriRequest request) {
        System.out.println("Sending request: " + request.getURI().toString());
        String responseString = null;
        try {
            HttpResponse response = httpClient.execute(request);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != 200) {
                System.err.println("Got a non 200 response!");
                throw new RuntimeException();
            }
            BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            String line = null;
            StringBuilder sb = new StringBuilder();
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            responseString = sb.toString();
            System.out.println("Response: " + responseString);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return responseString;
    }

}
