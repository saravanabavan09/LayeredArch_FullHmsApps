package com.cg.lab.service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.cg.lab.dao.LabDao;
import com.cg.lab.dao.LabDaoImpl;
import com.cg.lab.exceptions.LABException;
import com.cg.lab.model.LABModel;

public class LabServiceImpl implements LabService {
	static Logger logger = Logger.getLogger(LabServiceImpl.class);
	LabDao labDao = new LabDaoImpl();

	@Override
	public int insertDetails(LABModel labModel) throws LABException {
		logger.info("");
		List<String> list = new ArrayList<>();
		int num=0;
		if (!validateName(labModel.getName())) {
			logger.debug("INVALID NAME"+labModel.getName());
			list.add("Name should start with capital letter and have a maximum of 20 characters");
			logger.debug("ERROR THROWN "+list.toString());
		}
		if (!validateEmail(labModel.getMail())) {
			list.add("Enter a valid email");
		}
		if (!validatePhone(labModel.getPhone())) {
			list.add("Enter a 10 digit phone number");
		}

		if(!list.isEmpty()){
			System.err.println(list+"");
		}
		else{
			num=labDao.insertDetails(labModel);
			}
		return num;
	}

	private boolean validatePhone(String phone) {

		String phoneRegex = "[6|7|8|9]{1}[0-9]{9}$";
		boolean result = Pattern.matches(phoneRegex, phone);
		return result;
	}

	private boolean validateEmail(String mail) {
		String emailRegex = "[A-Za-z.0-9]*@[A-za-z]*\\.[A-za-z]*";
		boolean result = Pattern.matches(emailRegex, mail);
		return result;
	}

	private boolean validateName(String name) {
		String nameRegex = "[A-Z]{1}[a-zA-Z\\s]{4,19}$";
		boolean result = Pattern.matches(nameRegex, name);
		return result;
	}

	@Override
	public List<LABModel> getMobiles() throws LABException {
		// TODO Auto-generated method stub
		return labDao.getMobiles();
	}

	@Override
	public int deleteDetails(int idForDelete) throws LABException {
		// TODO Auto-generated method stub
		return labDao.deleteDetails(idForDelete);
	}

	@Override
	public List<LABModel> serachDetails(LABModel labModel3) throws LABException {
		// TODO Auto-generated method stub
		return labDao.serachDetails(labModel3);
	}

}
