package com.cg.lab.main;

import java.util.List;
import java.util.Scanner;

import com.cg.lab.exceptions.LABException;
import com.cg.lab.model.LABModel;
import com.cg.lab.service.LabService;
import com.cg.lab.service.LabServiceImpl;

public class Main {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		System.out.println("Jessica Mobile Park Pvt Ltd");
		System.out.println("1. Generate Bill");
		System.out.println("2. View Mobile Quantity");
		System.out.println("3. Remove Mobile based on Mobile ID");
		System.out.println("4. Search for Mobile based on prices");
		System.out.println("Choose any one of the following at a time: ");
		int choices = scanner.nextInt();
		scanner.nextLine();
		int result;

		switch (choices) {
		case 1:

			System.out.println("Enter the Customer Name: ");
			String name = scanner.nextLine();

			System.out.println("Enter the Mail-ID: ");
			String mail = scanner.nextLine();

			System.out.println("Enter the Mobile Number: ");
			String phone = scanner.nextLine();

			System.out.println("Enter the Mobile ID: ");
			Integer id = scanner.nextInt();

			LABModel labModel = new LABModel();
			labModel.setName(name);
			labModel.setMail(mail);
			labModel.setPhone(phone);
			labModel.setId(id);

			LabService service = new LabServiceImpl();

			try {
				result = service.insertDetails(labModel);
				if (result == 0) {
					System.err.println("Enter the details again");
				} else {
					System.out.println(result + " Bill Created");
				}

			} catch (LABException e) {
				System.err.println(e.getMessage());
			}

			break;
		case 2:

			try {
				LabService service1 = new LabServiceImpl();
				List<LABModel> list = service1.getMobiles();
				System.out.println("Mobile Details");
				for (LABModel lab : list) {

					System.out.println(lab.getId() + "---" + lab.getMobile()
							+ "---" + lab.getPrice() + "---"
							+ lab.getQuantity());
				}

			} catch (LABException e) {
				System.err.println(e.getMessage());
			}

			break;
		case 3:
			System.out.println("Enter the ID for deletion: ");
			int idForDelete = scanner.nextInt();
			LabService serviceDelete = new LabServiceImpl();
			try {
				result = serviceDelete.deleteDetails(idForDelete);
				System.out.println(result + " rows deleted");
			} catch (LABException e) {
				System.err.println(e.getMessage());
			}
			break;
		case 4:

			System.out.println("Enter the minimum range");
			Double minPrice = scanner.nextDouble();
			System.out.println("Enter the maximum range");
			Double maxPrice = scanner.nextDouble();
			LABModel labModel3 = new LABModel();
			labModel3.setMinPrice(minPrice);
			labModel3.setMaxPrice(maxPrice);

			LabService serviceSearch = new LabServiceImpl();

			try {
				List<LABModel> list = serviceSearch.serachDetails(labModel3);
				for (LABModel labModel31 : list) {
					System.out.println(labModel31.getId() + "----"
							+ labModel31.getMobile() + "----"
							+ labModel31.getPrice() + "----"
							+ labModel31.getQuantity());
				}

			} catch (LABException e) {
				System.err.println(e.getMessage());
			}

			break;
		default:
			break;
		}

	}

}
