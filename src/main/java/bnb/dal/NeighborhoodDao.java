package bnb.dal;

import bnb.model.Neighborhood;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NeighborhoodDao {
    protected ConnectionManager connectionManager;
    private static NeighborhoodDao instance = null;

    protected NeighborhoodDao() {
        connectionManager = new ConnectionManager();
    }

    public static NeighborhoodDao getInstance() {
        if (instance == null) {
            instance = new NeighborhoodDao();
        }
        return instance;
    }

    public Neighborhood create(Neighborhood neighborhood) throws SQLException {
        Connection connection = null;
        String insertNeighborhood = "INSERT INTO Neighborhood(Neighborhood, NeighborhoodGroup) VALUES(?,?);";
        PreparedStatement insertStmt = null;

        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertNeighborhood);
            insertStmt.setString(1, neighborhood.getNeighborhood());
            insertStmt.setString(2, neighborhood.getNeighborhoodGroup());
            insertStmt.executeUpdate();
            return neighborhood;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (insertStmt != null) {
                insertStmt.close();
            }
        }
    }

    public Neighborhood getNeighborhoodFromNeighborhood(String neighborhood) throws SQLException {
        Connection connection = null;
        String selectNeighborhood = "SELECT Neighborhood, NeighborhoodGroup FROM Neighborhood WHERE Neighborhood=?;";
        PreparedStatement selectStmt = null;
        ResultSet results = null;

        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectNeighborhood);
            selectStmt.setString(1, neighborhood);
            results = selectStmt.executeQuery();

            if (results.next()) {
                String resultNeighborhood = results.getString("Neighborhood");
                String neighborhoodGroup = results.getString("NeighborhoodGroup");
                Neighborhood neighborhoodObject = new Neighborhood(resultNeighborhood, neighborhoodGroup);
                return neighborhoodObject;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (selectStmt != null) {
                selectStmt.close();
            }
            if (results != null) {
                results.close();
            }
        }
        return null;
    }

    public List<Neighborhood> getNeighborhoodsFromNeighborhoodGroup(String neighborhoodGroup) throws SQLException {
        Connection connection = null;
        String selectNeighborhoods = "SELECT Neighborhood, NeighborhoodGroup FROM Neighborhood WHERE NeighborhoodGroup=?;";
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        List<Neighborhood> neighborhoods = new ArrayList<>();

        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectNeighborhoods);
            selectStmt.setString(1, neighborhoodGroup);
            results = selectStmt.executeQuery();

            while (results.next()) {
                String neighborhood = results.getString("Neighborhood");
                String resultNeighborhoodGroup = results.getString("NeighborhoodGroup");
                Neighborhood neighborhoodObject = new Neighborhood(neighborhood, resultNeighborhoodGroup);
                neighborhoods.add(neighborhoodObject);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (selectStmt != null) {
                selectStmt.close();
            }
            if (results != null) {
                results.close();
            }
        }
        return neighborhoods;
    }

    public Neighborhood updateNeighborhoodGroup(Neighborhood neighborhood, String neighborhoodGroup) throws SQLException {
        Connection connection = null;
        String updateNeighborhood = "UPDATE Neighborhood SET NeighborhoodGroup=? WHERE Neighborhood=?;";
        PreparedStatement updateStmt = null;

        try {
            connection = connectionManager.getConnection();
            updateStmt = connection.prepareStatement(updateNeighborhood);
            updateStmt.setString(1, neighborhoodGroup);
            updateStmt.setString(2, neighborhood.getNeighborhood());
            updateStmt.executeUpdate();

            neighborhood.setNeighborhoodGroup(neighborhoodGroup);
            return neighborhood;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (updateStmt != null) {
                updateStmt.close();
            }
        }
    }

    public Neighborhood deleteNeighborhood(Neighborhood neighborhood) throws SQLException {
        Connection connection = null;
        String deleteNeighborhood = "DELETE FROM Neighborhood WHERE Neighborhood=?;";
        PreparedStatement deleteStmt = null;

        try {
            connection = connectionManager.getConnection();
            deleteStmt = connection.prepareStatement(deleteNeighborhood);
            deleteStmt.setString(1, neighborhood.getNeighborhood());
            deleteStmt.executeUpdate();
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (deleteStmt != null) {
                deleteStmt.close();
            }
        }
    }

}
