package com.cg.lab.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cg.lab.exceptions.LABException;
import com.cg.lab.model.LABModel;
import com.cg.lab.utility.dbUtility;

public class LabDaoImpl implements LabDao {
	Connection connection = null;
	PreparedStatement statement = null;
	ResultSet resultSet = null;
	int result = 0;

	@Override
	public int insertDetails(LABModel labModel) throws LABException {
		int result = 0;
		
		connection = dbUtility.getConnection();
		try {
			List<LABModel> list = new ArrayList<>();
			int notZero = 0;
			statement = connection.prepareStatement(QueryClass.checkMobileQuantityQuery);
			statement.setInt(1, labModel.getId());
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				int quantity = resultSet.getInt(1);
				LABModel labs = new LABModel();
				labs.setId(quantity);
				list.add(labs);
				for (LABModel lab : list) {

					notZero = lab.getQuantity();
				}
				
			}
			
			if(!(notZero==0))
			{
			statement = connection.prepareStatement(QueryClass.insertQuery);

			statement.setString(1, labModel.getName());
			statement.setString(2, labModel.getMail());
			statement.setString(3, labModel.getPhone());
			statement.setInt(4, labModel.getId());
			int mobileId = labModel.getId();
			
			
			result = statement.executeUpdate();

			statement = connection
					.prepareStatement(QueryClass.updateMobileCountQuery);

			statement.setInt(1, mobileId);
			statement.executeUpdate();
			}
			else{
				System.out.println("There is no stock left for the given ID");
				result = 0;
			}
		} catch (SQLException e) {
			throw new LABException(
					"SQL Statement was not created check your exectue query");
		} finally {
			try {
				statement.close();
			} catch (SQLException e) {
				throw new LABException("Statement could not be closed");
			}
			try {
				connection.close();
			} catch (SQLException e) {
				throw new LABException("Connection could not be closed");
			}
		}
		return result;
	}

	@Override
	public List<LABModel> getMobiles() throws LABException {
		List<LABModel> list = new ArrayList<>();
		connection = dbUtility.getConnection();

		try {
			statement = connection.prepareStatement(QueryClass.getDetailsQuery);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				int id = resultSet.getInt(1);
				String mobile = resultSet.getString(2);
				Double price = resultSet.getDouble(3);
				int quantity = resultSet.getInt(4);

				LABModel labModel = new LABModel();
				labModel.setId(id);
				labModel.setMobile(mobile);
				labModel.setPrice(price);
				labModel.setQuantity(quantity);

				list.add(labModel);
			}
		} catch (SQLException e) {
			throw new LABException("Statement was not created");
		} finally {
			try {
				resultSet.close();
			} catch (SQLException e) {
				throw new LABException("ResultSet was not closed");
			}
			try {
				statement.close();
			} catch (SQLException e) {
				throw new LABException("Statement was not closed");
			}
			try {
				connection.close();
			} catch (SQLException e) {
				throw new LABException("Connection was not closed");
			}

		}

		return list;
	}

	@Override
	public int deleteDetails(int idForDelete) throws LABException {
		connection = dbUtility.getConnection();
		try {
			statement = connection.prepareStatement(QueryClass.deleteQuery);
			statement.setInt(1, idForDelete);
			result = statement.executeUpdate();
		} catch (SQLException e) {
			throw new LABException("Statement was not created");
		}
		try {
			statement.close();
		} catch (SQLException e) {
			throw new LABException("Statement was not closed");
		}
		try {
			connection.close();
		} catch (SQLException e) {
			throw new LABException("Connection was not closed");
		}
		return result;
	}

	@Override
	public List<LABModel> serachDetails(LABModel labModel3) throws LABException {
		connection = dbUtility.getConnection();
		List<LABModel> list = new ArrayList<>();
		try {
			statement = connection
					.prepareStatement(QueryClass.selectQueryBasedOnPrice);
			statement.setDouble(1, labModel3.getMinPrice());
			statement.setDouble(2, labModel3.getMaxPrice());
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				int id = resultSet.getInt(1);
				String mobile = resultSet.getString(2);
				Double price = resultSet.getDouble(3);
				int quantity = resultSet.getInt(4);

				LABModel labModel = new LABModel();
				labModel.setId(id);
				labModel.setMobile(mobile);
				labModel.setPrice(price);
				labModel.setQuantity(quantity);

				list.add(labModel);
			}
		} catch (SQLException e) {
			throw new LABException("Statement was not created");
		} finally {
			try {
				resultSet.close();
			} catch (SQLException e) {
				throw new LABException("ResultSet was not closed");
			}
			try {
				statement.close();
			} catch (SQLException e) {
				throw new LABException("Statement was not closed");
			}
			try {
				connection.close();
			} catch (SQLException e) {
				throw new LABException("Connection was not closed");
			}

		}
		return list;
	}
}
