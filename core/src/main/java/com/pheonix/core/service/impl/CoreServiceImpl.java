package com.pheonix.core.service.impl;

import com.pheonix.core.dto.vo.CategoryVo;
import com.pheonix.core.model.Brand;
import com.pheonix.core.model.SubscriptionMstr;
import com.pheonix.core.repository.dao.BrandDao;
import com.pheonix.core.service.PurchaseService;
import com.pheonix.core.service.ICoreService;
import com.pheonix.core.utils.helper.CommonUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Scanner;

@Service
@RequiredArgsConstructor
@Slf4j
public class CoreServiceImpl implements ICoreService {

	private final PurchaseService purchaseService;
	private final BrandDao brandDao;

	@Override
	public void populateInitialData() {
		try {
			Scanner scanner = new Scanner(new File("D:\\Pheonix\\core\\src\\main\\resources\\data\\category.csv"));
			scanner.useDelimiter(",");
			while (scanner.hasNext()){
				try {
					purchaseService.addCategory(CategoryVo.builder().name(CommonUtil.capitalizeEachWord(scanner.next().trim())).build());
				}catch (Exception e){
					log.error(e.getMessage());
				}
			}

			scanner = new Scanner(new File("D:\\Pheonix\\core\\src\\main\\resources\\data\\brands.csv"));
			scanner.useDelimiter(",");
			int totalCount = 0;
			int incorrectCount = 0;
			while (scanner.hasNext()){
				try {
					totalCount++;
					brandDao.save(Brand.builder().name(CommonUtil.capitalizeEachWord(scanner.next().trim())).build());
				}catch (Exception e){
					log.error(e.getMessage());
					incorrectCount++;
					totalCount++;
				}
			}
			log.info("TOTAL COUNT OF BRANDS IS:- " + totalCount + "AND INCORRECT IS:- " + incorrectCount);
			scanner.close();
		}catch (Exception e){
			log.error(e.getMessage());
		}
	}

	@Override
	public void test() {

		try {
			Scanner scanner = new Scanner(new File("D:\\Pheonix\\core\\src\\main\\resources\\data\\Subscriptions.csv"));
			scanner.useDelimiter("\n");
			while (scanner.hasNext()) {
				try {
					purchaseService.addSubscriptionMstr(SubscriptionMstr.builder().name(scanner.next()).build());
				} catch (Exception e) {
					log.error(e.getMessage());
				}
			}
		}catch (Exception e){}
	}

}
