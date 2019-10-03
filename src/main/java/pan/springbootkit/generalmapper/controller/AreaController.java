package pan.springbootkit.generalmapper.controller;

import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pan.springbootkit.base.BaseResult;
import pan.springbootkit.generalmapper.model.Area;
import pan.springbootkit.generalmapper.service.AreaService;
import pan.springbootkit.xml.pca.AreaDTO;
import pan.springbootkit.xml.pca.AreaUtil;

import javax.annotation.Resource;
import java.util.List;

/**
 * 区域 controller
 *
 * Created by panzhangbao on 2019-10-04 00:08:40
 * Copyright © 2019 panzhangbao. All rights reserved.
 */
@RestController
@RequestMapping("api/area")
public class AreaController {
	@Resource
	private AreaService areaService;

	@PostMapping("test")
	public BaseResult insert() {
		List<AreaDTO> provinceList = AreaUtil.getProvinceList();
		for (AreaDTO e1 :provinceList) {
			/**
			 * 省
			 */
			Area province = new Area();
			province.setName(e1.getName());
			province.setLevel(1);
			province.setParentId(0);
			province = areaService.insertOrUpdateSelective(province);

			/**
			 * 市
			 */
			List<AreaDTO> cityList = AreaUtil.getCityList(e1);
			if (CollectionUtils.isEmpty(cityList)) {
				continue;
			}
			for (AreaDTO e2 : cityList) {
				Area city = new Area();
				city.setName(e2.getName());
				city.setLevel(2);
				city.setParentId(province.getId());
				city = areaService.insertOrUpdateSelective(city);

				/**
				 * 县
				 */
				List<AreaDTO> countyList = AreaUtil.getCountyList(e2);
				if (CollectionUtils.isEmpty(countyList)) {
					continue;
				}

				for (AreaDTO e3 : countyList) {
					Area county = new Area();
					county.setName(e3.getName());
					county.setLevel(3);
					county.setParentId(city.getId());
					county = areaService.insertOrUpdateSelective(county);

					/**
					 * 镇
					 */
					List<AreaDTO> townList = AreaUtil.getTownList(e3);
					if (CollectionUtils.isEmpty(townList)) {
						continue;
					}

					for (AreaDTO e4 : townList) {
						Area town = new Area();
						town.setName(e4.getName());
						town.setLevel(4);
						town.setParentId(county.getId());
						town = areaService.insertOrUpdateSelective(town);

						/**
						 * 村
						 */
						List<AreaDTO> villageList = AreaUtil.getVillageList(e4);
						if (CollectionUtils.isEmpty(villageList)) {
							continue;
						}

						for (AreaDTO e5 : villageList) {
							Area village = new Area();
							village.setName(e5.getName());
							village.setLevel(5);
							village.setParentId(town.getId());
							areaService.insertOrUpdateSelective(village);
						}
					}
				}
			}
		}

		return BaseResult.success();
	}
}
